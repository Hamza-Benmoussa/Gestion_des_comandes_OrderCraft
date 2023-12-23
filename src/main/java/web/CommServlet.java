package web;

import dao.ClientImplDao;
import dao.CommandeImplDao;
import dao.ProduitDaoImpl;
import dao.impl.IClientDao;
import dao.impl.ICommandeDao;
import dao.impl.IProduitDao;
import entite.Client;
import entite.Commande;
import entite.Produit;
import entite.Status;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CommandeModel;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
// CommServlet.java
@WebServlet(name = "CommServlet", urlPatterns = {"*.comm"})
public class CommServlet extends HttpServlet {
    private ICommandeDao commandeDao;
    private IProduitDao produitDao;
    private IClientDao clientDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // Initialize data access objects (DAOs)
        commandeDao = new CommandeImplDao();
        produitDao = new ProduitDaoImpl();
        clientDao = new ClientImplDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        if (path.equals("/index.comm")) {
            // Display the list of all commands
            List<Commande> commandes = commandeDao.getAllCommande();
            CommandeModel model = new CommandeModel();
            model.setCommandes(commandes);
            req.setAttribute("model", model);
            req.getRequestDispatcher("commande/commande.jsp").forward(req, resp);
        } else if (path.equals("/saisie.comm")) {
            // Display the form to add a new command
            List<Produit> produits = produitDao.getAllProduits();
            List<Client> clients = clientDao.getAllClient();
            req.setAttribute("produits", produits);
            req.setAttribute("clients", clients);
            req.getRequestDispatcher("commande/saisieComm.jsp").forward(req, resp);
        } else if (path.equals("/addCommande.comm") && req.getMethod().equals("POST")) {
            // Handle the case where the "id" parameter is null or empty
            String idParameter = req.getParameter("id");
            if (idParameter == null || idParameter.isEmpty()) {
                int defaultClientId = 1; // Set a default value
                String errorMessage = "Client ID is required.";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("commande/saisieComm.jsp").forward(req, resp);
                return; // Stop further processing as there is an error
            }

            int idClient = Integer.parseInt(idParameter);
            String[] produitsArray = req.getParameterValues("produit");

            // Get the real Produit objects based on the product IDs
            List<Produit> produits = Arrays.stream(produitsArray)
                    .map(Integer::parseInt)
                    .map(produitDao::getProduit)
                    .collect(Collectors.toList());

            // Modify the code to get the selected status from the request
            Status status = Status.fromString(req.getParameter("status"));
            Commande commande = new Commande(idClient, LocalDate.now(), produits, status);
            commande = commandeDao.addCommande(commande,req);
            req.setAttribute("commande", commande);
            resp.sendRedirect("commande/confirmationCmd.jsp");


        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Delegate the processing of the POST request to the doGet method

        doGet(req, resp);
    }
}
