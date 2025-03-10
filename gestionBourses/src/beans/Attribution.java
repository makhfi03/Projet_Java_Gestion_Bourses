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
    private int id;
    private Etudiant etudiant;
    private Bourse bourse;

    public Attribution(int id, Etudiant etudiant, Bourse bourse) {
        this.id = id;
        this.etudiant = etudiant;
        this.bourse = bourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Attribution [id=" + id + ", etudiant=" + etudiant + ", bourse=" + bourse + "]";
    }
}