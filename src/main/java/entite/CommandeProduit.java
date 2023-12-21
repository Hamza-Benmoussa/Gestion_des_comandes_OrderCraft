package entite;

public class CommandeProduit {
    private int id;
    private int idProduit;
    private int idCommande;
    private int quantite;
    private double prixTotal;

    public CommandeProduit() {
    }

    public CommandeProduit(int idProduit, int idCommande, int quantite, double prixTotal) {
        this.idProduit = idProduit;
        this.idCommande = idCommande;
        this.quantite = quantite;
        this.prixTotal = prixTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
}
