/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;

import controllerMacDoi.OrderItemController;
import java.time.LocalDateTime;
import java.util.List;
import modelMacDoi.dao.OrderDAO;


/**
 *
 * @author Anh Thu
 */
public class Order {
    private int orderId;
    private int tableId;
    private int customerId;
    private OrderStatus status;
    private double totalAmount;
    private Integer chefId; //can be null
    private Integer waiterId; //can be null because heheheheh an order may not be assigned to a waiter. 
    private List<OrderItem> orderItems; //A list to store data
    private LocalDateTime timestamp;
    
    private OrderItemController orderItemController;  
    private OrderDAO orderDAO;
    
    public enum OrderStatus{
        PENDING, PLACED, COOKING, COOKED, DELIVERING, DELIVERED
    }
    
    //Constructor
    public Order(){
        this.orderItemController = new OrderItemController();
        this.orderDAO = new OrderDAO(MyConnection.getConnection());
    }

    public Order(int orderId, int tableId, int customerId, OrderStatus status, double totalAmount, Integer chefId, Integer waiterId, LocalDateTime timestamp) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.customerId = customerId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.chefId = chefId;
        this.waiterId = waiterId;
        this.timestamp = timestamp;
        this.orderItemController = new OrderItemController();
        this.orderDAO = new OrderDAO(MyConnection.getConnection());
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getChefId() {
        return chefId;
    }

    public void setChefId(Integer chefId) {
        this.chefId = chefId;
    }

    public Integer getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Integer waiterId) {
        this.waiterId = waiterId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
   
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) { 
        this.orderItems = orderItems; 
    }
   
    public void addOrderItem(OrderItem item) {
        this.orderItems.add(item);
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", tableId=" + tableId + ", customerId=" + customerId + ", status=" + status + ", totalAmount=" + totalAmount + '}';
    }
    

    public boolean placeOrder() {
        boolean success = orderDAO.setStatusPlaced(this.orderId);
        if (success) {
            this.status = OrderStatus.PLACED;
        }
        return success;
    }
    
 
    public double calculateTotal() {
        this.totalAmount = orderItemController.getTotal(this.orderId);
        return this.totalAmount;
    }
    

    public boolean placeAndPrepareForCheckout() {
        boolean placed = placeOrder();
        if (placed) {
            calculateTotal();  
        }
        return placed;
    }
 
    
    
}
