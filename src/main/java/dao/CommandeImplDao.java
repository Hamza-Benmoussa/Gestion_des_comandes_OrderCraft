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
            // Add the command to the database
            PreparedStatement ps = connection.prepareStatement("INSERT INTO commande (clientId, commandeDate, commandeStatus) VALUES (?, ?, ?)");
            ps.setInt(1, commande.getIdClient());
            ps.setDate(2, java.sql.Date.valueOf(commande.getDateCommande()));
            ps.setString(3, commande.getStatus().toString());
            ps.executeUpdate();

            // Retrieve the generated ID
            PreparedStatement ps2 = connection.prepareStatement("SELECT LAST_INSERT_ID() AS last_id");
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                int generatedId = rs.getInt("last_id");
                commande.setIdCommande(generatedId);

                // Update product quantities based on the command status
                updateProductQuantities(commande);
            }

            ps.close();
            ps2.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commande;
    }

    @Override
    public List<Commande> clientCommande(String mc) {
        List<Commande> commands = new ArrayList<>();
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM commande WHERE clientId = ?");
            ps.setString(1, mc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Commande command = new Commande();
                command.setIdCommande(rs.getInt("commandeId"));
                command.setIdClient(rs.getInt("clientId"));
                command.setDateCommande(rs.getDate("commandeDate").toLocalDate());
                command.setStatus(Status.valueOf(rs.getString("commandeStatus")));
                commands.add(command);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commands;
    }

    @Override
    public Commande getCommande(int id) {
        Commande command = null;
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM commande WHERE commandeId = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                command = new Commande();
                command.setIdCommande(rs.getInt("commandeId"));
                command.setIdClient(rs.getInt("clientId"));
                command.setDateCommande(rs.getDate("commandeDate").toLocalDate());
                command.setStatus(Status.valueOf(rs.getString("commandeStatus")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return command;
    }

    @Override
    public Commande updateCommande(Commande commande) {
        Connection connection = DbConnector.getConnection();
        try {
            // Update the command status in the database
            PreparedStatement ps = connection.prepareStatement("UPDATE commande SET commandeStaus=? WHERE commandeId=?");
            ps.setString(1, commande.getStatus().toString());
            ps.setInt(2, commande.getIdCommande());
            ps.executeUpdate();

            // Update product quantities based on the updated command status
            updateProductQuantities(commande);

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commande;
    }

    @Override
    public void deleteCommande(int id) {
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM commande WHERE commandeId = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Commande> getAllCommande() {
        List<Commande> commands = new ArrayList<>();
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM commande");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Commande command = new Commande();
                command.setIdCommande(rs.getInt("commandeId"));
                command.setIdClient(rs.getInt("clientId"));
                command.setDateCommande(rs.getDate("commandeDate").toLocalDate());
                command.setStatus(Status.valueOf(rs.getString("commandeStaus")));
                commands.add(command);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commands;
    }

    // Helper method to update product quantities based on the command status
    private void updateProductQuantities(Commande commande) throws SQLException {
        Connection connection = DbConnector.getConnection();
        PreparedStatement ps = null;

        try {
            // Retrieve products associated with the command
            List<CommandeProduit> commandProducts = getCommandeProduits(commande.getIdCommande());

            // Update product quantities based on the command status
            for (CommandeProduit commandProduit : commandProducts) {
                Produit produit = getProduit(commandProduit.getIdProduit());

                if (commande.getStatus() == Status.TERMINER) {
                    // Reduce the product quantity if the command is completed
                    ps = connection.prepareStatement("UPDATE produit SET quantite = quantite - ? WHERE produitId = ?");
                    ps.setInt(1, commandProduit.getQuantite());
                    ps.setInt(2, commandProduit.getIdProduit());
                    ps.executeUpdate();
                } else if (commande.getStatus() == Status.ANULLER) {
                    // Reset product quantities if the command is canceled
                    ps = connection.prepareStatement("UPDATE produit SET quantite = quantite + ? WHERE produitId = ?");
                    ps.setInt(1, commandProduit.getQuantite());
                    ps.setInt(2, commandProduit.getIdProduit());
                    ps.executeUpdate();
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    // Helper method to retrieve products associated with a command
    private List<CommandeProduit> getCommandeProduits(int idCommande) throws SQLException {
        Connection connection = DbConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CommandeProduit> commandProduits = new ArrayList<>();

        try {
            ps = connection.prepareStatement("SELECT * FROM commande_produit WHERE id_commande = ?");
            ps.setInt(1, idCommande);
            rs = ps.executeQuery();

            while (rs.next()) {
                CommandeProduit commandeProduit = new CommandeProduit();
                commandeProduit.setId(rs.getInt("id"));
                commandeProduit.setIdProduit(rs.getInt("id_produit"));
                commandeProduit.setIdCommande(rs.getInt("id_commande"));
                commandeProduit.setQuantite(rs.getInt("quantite"));
                commandeProduit.setPrixTotal(rs.getDouble("prix_total"));
                commandProduits.add(commandeProduit);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return commandProduits;
    }

    // Helper method to retrieve product details
    private Produit getProduit(int idProduit) throws SQLException {
        Connection connection = DbConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Produit produit = null;

        try {
            ps = connection.prepareStatement("SELECT * FROM produit WHERE produitId = ?");
            ps.setInt(1, idProduit);
            rs = ps.executeQuery();

            if (rs.next()) {
                produit = new Produit();
                produit.setId(rs.getInt("produitId"));
                produit.setName(rs.getString("produitName"));
                produit.setPrix(rs.getInt("prix"));
                produit.setQuantite(rs.getInt("quantite"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return produit;
    }
}
