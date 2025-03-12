/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.Attribution;
import beans.Bourse;
import beans.Etudiant;
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
public class AttributionService implements IDao<Attribution> {

    private Connexion connexion;

    public AttributionService() {
        connexion = Connexion.getInstance();
    }

    @Override
public boolean create(Attribution o) {
    if (o.getEtudiant() == null || o.getBourse() == null) {
        System.out.println("Erreur : L'étudiant ou la bourse est null.");
        return false;
    }

    String req = "INSERT INTO Attribution (etudiant_id, bourse_id) VALUES (?, ?)";
    try {
        PreparedStatement ps = connexion.getCn().prepareStatement(req);
        ps.setInt(1, o.getEtudiant().getId());
        ps.setInt(2, o.getBourse().getId());
        ps.executeUpdate();
        return true;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return false;
}

    @Override
    public boolean delete(Attribution o) {
        String req = "DELETE FROM Attribution WHERE id = ?";
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
    public boolean update(Attribution o) {
        String req = "UPDATE Attribution SET etudiant_id = ?, bourse_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getEtudiant().getId());
            ps.setInt(2, o.getBourse().getId());
            ps.setInt(3, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Attribution findById(int id) {
        String req = "SELECT * FROM Attribution WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Etudiant etudiant = new EtudiantService().findById(rs.getInt("etudiant_id"));
                Bourse bourse = new BourseService().findById(rs.getInt("bourse_id"));
                return new Attribution(rs.getInt("id"), etudiant, bourse);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Attribution> findAll() {
        List<Attribution> attributions = new ArrayList<>();
        String req = "SELECT * FROM Attribution";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etudiant etudiant = new EtudiantService().findById(rs.getInt("etudiant_id"));
                Bourse bourse = new BourseService().findById(rs.getInt("bourse_id"));
                attributions.add(new Attribution(rs.getInt("id"), etudiant, bourse));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return attributions;
    }

    public List<Bourse> trouverBoursesParEtudiant(int etudiantId) {
        List<Bourse> bourses = new ArrayList<>();
        String req = "SELECT b.* FROM Bourse b JOIN Attribution a ON b.id = a.bourse_id WHERE a.etudiant_id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, etudiantId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bourses.add(new Bourse(rs.getInt("id"), rs.getString("type"), rs.getDouble("montant")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bourses;
    }
}