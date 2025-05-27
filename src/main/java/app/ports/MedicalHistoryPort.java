// src/main/java/app/ports/MedicalHistoryPort.java
package app.ports;

import app.Entities.MedicalHistoryEntity;
import app.domain.models.MedicalHistory;
import java.util.List;
import java.util.Optional;

public interface MedicalHistoryPort {


    MedicalHistory save(MedicalHistory history); 

  
    List<MedicalHistory> findAll();

    // Encuentra una historia clínica por su ID y la devuelve como un Optional de modelo de dominio
    Optional<MedicalHistory> findById(String id);

    // Encuentra historias clínicas por ID de mascota y las devuelve como una lista de modelos de dominio
    List<MedicalHistory> findByPetId(String petId);

    // Elimina una historia clínica por su ID
    void deleteById(String id);

    // Verifica si una historia clínica existe por su ID
    boolean existsById(String id);

    Optional<MedicalHistoryEntity> findMedicalHistoryEntityById(String id);

}
