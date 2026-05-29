/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllerMacDoi;

import javax.swing.JOptionPane;
import modelMacDoi.RoleEnum;
import modelMacDoi.User;
import modelMacDoi.dao.UserDAO;

/**
 *
 * @author Anh Thu
 */
public class UserController {
    private UserDAO UserDAO;
    
    public UserController() {
       this.UserDAO = new UserDAO();
    }
    
    public boolean registerUser(String username, String password, String roleStr){
        try{
        if(UserDAO.isUserNameExist(username)){
            throw new IllegalArgumentException("Username already exists!");
        }
        
        //Create user
        RoleEnum role = RoleEnum.fromString(roleStr); //From string to enum 
        User newUser = new User(0, username, password, role);
        return UserDAO.createUser(newUser); //true
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Got Error creating User " + ex.getMessage(), "User Creation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //connection automatically closes
    }
    
    public User loginUser(String username, String password) {
        try {
            // Use UserDAO to check credentials
            User user =  UserDAO.getUserByCredentials(username, password);
            
            if (user != null) {
                return user; // Login successful
            } else {
                throw new IllegalArgumentException("Invalid username or password!");
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Login Error: " + ex.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
}
