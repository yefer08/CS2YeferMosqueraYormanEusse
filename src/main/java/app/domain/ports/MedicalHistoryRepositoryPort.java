/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.ports;

import app.domain.models.MedicalHistory;
import java.util.List;

/**
 *
 * @author User
 */
public interface MedicalHistoryRepositoryPort {
    void save(MedicalHistory record);
    MedicalHistory findById(String id);
    
    void update(MedicalHistory updatedRecord);
    List<MedicalHistory> findAll();
}
