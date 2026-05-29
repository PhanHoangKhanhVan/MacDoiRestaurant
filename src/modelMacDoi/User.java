/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelMacDoi.interfaces.Authenticatable;
import modelMacDoi.interfaces.Displayable;


/**
 *
 * @author Anh Thu
 */
public class User implements Displayable, Authenticatable {
    protected int UserId; // for inheritance pls 
    protected String username;
    protected String password;
    protected RoleEnum role;
    
    // Default constructor
    public User() {
    
    }
    
    // Constructor
    public User(int UserId, String username, String password, RoleEnum role) {
        this.UserId = UserId;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    
    
    // Interface implementation
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    @Override
    public void logout() {
        System.out.println(username + " has just logged out");
    }
    
    @Override
    public String getDisplayInfo() {
        return String.format("User: %s (%s)", username, role);
    }
    
        

    public <T> List<T> filterList(List<T> items, Predicate<T> condition) {
        return items.stream()
                   .filter(condition)
                   .collect(Collectors.toList());
    }
    
    //Getters and Setters
    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    //For debugging
    @Override
    public String toString() {
        return "User{" +
                "userId=" + UserId +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
    

}

   
