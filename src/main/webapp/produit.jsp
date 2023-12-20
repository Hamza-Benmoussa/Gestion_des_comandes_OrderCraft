<%@ page import="web.ProduitModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Search Produit</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<%@ include file="headerGestionProduit.jsp"%>
<div class="container mt-4">
    <div class="card">
        <div class="card-header">
            <h5 class="card-title">Produits</h5>
        </div>
        <div class="card-body">
            <form action="chercher.do" method="get">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Recherche" aria-label="Recherche" aria-describedby="basic-addon2" name="motCle" value="${model.motCle}">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Rechercher</button>
                    </div>
                </div>
            </form>
            <table class="table">

                <tr>
                    <th>produitId</th>
                    <th>produitName</th>
                    <th>prix</th>
                    <th>quantite</th>
                </tr>

                <tbody>
                <c:forEach items="${model.produits}" var="p">

                        <tr>
                            <td>${p.id}</td>
                            <td>${p.name}</td>
                            <td>${p.prix}</td>
                            <td>${p.quantite}</td>
                            <!-- Delete Button -->
                            <td>
                                <form action="delete.do" method="post" style="display: inline;">
                                    <input type="hidden" name="id" value="${p.id}">
                                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                </form>
                            </td>

                            <!-- Edit Button -->
                            <td>
                                <a href="update.do?id=${p.id}" class="btn btn-primary btn-sm">Edit</a>
                            </td>
                        </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
