<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestion Produits</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<%@ include file="headerGestionClient.jsp"%>
<div class="container mt-4">
    <div class="card">
        <div class="card-header">
            <h5 class="card-title">Add Produit</h5>
        </div>
        <div class="card-body">
            <form action="addClient.client" method="post">
                <div class="form-group">
                    <label for="nom">Nom du Client:</label>
                    <input type="text" class="form-control" id="nom" name="name" required>
                </div>
                <div class="form-group">
                    <label for="adress">Adress du Client:</label>
                    <input type="text" class="form-control" id="adress" name="adress" required>
                </div>
                <div class="form-group">
                    <label for="email">Email du Client:</label>
                    <input type="text" class="form-control" id="email" name="email" required>
                </div>

                <button type="submit" class="btn btn-primary mt-3">Ajouter Client</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
