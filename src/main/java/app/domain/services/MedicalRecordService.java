/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import app.domain.models.MedicalHistory;
import app.domain.models.Veterinarian;
import app.domain.ports.MedicalHistoryRepositoryPort;
import java.time.LocalDateTime;
import java.util.UUID;


public class MedicalRecordService {
    private final MedicalHistoryRepositoryPort recordRepository;

    public MedicalRecordService(MedicalHistoryRepositoryPort  recordRepository) {
        this.recordRepository = recordRepository;
    }

   
    public MedicalHistory createMedicalHistory(String petId, Veterinarian veterinarian, String reason,
                                               String symptoms, String diagnosis, String procedure,
                                               String medication, String medicationDose, String idOrder,
                                               String vaccinationHistory, String allergies,
                                               String procedureDetails) {
        String id = UUID.randomUUID().toString(); // Generar ID único
        LocalDateTime date = LocalDateTime.now(); // Fecha actual
        float dosage = Float.parseFloat(medicationDose); // Convertir a float

        // Crear objeto de historia clínica
        MedicalHistory record = new MedicalHistory(id, date, veterinarian, reason,
                                                   symptoms, diagnosis, procedure, medication, medicationDose,
                                                   idOrder, vaccinationHistory, allergies, procedureDetails);

        recordRepository.save(record); 
        return record;
    }

   
    public void cancelMedicalRecord(String recordId) {
        MedicalHistory record = recordRepository.findById(recordId);
        if (record == null) {
            throw new IllegalArgumentException("Registro no encontrado.");
        }
        record.cancelRecord();
        recordRepository.update(record);
    }
}

