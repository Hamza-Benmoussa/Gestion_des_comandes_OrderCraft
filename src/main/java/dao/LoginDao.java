package DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
    public boolean validateUser(String email, String password) {
        try {
            Connection connection = DbConnector.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT userId FROM useradmin WHERE emailId=? AND passwordId=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (Exception e) {
            // Handle exceptions appropriately in a real application
            e.printStackTrace();
            return false;
        }
    }
}
