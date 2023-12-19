<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <style>
        .form-group-row {
            margin-bottom: 15px;
        }

        .login-button {
            margin-top: 10px;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <div class="card">
        <h5 class="card-header bg-primary text-white">Login</h5>
        <div class="card-body">
            <form action="Login" method="post">
                <div class="form-group row form-group-row">
                    <label>Email :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="email">
                    </div>
                </div>
                <div class="form-group row form-group-row">
                    <label>Password :</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" name="password">
                    </div>
                </div>
                <div class="col-sm-10 login-button">
                    <button class="btn btn-danger">Login</button>
                </div>

                <!-- Display error message if present -->
                <div class="col-sm-10 error-message">
                    <%-- Check if there is an error message in the session --%>
                    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                    <% if (errorMessage != null) { %>
                    <%= errorMessage %>
                    <% } %>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
