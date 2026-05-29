/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;

import java.util.List;


/**
 *
 *  @author nnnhi
 */
public class Chef extends Staff{
    public Chef(int userId, String username, String password, RoleEnum role) {
        super(userId, username, password, role);
    }
    
    public List<Order> getOrdersForChef() {
        return orderController.getOrdersForChef();
    }
    
    public boolean startCooking(int orderID) {
        return orderController.startCooking(orderID);
    }
    
    public boolean finishCooking(int orderID) {
        return orderController.markAsDone(orderID);
    }
    
}
