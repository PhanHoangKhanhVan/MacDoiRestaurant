/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;

/**
 *
 * @author Anh Thu
 */
public class Table {
    private int tableId;
    private int tableNumber;
    private tableStatus  status;
    public enum tableStatus{
        AVAILABLE, OCCUPIED
    }
    
    //Constructor
    public Table(){}

    public Table(int tableId, int tableNumber, tableStatus status) {
        this.tableId = tableId;
        this.tableNumber = tableNumber;
        this.status = status;
    }
    
    //Getters and Setters
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public tableStatus getStatus() {
        return status;
    }

    public void setStatus(tableStatus status) {
        this.status = status;
    }

    //For debugging
    @Override
    public String toString() {
        return "Table{" + "tableId=" + tableId + ", tableNumber=" + tableNumber + ", status=" + status + '}';
    }
  
}
