/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Entities;

import app.domain.models.MedicalHistory;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
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
@Table(name = "orders")
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; 

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner; 

    @ManyToOne
    @JoinColumn(name = "veterinarian_id", nullable = false)
    private UserEntity veterinarian; 

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity pet; 

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private Boolean completed;

    @OneToOne
    @JoinColumn(name = "medical_history_id")
    private MedicalHistoryEntity medication; 

    public OrderEntity() {}

    public OrderEntity(String id, LocalDateTime date, UserEntity owner, UserEntity veterinarian, PetEntity pet, String details, Boolean completed, MedicalHistoryEntity medication) {
        this.id = id;
        this.date = date;
        this.owner = owner;
        this.veterinarian = veterinarian;
        this.pet = pet;
        this.details = details;
        this.completed = completed;
        this.medication = medication;
    }

   
}
