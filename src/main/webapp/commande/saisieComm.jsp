<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestion Commandes</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
    <style>
        #quantity-error {
            color: red;
        }
    </style>
</head>
<body>
<%@ include file="headerGestionCommande.jsp"%>
<div class="container mt-4">
    <div class="card">
        <div class="card-header">
            <h5 class="card-title">Ajouter Commande</h5>
        </div>
        <div class="card-body">
            <form action="addCommande.comm" method="post">
                <div class="form-group">
                    <label>ID du Client :</label>
                    <!-- Client Dropdown -->
                    <select class="form-control" id="id" name="id" required>
                        <option value="">Sélectionnez un client</option>
                        <c:forEach items="${clients}" var="client">
                            <option value="${client.id}">${client.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Produits :</label>
                    <select class="form-control" id="produit" name="produit">
                        <option value="">Sélectionnez des produits</option>
                        <c:forEach items="${produits}" var="p">
                            <option value="${p.id}" data-price="${p.prix}" data-quantity="${p.quantite}">
                                    ${p.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="quantity">Quantité :</label>
                    <input type="number" class="form-control" id="quantity" name="quantity" min="1" required
                           onchange="validateQuantity()">
                    <div id="quantity-error"></div>
                </div>
                <div class="form-group">
                    <label for="totalPrix">Prix Total :</label>
                    <input type="text" class="form-control" id="totalPrix" name="totalPrix" readonly>
                </div>
                <div class="form-group">
                    <label for="status">Statut :</label>
                    <select id="status" name="status">
                        <option value="encour">en cours</option>
                        <option value="TERMINER">TERMINÉE</option>
                        <option value="ANNULER">ANNULÉE</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Ajouter Commande</button>
            </form>
        </div>
    </div>
</div>

<script>
    // JavaScript to calculate total price based on selected product and quantity
    function calculateTotalPrice() {
        var selectedProduct = document.getElementById("produit");
        var productPrice = parseFloat(selectedProduct.options[selectedProduct.selectedIndex].getAttribute("data-price"));
        var quantity = parseInt(document.getElementById("quantity").value);

        var totalPrice = productPrice * quantity;

        document.getElementById("totalPrix").value = totalPrice.toFixed(2);
    }

    // JavaScript to validate quantity against available stock
    function validateQuantity() {
        var selectedProduct = document.getElementById("produit");
        var availableStock = parseInt(selectedProduct.options[selectedProduct.selectedIndex].getAttribute("data-quantity"));
        var quantity = parseInt(document.getElementById("quantity").value);

        var quantityError = document.getElementById("quantity-error");

        if (quantity > availableStock) {
            quantityError.innerHTML = "Quantité sélectionnée supérieure au stock disponible.";
            quantityError.style.display = "block";
            return false; // Stop form submission
        } else {
            quantityError.innerHTML = "";
            quantityError.style.display = "none";
            // If validation passes, calculate total price
            calculateTotalPrice();
            return true; // Allow form submission
        }
    }
</script>

</body>
</html>
