/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.repositories;

import app.Entities.MedicalHistoryEntity;
import app.domain.models.MedicalHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistoryEntity, UUID> {
    List<MedicalHistoryEntity> findByPetId(String idpet);

    public Object findById(String historyId);

    public boolean existsById(String historyId);

    public void deleteById(String historyId);

    public void save(MedicalHistory history);
}

