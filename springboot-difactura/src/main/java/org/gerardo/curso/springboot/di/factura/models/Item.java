package org.gerardo.curso.springboot.di.factura.models;

public class Item {

    private Product product;
    private Integer quantity;


    // Getters y Setters
 public Product getProduct() {
    return product;
 }

 public void setProduct(Product product) {
    this.product = product;
 }

 public Integer getQuantity() {
    return quantity;
 }

 public void setQuantity(Integer quantity) {
    this.quantity = quantity;
 }
    
    
}
