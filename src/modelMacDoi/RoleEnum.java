/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;

/**
 *
 * @author Anh Thu
 */
public enum RoleEnum {
    ADMIN("Admin"), 
    MANAGER("Manager"),
    WAITER("Waiter"),
    CHEF("Chef"),
    CUSTOMER("Customer");
    
    private final String displayName;
    
    RoleEnum(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    // Convert string to Role enum
    public static RoleEnum fromString(String roleStr) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.name().equalsIgnoreCase(roleStr) || 
                role.displayName.equalsIgnoreCase(roleStr)) {
                return role;
            }
        }
        return null; // or throw exception
    }
    
    @Override
    public String toString() {
        return displayName;
    }
    
}
