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
    private int etudiantId;
    private int bourseId;

    public Attribution() {}

    public Attribution(int id, int etudiantId, int bourseId) {
        this.id = id;
        this.etudiantId = etudiantId;
        this.bourseId = bourseId;
    }

    public int getId() {
        return id; 
    }
    public void setId(int id) {
        this.id = id; 
    }

    public int getEtudiantId() {
        return etudiantId; 
    }
    public void setEtudiantId(int etudiantId) {
        this.etudiantId = etudiantId; 
    }

    public int getBourseId() {
        return bourseId; 
    }
    public void setBourseId(int bourseId) {
        this.bourseId = bourseId; 
    }

    @Override
    public String toString() {
        return "Attribution [id=" + id + ", etudiantId=" + etudiantId + ", bourseId=" + bourseId + "]";
    }
}