package dao;

import dao.impl.IClientDao;
import entite.Client;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static DBconnection.DbConnector.connection;

public class ClientDaoImpl implements IClientDao {
    private static final String INSERT_CLIENT_SQL = "INSERT INTO client (clientName, clientAddress, clientEmail) VALUES (?, ?, ?)";
    private static final String SELECT_CLIENT_BY_ID = "SELECT clientId, clientName, clientAddress, clientEmail FROM clients WHERE clientId=?";
    private static final String SELECT_ALL_CLIENTS = "SELECT id, name, address, email FROM clients";
    private static final String DELETE_CLIENT_SQL = "DELETE FROM clients WHERE id=?";
    private static final String UPDATE_CLIENT_SQL = "UPDATE clients SET name=?, address=?, email=? WHERE id=?";

    @Override
    public void insertUser(Client client) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getAddress());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.executeUpdate();
    }

    @Override
    public Client selectUser(int id) {
        return null;
    }

    @Override
    public List<Client> selectAllUsers() {
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public boolean updateUser(Client client) {
        return false;
    }
}