/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.rest.request;

import java.time.LocalDateTime;


public class InvoiceRequest {
    private String idInvoice;
    private String pet;
    private Long owner; //replace
    private String order; //replace
    private String productName;
    private float value;
    private int quantity;
    private LocalDateTime date;

 
    public InvoiceRequest(String idInvoice, String pet, Long owner, String order, String productName,
            float value, int quantity, LocalDateTime date) {
        this.idInvoice = idInvoice;
        this.pet = pet;
        this.owner = owner;
        this.order = order;
        this.productName = productName;
        this.value = value;
        this.quantity = quantity;
        this.date = date;
    }  

    public String getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(String idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    
}
