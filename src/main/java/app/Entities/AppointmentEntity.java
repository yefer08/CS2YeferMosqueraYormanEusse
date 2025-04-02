/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
public class AppointmentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;                // ID único de la cita
    private UserEntity veterinarianId;    // ID del veterinario que atiende la cita
    private UserEntity ownerId;           // ID del dueño de la mascota
    private PetEntity petId;             // ID de la mascota
    private String appointmentDate;   // Fecha de la cita (en formato String para persistencia sencilla)
    private String reason;            // Motivo de la cita
    private String symptoms;          // Sintomatología observada
    private String diagnosis;         // Diagnóstico realizado
    private String treatment;         // Tratamiento sugerido

    // Constructor vacío para frameworks como Hibernate o JPA
    public AppointmentEntity() {
    }

    public AppointmentEntity(String id, UserEntity veterinarianId, UserEntity ownerId, PetEntity petId, String appointmentDate, String reason, String symptoms, String diagnosis, String treatment) {
        this.id = id;
        this.veterinarianId = veterinarianId;
        this.ownerId = ownerId;
        this.petId = petId;
        this.appointmentDate = appointmentDate;
        this.reason = reason;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "AppointmentEntity{" + "id=" + id + 
                ", veterinarianId=" + veterinarianId +
                ", ownerId=" + ownerId + ", petId=" + petId + 
                ", appointmentDate=" + appointmentDate + ", reason=" + reason + 
                ", symptoms=" + symptoms + ", diagnosis=" + diagnosis + 
                ", treatment=" + treatment + '}';
    }

}
