<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestion Clients</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
</head>
<body>
<%@ include file="../produit/headerGestionProduit.jsp"%>
<div class="container mt-4">
    <div class="card">
        <div class="card-header">
            <h5 class="card-title">Update Produit</h5>
        </div>
        <div class="card-body">
            <form action="updateClient.client" method="post">
                <div class="form-group">
                    <label for="nom">ID:</label>
                    <input type="text" class="form-control" id="id" name="id" value="${client.id}" required>
                </div>
                <div class="form-group">
                    <label for="nom">Nom du Client:</label>
                    <input type="text" class="form-control" id="nom" name="name" value="${client.name}" required>
                </div>
                <div class="form-group">
                    <label >Email du Client:</label>
                    <input type="text" class="form-control" id="address" name="address" value="${client.address}" required>
                </div>
                <div class="form-group">
                    <label >Address du Client:</label>
                    <input type="text" class="form-control" id="email" name="email" value="${client.email}" required>
                </div>

                <button type="submit" class="btn btn-primary mt-3">Update Client</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
