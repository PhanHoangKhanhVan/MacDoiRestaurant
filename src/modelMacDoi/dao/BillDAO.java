/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi.dao;

import static com.mysql.cj.conf.PropertyKey.logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Anh Thu
 */
public class BillDAO {
    
    private Connection con;

    
    public BillDAO(Connection connection){
        this.con = connection;
    }

    public int addUnpaidBill(int tableId, double total) {
        String sql = "INSERT INTO bills (table_id, total_amount, is_paid) VALUES (?, ?, 0)";
        
        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, tableId);
            ps.setDouble(2, total);
            
            if ( ps.executeUpdate() > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int billId = generatedKeys.getInt(1);
                        return billId;
                    }
                }
            }
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, "Got Error adding to Bill ", "Order Creation Error", JOptionPane.ERROR_MESSAGE);
            
        }
        return -1;
    }

    public boolean markBillAsPaid(int billId) {
        String sql = "UPDATE bills SET is_paid = 1 WHERE bill_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, billId);
            
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Got Error marking Bill ", "Order Updating Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
    

