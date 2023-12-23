package model;

import entite.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientModel {
    private String motCle;
    private List<Client> clients = new ArrayList<>();

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
