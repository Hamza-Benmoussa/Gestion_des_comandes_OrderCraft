package dao.impl;

import entite.Client;

import java.sql.SQLException;
import java.util.List;

public interface IClientDao {
    void insertUser(Client client) throws SQLException;

    Client selectUser(int id);

    List<Client> selectAllUsers();

    boolean deleteUser(int id);

    boolean updateUser(Client client);
}
