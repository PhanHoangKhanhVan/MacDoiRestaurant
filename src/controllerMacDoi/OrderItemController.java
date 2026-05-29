/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllerMacDoi;

import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelMacDoi.MenuItem;
import modelMacDoi.MyConnection;
import modelMacDoi.Order;
import modelMacDoi.OrderItem;
import modelMacDoi.dao.MenuItemDAO;
import modelMacDoi.dao.OrderDAO;
import modelMacDoi.dao.OrderItemDAO;

/**
 *
 * @author Anh Thu
 */
public class OrderItemController {
    private OrderItemDAO  orderitem;
    private MenuItemDAO menuitem;
    private OrderDAO order;
    
    public OrderItemController() {
        Connection connection = MyConnection.getConnection(); // Get shared connection
        this.orderitem = new OrderItemDAO(connection);
        this.menuitem = new MenuItemDAO(connection);        
        this.order = new OrderDAO(connection);

    }
    
    public double getTotal(int i){
        return order.getTotal(i);
    }
    
    public boolean isOrderItemExist(int orderId, int itemId) {
        return orderitem.isOrderItemExist(orderId, itemId);
    }
    
    
    public boolean addItemToOrder(Order o, MenuItem m, int quantity) {
        try {
            // Validate inputs
            if (o.getOrderId() <= 0) {
                throw new IllegalArgumentException("Invalid order ID");
            }
            if (m.getItemId() <= 0) {
                throw new IllegalArgumentException("Invalid menu item ID");
            }
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be at least 1");
            }
            
            double itemPrice = m.getPrice();
            double subtotal = itemPrice * quantity;
            
            OrderItem orderItem = new OrderItem();
            
            orderItem.setOrderId(o.getOrderId());
            orderItem.setItemId(m.getItemId());
            orderItem.setQuantity(quantity);
            orderItem.setSubtotal(subtotal);
            
            boolean success;
            success = orderitem.insertOrderItem(orderItem);
            
            if(success){
                double newTotal = orderitem.calculateOrderTotal(o.getOrderId());
                order.updateTotal(o, newTotal);
                JOptionPane.showMessageDialog(null, 
                    "Item added to order successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Failed to add item to order", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
            return success;
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                "Error adding item to order: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            
        }
        return false;
    }
    public void loadOrderItemsByOrderId(JTable table, int orderId) {
    try {
        orderitem.getOrderItemsByOrderId(table, orderId);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null,
            "Error loading cart items: " + ex.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
}

    public void setStatusPlaced(int orderId) {
        order.setStatusPlaced(orderId);
    }
            
}
