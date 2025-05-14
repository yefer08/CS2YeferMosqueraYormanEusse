/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.domain.models.User;
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

    public MedicalHistoryService(PetPort petPort, Orderport orderPort, MedicalHistoryPort medicalHistoryPort,
                                 Userport userport, MedicalHistoryRepository medicalHistoryRepository) {
        this.petPort = petPort;
        this.orderPort = orderPort;
        this.medicalHistoryPort = medicalHistoryPort;
    }

    public void createMedicalHistory(MedicalHistory medicalHistory) {
        // 🔹 Validar existencia de la mascota
        Pet pet = petPort.findByidpet(medicalHistory.getPet().getId());
        if (pet == null) {
            throw new InvalidDataException("⚠️ ID de mascota no encontrado");
        }

        // 🔹 Validar existencia del veterinario
        User veterinarian = medicalHistory.getVeterinarian();
        if (veterinarian == null) {
            throw new InvalidDataException("⚠️ El objeto Veterinario no puede ser nulo.");
        }

        // 🔹 Validar existencia de la orden médica
        Order order = orderPort.findByorderId(medicalHistory.getOrder().getId());
        if (order == null || order.getId() == null) {
            throw new InvalidDataException("⚠️ Orden no encontrada");
        }

        // 🔹 Validar datos obligatorios
        validateMedicalHistory(medicalHistory);

        // 🔹 Guardar la historia clínica
        medicalHistoryPort.save(medicalHistory);
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