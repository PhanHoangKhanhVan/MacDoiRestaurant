/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllerMacDoi;

import java.sql.Connection;
import modelMacDoi.MyConnection;
import modelMacDoi.dao.BillDAO;

/**
 *
 * @author Anh Thu
 */
public class BillController {
    private BillDAO billDAO;

    public BillController() {
        Connection connection = MyConnection.getConnection();
        this.billDAO = new BillDAO(connection);
    }
    
    public int createUnpaidBill(int tableId, double total) {
        return billDAO.addUnpaidBill(tableId, total);
    }
    
    public boolean markAsPaid(int billId) {
        return billDAO.markBillAsPaid(billId);
    }
    
}
