# Gestion des Bourses

## Description du Projet

Ce projet consiste à développer une application de gestion des bourses pour les établissements d'éducation (écoles, universités, instituts). L'application permet de gérer les bourses, les étudiants, et les attributions de bourses aux étudiants. 

## Fonctionnalités

### Gestion des Bourses
- **Ajouter une bourse** : Ajouter une nouvelle bourse avec un type et un montant.
- **Modifier une bourse** : Mettre à jour les informations d'une bourse existante.
- **Supprimer une bourse** : Supprimer une bourse de la base de données.

### Gestion des Étudiants
- **Ajouter un étudiant** : Ajouter un nouvel étudiant avec son nom, prénom et email.
- **Modifier un étudiant** : Mettre à jour les informations d'un étudiant existant.
- **Supprimer un étudiant** : Supprimer un étudiant de la base de données.

### Gestion des Attributions
- **Attribuer une bourse** : Attribuer une bourse à un étudiant.
- **Supprimer une attribution** : Supprimer une attribution de bourse.

## Structure du Projet

Le projet est organisé en plusieurs packages pour une meilleure modularité :

- **`beans`** : Contient les classes Java qui représentent les entités de la base de données (Bourse, Étudiant, Attribution).
- **`connexion`** : Gère la connexion à la base de données.
- **`dao`** : Contient les classes pour interagir avec la base de données.
- **`services`** : Contient la logique métier et fait le lien entre l'interface utilisateur et le DAO.
- **`test`** : Contient les classes de test pour vérifier le bon fonctionnement des autres packages.

## Création de la Base de Données

   ```sql

   CREATE TABLE Bourse (
       id INT AUTO_INCREMENT PRIMARY KEY,
       type VARCHAR(100) NOT NULL,
       montant DECIMAL(10, 2) NOT NULL
   );

   CREATE TABLE Etudiant (
       id INT AUTO_INCREMENT PRIMARY KEY,
       nom VARCHAR(100) NOT NULL,
       prenom VARCHAR(100) NOT NULL,
       email VARCHAR(100) NOT NULL UNIQUE
   );

   CREATE TABLE Attribution (
       id INT AUTO_INCREMENT PRIMARY KEY,
       etudiant_id INT NOT NULL,
       bourse_id INT NOT NULL,
       FOREIGN KEY (etudiant_id) REFERENCES Etudiant(id),
       FOREIGN KEY (bourse_id) REFERENCES Bourse(id)
   );
