/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

 
    private String orderId; 

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false) // Relación con la mascota
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false) // Relación con el dueño
    private Owner ceduleOwner;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id", nullable = false) // Relación con el veterinario
    private Veterinarian ceduleVeterinarian;

    @OneToOne
    @JoinColumn(name = "medical_history_id") // Una orden tiene un historial médico
    private MedicalHistory medication;

    @Column(nullable = false)
    private LocalDateTime date;

    
}

