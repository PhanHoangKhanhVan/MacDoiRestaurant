/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllerMacDoi;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelMacDoi.MenuItem;
import modelMacDoi.dao.MenuItemDAO;
import modelMacDoi.MyConnection; 


/**
 *
 * @author Anh Thu
 */
public class MenuItemController {
    private MenuItemDAO dao;
    
    public MenuItemController() {
        Connection connection = MyConnection.getConnection(); 
        this.dao = new MenuItemDAO(connection);
    }

    
    public boolean addMenuItem(String name, double price, byte[] imagePath){
        try{
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Menu item name cannot be empty");
        }
        if (price <= 0) {
                throw new IllegalArgumentException("How can the price negative?");
            }
        
        MenuItem newItem = new MenuItem(0, name, price, imagePath);
        return dao.insertMenuItem(newItem); 
        
        }catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null, "Got Error adding Menu Item " + ex.getMessage(), "Add Item Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
     
    public void loadAllMenuItems    (JTable table) {
        try {
            dao.getAllProducts(table);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                "Error loading menu items: " + ex.getMessage(), 
                "Load Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean updateMenuItem(int itemId, String name, double price){
        try{
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Menu item name cannot be empty");
        }
        if (price <= 0) {
                throw new IllegalArgumentException("How can the price negative?");
            }
        
        MenuItem newItem = new MenuItem(itemId, name, price, null);
        return dao.update(newItem); 
        
        }catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null, "Failed to update menu item", "Update Item Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    
    public boolean deleteMenuItem(int itemId) {
        try {
            if (itemId <= 0) {
                throw new IllegalArgumentException("Invalid menu item ID");
            }
            
            int confirm = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to delete this menu item?", 
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                
                
                boolean success = dao.delete(itemId);
                
                
                if (success) {
                    JOptionPane.showMessageDialog(null, "Menu item deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete menu item", "Delete Error", JOptionPane.ERROR_MESSAGE);
                }
                return success;
            }
            return false;
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error deleting menu item: " + ex.getMessage(), "Delete Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
}
