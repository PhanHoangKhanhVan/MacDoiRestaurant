/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Anh Thu
 */
public class MyConnection {
    public static final String username = "root";     
    public static final String password = "anhthu2005";     
    public static final String url = "jdbc:mysql://localhost:3306/restaurant_system"; 
    public static Connection con;

     // Private constructor - no one can instantiate this
    private MyConnection() {}
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                System.out.println("Database connected successfully!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return con;
    }
    
    public static void closeConnection(){
        if(con != null){
            try{
                con.close();
                con = null;
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error closing connection", "Close Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
    }
    
    public static void main(String[] args) {
        
    }
    
    
}
