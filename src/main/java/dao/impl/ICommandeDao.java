package dao.impl;

import entite.Commande;
import entite.Produit;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

public interface ICommandeDao {
    public Commande addCommande(Commande commande, HttpServletRequest request);
    List<Commande> clientCommande(String mc);

    Commande getCommande(int id);
    List<Commande> getAllCommande();
    void updateProductQuantities(List<Produit> produits, HttpServletRequest request) throws SQLException;
    int getQuantityFromUserInput(Produit produit, HttpServletRequest request);

}
