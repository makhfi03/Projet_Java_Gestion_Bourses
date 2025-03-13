/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import beans.Bourse;
import beans.Etudiant;
import beans.Attribution;
import services.BourseService;
import services.EtudiantService;
import services.AttributionService;

import java.util.List;
/**
 *
 * @author HNIA
 */
public class Test {
    public static void main(String[] args) {
        
        BourseService bs = new BourseService();
        EtudiantService es = new EtudiantService();
        AttributionService as = new AttributionService();

        for (Attribution a : as.findAll()) {
            as.delete(a);
        }
        for (Etudiant e : es.findAll()) {
            es.delete(e);
        }
        for (Bourse b : bs.findAll()) {
            bs.delete(b);
        }

        System.out.println("=== Test des fonctionnalités de BourseService ===");

        Bourse bourse1 = new Bourse(1, "Bourse d'excellence", 5000.0);
        if (bs.findByType(bourse1.getType()) == null) {
            bs.create(bourse1);
        } else {
            System.out.println("La bourse de type " + bourse1.getType() + " existe déjà.");
        }

        Bourse bourse2 = new Bourse(2, "Bourse sociale", 3000.0);
        if (bs.findByType(bourse2.getType()) == null) {
            bs.create(bourse2);
        } else {
            System.out.println("La bourse de type " + bourse2.getType() + " existe déjà.");
        }

        Bourse bourse = bs.findById(1);
        if (bourse != null) {
            System.out.println("Bourse trouvée : " + bourse.getType() + " - " + bourse.getMontant());
        } else {
            System.out.println("Aucune bourse trouvée avec cet ID.");
        }

        if (bourse != null) {
            bourse.setMontant(5500.0);
            bs.update(bourse);
            System.out.println("Montant de la bourse mis à jour : " + bs.findById(1).getMontant());
        }

        List<Bourse> bourses = bs.findAll();
        System.out.println("Liste des bourses :");
        for (Bourse b : bourses) {
            System.out.println(b.getType() + " - " + b.getMontant());
        }

        System.out.println("\n=== Test des fonctionnalités de EtudiantService ===");

        Etudiant etudiant1 = new Etudiant(1, "Makhfi", "Ghita", "ghita.makhfi@gmail.com");
        if (es.findByEmail(etudiant1.getEmail()) == null) {
            es.create(etudiant1);
        } else {
            System.out.println("L'étudiant avec l'email " + etudiant1.getEmail() + " existe déjà.");
        }

        Etudiant etudiant2 = new Etudiant(2, "Oudada", "Rita", "rita.oudada@gmail.com");
        if (es.findByEmail(etudiant2.getEmail()) == null) {
            es.create(etudiant2);
        } else {
            System.out.println("L'étudiant avec l'email " + etudiant2.getEmail() + " existe déjà.");
        }

        Etudiant etudiant = es.findById(1);
        if (etudiant != null) {
            System.out.println("Étudiant trouvé : " + etudiant.getNom() + " " + etudiant.getPrenom());

            etudiant.setEmail("ghita.new@gmail.com");
            es.update(etudiant);
            System.out.println("Email de l'étudiant mis à jour : " + es.findById(1).getEmail());
        } else {
            System.out.println("Aucun étudiant trouvé avec cet ID.");
        }

        // Lister tous les étudiants
        List<Etudiant> etudiants = es.findAll();
        System.out.println("Liste des étudiants :");
        for (Etudiant e : etudiants) {
            System.out.println(e.getNom() + " " + e.getPrenom() + " - " + e.getEmail());
        }

        System.out.println("\n=== Test des fonctionnalités de AttributionService ===");

        Etudiant etudiantAttribution1 = es.findById(1);
        Bourse bourseAttribution1 = bs.findById(1);

        if (etudiantAttribution1 != null && bourseAttribution1 != null) {
            as.create(new Attribution(etudiantAttribution1, bourseAttribution1));
        } else {
            System.out.println("Erreur : l'étudiant ou la bourse est null.");
        }

        Etudiant etudiantAttribution2 = es.findById(2);
        Bourse bourseAttribution2 = bs.findById(2);

        if (etudiantAttribution2 != null && bourseAttribution2 != null) {
            as.create(new Attribution(etudiantAttribution2, bourseAttribution2));
        } else {
            System.out.println("Erreur : l'étudiant ou la bourse est null.");
        }

        List<Attribution> attributions = as.findAll();
        System.out.println("Liste des attributions :");
        for (Attribution a : attributions) {
            System.out.println("Étudiant : " + a.getEtudiant().getNom() + " - Bourse : " + a.getBourse().getType());
        }

        if (!attributions.isEmpty()) {
            Attribution attributionToDelete = attributions.get(0);
            as.delete(attributionToDelete);
            System.out.println("Attribution supprimée : " + attributionToDelete.getEtudiant().getNom() + " - " + attributionToDelete.getBourse().getType());
        }

        System.out.println("\nAttributions restantes :");
        for (Attribution a : as.findAll()) {
            System.out.println("Étudiant : " + a.getEtudiant().getNom() + " - Bourse : " + a.getBourse().getType());
        }
    }
}
