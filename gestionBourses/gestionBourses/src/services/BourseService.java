/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.Bourse;
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
public class BourseService implements IDao<Bourse> {

    private Connexion connexion;

    public BourseService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Bourse o) {
        String req = "INSERT INTO Bourse (type, montant) VALUES (?, ?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getType());
            ps.setDouble(2, o.getMontant());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Bourse o) {
        String req = "DELETE FROM Bourse WHERE id = ?";
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
    public boolean update(Bourse o) {
        String req = "UPDATE Bourse SET type = ?, montant = ? WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getType());
            ps.setDouble(2, o.getMontant());
            ps.setInt(3, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Bourse findById(int id) {
        String req = "SELECT * FROM Bourse WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Bourse(rs.getInt("id"), rs.getString("type"), rs.getDouble("montant"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Bourse> findAll() {
        List<Bourse> bourses = new ArrayList<>();
        String req = "SELECT * FROM Bourse";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bourses.add(new Bourse(rs.getInt("id"), rs.getString("type"), rs.getDouble("montant")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bourses;
    }
    
    public Bourse findByType(String type) {
    String req = "SELECT * FROM Bourse WHERE type = ?";
    try {
        PreparedStatement ps = connexion.getCn().prepareStatement(req);
        ps.setString(1, type);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Bourse(rs.getInt("id"), rs.getString("type"), rs.getDouble("montant"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}
    }

   