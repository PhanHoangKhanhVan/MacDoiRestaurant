/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllerMacDoi;
import java.sql.Connection;
import modelMacDoi.dao.OrderDAO;
import modelMacDoi.interfaces.OrderDAOInterface;
import modelMacDoi.Order;
import modelMacDoi.Order.OrderStatus;
import java.util.List;
import modelMacDoi.MyConnection;
import modelMacDoi.dao.MenuItemDAO;
import modelMacDoi.dao.OrderDAO;
/**
 *
 * @author nnnhi
 */
public class OrderController {
    private final OrderDAO orderDAO;
    
    public OrderController() {
        Connection connection = MyConnection.getConnection(); // Get shared connection
        this.orderDAO = new OrderDAO(connection);
    }
    
    public List<Order> getOrdersForChef() {
        List<Order> allOrders = orderDAO.getAllOrders();
        allOrders.removeIf(order -> 
            order.getStatus() != OrderStatus.PLACED && 
            order.getStatus() != OrderStatus.COOKING
        );
        return allOrders;
    }
    
    // Start cooking (PLACED → COOKING)
    public boolean startCooking(int orderID) {
        Order order = orderDAO.getOrderById(orderID);
        
        if (order == null) {
            System.err.println("Order not found!");
            return false;
        }
        
        if (order.getStatus() != OrderStatus.PLACED) {
            System.err.println("Order is not in PLACED status!");
            return false;
        }
        
        return orderDAO.updateOrderStatus(orderID, OrderStatus.COOKING);
    }
    
    // Mark as done (COOKING → COOKED)
    public boolean markAsDone(int orderID) {
        Order order = orderDAO.getOrderById(orderID);
        
        if (order == null) {
            System.err.println("Order not found!");
            return false;
        }
        
        if (order.getStatus() != OrderStatus.COOKING) {
            System.err.println("Order is not in COOKING status!");
            return false;
        }
        
        return orderDAO.updateOrderStatus(orderID, OrderStatus.COOKED);
    }
    
    //To get order which status is cooked and delivering
    public List<Order> getOrdersForWaiter() {
        List<Order> allOrders = orderDAO.getAllOrders();
        allOrders.removeIf(order -> 
            order.getStatus() != OrderStatus.COOKED && 
            order.getStatus() != OrderStatus.DELIVERING
        );
        return allOrders;
    }
    
    // Start cooking (COOKED → DELIVERING)
    public boolean delivering(int orderID) {
        Order order = orderDAO.getOrderById(orderID);
        
        if (order == null) {
            System.err.println("Order not found!");
            return false;
        }
        
        if (order.getStatus() != OrderStatus.COOKED) {
            System.err.println("Order is not in PLACED status!");
            return false;
        }
        
        return orderDAO.updateOrderStatus(orderID, OrderStatus.DELIVERING);
    }
    
    // Mark as done (DELIVERING → DELIVERED
    public boolean delivered(int orderID) {
        Order order = orderDAO.getOrderById(orderID);
        
        if (order == null) {
            System.err.println("Order not found!");
            return false;
        }
        
        if (order.getStatus() != OrderStatus.DELIVERING) {
            System.err.println("Order is not in COOKING status!");
            return false;
        }
        
        return orderDAO.updateOrderStatus(orderID, OrderStatus.DELIVERED);
    }
    
    public String getOrderSummary(int orderID) {
        return orderDAO.getOrderSummary(orderID);
    }
}