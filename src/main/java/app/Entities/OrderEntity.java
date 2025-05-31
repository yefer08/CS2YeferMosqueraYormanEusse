// src/main/java/app/Entities/OrderEntity.java
package app.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List; 


@Table(name = "orders")
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner; 

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "veterinarian_id", nullable = false)
    private UserEntity veterinarian; 

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity pet;

    @Column(columnDefinition = "TEXT") 
    private String description;

    @Column(nullable = false)
    private Boolean completed;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "medical_history_id", nullable = true) 
    private MedicalHistoryEntity medicalHistory; // Renombrado de 'medication' a 'medicalHistory'


    public OrderEntity() {
    }

 
    public OrderEntity(LocalDateTime date, UserEntity owner, UserEntity veterinarian, PetEntity pet, String description, Boolean completed, MedicalHistoryEntity medicalHistory) {
        this.date = date;
        this.owner = owner;
        this.veterinarian = veterinarian;
        this.pet = pet;
        this.description = description;
        this.completed = completed;
        this.medicalHistory = medicalHistory;
        // medicationItems se añadirán a través de setters o un método addMedicationItem
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public UserEntity getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(UserEntity veterinarian) {
        this.veterinarian = veterinarian;
    }

    public PetEntity getPet() {
        return pet;
    }

    public void setPet(PetEntity pet) {
        this.pet = pet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public MedicalHistoryEntity getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistoryEntity medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
  
}
