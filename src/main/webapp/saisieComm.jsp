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
            <h5 class="card-title">Ajouter Commande</h5>
        </div>
        <div class="card-body">
            <form action="addCommande.com" method="post">
                <div class="form-group">
                    <label>ID du Client:</label>
                    <c:out value="${clients.size()}"></c:out>
                    <!-- Client Dropdown -->
                    <select class="form-control" id="id" name="id" required>
                        <option value="">Select a client</option>
                        <c:forEach var="client" items="${clients}">
                            <option value="${client.id}">${client.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Produits:</label>
                    <select class="form-control" id="produit" name="produit" >
                        <option value="">Select products</option>
                        <c:forEach var="p" items="${produits}">
                            <option value="${p.id}">
                                    ${p.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="totalPrix">Prix Total:</label>
                    <input type="text" class="form-control" id="totalPrix" name="totalPrix" readonly>
                </div>
                <div class="form-group">
                    <label for="status">Statut:</label>
                    <select class="form-control" id="status" name="status" required>
                        <option value="EN_COURS">En Cours</option>
                        <option value="TERMINER">Terminé</option>
                        <option value="ANULLER">Annulé</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary mt-3">Ajouter Commande</button>
            </form>
        </div>
    </div>
</div>

<script>
    // JavaScript to calculate total price based on selected products
    document.getElementById("produit").addEventListener("change", calculateTotalPrice);

    function calculateTotalPrice() {
        var selectedProducts = document.getElementById("produit").selectedOptions;
        var totalPrice = 0;
console.log(selectedProducts);
        for (var i = 0; i < selectedProducts.length; i++) {
            // Assuming each product has a data-price attribute
            var productPrice = parseFloat(selectedProducts[i].getAttribute("data-price"));
            totalPrice += productPrice;
        }

        document.getElementById("totalPrix").value = totalPrice.toFixed(2);
    }
</script>

</body>
</html>
