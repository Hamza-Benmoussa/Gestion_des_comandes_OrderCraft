<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestion Commandes</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<%@ include file="headerGestionCommande.jsp"%>
<div class="container mt-4">
    <div class="card">
        <div class="card-header">
            <h5 class="card-title">Modifier Commande</h5>
        </div>
        <div class="card-body">
            <form action="updateCommande.commande" method="post">
                <div class="form-group">
                    <label for="idClient">ID du Client:</label>
                    <select class="form-control" id="idClient" name="idClient" required readonly>
                        <c:forEach var="client" items="${clients}">
                            <option value="${client.idClient}" <c:if test="${client.idClient eq commande.idClient}">selected</c:if>>${client.nomClient}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Produits:</label>
                    <select multiple class="form-control" id="produits" name="produits[]" required>
                        <c:forEach var="produit" items="${produits}">
                            <c:choose>
                                <c:when test="${produit.selected}">
                                    <option value="${produit.idProduit}" selected>${produit.nomProduit} - ${produit.prix}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${produit.idProduit}">${produit.nomProduit} - ${produit.prix}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="totalPrix">Prix Total:</label>
                    <input type="text" class="form-control" id="totalPrix" name="totalPrix" value="${commande.totalPrix}" readonly>
                </div>
                <div class="form-group">
                    <label for="status">Statut:</label>
                    <select class="form-control" id="status" name="status" required>
                        <option value="EN_COURS" <c:if test="${'EN_COURS' eq commande.status}">selected</c:if>>En Cours</option>
                        <option value="TERMINER" <c:if test="${'TERMINER' eq commande.status}">selected</c:if>>Terminé</option>
                        <option value="ANULLER" <c:if test="${'ANULLER' eq commande.status}">selected</c:if>>Annulé</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary mt-3">Modifier Commande</button>
            </form>
        </div>
    </div>
</div>

<script>
    // JavaScript to calculate total price based on selected products
    document.getElementById("produits").addEventListener("change", calculateTotalPrice);

    function calculateTotalPrice() {
        var selectedProducts = document.getElementById("produits").selectedOptions;
        var totalPrice = 0;

        for (var i = 0; selectedProducts && i < selectedProducts.length; i++) {
            // Assuming each product has a data-price attribute
            var productPrice = parseFloat(selectedProducts[i].getAttribute("data-price"));
            totalPrice += productPrice;
        }

        document.getElementById("totalPrix").value = totalPrice.toFixed(2);
    }
</script>

</body>
</html>
