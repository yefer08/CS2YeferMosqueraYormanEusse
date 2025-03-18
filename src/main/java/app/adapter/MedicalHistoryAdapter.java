/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.adapter;

import app.Entity.MedicalHistoryEntity;
import app.domain.models.MedicalHistory;
import app.infrastructure.repositories.MedicalHistoryRepository;
import app.ports.MedicalHistoryport;
import org.springframework.beans.factory.annotation.Autowired;
    

public class MedicalHistoryAdapter implements MedicalHistoryport{

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;
    
    @Override
    public void save(MedicalHistory history) {
        
        MedicalHistoryEntity medicalHistoryEntity = convertToEntity(history);
        
        medicalHistoryRepository.save(medicalHistoryEntity);
    }
        private MedicalHistoryEntity convertToEntity(MedicalHistory history) {
        return new MedicalHistoryEntity(
            history.getId(),
            history.getDate(),
            history.getVeterinarian(),
            history.getReason(),
            history.getSymptoms(),
            history.getDiagnosis(),
            history.getProcedure(),
            history.getMedication(),
            history.getMedicationDose(),
            history.getOrder(),
            history.getVaccinationHistory(),
            history.getAllergies(),
            history.getProcedureDetails(),
            history.getCanceled(),
            history.getPet()
        );

     }
}
