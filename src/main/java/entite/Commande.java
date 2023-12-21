package entite;

import java.time.LocalDate;
import java.util.List;

public class Commande {
    private int idCommande;
    private int idClient;
    private LocalDate dateCommande;
    private Status status;
    private List<Produit> produitsIds; // Change the type to a list of product IDs

    public Commande() {
    }

    // Constructor that accepts necessary parameters
    public Commande(int idCommande, int idClient, LocalDate dateCommande, List<Produit> produitsIds, Status status) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.dateCommande = dateCommande;
        this.produitsIds = produitsIds;
        this.status = status;
    }

    public Commande(int idClient, LocalDate dateCommande, List<Produit> produitsIds, Status status) {
        this.idClient = idClient;
        this.dateCommande = dateCommande;
        this.status = status;
        this.produitsIds = produitsIds;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Produit> getProduitsIds() {
        return produitsIds;
    }

    public void setProduitsIds(List<Produit> produitsIds) {
        this.produitsIds = produitsIds;
    }
}
