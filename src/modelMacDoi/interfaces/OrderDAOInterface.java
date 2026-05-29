/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelMacDoi.interfaces;

import modelMacDoi.Order;
import java.util.List;
import modelMacDoi.Order.OrderStatus;
/**
 *
 * @author nnnhi
 */
public interface OrderDAOInterface {
    List<Order> getAllOrders();
    List<Order> getOrdersByStatus(OrderStatus status);
    Order getOrderById(int orderID);
    boolean insertOrder(Order order);
    boolean updateOrderStatus(int orderID, OrderStatus status);
    boolean deleteOrder(int orderID);
    
    // Business methods
    List<Order> getPlacedOrders();
    List<Order> getCookingOrders();
    List<Order> getCookedOrders();
    List<Order> getDeliveringOrders();
    String getOrderSummary(int orderID);
}
