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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelMacDoi.MenuItem;


/**
 *
 * @author Anh Thu
 */
public class MenuItemDAO {
    private Connection con;

    
    public MenuItemDAO(Connection connection){
        this.con = connection;
    }
    
    public boolean insertMenuItem(MenuItem m){
        String sql = "insert into MenuItems (name, price, image_path) values(?, ?, ?)";
        try(PreparedStatement ps = con.prepareStatement(sql)) 
        {
            
            ps.setString(1, m.getName());
            ps.setDouble(2, m.getPrice());
            ps.setBytes(3, m.getImagePath());
            
            return ps.executeUpdate() > 0; 
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Got Error inserting MenuItem " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    
    public void getAllProducts(JTable table){
        String sql = "SELECT * FROM MenuItems ORDER BY item_id ASC";
        try (PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
            )
        {   
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            Object[] row;
            
            while(rs.next()){
                row = new Object[4];
                row [0] = rs.getInt(1);
                row [1] = rs.getString(2);
                row [2] = rs.getDouble(3);
                row [3] = rs.getBytes(4);
                model.addRow(row);
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Got Error loading menu items: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
           
        }
        
    }
    
    public boolean update(MenuItem m){
        String sql = "UPDATE MenuItems SET name = ?, price = ? WHERE item_id = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql))
        {
            
            ps.setString(1, m.getName());
            ps.setDouble(2, m.getPrice());
            ps.setInt(3, m.getItemId());
            return ps.executeUpdate() > 0;
        
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Got Error updating MenuItem " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean delete(int itemId){
        String sql = "DELETE FROM MenuItems WHERE item_id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, itemId);
            return ps.executeUpdate() > 0;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Got Error deleting MenuItem " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    
}
