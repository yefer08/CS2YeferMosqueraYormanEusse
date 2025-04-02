/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Appointment {

    // Propiedades de la cita
    private String id;
    private Veterinarian veterinarianId; // ID del veterinario que atiende
    private Owner ownerId;        // ID del dueño de la mascota
    private Pet petId;          // ID de la mascota
    private String reason;      // Motivo de la consulta
    private String symptoms;    // Sintomatología reportada
    private String diagnosis;   // Diagnóstico realizado
    private String treatment;   // Tratamiento recomendado
    private String appointmentDate; // Fecha y hora de la cita (por simplicidad, un String)

    public Appointment(String id, Veterinarian veterinarianId, Owner ownerId, Pet petId, String reason, String symptoms, String diagnosis, String treatment, String appointmentDate) {
        this.id = id;
        this.veterinarianId = veterinarianId;
        this.ownerId = ownerId;
        this.petId = petId;
        this.reason = reason;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.appointmentDate = appointmentDate;
    }

    
}
