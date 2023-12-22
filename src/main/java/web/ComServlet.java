//package web;
//
//import dao.ClientImplDao;
//import dao.CommandeImplDao;
//import dao.ProduitDaoImpl;
//import dao.impl.IClientDao;
//import dao.impl.ICommandeDao;
//import dao.impl.IProduitDao;
//import entite.Client;
//import entite.Commande;
//import entite.Produit;
//import entite.Status;
//import jakarta.servlet.ServletConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@WebServlet(name = "cs", urlPatterns = {"*.com"})
//public class ComServlet extends HttpServlet {
//    private ICommandeDao commandeDao;
//    private IProduitDao produitDao;
//    private IClientDao clientDao;
//
//    @Override
//    public void init(ServletConfig config) {
//        commandeDao = new CommandeImplDao();
//        produitDao = new ProduitDaoImpl();
//        clientDao = new ClientImplDao();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String path = req.getServletPath();
//        System.out.println("hello");
//        if (path.equals("/index.com")) {
//            req.getRequestDispatcher("commande.jsp").forward(req, resp);
//        } else if (path.equals("/chercher.com")) {
//            // Code to handle search after form submission
//            String motCle = req.getParameter("motCle");
//            CommandeModel model = new CommandeModel();
//            model.setMotCle(motCle);
//            // Fetch commands from the database based on the search criteria
//            List<Commande> commandes = commandeDao.clientCommande("%" + motCle + "%");
//            model.setCommandes(commandes);
//            req.setAttribute("model", model);
//            req.getRequestDispatcher("commande.jsp").forward(req, resp);
//        } else if (path.equals("/saisie.com")) {
//            // Fetch all products for populating the dropdown in the command form
//            List<Produit> produits = produitDao.getAllProduits();
//            List<Client> clients = clientDao.getAllClient(); // Fetch all clients
//            req.setAttribute("produits", produits);
//            req.setAttribute("clients", clients); // Set the attribute name to "clients"
//            System.out.println(clients);
//            req.getRequestDispatcher("saisieComm.jsp").forward(req, resp);
//        } else if (path.equals("/addCommande.com") && (req.getMethod().equals("POST"))) {
//            int idClient = Integer.parseInt(req.getParameter("idClient")); // Utiliser "idClient" au lieu de "id"
//            String[] produitsArray = req.getParameterValues("produits");
//
//            // Fetch all products from the database
//            List<Produit> produits = produitDao.getAllProduits();
//            List<Integer> produitsIds = Arrays.stream(produitsArray)
//                    .map(Integer::parseInt)
//                    .collect(Collectors.toList());
//            Status status = Status.EN_COURS;
//
//            // Create the command and associated products
//            Commande commande = new Commande(idClient, LocalDate.now(), produitsIds, status);
//            commande = commandeDao.addCommande(commande);
//            req.setAttribute("commande", commande);
//            req.getRequestDispatcher("confirmationCmd.jsp").forward(req, resp);
//        } else if (path.equals("/delete.com")) {
//            int id = Integer.parseInt(req.getParameter("id"));
//            commandeDao.deleteCommande(id);
//            resp.sendRedirect("chercher.co?motCle=");
//        } else if (path.equals("/update.com")) {
//            int id = Integer.parseInt(req.getParameter("id"));
//            Commande commande = commandeDao.getCommande(id);
//            List<Produit> produits = produitDao.getAllProduits();
//            List<Client> clients = clientDao.getAllClient(); // Fetch all clients
//            req.setAttribute("commande", commande);
//            req.setAttribute("produits", produits);
//            req.setAttribute("clients", clients); // Set the attribute name to "clients"
//            req.getRequestDispatcher("editCommande.jsp").forward(req, resp);
//        } else if (path.equals("/updateCommande.com") && (req.getMethod().equals("POST"))) {
//            // Code to handle updating a command
//            int id = Integer.parseInt(req.getParameter("id"));
//            int idClient = Integer.parseInt(req.getParameter("idClient"));
//            String[] produitsArray = req.getParameterValues("produits");
//            List<Integer> produitsIds = Arrays.stream(produitsArray)
//                    .map(Integer::parseInt)
//                    .collect(Collectors.toList());
//            Status status = Status.valueOf(req.getParameter("status"));
//
//            // Create the command and associated products
//            Commande commande = new Commande(idClient, LocalDate.now(), produitsIds, status);
//            commande.setIdCommande(id); // Set the existing command ID
//            commandeDao.updateCommande(commande);
//            req.setAttribute("commande", commande);
//            req.getRequestDispatcher("confirmationUpdateCmd.jsp").forward(req, resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req, resp);
//    }
//}
