/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import beans.Bourse;
import beans.Etudiant;
import beans.EGenre;
import beans.Attribution;
import services.BourseService;
import services.EtudiantService;
import services.AttributionService;

import java.util.List;

/**
 * Classe de test pour les fonctionnalités de gestion des bourses, étudiants et attributions.
 */
public class Test {
    public static void main(String[] args) {
     
        BourseService bs = new BourseService();
        EtudiantService es = new EtudiantService();
        AttributionService as = new AttributionService();

        // Nettoyage des données existantes
        nettoyerDonneesExistantes(as, es, bs);

        // Test des fonctionnalités de BourseService
        testerBourseService(bs);

        // Test des fonctionnalités de EtudiantService
        testerEtudiantService(es);

        // Test des fonctionnalités de AttributionService
        testerAttributionService(as, es, bs);

        // Test des fonctionnalités de filtrage et de recherche
        testerFiltrageEtRecherche(bs, es);
    }

    /**
     * Nettoie les données existantes dans les tables Attribution, Étudiant et Bourse.
     */
    private static void nettoyerDonneesExistantes(AttributionService as, EtudiantService es, BourseService bs) {
        for (Attribution a : as.findAll()) {
            as.delete(a);
        }
        for (Etudiant e : es.findAll()) {
            es.delete(e);
        }
        for (Bourse b : bs.findAll()) {
            bs.delete(b);
        }
        System.out.println("Données existantes nettoyées.");
    }

    /**
     * Teste les fonctionnalités de BourseService.
     */
    private static void testerBourseService(BourseService bs) {
        System.out.println("\n=== Test des fonctionnalités de BourseService ===");

        // Ajout de bourses
        Bourse bourse1 = new Bourse(1, "Bourse d'excellence", 5000.0);
        Bourse bourse2 = new Bourse(2, "Bourse sociale", 3000.0);

        if (bs.create(bourse1)) {
            System.out.println("Bourse d'excellence ajoutée.");
        }
        if (bs.create(bourse2)) {
            System.out.println("Bourse sociale ajoutée.");
        }

        // Recherche d'une bourse par ID
        Bourse bourseTrouvee = bs.findById(1);
        if (bourseTrouvee != null) {
            System.out.println("Bourse trouvée : " + bourseTrouvee.getType() + " - " + bourseTrouvee.getMontant());
        }

        // Mise à jour d'une bourse
        if (bourseTrouvee != null) {
            bourseTrouvee.setMontant(5500.0);
            bs.update(bourseTrouvee);
            System.out.println("Montant de la bourse mis à jour : " + bs.findById(1).getMontant());
        }

        // Liste des bourses
        List<Bourse> bourses = bs.findAll();
        System.out.println("Liste des bourses :");
        for (Bourse b : bourses) {
            System.out.println(b.getType() + " - " + b.getMontant());
        }
    }

    /**
     * Teste les fonctionnalités de EtudiantService.
     */
    private static void testerEtudiantService(EtudiantService es) {
        System.out.println("\n=== Test des fonctionnalités de EtudiantService ===");

        // Ajout d'étudiants
        Etudiant etudiant1 = new Etudiant(1, "Makhfi", "Ghita", "ghita.makhfi@gmail.com", EGenre.FEMININ);
        Etudiant etudiant2 = new Etudiant(2, "Oudada", "Rita", "rita.oudada@gmail.com", EGenre.FEMININ);

        if (es.create(etudiant1)) {
            System.out.println("Étudiant Ghita Makhfi ajouté.");
        }
        if (es.create(etudiant2)) {
            System.out.println("Étudiant Rita Oudada ajouté.");
        }

        // Recherche d'un étudiant par ID
        Etudiant etudiantTrouve = es.findById(1);
        if (etudiantTrouve != null) {
            System.out.println("Étudiant trouvé : " + etudiantTrouve.getNom() + " " + etudiantTrouve.getPrenom() + " - Genre : " + etudiantTrouve.getGenre());
        }

        // Mise à jour d'un étudiant
        if (etudiantTrouve != null) {
            etudiantTrouve.setEmail("ghita.new@gmail.com");
            es.update(etudiantTrouve);
            System.out.println("Email de l'étudiant mis à jour : " + es.findById(1).getEmail());
        }

        // Liste des étudiants
        List<Etudiant> etudiants = es.findAll();
        System.out.println("Liste des étudiants :");
        for (Etudiant e : etudiants) {
            System.out.println(e.getNom() + " " + e.getPrenom() + " - " + e.getEmail() + " - Genre : " + e.getGenre());
        }
    }

    /**
     * Teste les fonctionnalités de AttributionService.
     */
    private static void testerAttributionService(AttributionService as, EtudiantService es, BourseService bs) {
        System.out.println("\n=== Test des fonctionnalités de AttributionService ===");

        // Attribution de bourses à des étudiants
        Etudiant etudiant1 = es.findById(1);
        Bourse bourse1 = bs.findById(1);

        if (etudiant1 != null && bourse1 != null) {
            as.create(new Attribution(1, etudiant1, bourse1));
            System.out.println("Bourse attribuée à Ghita Makhfi.");
        }

        Etudiant etudiant2 = es.findById(2);
        Bourse bourse2 = bs.findById(2);

        if (etudiant2 != null && bourse2 != null) {
            as.create(new Attribution(2, etudiant2, bourse2));
            System.out.println("Bourse attribuée à Rita Oudada.");
        }

        // Liste des attributions
        List<Attribution> attributions = as.findAll();
        System.out.println("Liste des attributions :");
        for (Attribution a : attributions) {
            System.out.println("Étudiant : " + a.getEtudiant().getNom() + " - Bourse : " + a.getBourse().getType());
        }

        // Suppression d'une attribution
        if (!attributions.isEmpty()) {
            Attribution attributionToDelete = attributions.get(0);
            as.delete(attributionToDelete);
            System.out.println("Attribution supprimée : " + attributionToDelete.getEtudiant().getNom() + " - " + attributionToDelete.getBourse().getType());
        }

        // Liste des attributions restantes
        System.out.println("\nAttributions restantes :");
        for (Attribution a : as.findAll()) {
            System.out.println("Étudiant : " + a.getEtudiant().getNom() + " - Bourse : " + a.getBourse().getType());
        }
    }

    /**
     * Teste les fonctionnalités de filtrage et de recherche.
     */
    private static void testerFiltrageEtRecherche(BourseService bs, EtudiantService es) {
        System.out.println("\n=== Test des fonctionnalités de filtrage et de recherche ===");

        // Filtrage des bourses par type
String typeFiltre = "Bourse sociale";
List<Bourse> boursesFiltrees = bs.findByType(typeFiltre); 
if (boursesFiltrees != null) { 
    System.out.println("Bourses filtrées par type '" + typeFiltre + "' :");
    for (Bourse b : boursesFiltrees) {
        System.out.println(b.getType() + " - " + b.getMontant());
    }
} else {
    System.out.println("Aucune bourse trouvée pour le type : " + typeFiltre);
}

        // Recherche d'un étudiant par email
        String emailRecherche = "rita.oudada@gmail.com";
        Etudiant etudiantTrouve = es.findByEmail(emailRecherche);
        if (etudiantTrouve != null) {
            System.out.println("Étudiant trouvé par email : " + etudiantTrouve.getNom() + " " + etudiantTrouve.getPrenom());
        } else {
            System.out.println("Aucun étudiant trouvé avec l'email : " + emailRecherche);
        }
    }
}