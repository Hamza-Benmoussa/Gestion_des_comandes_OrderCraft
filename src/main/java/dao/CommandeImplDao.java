package dao;

import DBconnection.DbConnector;
import dao.impl.ICommandeDao;
import entite.Commande;
import entite.Produit;
import entite.Status;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DBconnection.DbConnector.connection;

public class CommandeImplDao implements ICommandeDao {

    @Override
    public Commande addCommande(Commande commande, HttpServletRequest request) {
        Connection connection = DbConnector.getConnection();
        try {
            if (!clientExists(commande.getIdClient())) {
                throw new RuntimeException("Le client avec l'ID " + commande.getIdClient() + " n'existe pas.");
            }

            for (Produit produitId : commande.getProduitsIds()) {
                if (!produitExists(produitId)) {
                    throw new RuntimeException("Le produit avec l'ID " + produitId.getId() + " n'existe pas.");
                }
            }

            // Begin a transaction
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO commande (clientId, commandeDate, commandeStatus) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, commande.getIdClient());
                ps.setDate(2, Date.valueOf(commande.getDateCommande()));
                ps.setString(3, commande.getStatus().toString());
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        commande.setIdCommande(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("La création de la commande a échoué, aucun ID obtenu.");
                    }
                }
            }

            // Insert into commande_produit table
            try (PreparedStatement ps2 = connection.prepareStatement("INSERT INTO commande_produit (id_commande, id_produit, quantite, prix_total) VALUES (?, ?, ?, ?)")) {
                for (Produit produitId : commande.getProduitsIds()) {
                    Produit produit = getProduit(produitId);
                    ps2.setInt(1, commande.getIdCommande());
                    ps2.setInt(2, produit.getId());
                    ps2.setInt(3, 1);  // Assuming a quantity of 1, modify as needed
                    ps2.setDouble(4, produit.getPrix());
                    ps2.addBatch();

                }
                // Execute batch insert
                ps2.executeBatch();
            }


        } catch (SQLException e) {
            // Rollback the transaction in case of an exception
            e.printStackTrace();
        }

        return commande;
    }

    public void updateProductQuantities(List<Produit> produits, HttpServletRequest request) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE produit SET quantite = quantite - ? WHERE produitId = ?")) {
            for (Produit produit : produits) {
                // Utilisation de la nouvelle méthode pour obtenir la quantité
                int quantity = produit.getQuantite();

                ps.setInt(1, quantity);
                ps.setInt(2, produit.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public int getQuantityFromUserInput(Produit produit, HttpServletRequest request) {
        // Obtenez la quantité à partir des paramètres de la requête
        String paramName = "quantity_" + produit.getId(); // Utilisez un nom de paramètre unique pour chaque produit
        String quantityParam = request.getParameter(paramName);

        // Vérifiez si le paramètre est présent et non vide
        if (quantityParam != null && !quantityParam.isEmpty()) {
            try {
                // Essayez de convertir la chaîne en un entier
                int quantity = Integer.parseInt(quantityParam);

                // Assurez-vous que la quantité est positive
                if (quantity > 0) {
                    return quantity;
                }
            } catch (NumberFormatException e) {
                // Gérez l'exception si la conversion échoue
                e.printStackTrace(); // Vous pouvez loguer l'erreur ou prendre d'autres mesures nécessaires
            }
        }

        // Retournez une valeur par défaut si la quantité n'a pas pu être obtenue
        return 1; // Valeur par défaut
    }


    @Override
    public List<Commande> clientCommande(String mc) {
        List<Commande> commands = new ArrayList<>();
        Connection connection = DbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM commande WHERE clientId = ?")) {
            ps.setString(1, mc);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Commande command = new Commande();
                    command.setIdCommande(rs.getInt("commandeId"));
                    command.setIdClient(rs.getInt("clientId"));
                    command.setDateCommande(rs.getDate("commandeDate").toLocalDate());
                    command.setStatus(Status.valueOf(rs.getString("commandeStatus")));

                    // Retrieve and set the products for each command
                    command.setProduitsIds(getProduitsForCommande(command.getIdCommande()));

                    commands.add(command);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des commandes client.", e);
        }
        return commands;
    }

    @Override
    public Commande getCommande(int id) {
        Commande command = null;
        Connection connection = DbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM commande WHERE commandeId = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    command = new Commande();
                    command.setIdCommande(rs.getInt("commandeId"));
                    command.setIdClient(rs.getInt("clientId"));
                    command.setDateCommande(rs.getDate("commandeDate").toLocalDate());
                    command.setStatus(Status.valueOf(rs.getString("commandeStatus")));

                    // Retrieve and set the products for the command
                    command.setProduitsIds(getProduitsForCommande(command.getIdCommande()));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de la commande par ID.", e);
        }
        return command;
    }

    @Override
    public void deleteCommande(int id) {
        Connection connection = DbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM commande WHERE commandeId = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la commande.", e);
        }
    }

    @Override
    public List<Commande> getAllCommande() {
        List<Commande> commands = new ArrayList<>();
        Connection connection = DbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM commande");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Commande command = new Commande();
                command.setIdCommande(rs.getInt("commandeId"));
                command.setIdClient(rs.getInt("clientId"));
                command.setDateCommande(rs.getDate("commandeDate").toLocalDate());

                // Print or log the actual value retrieved from the database
                String statusValue = rs.getString("commandeStatus");
                System.out.println("Status value from database: " + statusValue);

                command.setStatus(Status.fromString(statusValue));

                // Retrieve and set the products for each command
                command.setProduitsIds(getProduitsForCommande(command.getIdCommande()));

                commands.add(command);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de toutes les commandes.", e);
        }
        return commands;
    }


    private List<Produit> getProduitsForCommande(int commandeId) throws SQLException {
        List<Produit> produits = new ArrayList<>();
        Connection connection = DbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT produit.* FROM produit " +
                "INNER JOIN commande_produit ON produit.produitId = commande_produit.id_produit " +
                "WHERE commande_produit.id_commande = ?")) {
            ps.setInt(1, commandeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Produit produit = new Produit();
                    produit.setId(rs.getInt("produitId"));
                    produit.setName(rs.getString("produitName"));
                    produit.setPrix(rs.getInt("prix"));
                    produit.setQuantite(rs.getInt("quantite"));
                    produits.add(produit);
                }
            }
        }
        return produits;
    }

    private boolean clientExists(int clientId) throws SQLException {
        Connection connection = DbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE clientId = ?")) {
            ps.setInt(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la vérification de l'existence du client.", e);
        }
    }

    private boolean produitExists(Produit produit) throws SQLException {
        Connection connection = DbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit WHERE produitId = ?")) {
            ps.setInt(1, produit.getId());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la vérification de l'existence du produit.", e);
        }
    }

    private Produit getProduit(Produit idProduit) throws SQLException {
        Connection connection = DbConnector.getConnection();
        Produit produit = null;

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit WHERE produitId = ?")) {
            ps.setInt(1, idProduit.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    produit = new Produit();
                    produit.setId(rs.getInt("produitId"));
                    produit.setName(rs.getString("produitName"));
                    produit.setPrix(rs.getInt("prix"));
                    produit.setQuantite(rs.getInt("quantite"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du produit.", e);
        }

        return produit;
    }
}