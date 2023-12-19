package web;

import dao.ProduitDaoImpl;
import dao.impl.IProduitDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/ControleurServletProduit")
public class ControleurServletProduit extends HttpServlet {
    private IProduitDao metier;

    @Override
    public void init(ServletConfig config) throws ServletException {
        metier = new ProduitDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("produit.jsp").forward(req,resp);
    }
}
