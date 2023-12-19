package dao.impl;

import entite.Produit;

import java.util.List;

public interface IProduitDao {
    public Produit addProduit(Produit p);
    public List<Produit> produitMotCle(String mc);
    public Produit getProduit(int id);
    public Produit updateProduit(Produit p);
    public  void deleteProduit(int id);
}
