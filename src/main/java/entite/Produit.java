package entite;

import java.io.Serializable;

public class Produit implements Serializable {
    private int id;
    private String name;
    private int quantityInStock;
    private int prix;

    public Produit() {
    }

    public Produit(int id, String name, int quantityInStock, int prix) {
        this.id = id;
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.prix = prix;
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

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
