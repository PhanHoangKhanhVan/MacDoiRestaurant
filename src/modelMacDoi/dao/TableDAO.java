/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelMacDoi.MyConnection;
import modelMacDoi.Table;

/**
 *
 */
public class TableDAO {
    
    private Connection con;
    
    public TableDAO(Connection connection){
        this.con = connection;
    }
    
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    public boolean isTableAvailable(int tableNo){
        String sql = "SELECT status FROM Tables WHERE table_number = ?";
        try (PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, tableNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String status = rs.getString("status");
                return "available".equalsIgnoreCase(status);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error checking table status: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        
        }
        return false;
    }
    
    public boolean update(Table t, String status){
        String sql = "UPDATE Tables SET status = ? WHERE table_number = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql))
        {
            
            ps.setString(1, status);
            ps.setInt(2, t.getTableNumber());
            
            return ps.executeUpdate() > 0;
        
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Got Error updating MenuItem " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public int getTableId(int tableNumber) {
        String sql = "SELECT table_id FROM tables WHERE table_number = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, tableNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("table_id");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error getting table ID: " + ex.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }
    
    public boolean updateByTableId(int tableId, String status){
    String sql = "UPDATE Tables SET status = ? WHERE table_id = ?";
    
    try (PreparedStatement ps = con.prepareStatement(sql))
    {
        ps.setString(1, status);
        ps.setInt(2, tableId);
        
        return ps.executeUpdate() > 0;
    
    } catch (Exception ex){
        JOptionPane.showMessageDialog(null, 
            "Error updating table: " + ex.getMessage(), 
            "Database Error", 
            JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
    
}
