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
public class Bourse {
    private int id;
    private String type;
    private double montant;

    public Bourse(int id, String type, double montant) {
        this.id = id;
        this.type = type;
        this.montant = montant;
    }
    
    public Bourse(String type, double montant){
        this.type = type;
        this.montant = montant;
    }

    public int getId() { 
        return id; 
    }
    public void setId(int id) {
        this.id = id; 
    }

    public String getType() {
        return type; 
    }
    public void setType(String type) {
        this.type = type; 
    }

    public double getMontant() {
        return montant; 
    }
    public void setMontant(double montant) {
        this.montant = montant; 
    }

    @Override
    public String toString() {
        return "Bourse [id=" + id + ", type=" + type + ", montant=" + montant + "]";
    }
}