package model;

import entite.Produit;

import java.util.ArrayList;
import java.util.List;

public class ProduitModel {
    private String motCle;
    private List<Produit> produits = new ArrayList<Produit>();

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
