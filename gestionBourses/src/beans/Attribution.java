/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
/**
 *
 * @author HNIA
 */
public class Attribution {
    private Etudiant etudiant;
    private Bourse bourse;

    public Attribution(Etudiant etudiant, Bourse bourse) {
        this.etudiant = etudiant;
        this.bourse = bourse;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Bourse getBourse() {
        return bourse;
    }

    public void setBourse(Bourse bourse) {
        this.bourse = bourse;
    }

    @Override
    public String toString() {
        return "Attribution [etudiant=" + etudiant + ", bourse=" + bourse + "]";
    }
}
