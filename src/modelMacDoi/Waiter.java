/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;

import java.util.List;


/**
 *
 * @author Anh Thu
 */
public class Waiter extends Staff{
    public Waiter(int userId, String username, String password, RoleEnum role) {
        super(userId, username, password, role);
    }
    
    public List<Order> getOrdersForWaiter() {
        return orderController.getOrdersForWaiter();
    }
    
    @Override
    public String getOrderSummary(int orderId) {
        return orderController.getOrderSummary(orderId);
    }
    
    public void delivering(int orderId) {
        orderController.delivering(orderId);
    }
    
    public void delivered(int orderId) {
        orderController.delivered(orderId);
    }
    
}
