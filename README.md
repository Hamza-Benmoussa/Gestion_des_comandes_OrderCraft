# OrderCraft - Gestion des Commandes

## Aperçu

OrderCraft est une application web développée avec Jakarta EE, Servlets, JSP, JSTL et Bootstrap. Elle facilite la gestion des clients, des produits et des commandes.

## Technologies Utilisées

- **Jakarta EE :** Plateforme Java pour les applications d'entreprise.
- **Servlet :** Technologie Java pour la création de pages web.
- **JSP (JavaServer Pages) :** Technologie Java pour la création de pages web dynamiques.
- **JSTL (JavaServer Pages Standard Tag Library) :** Bibliothèque de balises pour les pages JSP.
- **Bootstrap :** Cadre CSS pour la conception d'interfaces utilisateur réactives.

## Structure du Projet

Le projet suit une architecture MVC (Modèle-Vue-Contrôleur) :

- **Package `web` :** Contient des Servlets pour gérer les requêtes web.
  - `CliServlet.java` : Gère les opérations liées aux clients.
  - `CommServlet.java` : Gère les opérations liées aux commandes.
  - `ControleurServletProduit.java` : Gère les opérations liées aux produits.

- **Package `dao` :** Contient les objets d'accès aux données (DAOs) et leurs implémentations.
  - Sous-package `impl` : Contient les implémentations d'interfaces.
  - `IClientDao.java` : Interface pour l'accès aux données des clients.
  - `ICommandeDao.java` : Interface pour l'accès aux données des commandes.
  - `IProduitDao.java` : Interface pour l'accès aux données des produits.
  - `ClientImplDao.java` : Implémentation de l'accès aux données des clients.
  - `CommandeImplDao.java` : Implémentation de l'accès aux données des commandes.
  - `ProduitDaoImpl.java` : Implémentation de l'accès aux données des produits.

- **Package `entite` :** Contient des classes d'entité représentant les clients, les produits, les produits des commandes et les commandes.

- **Package `DBconnection` :** Gère la connexion à la base de données.
  - `DbConnector.java` : Gère la connexion à la base de données.

- **Package `model` :** Contient des classes de modèle pour la représentation des données dans l'application web et des Servlets.

## Fonctionnalités

- **Gestion des Clients :**
  - Ajouter, modifier et supprimer des clients.
  - Rechercher des clients.

- **Gestion des Produits :**
  - Ajouter, modifier et supprimer des produits.
  - Rechercher des produits.

- **Gestion des Commandes :**
  - Afficher toutes les commandes.
  - Passer une nouvelle commande.

## Connexion à la Base de Données

L'application utilise une connexion à la base de données gérée par la classe `DbConnector`. Assurez-vous de configurer les détails de la base de données dans cette classe.

## Configuration et Installation

1. Cloner le dépôt.
2. Configurer la connexion à la base de données dans `DbConnector.java`.
3. Déployer l'application sur un serveur compatible Jakarta EE (Tomcat).
4. Utiliser la commande git.
