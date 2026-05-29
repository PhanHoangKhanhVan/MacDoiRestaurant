/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi.dao;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import modelMacDoi.MyConnection;  
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelMacDoi.RoleEnum;
import modelMacDoi.User;


/**
 *
 * @author Anh Thu
    */
public class UserDAO {
    
    private Connection con;
    
    public UserDAO(){
        this.con = MyConnection.getConnection();
    }
    
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    public boolean isUserNameExist(String username){
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "User Name already exists: " + ex.getMessage(), "Name Error? Find another name pls", JOptionPane.ERROR_MESSAGE);
        
        }
        return false;
    }
    
    public boolean createUser(User user){
        String sql = "INSERT INTO Users(username, password, role) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql))
        {
            
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().name());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Got Error creating User " + ex.getMessage(), "User Creation Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; //Connection closed
    }
    

    public User getUserByCredentials(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, username);
            ps.setString(2, password);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        RoleEnum.fromString(rs.getString("role"))
                    );
                }
            }
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error getting user by credentials" + ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
        }
        return null; 
    }
}
