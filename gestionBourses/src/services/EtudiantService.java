/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.Etudiant;
import beans.EGenre; 
import connexion.Connexion;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNIA
 */
public class EtudiantService implements IDao<Etudiant> {

    private Connexion connexion;

    public EtudiantService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Etudiant o) {
        String req = "INSERT INTO Etudiant (nom, prenom, email, genre) VALUES (?, ?, ?, ?)"; // Ajout du genre
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getEmail());
            ps.setString(4, o.getGenre().name()); // Conversion de l'enum en String
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Etudiant o) {
        String req = "DELETE FROM Etudiant WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Etudiant o) {
        String req = "UPDATE Etudiant SET nom = ?, prenom = ?, email = ?, genre = ? WHERE id = ?"; // Ajout du genre
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getEmail());
            ps.setString(4, o.getGenre().name()); // Conversion de l'enum en String
            ps.setInt(5, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Etudiant findById(int id) {
        String req = "SELECT * FROM Etudiant WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Récupération du genre depuis la base de données
                EGenre genre = EGenre.valueOf(rs.getString("genre")); // Conversion de String en enum
                return new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), genre);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de l'étudiant : " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        String req = "SELECT * FROM Etudiant";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Récupération du genre depuis la base de données
                EGenre genre = EGenre.valueOf(rs.getString("genre")); // Conversion de String en enum
                etudiants.add(new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), genre));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return etudiants;
    }

    public Etudiant findByEmail(String email) {
        String req = "SELECT * FROM Etudiant WHERE email = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                EGenre genre = EGenre.valueOf(rs.getString("genre"));
                return new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), genre);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}