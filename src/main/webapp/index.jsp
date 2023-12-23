<%@ page language="java" %>
<%@ page import="java.sql.*"%>
<%
    String login = "";
    if (session.getAttribute("email") != null) {
        login = session.getAttribute("email").toString();
    } else {
        response.sendRedirect("auth.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <style>


        .navbar {
            background-color: #343a40;
        }

        .navbar-dark .navbar-brand {
            color: #ffffff;
        }

        .navbar-dark .navbar-nav .nav-link {
            color: #ffffff;
        }

        .navbar-dark .navbar-toggler-icon {
            background-color: #ffffff;
        }

        .container {
            text-align: center;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp"%>
<div class="container mt-3">
    <h2>Bienvenue Hamza Benmoussa</h2>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-MsA3UYBzBSB8h9tiQmCC2P04FAHc7S7cKNRSq6g+E5fqr5S5rh3WViQDVEAnF3ah"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
