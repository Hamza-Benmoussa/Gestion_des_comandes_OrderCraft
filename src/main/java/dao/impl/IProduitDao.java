package dao.impl;

import entite.Produit;

import java.util.List;

public interface IProduitDao {
    Produit addProduit(Produit produit);

    List<Produit> getAllProduits();

    Produit getProduit(int id);

    List<Produit> getProduitsByIds(List<Integer> ids);  // Add this method

    Produit updateProduit(Produit produit);

    void deleteProduit(int id);
    List<Produit> produitMotCle(String mc);

}
