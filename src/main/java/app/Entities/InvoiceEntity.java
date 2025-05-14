/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yefer_cordoba
 */

@Getter
@Setter
@Entity
@Table(name = "invoices")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idInvoice;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity pet;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private float value;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private LocalDateTime date;

    public InvoiceEntity() {}

    public InvoiceEntity(PetEntity pet, UserEntity owner, OrderEntity order, String productName, float value, int quantity, LocalDateTime date) {
        this.pet = pet;
        this.owner = owner;
        this.order = order;
        this.productName = productName;
        this.value = value;
        this.quantity = quantity;
        this.date = date;
    }


}
