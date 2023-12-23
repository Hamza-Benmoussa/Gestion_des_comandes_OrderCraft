<%@ page import="model.CommandeModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Liste des Commandes</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
</head>
<body>
<%@ include file="headerGestionCommande.jsp"%>
<div class="container mt-4">
    <div class="card">
        <div class="card-header">
            <h5 class="card-title">Commandes</h5>
        </div>
        <div class="card-body">
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
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
