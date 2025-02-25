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
    private LocalDateTime date;
    private Veterinarian veterinarian;
    private String reason;
    private String symptoms;
    private String diagnosis;
    private String procedure;
    private String medication;
    private float dosage;
    private String idOrder;
    private String vaccinationHistory;
    private String allergies;
    private String procedureDetails;
    private Boolean canceled;

    public MedicalHistory(LocalDateTime date, Veterinarian veterinarian, String reason, String symptoms, String diagnosis, String procedure, String medication, float dosage, String idOrder, String vaccinationHistory, String allergies, String procedureDetails, Boolean canceled) {
        this.date = date;
        this.veterinarian = veterinarian;
        this.reason = reason;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.procedure = procedure;
        this.medication = medication;
        this.dosage = dosage;
        this.idOrder = idOrder;
        this.vaccinationHistory = vaccinationHistory;
        this.allergies = allergies;
        this.procedureDetails = procedureDetails;
        this.canceled = canceled;
    }

    

    
    
}
