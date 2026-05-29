/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelMacDoi;

/**
 *
 * @author Anh Thu
 */
public class MenuItem {
    private int itemId;
    private String name;
    private double price;
    private byte []imagePath;
    
    public MenuItem(){}

    public MenuItem(int itemId, String name, double price, byte[] imagePath) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }
    
    
    
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    
    public double getItemPrice(int itemId) {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImagePath() {
        return imagePath;
    }

    public void setImagePath(byte[] imagePath) {
        this.imagePath = imagePath;
    }

     

    @Override
    public String toString() {
        return "MenuItem{" + "itemId=" + itemId + ", name=" + name + ", price=" + price + '}';
    }
    
    
}
