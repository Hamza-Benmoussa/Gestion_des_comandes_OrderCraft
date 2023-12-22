package dao;

import DBconnection.DbConnector;
import dao.impl.ICommandeDao;
import entite.Commande;
import entite.CommandeProduit;
import entite.Produit;
import entite.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeImplDao implements ICommandeDao {

    @Override
    public Commande addCommande(Commande commande) {
        Connection connection = DbConnector.getConnection();
        try {
            if (!clientExists(commande.getIdClient())) {
                throw new RuntimeException("Le client avec l'ID " + commande.getIdClient() + " n'existe pas.");
            }

            for (Integer produitId : commande.getProduitsIds()) {
                if (!produitExists(produitId)) {
                    throw new RuntimeException("Le produit avec l'ID " + produitId + " n'existe pas.");
                }
            }

            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO commande (clientId, commandeDate, commandeStatus) VALUES (?, ?, ?)")) {
                ps.setInt(1, commande.getIdClient());
                ps.setDate(2, java.sql.Date.valueOf(commande.getDateCommande()));
                ps.setString(3, commande.getStatus().toString());
                ps.executeUpdate();
            }


            try (PreparedStatement ps2 = connection.prepareStatement("SELECT LAST_INSERT_ID() AS last_id");
                 ResultSet rs = ps2.executeQuery()) {
                if (rs.next()) {
                    int generatedId = rs.getInt("last_id");
                    commande.setIdCommande(generatedId);
                    updateProductQuantities(commande);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commande;
    }

    private boolean clientExists(int clientId) throws SQLException {
        Connection connection = DbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE clientId = ?")) {
            ps.setInt(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return false;
    }

    private boolean produitExists(int produitId) throws SQLException {
        Connection connection = DbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit WHERE produitId = ?")) {
            ps.setInt(1, produitId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return false;
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
                    commands.add(command);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return command;
    }

    @Override
    public Commande updateCommande(Commande commande) {
        Connection connection = DbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("UPDATE commande SET commandeStatus=? WHERE commandeId=?")) {
            ps.setString(1, commande.getStatus().toString());
            ps.setInt(2, commande.getIdCommande());
            ps.executeUpdate();

            updateProductQuantities(commande);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commande;
    }


    @Override
    public void deleteCommande(int id) {
        Connection connection = DbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM commande WHERE commandeId = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
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
                command.setStatus(Status.valueOf(rs.getString("commandeStaus")));
                commands.add(command);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return commands;
    }

    private void updateProductQuantities(Commande commande) throws SQLException {
        Connection connection = DbConnector.getConnection();

        try {
            List<CommandeProduit> commandProducts = getCommandeProduits(commande.getIdCommande());

            for (CommandeProduit commandProduit : commandProducts) {
                Produit produit = getProduit(commandProduit.getIdProduit());

                if (commande.getStatus() == Status.TERMINER) {
                    try (PreparedStatement ps = connection.prepareStatement("UPDATE produit SET quantite = quantite - ? WHERE produitId = ?")) {
                        ps.setInt(1, commandProduit.getQuantite());
                        ps.setInt(2, commandProduit.getIdProduit());
                        ps.executeUpdate();
                    }
                } else if (commande.getStatus() == Status.ANULLER) {
                    try (PreparedStatement ps = connection.prepareStatement("UPDATE produit SET quantite = quantite + ? WHERE produitId = ?")) {
                        ps.setInt(1, commandProduit.getQuantite());
                        ps.setInt(2, commandProduit.getIdProduit());
                        ps.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<CommandeProduit> getCommandeProduits(int idCommande) throws SQLException {
        Connection connection = DbConnector.getConnection();
        List<CommandeProduit> commandProduits = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM commande_produit WHERE id_commande = ?")) {
            ps.setInt(1, idCommande);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CommandeProduit commandeProduit = new CommandeProduit();
                    commandeProduit.setId(rs.getInt("id"));
                    commandeProduit.setIdProduit(rs.getInt("id_produit"));
                    commandeProduit.setIdCommande(rs.getInt("id_commande"));
                    commandeProduit.setQuantite(rs.getInt("quantite"));
                    commandeProduit.setPrixTotal(rs.getDouble("prix_total"));
                    commandProduits.add(commandeProduit);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return commandProduits;
    }

    private Produit getProduit(int idProduit) throws SQLException {
        Connection connection = DbConnector.getConnection();
        Produit produit = null;

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit WHERE produitId = ?")) {
            ps.setInt(1, idProduit);
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
            throw new RuntimeException(e);
        }

        return produit;
    }
}
