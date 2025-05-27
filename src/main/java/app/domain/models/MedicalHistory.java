/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter

public class MedicalHistory {

    private String id;
    private LocalDateTime date;
    private Veterinarian veterinarian;
    private String reason;
    private String symptoms;
    private String diagnosis;
    private String medicalProcedure;
    private String medication;
    private String medicationDose;
    private Order order;
    private String vaccinationHistory;
    private String allergies;
    private String procedureDetails;
    private Boolean canceled;
    private Pet pet;
    
    public MedicalHistory() {}

    public MedicalHistory( LocalDateTime date, Veterinarian veterinarian, 
            String reason, String symptoms, String diagnosis, String procedure, String medication,
            String medicationDose, Order order, String vaccinationHistory, String allergies, 
            String procedureDetails, Boolean canceled, Pet pet) {
        
        this.date = date;
        this.veterinarian = veterinarian;
        this.reason = reason;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.medicalProcedure = procedure;
        this.medication = medication;
        this.medicationDose = medicationDose;
        this.order = order;
        this.vaccinationHistory = vaccinationHistory;
        this.allergies = allergies;
        this.procedureDetails = procedureDetails;
        this.canceled = canceled;
        this.pet = pet;
    }
    public void cancelRecord() {
        this.canceled = true;
    }   
}

