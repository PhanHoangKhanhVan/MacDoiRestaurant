/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelMacDoi.MyConnection;
import modelMacDoi.Order;
import modelMacDoi.Order.OrderStatus;
import modelMacDoi.interfaces.OrderDAOInterface;
import java.sql.Timestamp;

/**
 *
 * @author Anh Thu
 */
public class OrderDAO implements OrderDAOInterface {
    private Connection con;
    
    public OrderDAO(Connection connection) {
        this.con = connection;
    }
    
    public boolean updateTotal(Order o, double amount) {
        String sql = "UPDATE orders SET total_amount = ? WHERE order_id = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, o.getOrderId());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Got Error updating MenuItem " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public int createOrder(Order order) {
        String sql = "INSERT INTO orders (table_id, customer_id, status, total_amount, order_time) VALUES (?, ?, 'pending', 0.00, CURTIME())";
        
        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getTableId());
            ps.setInt(2, order.getCustomerId());
            
            if (ps.executeUpdate() > 0) {
                try (ResultSet generatedOrderId = ps.getGeneratedKeys()) {
                    if (generatedOrderId.next()) {
                        return generatedOrderId.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Got Error creating Order " + ex.getMessage(), "Order Creation Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }
    
    public double getTotal(int orderId) {
        String sql = "SELECT total_amount FROM orders WHERE order_id = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total_amount");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Got getting order total " + ex.getMessage(), "Order Creation Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0.0;
    }

    public boolean setStatusPlaced(int orderId) {
        String sql = "UPDATE Orders SET status = ? WHERE order_id = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "PLACED");
            ps.setInt(2, orderId);
            
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "no order found with id", 
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error updating order status: " + ex.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders ORDER BY order_time DESC";
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("order_id"),
                    rs.getInt("table_id"),
                    rs.getInt("customer_id"),
                    OrderStatus.valueOf(rs.getString("status").toUpperCase()),
                    rs.getDouble("total_amount"),
                    (Integer) rs.getObject("chef_id"),
                    (Integer) rs.getObject("waiter_id"),
                    rs.getTimestamp("order_time").toLocalDateTime()
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all orders: " + e.getMessage());
        }
        
        return orders;
    }
    
    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE status = ? ORDER BY order_time";
        
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, status.name());
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("table_id"),
                        rs.getInt("customer_id"),
                        OrderStatus.valueOf(rs.getString("status").toUpperCase()),
                        rs.getDouble("total_amount"),
                        (Integer) rs.getObject("chef_id"),
                        (Integer) rs.getObject("waiter_id"),
                        rs.getTimestamp("order_time").toLocalDateTime()
                    );
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting orders by status: " + e.getMessage());
        }
        
        return orders;
    }
    
    @Override
    public Order getOrderById(int orderID) {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, orderID);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                        rs.getInt("order_id"),
                        rs.getInt("table_id"),
                        rs.getInt("customer_id"),
                        OrderStatus.valueOf(rs.getString("status").toUpperCase()),
                        rs.getDouble("total_amount"),
                        (Integer) rs.getObject("chef_id"),
                        (Integer) rs.getObject("waiter_id"),
                        rs.getTimestamp("order_time").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting order by ID: " + e.getMessage());
        }
        
        return null;
    }
    
    @Override
    public boolean insertOrder(Order order) {
        String query = "INSERT INTO orders (order_time, status, table_id, customer_id, total_amount, chef_id, waiter_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(order.getTimestamp()));
            pstmt.setString(2, order.getStatus().name());
            pstmt.setInt(3, order.getTableId());
            pstmt.setInt(4, order.getCustomerId());
            pstmt.setDouble(5, order.getTotalAmount());

            if (order.getChefId() != null) {
                pstmt.setInt(6, order.getChefId());
            } else {
                pstmt.setNull(6, java.sql.Types.INTEGER);
            }

            if (order.getWaiterId() != null) {
                pstmt.setInt(7, order.getWaiterId());
            } else {
                pstmt.setNull(7, java.sql.Types.INTEGER);
            }

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        order.setOrderId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error inserting order: " + e.getMessage());
        }

        return false;
    }
    
    @Override
    public boolean updateOrderStatus(int orderID, OrderStatus status) {
        String query = "UPDATE orders SET status = ? WHERE order_id = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, status.name());
            pstmt.setInt(2, orderID);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating order status: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean deleteOrder(int orderID) {
        String query = "DELETE FROM orders WHERE order_id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, orderID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting order: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Order> getPlacedOrders() {
        return getOrdersByStatus(OrderStatus.PLACED);
    }

    @Override
    public List<Order> getCookingOrders() {
        return getOrdersByStatus(OrderStatus.COOKING);
    }
    
    @Override
    public List<Order> getCookedOrders() {
        return getOrdersByStatus(OrderStatus.COOKED);
    }
    
    @Override
    public List<Order> getDeliveringOrders() {
        return getOrdersByStatus(OrderStatus.DELIVERING);
    }
    
    @Override
    public String getOrderSummary(int orderID) {
        String query = """
            SELECT GROUP_CONCAT(CONCAT(m.name, ' x', oi.quantity) SEPARATOR ', ') AS items_summary
            FROM orderitems oi
            JOIN menuitems m ON oi.item_id = m.item_id
            WHERE oi.order_id = ?
            GROUP BY oi.order_id
        """;
        
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, orderID);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String summary = rs.getString("items_summary");
                    return summary != null ? summary : "";
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting order summary: " + e.getMessage());
        }
        
        return "";
    }
}