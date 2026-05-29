/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelMacDoi.interfaces;


public interface Displayable {
    
    
    String getDisplayInfo();
    
    default void showInfo() {
        System.out.println("Display: " + getDisplayInfo());
    }
    
}
