/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelMacDoi.interfaces;

/**
 *
 * @author Anh Thu
 */
public interface Authenticatable {
    
   
    boolean login(String username, String password);
    
    void logout();
    
        default boolean isValidCredential(String input) {
        return input != null && !input.trim().isEmpty();
    }
    
}
