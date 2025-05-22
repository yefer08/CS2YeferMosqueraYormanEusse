    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.rest.request;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalHistoryRequest {
    private String id;
    private LocalDateTime date;
    private Long veterinarian; //replace
    private String reason;
    private String symptoms;
    private String diagnosis;
    private String medicalProcedure;
    private String medication;
    private String medicationDose;
    private String order; //replace
    private String vaccinationHistory;
    private String allergies;
    private String procedureDetails;
    private Boolean canceled;
    private String pet; // replace 

    public MedicalHistoryRequest(String id, LocalDateTime date, Long veterinarian,
            String reason, String symptoms, String diagnosis, String medicalProcedure, String medication, 
            String medicationDose, String order, String vaccinationHistory,
            String allergies, String procedureDetails, Boolean canceled, String pet) {
        this.id = id;
        this.date = date;
        this.veterinarian = veterinarian;
        this.reason = reason;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.medicalProcedure = medicalProcedure;
        this.medication = medication;
        this.medicationDose = medicationDose;
        this.order = order;
        this.vaccinationHistory = vaccinationHistory;
        this.allergies = allergies;
        this.procedureDetails = procedureDetails;
        this.canceled = canceled;
        this.pet = pet;
    }
    
    
}
