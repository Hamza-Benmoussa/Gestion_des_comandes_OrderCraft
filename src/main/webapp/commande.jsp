<%@ page import="web.CommandeModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Search Commande</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<%@ include file="headerGestionCommande.jsp"%>
<div class="container mt-4">
    <div class="card">
        <div class="card-header">
            <h5 class="card-title">Commandes</h5>
        </div>
        <div class="card-body">
            <form action="chercher.com" method="get">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Recherche" aria-label="Recherche" aria-describedby="basic-addon2" name="motCle" value="${model.motCle}">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Rechercher</button>
                    </div>
                </div>
            </form>
            <table class="table">
                <tr>
                    <th>ID Commande</th>
                    <th>ID Client</th>
                    <th>Date Commande</th>
                    <th>Statut</th>
                </tr>
                <tbody>
                <c:forEach items="${model.commandes}" var="commande">
                    <tr>
                        <td>${commande.idCommande}</td>
                        <td>${commande.idClient}</td>
                        <td>${commande.dateCommande}</td>
                        <td>${commande.status}</td>
                        <!-- Delete Button -->
                        <td>
                            <form action="delete.co" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="${commande.idCommande}">
                                <button type="submit" class="btn btn-danger btn-sm">Supprimer</button>
                            </form>
                        </td>
                        <!-- Edit Button -->
                        <td>
                            <a href="update.com?id=${commande.idCommande}" class="btn btn-primary btn-sm">Éditer</a>
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
