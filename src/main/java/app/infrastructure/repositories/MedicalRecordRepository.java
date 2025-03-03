/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.repositories;

import app.domain.models.MedicalHistory;
import app.domain.ports.MedicalHistoryRepositoryPort;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MedicalRecordRepository implements MedicalHistoryRepositoryPort  {
     private List<MedicalHistory> medicalRecords = new ArrayList<>();

     @Override
     public void save(MedicalHistory record) {
        medicalRecords.add(record);
    }
    
     @Override
    public MedicalHistory findById(String id) {
    return medicalRecords.stream()
                         .filter(r -> r.getId().equals(id))  // Filtra por ID
                         .findFirst()                        // Toma el primer resultado encontrado
                         .orElse(null);                      // Si no encuentra nada, devuelve null
}
     @Override
    public void update(MedicalHistory updatedRecord) {
        for (int i = 0; i < medicalRecords.size(); i++) {
            if (medicalRecords.get(i).getId().equals(updatedRecord.getId())) {
                medicalRecords.set(i, updatedRecord);  // Reemplaza el registro encontrado
                break;
            }
        }

    }
     @Override
    public List<MedicalHistory> findAll() {
        return new ArrayList<>(medicalRecords);
    }
    
}
