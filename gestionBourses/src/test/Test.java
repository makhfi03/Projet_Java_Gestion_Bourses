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

        bs.create(new Bourse("Bourse d'excellence", 5000.0));
        bs.create(new Bourse("Bourse sociale", 3000.0));

        Bourse bourse = bs.findById(1);
        if (bourse != null) {
            System.out.println("Bourse trouvée : " + bourse.getType() + " - " + bourse.getMontant());
        }

        bourse.setMontant(5500.0);
        bs.update(bourse);
        System.out.println("Montant de la bourse mis à jour : " + bs.findById(1).getMontant());

        List<Bourse> bourses = bs.findAll();
        for (Bourse b : bourses) {
            System.out.println(b.getType() + " - " + b.getMontant());
        }

        es.create(new Etudiant("Makhfi", "Ghita", "ghita.makhfi@gmail.com"));
        es.create(new Etudiant("Oudada", "Rita", "rita.oudada@gmail.com"));

        Etudiant etudiant = es.findById(1);
        if (etudiant != null) {
            System.out.println("Étudiant trouvé : " + etudiant.getNom() + " " + etudiant.getPrenom());
        }

        etudiant.setEmail("ghita.new@gmail.com");
        es.update(etudiant);
        System.out.println("Email de l'étudiant mis à jour : " + es.findById(1).getEmail());

        List<Etudiant> etudiants = es.findAll();
        for (Etudiant e : etudiants) {
            System.out.println(e.getNom() + " " + e.getPrenom() + " - " + e.getEmail());
        }

        as.create(new Attribution(1, 1)); 
        as.create(new Attribution(2, 2)); 

        List<Attribution> attributions = as.findAll();
        for (Attribution a : attributions) {
            System.out.println("Étudiant ID : " + a.getEtudiantId() + ", Bourse ID : " + a.getBourseId());
        }

        Attribution attributionToDelete = as.findAll().get(0);
        as.delete(attributionToDelete);
        System.out.println("Attribution supprimée : Étudiant ID " + attributionToDelete.getEtudiantId() + ", Bourse ID " + attributionToDelete.getBourseId());

        System.out.println("\nAttributions restantes :");
        for (Attribution a : as.findAll()) {
            System.out.println("Étudiant ID : " + a.getEtudiantId() + ", Bourse ID : " + a.getBourseId());
        }
    }
}