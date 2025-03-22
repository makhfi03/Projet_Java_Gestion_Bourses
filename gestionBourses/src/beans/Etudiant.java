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
public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private EGenre genre; 

    public Etudiant(int id, String nom, String prenom, String email, EGenre genre) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.genre = genre;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EGenre getGenre() {
        return genre;
    }

    public void setGenre(EGenre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", genre=" + genre + "]";
    }
}