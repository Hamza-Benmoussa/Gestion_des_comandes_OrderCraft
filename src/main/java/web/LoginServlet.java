package web;

import DBconnection.LoginDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            // Using UserDao for database operations
            LoginDao userDao = new LoginDao();
            boolean isValidUser = userDao.validateUser(email, password);

            if (isValidUser) {
                session.setAttribute("email", email);
                resp.sendRedirect("index.jsp");
            } else {
                // Informations de connexion incorrectes, définir un attribut d'erreur dans la session
                // Inside your servlet where login fails
                req.setAttribute("errorMessage", "Email or password is incorrect");
                RequestDispatcher rd = req.getRequestDispatcher("/auth.jsp");
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            // Gérer les exceptions de manière appropriée dans une application réelle
            e.printStackTrace();
            resp.sendRedirect("auth.jsp");
        }
    }
}
