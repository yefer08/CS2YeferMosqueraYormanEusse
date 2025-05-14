/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.rest.request;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceRequest {
    private String idInvoice;
    private String pet;
    private Long owner; //replace
    private String order; //replace
    private String productName;
    private float value;
    private int quantity;
    private LocalDateTime date;

    //es necesario el constructor vacio=?
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
}
