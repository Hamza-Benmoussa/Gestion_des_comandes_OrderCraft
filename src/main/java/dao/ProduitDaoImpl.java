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
import java.util.Scanner;

public class ProduitDaoImpl implements IProduitDao {
    @Override
    public Produit addProduit(Produit p) {
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO produit (produitName , prix) VALUES (?,?)");
            ps.setString(1,p.getName());
            ps.setInt(2,p.getPrix());
            ps.executeUpdate();
//            PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(produitId) AS MAX_produitId FROM produit");
//            ResultSet rs = ps2.executeQuery();
//            if (rs.next()){
//                p.setId(rs.getInt("MAX_produitId"));
//            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;

    }

    @Override
    public List<Produit> produitMotCle(String mc) {
        List<Produit> produits = new ArrayList<>();
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit WHERE produitName ?");
            ps.setString(1, mc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getInt("produitId"));
                p.setName(rs.getString("produitName"));
                p.setPrix(rs.getInt("prix"));
                produits.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }
    @Override
    public Produit getProduit(int id) {
        return null;
    }

    @Override
    public Produit updateProduit(Produit p) {
        return null;
    }

    @Override
    public void deleteProduit(int id) {

    }
}
