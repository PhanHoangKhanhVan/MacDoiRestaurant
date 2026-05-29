/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelMacDoi.OrderItem;

/**
 *
 * @author Anh Thu
 */
public class OrderItemDAO {
    private Connection con;

    
    public OrderItemDAO(Connection connection){
        this.con = connection;
    }
    
    

    public boolean isOrderItemExist(int orderId, int itemId){
        String sql = "SELECT * FROM OrderItems WHERE order_id = ? AND item_id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)) 
        {
            
            ps.setInt(1, orderId);
            ps.setInt(2, itemId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Got error checking order item" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return false;
    }
    
    
    public boolean insertOrderItem(OrderItem o){
        String sql = "insert into OrderItems (order_id, item_id, quantity, subtotal) values(?, ?, ?, ?)";
        try(PreparedStatement ps = con.prepareStatement(sql)) 
        {
            
            ps.setInt(1, o.getOrderId());
            ps.setInt(2, o.getItemId());
            ps.setInt(3, o.getQuantity());
            ps.setDouble(4, o.getSubtotal());
            
            return ps.executeUpdate() > 0; 
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Got Error inserting Ỏrder Item " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Connection closed automatically
        
    }
    
        public double calculateOrderTotal(int orderId) {
        String sql = "SELECT SUM(subtotal) as total FROM orderitems WHERE order_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error calculating order total: " + ex.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0.0;
    }
        
    public void getOrderItemsByOrderId(JTable table, int orderId){
        String sql = "SELECT m.name, m.price, oi.quantity, oi.subtotal FROM orderitems oi JOIN menuitems m ON oi.item_id = m.item_id WHERE oi.order_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql))
        {   
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            Object[] row;
            
            while(rs.next()){
                row = new Object[4];
                row [0] = rs.getString("name");
                row [1] = rs.getDouble("price");
                row [2] = rs.getInt("quantity");
                row [3] = rs.getDouble("subtotal");
                model.addRow(row);
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Got Error loading menu items: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
           
        }
        
    }
}
