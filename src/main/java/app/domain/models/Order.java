/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Order {
    private String id;  
    private Pet pet;
    private Owner owner;
    private Veterinarian veterinarian; 
    private MedicalHistory medication;
    private LocalDateTime date;
    private String details;
    private boolean completed;

    public Order( Pet pet, Owner owner, Veterinarian veterinarian, MedicalHistory medication, LocalDateTime date, String details,Boolean completed) {
      
        this.pet = pet;
        this.owner = owner;
        this.veterinarian = veterinarian;
        this.medication = medication;
        this.date = date;
        this.details = details;
        this.completed = completed;
    }    

    
}

    

