package web;

import dao.ClientImplDao;
import dao.impl.IClientDao;
import entite.Client;
import entite.Produit;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "csc", urlPatterns = {"*.client"})
public class ControleurServletClient extends HttpServlet {
    private IClientDao clientDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        clientDao = new ClientImplDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/index.client")) {
            req.getRequestDispatcher("client.jsp").forward(req, resp);
        } else if (path.equals("/chercher.client")) {
            // Code to handle search after form submission
            String motCle = req.getParameter("motCle");
            ClientModel model = new ClientModel();
            model.setMotCle(motCle);
            // Fetch clients from the database based on the search criteria
            List<Client> clients = clientDao.clientMotCle("%" + motCle + "%");
            model.setClients(clients);
            req.setAttribute("model", model);
            req.getRequestDispatcher("client.jsp").forward(req, resp);
        } else if (path.equals("/saisie.client")) {
            req.getRequestDispatcher("saisieClient.jsp").forward(req, resp);
        } else if (path.equals("/addClient.client") && (req.getMethod().equals("POST"))) {
            // Code to handle adding a new client
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String address=req.getParameter("adress");
            Client client = clientDao.addClient(new Client(name, email,address));
            req.setAttribute("client", client);
            req.getRequestDispatcher("confirmationC.jsp").forward(req, resp);
        } else if (path.equals("/delete.client")) {
            int id = Integer.parseInt(req.getParameter("id"));
            clientDao.deleteClient(id);
            resp.sendRedirect("chercher.client?motCle=");
        } else if (path.equals("/update.client")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Client client = clientDao.getClient(id);
            req.setAttribute("client", client);
            req.getRequestDispatcher("editClient.jsp").forward(req, resp);
        } else if (path.equals("/updateClient.client") && (req.getMethod().equals("POST"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String address=req.getParameter("address");
            Client client = new Client(name, email, address);
            client.setId(id);
            clientDao.updateClient(client);
            req.setAttribute("client", client);
            req.getRequestDispatcher("confirmationUpdateC.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
