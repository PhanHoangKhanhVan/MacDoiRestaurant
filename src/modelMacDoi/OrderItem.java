/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;

/**
 *
 * @author Anh Thu
 */
public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int itemId;
    private int quantity;
    private double subtotal;
    private MenuItem menuItem;
    
    //Constructor
    public OrderItem(){}

    public OrderItem(int orderItemId, int orderId, int itemId, int quantity, double subtotal) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
    
    
    
    //Getters and Setters

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    public MenuItem getMenuItem() { 
        return menuItem; 
    }

    public void setMenuItem(MenuItem menuItem) { 
        this.menuItem = menuItem; 
    }

    @Override
    public String toString() {
        if (menuItem != null) {
            return menuItem.getName() + " x" + quantity;
        }
        return "Item #" + itemId + " x" + quantity;
    }
    
    
}
