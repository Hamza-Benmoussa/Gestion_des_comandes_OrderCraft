package web;

import dao.ProduitDaoImpl;
import dao.impl.IProduitDao;
import entite.Produit;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "csp", urlPatterns={"*.do"})
public class ControleurServletProduit extends HttpServlet {
    private IProduitDao metier;

    @Override
    public void init(ServletConfig config) throws ServletException {
        metier = new ProduitDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/index.do")) {
            req.getRequestDispatcher("produit.jsp").forward(req, resp);
        } else if (path.equals("/chercher.do")) {
            // Code to handle search after form submission
            String motCle = req.getParameter("motCle");
            ProduitModel model = new ProduitModel();
            model.setMotCle(motCle);
            // Fetch products from the database based on the search criteria
            List<Produit> produits = metier.produitMotCle("%" + motCle + "%");
            model.setProduits(produits);
            req.setAttribute("model", model);
            req.getRequestDispatcher("produit.jsp").forward(req, resp);
        } else if (path.equals("/saisie.do")) {
            req.getRequestDispatcher("saisieProduit.jsp").forward(req, resp);
        } else if (path.equals("/addProduit.do") && (req.getMethod().equals("POST"))) {
            // Code to handle adding a new product
            String name = req.getParameter("name");
            int prix = Integer.parseInt(req.getParameter("prix"));
            int quantite = Integer.parseInt(req.getParameter("quantite"));
            Produit p = metier.addProduit(new Produit(name, prix,quantite));

            req.setAttribute("produit", p);
            req.getRequestDispatcher("confirmation.jsp").forward(req, resp);
        } else if (path.equals("/delete.do")) {
            int id = Integer.parseInt(req.getParameter("id"));
            metier.deleteProduit(id);
//            req.getRequestDispatcher("produit.jsp").forward(req,resp);
            resp.sendRedirect("chercher.do?motCle=");
        } else if (path.equals("/update.do")) {
            
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
