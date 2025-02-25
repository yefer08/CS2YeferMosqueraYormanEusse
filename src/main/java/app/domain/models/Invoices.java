/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Invoices {
    private String idInvoice;
    private Pet idpet;
    private Owner ceduleOwner;
    private Order Orderid;
    private String nameproducr;
    private float value;
    private int quantity;
    private LocalDateTime date;

    public Invoices(String idInvoice, Pet idpet, Owner ceduleOwner, Order Orderid, String nameproducr, float value, int quantity, LocalDateTime date) {
        this.idInvoice = idInvoice;
        this.idpet = idpet;
        this.ceduleOwner = ceduleOwner;
        this.Orderid = Orderid;
        this.nameproducr = nameproducr;
        this.value = value;
        this.quantity = quantity;
        this.date = date;
    }
    
    
}
