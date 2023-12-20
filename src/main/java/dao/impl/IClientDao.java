package dao.impl;

import entite.Client;
import entite.Produit;

import java.sql.SQLException;
import java.util.List;

public interface IClientDao {

    public Client addClient(Client client);
    public List<Client> clientMotCle(String MC);
    public Client getClient(int id);
    public Client updateClient(Client client);
    public  void deleteClient(int id);
    public List<Client> getAllClient();
}
