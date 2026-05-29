/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;

/**
 *
 * @author Anh Thu
 */
public class Customer extends User{
    
    public Customer() {
        super();
        this.role = RoleEnum.CUSTOMER;
    }

    public Customer(int UserId, String username, String password, RoleEnum role) {
        super(UserId, username, password, role);
    }
    
    
    
}
