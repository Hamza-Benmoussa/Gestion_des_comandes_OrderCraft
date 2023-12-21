package web;

import dao.CommandeImplDao;
import dao.ProduitDaoImpl;
import dao.impl.ICommandeDao;
import dao.impl.IProduitDao;
import entite.Commande;
import entite.Produit;
import entite.Status;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "csc", urlPatterns = {"*.commande"})
public class ControleurServletCommande extends HttpServlet {
    private ICommandeDao commandeDao;
    private IProduitDao produitDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        commandeDao = new CommandeImplDao();
        produitDao = new ProduitDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/index.commande")) {
            req.getRequestDispatcher("commande.jsp").forward(req, resp);
        } else if (path.equals("/chercher.commande")) {
            // Code to handle search after form submission
            String motCle = req.getParameter("motCle");
            CommandeModel model = new CommandeModel();
            model.setMotCle(motCle);
            // Fetch commands from the database based on the search criteria
            List<Commande> commandes = commandeDao.clientCommande("%" + motCle + "%");
            model.setCommandes(commandes);
            req.setAttribute("model", model);
            req.getRequestDispatcher("commande.jsp").forward(req, resp);
        } else if (path.equals("/saisie.commande")) {
            // Fetch all products for populating the dropdown in the command form
            List<Produit> produits = produitDao.getAllProduits();
            req.setAttribute("produits", produits);
            req.getRequestDispatcher("saisieCommande.jsp").forward(req, resp);
        } else if (path.equals("/addCommande.commande") && (req.getMethod().equals("POST"))) {
            // Code to handle adding a new command
            int idClient = Integer.parseInt(req.getParameter("idClient"));
            String[] produitsArray = req.getParameterValues("produits");
            List<Produit> produitsIds = Arrays.asList(produitsArray != null ? produitsArray : new String[0]);
            Status status = Status.EN_COURS;

            // Create the command and associated products
            Commande commande = new Commande(idClient, LocalDate.now(), produitsIds, status);
            commande = commandeDao.addCommande(commande);
            req.setAttribute("commande", commande);
            req.getRequestDispatcher("confirmationCmd.jsp").forward(req, resp);
        } else if (path.equals("/delete.commande")) {
            int id = Integer.parseInt(req.getParameter("id"));
            commandeDao.deleteCommande(id);
            resp.sendRedirect("chercher.commande?motCle=");
        } else if (path.equals("/update.commande")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Commande commande = commandeDao.getCommande(id);
            List<Produit> produits = produitDao.getAllProduits();
            req.setAttribute("commande", commande);
            req.setAttribute("produits", produits);
            req.getRequestDispatcher("editCommande.jsp").forward(req, resp);
        } else if (path.equals("/updateCommande.commande") && (req.getMethod().equals("POST"))) {
            // Code to handle updating a command
            int id = Integer.parseInt(req.getParameter("id"));
            int idClient = Integer.parseInt(req.getParameter("idClient"));
            String[] produitsIds = req.getParameterValues("produits");
            Status status = Status.valueOf(req.getParameter("status"));

            // Create the command and associated products
            Commande commande = new Commande(idClient, LocalDate.now(), produitsIds, status);
            commandeDao.updateCommande(commande);
            req.setAttribute("commande", commande);
            req.getRequestDispatcher("confirmationUpdateCmd.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
