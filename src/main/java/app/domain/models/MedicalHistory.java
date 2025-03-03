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
    private String procedure;
    private String medication;
    private String medicationDose;
    private String idOrder;
    private String vaccinationHistory;
    private String allergies;
    private String procedureDetails;
    private Boolean canceled;

  
    public MedicalHistory(String id, LocalDateTime date, Veterinarian veterinarian, String reason, String symptoms, 
                          String diagnosis, String procedure, String medication, String medicationDose, String idOrder, 
                          String vaccinationHistory, String allergies, String procedureDetails) {
        this.id = id;
        this.date = date;
        this.veterinarian = veterinarian;
        this.reason = reason;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.procedure = procedure;
        this.medication = medication;
        this.medicationDose = medicationDose;
        this.idOrder = idOrder;
        this.vaccinationHistory = vaccinationHistory;
        this.allergies = allergies;
        this.procedureDetails = procedureDetails;
        this.canceled = false; // Inicializar como no cancelado
    }

    public void cancelRecord() {
        this.canceled = true;
    }
}

