package entite;

import java.io.Serializable;

public class Produit {
    private int id;
    private String name;
    private int prix;
    private int quantite;

    public Produit() {
    }

    public Produit(String name, int prix,int quantite) {
        this.name = name;
        this.prix = prix;
        this.quantite=quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
