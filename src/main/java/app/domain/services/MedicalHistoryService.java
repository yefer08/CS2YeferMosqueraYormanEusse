/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

import app.Converted.MedicalHistoryConverter;
import app.Entities.MedicalHistoryEntity;
import app.Entities.OrderEntity;
import app.Entities.PetEntity;
import app.Entities.UserEntity;
import app.domain.models.MedicalHistory;
import app.domain.models.Veterinarian;
import app.exception.InvalidDataException;
import app.infrastructure.repositories.MedicalHistoryRepository;
import app.ports.MedicalHistoryPort;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class MedicalHistoryService {


    private final PetPort petPort;
    private final Orderport orderPort;
    private final MedicalHistoryPort medicalHistoryPort;
    private final Userport userport;

    public MedicalHistoryService(PetPort petPort, Orderport orderPort, MedicalHistoryPort medicalHistoryPort,
                                 Userport userport, MedicalHistoryRepository medicalHistoryRepository) {
        this.petPort = petPort;
        this.orderPort = orderPort;
        this.medicalHistoryPort = medicalHistoryPort;
        this.userport = userport;
    }
  
    public void createMedicalHistory(MedicalHistory medicalHistory) {
        // 🔹 Validaciones básicas del modelo de dominio
        validateMedicalHistory(medicalHistory);

        // --- Recuperar las entidades gestionadas para las relaciones ---
        String petId = medicalHistory.getPet() != null ? medicalHistory.getPet().getId() : null;
        if (petId == null) {
            throw new InvalidDataException("⚠️ La historia clínica debe estar asociada a una mascota válida.");
        }

        PetEntity petEntity = petPort.findPetEntityById(petId)
                .orElseThrow(() -> new InvalidDataException("⚠️ Mascota no encontrada con el ID: " + petId));

        Long veterinarianId = medicalHistory.getVeterinarian() != null ? medicalHistory.getVeterinarian().getId() : null;
        if (veterinarianId == null) {
            throw new InvalidDataException("⚠️ El veterinario no puede ser nulo.");
        }

        UserEntity veterinarianEntity = userport.findVeterinarianById(veterinarianId)
                .orElseThrow(() -> new InvalidDataException("⚠️ Veterinario no encontrado con el ID: " + veterinarianId));
       

        OrderEntity orderEntity = null;
        if (medicalHistory.getOrder() != null && medicalHistory.getOrder().getId() != null) {
            orderEntity = orderPort.findOrderEntityById(medicalHistory.getOrder().getId())
                    .orElseThrow(() -> new InvalidDataException("⚠️ Orden no encontrada con el ID: " + medicalHistory.getOrder().getId()));
        }

        // --- Convertir el modelo de dominio a MedicalHistoryEntity usando las entidades gestionadas ---
        MedicalHistoryEntity medicalHistoryEntity = MedicalHistoryConverter.convertToEntity(
                medicalHistory,
                petEntity,
                veterinarianEntity,
                orderEntity
        );
        

        // --- Guardar la MedicalHistoryEntity ---
        medicalHistoryPort.save(medicalHistoryEntity);
        System.out.println("✅ Historia clínica guardada exitosamente.");
    }


    private void validateMedicalHistory(MedicalHistory medicalHistory) {
        if (medicalHistory.getReason() == null || medicalHistory.getReason().trim().isEmpty()) {
            throw new InvalidDataException("⚠️ La razón de la consulta no puede estar vacía.");
        }
        if (medicalHistory.getSymptoms() == null || medicalHistory.getSymptoms().trim().isEmpty()) {
            throw new InvalidDataException("⚠️ Los síntomas no pueden estar vacíos.");
        }
        if (medicalHistory.getDiagnosis() == null || medicalHistory.getDiagnosis().trim().isEmpty()) {
            throw new InvalidDataException("⚠️ El diagnóstico no puede estar vacío.");
        }
        if (medicalHistory.getMedicalProcedure()== null || medicalHistory.getMedicalProcedure().trim().isEmpty()) {
            throw new InvalidDataException("⚠️ El procedimiento no puede estar vacío.");
        }
    }

    public List<MedicalHistory> findAll() {
        return medicalHistoryPort.findAll();
    }

    public List<MedicalHistory> findByPetId(String petId) {
        return medicalHistoryPort.findByPetId(petId);
    }

    public MedicalHistory findById(String id) {
        return medicalHistoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("⚠️ Historia clínica no encontrada con ID: " + id));
    }

    public void updateMedicalHistory(MedicalHistory history) {
        if (!medicalHistoryPort.existsById(history.getId())) {
            throw new RuntimeException("⚠️ No se puede actualizar, la historia clínica no existe.");
        }
        medicalHistoryPort.save(history);
        System.out.println("✅ Historia clínica actualizada correctamente.");
    }

    public void deleteMedicalHistory(String historyId) {
        if (!medicalHistoryPort.existsById(historyId)) {
            throw new RuntimeException("⚠️ No se puede eliminar, la historia clínica no existe.");
        }
        
        medicalHistoryPort.deleteById(historyId);
        System.out.println("✅ Historia clínica eliminada correctamente.");
    }
}