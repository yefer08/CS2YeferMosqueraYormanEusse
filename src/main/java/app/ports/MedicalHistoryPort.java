/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ports;

import app.Entities.MedicalHistoryEntity;
import app.domain.models.MedicalHistory;
import java.util.List;
import java.util.Optional;


public interface MedicalHistoryPort {

    public void save(MedicalHistory history);
    public void save(MedicalHistoryEntity historyEntity);

    public List<MedicalHistory> findAll();
    
    Optional<MedicalHistory> findById(String id);

    List<MedicalHistory> findByPetId(String petId);
    public List<MedicalHistoryEntity> findByPetIdEntity(String petId);

    void deleteById(String id);

    public boolean existsById(String id);
    
    
    
}
