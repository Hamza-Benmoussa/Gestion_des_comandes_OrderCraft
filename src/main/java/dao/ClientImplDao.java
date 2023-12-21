package dao;

import DBconnection.DbConnector;
import dao.impl.IClientDao;
import entite.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientImplDao implements IClientDao {

    @Override
    public Client addClient(Client client) {
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO client (clientName, clientEmail, clientAddress) VALUES (?, ?, ?)");
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getAddress());
            ps.executeUpdate();

            PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(clientId) AS MAX_clientId FROM client");
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                client.setId(rs.getInt("MAX_clientId"));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    @Override
    public List<Client> clientMotCle(String mc) {
        List<Client> clients = new ArrayList<>();
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE clientName LIKE ?");
            ps.setString(1, mc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("clientId"));
                client.setName(rs.getString("clientName"));
                client.setEmail(rs.getString("clientEmail"));
                client.setAddress(rs.getString("clientAddress"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client getClient(int id) {
        Client client = null;
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE clientId = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("clientId"));
                client.setName(rs.getString("clientName"));
                client.setEmail(rs.getString("clientEmail"));
                client.setAddress(rs.getString("clientAddress"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client updateClient(Client client) {
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE client SET clientName=?, clientEmail=? , clientAddress=? WHERE clientId=?");
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3,client.getAddress());
            ps.setInt(4, client.getId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteClient(int id) {
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM client WHERE clientId = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getAllClient() {
        List<Client> clients = new ArrayList<>();
        Connection connection = DbConnector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM client");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("clientId"));
                client.setName(rs.getString("clientName"));
                client.setEmail(rs.getString("clientEmail"));
                client.setAddress(rs.getString("clientAddress"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
