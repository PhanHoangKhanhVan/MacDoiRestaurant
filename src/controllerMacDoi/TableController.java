/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllerMacDoi;
import java.sql.Connection;
import javax.swing.JOptionPane;
import modelMacDoi.Customer;
import modelMacDoi.MyConnection;
import modelMacDoi.Order;
import modelMacDoi.Table;
import modelMacDoi.dao.MenuItemDAO;
import modelMacDoi.dao.OrderDAO;
import modelMacDoi.dao.TableDAO;

/**
 *
 * @author Anh Thu
 */
public class TableController {
    private TableDAO table;    
    private OrderDAO order;

    
    public TableController() {
        Connection connection = MyConnection.getConnection(); // Get shared connection
        this.table = new TableDAO(connection);        
        this.order = new OrderDAO(connection);

    }
    
    public boolean free(int tableId){
        return table.updateByTableId(tableId, "available");
    }
    
    
    
    //return order int
    public int selectTable(Table t, Customer c){
        try{
            if (c == null) {
            JOptionPane.showMessageDialog(null,
                "Customer information is missing. Please log in again.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
            }
            int tableNo = t.getTableNumber();
            if (!table.isTableAvailable(tableNo)) {
                JOptionPane.showMessageDialog(null,"Table " + tableNo + " is currently occupied. Please choose another table.", 
                    "Table Not Available", JOptionPane.WARNING_MESSAGE);
                return -1;
            }
            
            int tableId = t.getTableId();
            if(tableId == -1){
                JOptionPane.showMessageDialog(null,"Table " + tableNo + "is not in the database", 
                    "DBS error", JOptionPane.WARNING_MESSAGE);
                return -1;
            }
            
            boolean tableUpdated = table.update(t, "occupied");
            
            Order newOrder = new Order();
            newOrder.setCustomerId(c.getUserId());
            newOrder.setTableId(t.getTableId());
            
            int orderId = order.createOrder(newOrder);
            
            JOptionPane.showMessageDialog(null, 
                "Table " + tableNo + " selected successfully! Order #" + orderId + " created.", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            
            return orderId;
            
        
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"idk what error is this", 
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        return 0;
        
    }
    
    public boolean isTableAvailable(int tableNumber) {
        return table.isTableAvailable(tableNumber);
    }

    public boolean isTableOccupied(int tableNumber) {
        return !table.isTableAvailable(tableNumber);
    }
    
    
}
