/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ports;

import app.domain.models.MedicalHistory;
import java.util.List;

/**
 *
 * @author User
 */
public interface MedicalHistoryPort {

    public void save(MedicalHistory history);

    public List<MedicalHistory> findAll();
    
    MedicalHistory findById(String id);

    List<MedicalHistory> findByPetId(String petId);

    void deleteById(String id);
    
    
}
