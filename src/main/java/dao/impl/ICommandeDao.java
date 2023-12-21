package dao.impl;

import entite.Commande;

import java.util.List;

public interface ICommandeDao {
    Commande addCommande(Commande commande);

    List<Commande> clientCommande(String mc);

    Commande getCommande(int id);

    Commande updateCommande(Commande commande);

    void deleteCommande(int id);

    List<Commande> getAllCommande();
}
