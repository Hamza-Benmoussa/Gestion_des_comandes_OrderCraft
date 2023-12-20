package dao;

import DBconnection.DbConnector;
import dao.impl.IProduitDao;
import entite.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImpl implements IProduitDao {

    @Override
    public Produit addProduit(Produit p) {
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO produit (produitName, prix,quantite) VALUES (?, ? ,?)");
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPrix());
            ps.setInt(3,p.getQuantite());
            ps.executeUpdate();

            PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(produitId) AS MAX_produitId FROM produit");
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt("MAX_produitId"));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override
    public List<Produit> getAllProduits() {
        List<Produit> produits = new ArrayList<>();
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getInt("produitId"));
                p.setName(rs.getString("produitName"));
                p.setPrix(rs.getInt("prix"));
                p.setQuantite(rs.getInt("quantite"));
                produits.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public List<Produit> produitMotCle(String mc) {
        List<Produit> produits = new ArrayList<>();
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit WHERE produitName LIKE ?");
            ps.setString(1, mc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getInt("produitId"));
                p.setName(rs.getString("produitName"));
                p.setPrix(rs.getInt("prix"));
                p.setQuantite(rs.getInt("quantite"));
                produits.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public Produit getProduit(int id) {
        Produit produit = null;
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit WHERE produitId = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                produit = new Produit();
                produit.setId(rs.getInt("produitId"));
                produit.setName(rs.getString("produitName"));
                produit.setPrix(rs.getInt("prix"));
                produit.setQuantite((rs.getInt("quantite")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produit;
    }

    @Override
    public Produit updateProduit(Produit p) {
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE produit SET produitName=?, prix=? , quantite =? WHERE produitId=?");
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPrix());
            ps.setInt(3,p.getQuantite());
            ps.setInt(4, p.getId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteProduit(int id) {
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM produit WHERE produitId = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}