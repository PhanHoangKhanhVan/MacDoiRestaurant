/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;
import modelMacDoi.dao.OrderDAO;
import controllerMacDoi.OrderController;
import java.sql.Connection;

/**
 *
 * @author nnnhi
 */
public abstract class Staff extends User {
    protected OrderDAO orderDAO;
    protected OrderController orderController;

    public Staff(int UserId, String username, String password, RoleEnum role) {
        super(UserId, username, password, role);
        // Initialize with shared connection
        Connection connection = MyConnection.getConnection();
        this.orderDAO = new OrderDAO(connection);
        this.orderController = new OrderController();
    }

    public Order getOrderById(int orderID) {
        return orderDAO.getOrderById(orderID);
    }

    public String getOrderSummary(int orderID) {
        return orderDAO.getOrderSummary(orderID);
    }

    @Override
    public boolean login(String username, String password) { 
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println(role + " " + username + " logged in.");
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        System.out.println(role + " " + username + " logged out.");
    }
}
