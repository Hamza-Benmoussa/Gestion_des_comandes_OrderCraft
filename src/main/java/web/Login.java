package web;

import DBconnection.DbConnector;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            Connection connection = DbConnector.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT userId FROM useradmin WHERE emailId=? AND passwordId=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                session.setAttribute("email", email);
                resp.sendRedirect("index.jsp");
            } else {
                // Informations de connexion incorrectes, définir un attribut d'erreur dans la session
                // Inside your servlet where login fails
                req.setAttribute("errorMessage", "Email or password is incorrect");
                RequestDispatcher rd = req.getRequestDispatcher("/auth.jsp");
                rd.forward(req, resp);
                resp.sendRedirect("auth.jsp");
            }


        } catch (Exception e) {
            // Gérer les exceptions de manière appropriée dans une application réelle
            e.printStackTrace();
            resp.sendRedirect("auth.jsp");
        }
    }
}
