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

## UML Diagramme 
*-Use case :
![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/b22bd21b-437e-4ee2-93dc-bb59a566b723)


## L'interface de l'app
*Login de l'app

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/4b33f997-6af1-49c6-ac40-92b2e8f580b9)

*si login et incorrect

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/beb78e49-a42b-4abc-84cd-919140000b27)

*home page

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/ea6b557f-7768-4a70-94bd-8a0065c5473f)

*afficher de client

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/1a684bb8-39a7-41cd-97ad-eddde834b7fd)

*ajouter client

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/f69fa320-276e-4783-a1a7-f32be5a7b885)

*succes de l'ajoute

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/66c92b45-4819-40f9-a2d2-de9c6573c7e1)

*le client ajouter avec succes et afficher 

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/ba3caadd-043e-4a6a-8509-89be77e13afb)

*chercher avec motCle

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/da228b98-010a-4500-8315-9296cf2ccc81)

*mettre a jour le client

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/d3943c54-e5bb-49d5-9d6c-5b1ff060cfce)

*suppresion de le client 

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/4e9b23e2-c2cf-4403-ae32-dcdd2b206256)

*produit comme le client CRUD

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/e132a4c7-d221-4cc6-8a58-105370eec9dd)

*affichage de la commande

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/a182bbc9-3c5b-4035-a292-69a6d0329bdc)

*ajouter un commande selectioner l'email de client 

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/9898535d-d283-452e-a157-6b03cf5d25ec)

*ajouter un commande selectioner le produit

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/2d07c412-5769-416b-939e-aeb9071bf122)

*si tu depasser la quantite existe 

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/10cc773b-0ecf-4b04-90e7-77efb708eb84)

*le msg de l'ajoute de commande avec succes

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/1a94f55d-f82e-471a-a8be-344c9472957e)

*voila ajouter avec succes et afficher en list de commande

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/03a9f126-62e0-4f69-b72a-5657357b740b)

*si vous click logout vous aves perdu la session et allez vers la page login

![image](https://github.com/HAMZA0707/Gestion_des_comandes_OrderCraft/assets/89112359/89ad2ae1-4921-4656-9852-67c12368dd26)








