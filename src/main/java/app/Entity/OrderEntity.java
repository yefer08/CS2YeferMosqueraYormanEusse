/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Entity;

import app.domain.models.MedicalHistory;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author User
 */
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental en BD
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity owner;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity pet;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private Boolean completed;

    public OrderEntity() {}

    public OrderEntity(LocalDateTime date, OwnerEntity owner, PetEntity pet, String details, Boolean completed) {
        this.date = date;
        this.owner = owner;
        this.pet = pet;
        this.details = details;
        this.completed = completed;
    }


    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public OwnerEntity getOwner() { return owner; }
    public void setOwner(OwnerEntity owner) { this.owner = owner; }

    public PetEntity getPet() { return pet; }
    public void setPet(PetEntity pet) { this.pet = pet; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }
}

