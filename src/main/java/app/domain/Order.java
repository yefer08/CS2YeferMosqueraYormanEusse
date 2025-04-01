/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yefer_cordoba
 */

@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Genera un UUID automático
    private String id;  
    private Pet pet;
    private Owner owner;
    private Veterinarian veterinarian; 
    private MedicalHistory medication;
    private LocalDateTime date;
    private String details;
    private boolean completed;

    public Order(String id, Pet pet, Owner owner, Veterinarian veterinarian, MedicalHistory medication, LocalDateTime date, String details,Boolean completed) {
        this.id = id;
        this.pet = pet;
        this.owner = owner;
        this.veterinarian = veterinarian;
        this.medication = medication;
        this.date = date;
        this.details = details;
        this.completed = completed;
    }    

    
}

    

