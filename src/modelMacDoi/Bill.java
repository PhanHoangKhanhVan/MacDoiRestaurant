/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;

import controllerMacDoi.BillController;
import controllerMacDoi.TableController;

/**
 *
 * @author Anh Thu
 */
public class Bill {
    private int billId;
    private int tableId;
    private double totalAmount;
    private boolean isPaid;
    
    private BillController billController;      
    private TableController tableController;    
    
    
    //Constructor
    public Bill(){
        this.billController = new BillController();
        this.tableController = new TableController();
    }

    public Bill(int billId, int tableId, double totalAmount, boolean isPaid) {
        this.billId = billId;
        this.tableId = tableId;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
    }
    
    //Getters and Setters
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
    

    public int createUnpaidBill() {
        this.billId = billController.createUnpaidBill(this.tableId, this.totalAmount);
        return this.billId;
    }
    
 
    public boolean payAndFreeTable() {
        // Mark bill as paid
        boolean billPaid = billController.markAsPaid(this.billId);
        
        if (!billPaid) {
            return false;
        }
        
        // Free the table
        boolean tableFreed = tableController.free(this.tableId);
        
        if (tableFreed) {
            this.isPaid = true;
            return true;
        }
        
        return false;
    }
    
}
