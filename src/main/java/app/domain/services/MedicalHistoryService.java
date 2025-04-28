/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

import app.Entities.MedicalHistoryEntity;
import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import app.exception.InvalidDataException;
import app.infrastructure.repositories.MedicalHistoryRepository;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;
import app.ports.MedicalHistoryPort;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class MedicalHistoryService {
    
    @Autowired
    private PetPort petPort;
    @Autowired
    private Orderport orderPort;
    @Autowired
    private MedicalHistoryPort medicalHistoryPort;
    @Autowired
    private Userport userport;
    
    private final MedicalHistoryRepository medicalHistoryRepository;
    
    public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }
 

    public void createMedicalHistory(
            LocalDateTime date, Veterinarian veterinarian, String reason, String symptoms,
            String diagnosis, String procedure, String medication, String medicationDose,
            String orderId, String vaccinationHistory, String allergies, String procedureDetails,
            Boolean canceled, String petId) {

        // 🔹 Validar la existencia de la mascota
        Pet pet = petPort.findByidpet(petId);
        if (pet == null) {
            throw new InvalidDataException("⚠️ ID de mascota no encontrado");
        }

        // 🔹 Validar la existencia del veterinario
        if (veterinarian == null || veterinarian.getId() == null) {
            throw new InvalidDataException("⚠️ Ingrese la información del veterinario.");
        }

        // 🔹 Validar la existencia de la orden médica
        Order order = orderPort.findByorderId(orderId);
        if (order == null || order.getId()== null) {
            throw new InvalidDataException("⚠️ Orden no encontrada");
        }

        // 🔹 Validar que los campos obligatorios no estén vacíos
        if (reason == null || reason.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ La razón de la consulta no puede estar vacía.");
        }
        if (symptoms == null || symptoms.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ Los síntomas no pueden estar vacíos.");
        }
        if (diagnosis == null || diagnosis.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ El diagnóstico no puede estar vacío.");
        }
        if (procedure == null || procedure.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ El procedimiento no puede estar vacío.");
        }

        // 🔹 Crear la historia clínica
        MedicalHistory newHistory = new MedicalHistory(
                date, veterinarian, reason, symptoms, diagnosis, procedure,
                medication, medicationDose, order, vaccinationHistory, allergies, procedureDetails, canceled, pet
        );

        // 🔹 Guardar la historia clínica
        medicalHistoryPort.save(newHistory);
        System.out.println("✅ Historia clínica guardada exitosamente.");
    }
    
    public List<MedicalHistoryEntity> findAll() {
        return medicalHistoryRepository.findAll(); 
    }

    public List<MedicalHistoryEntity> findByPetId(String idpet) {
        return medicalHistoryRepository.findByPetId(idpet); // 🔹 Llamada correcta al método del repositorio
    }
    

    public MedicalHistory findById(String id) {
        Optional<MedicalHistory> optionalHistory = (Optional<MedicalHistory>) medicalHistoryRepository.findById(id);
        if (optionalHistory.isPresent()) {
            return optionalHistory.get();
        } else {
            throw new RuntimeException("⚠️ Historia clínica no encontrada con ID: " + id);
        }

    }

    public void updateMedicalHistory(MedicalHistory history) {
        if (!medicalHistoryRepository.existsById(history.getId())) {
            throw new RuntimeException("⚠️ No se puede actualizar, la historia clínica no existe.");
        }
        medicalHistoryRepository.save(history);
        System.out.println("✅ Historia clínica actualizada correctamente.");
    }

    public void deleteMedicalHistory(String historyId) {
        if (!medicalHistoryRepository.existsById(historyId)) {
            throw new RuntimeException("⚠️ No se puede eliminar, la historia clínica no existe.");
        }
        medicalHistoryRepository.deleteById(historyId);
        System.out.println("✅ Historia clínica eliminada correctamente.");
    }
}

