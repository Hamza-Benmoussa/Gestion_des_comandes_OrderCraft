package entite;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Commande {
    private int idCommande;
    private int idClient;
    private LocalDate dateCommande;
    private Status status;
    private List<Integer> produitsIds; // Change the type to a list of product IDs

    public Commande() {
    }

    // Constructor that accepts necessary parameters
    public Commande(int idClient, LocalDate dateCommande, List<Integer> produitsIds, Status status) {
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

    public List<Integer> getProduitsIds() {
        return produitsIds;
    }

    public void setProduitsIds(List<Integer> produitsIds) {
        this.produitsIds = produitsIds;
    }
}
