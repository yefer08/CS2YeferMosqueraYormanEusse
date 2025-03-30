/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yefer_cordoba
 */

@Setter
@Getter

public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private String idInvoice;
    private Pet pet;
    private Owner owner;
    private Order order;
    private String productName;
    private float value;
    private int quantity;
    private LocalDateTime date;

    public Invoices(String idInvoice, Pet pet, Owner owner, Order order, String productName, float value, int quantity, LocalDateTime date) {
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