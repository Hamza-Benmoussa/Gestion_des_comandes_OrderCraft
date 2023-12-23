<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestion Produits</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
</head>
<body>
<%@ include file="headerGestionProduit.jsp"%>
<div class="container mt-4">
    <div class="card">
        <div class="card-header">
            <h5 class="card-title">Update Produit</h5>
        </div>
        <div class="card-body">
            <form action="updateProduit.do" method="post">
                <div class="form-group">
                    <label for="nom">ID:</label>
                    <input type="text" class="form-control" id="id" name="id" value="${produit.id}" required>
                </div>
                <div class="form-group">
                    <label for="nom">Nom du Produit:</label>
                    <input type="text" class="form-control" id="nom" name="name" value="${produit.name}" required>
                </div>
                <div class="form-group">
                    <label for="prix">Prix du Produit:</label>
                    <input type="text" class="form-control" id="prix" name="prix" value="${produit.prix}" required>
                </div>
                <div class="form-group">
                    <label for="prix">Quantite du Produit:</label>
                    <input type="text" class="form-control" id="quantite" name="quantite" value="${produit.quantite}" required>
                </div>
                <button type="submit" class="btn btn-primary mt-3">Update Produit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
