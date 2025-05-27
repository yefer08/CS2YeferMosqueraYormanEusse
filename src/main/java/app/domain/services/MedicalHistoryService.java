package app.domain.services;

import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.models.Veterinarian;
import app.exception.InvalidDataException;
import app.ports.MedicalHistoryPort;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional 
@Service
public class MedicalHistoryService {

    private final PetPort petPort;
    private final Orderport orderPort;
    private final MedicalHistoryPort medicalHistoryPort;
    private final Userport userport;

    public MedicalHistoryService(PetPort petPort,
            Orderport orderPort,
            MedicalHistoryPort medicalHistoryPort,
            Userport userport) {
        this.petPort = petPort;
        this.orderPort = orderPort;
        this.medicalHistoryPort = medicalHistoryPort;
        this.userport = userport;
    }

    // 🔹 Crear nueva historia clínica
    public void createMedicalHistory(MedicalHistory medicalHistory) {
        validateMedicalHistory(medicalHistory);

        // Obtener y validar mascota
        String petId = Optional.ofNullable(medicalHistory.getPet())
                .map(Pet::getId)
                .orElseThrow(() -> new InvalidDataException("⚠️ La historia clínica debe estar asociada a una mascota válida."));

        Pet pet = petPort.findByidpet(petId);
        if (pet == null) {
            throw new InvalidDataException("⚠️ Mascota no encontrada con el ID: " + petId);
        }

        // Obtener y validar veterinario
        Long vetId = Optional.ofNullable(medicalHistory.getVeterinarian())
                .map(User::getId)
                .orElseThrow(() -> new InvalidDataException("⚠️ El veterinario no puede ser nulo."));

        User veterinarian = userport.findById(vetId);
        if (veterinarian == null) {
            throw new InvalidDataException("⚠️ Veterinario no encontrado con el ID: " + vetId);
        }

        // Asociar los objetos válidos
        medicalHistory.setPet(pet);
        medicalHistory.setVeterinarian((Veterinarian) veterinarian);

        // Validar si hay orden médica
        if (medicalHistory.getOrder() != null && medicalHistory.getOrder().getId() != null) {
            String orderId = medicalHistory.getOrder().getId();
            Optional<Order> optionalOrder = orderPort.findById(orderId);

            if (optionalOrder.isEmpty()) {
                throw new InvalidDataException("⚠️ Orden no encontrada con el ID: " + orderId);
            }

            medicalHistory.setOrder(optionalOrder.get()); // Ya tienes el objeto Order completo
        }
        System.out.println("Guardando historia...");
        medicalHistoryPort.save(medicalHistory);

    }

    private void validateMedicalHistory(MedicalHistory medicalHistory) {
        if (medicalHistory == null) {
            throw new InvalidDataException("⚠️ La historia clínica no puede ser nula.");
        }
        if (isBlank(medicalHistory.getReason())) {
            throw new InvalidDataException("⚠️ La razón de la consulta no puede estar vacía.");
        }
        if (isBlank(medicalHistory.getSymptoms())) {
            throw new InvalidDataException("⚠️ Los síntomas no pueden estar vacíos.");
        }
        if (isBlank(medicalHistory.getDiagnosis())) {
            throw new InvalidDataException("⚠️ El diagnóstico no puede estar vacío.");
        }
        if (isBlank(medicalHistory.getMedicalProcedure())) {
            throw new InvalidDataException("⚠️ El procedimiento no puede estar vacío.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    // 📋 Buscar todas las historias
    public List<MedicalHistory> findAll() {
        return medicalHistoryPort.findAll();
    }

    // 🔎 Buscar por mascota
    public List<MedicalHistory> findByPetId(String petId) {
        return medicalHistoryPort.findByPetId(petId);
    }

    // 🔎 Buscar por ID
    public MedicalHistory findById(String id) {
        return medicalHistoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("⚠️ Historia clínica no encontrada con ID: " + id));
    }

    // ✏️ Actualizar historia
    public void updateMedicalHistory(MedicalHistory history) {
        if (!medicalHistoryPort.existsById(history.getId())) {
            throw new RuntimeException("⚠️ No se puede actualizar, la historia clínica no existe.");
        }
        medicalHistoryPort.save(history);
        System.out.println("✅ Historia clínica actualizada correctamente.");
    }

    // 🗑️ Eliminar historia
    public void deleteMedicalHistory(String historyId) {
        if (!medicalHistoryPort.existsById(historyId)) {
            throw new RuntimeException("⚠️ No se puede eliminar, la historia clínica no existe.");
        }
        medicalHistoryPort.deleteById(historyId);
        System.out.println("✅ Historia clínica eliminada correctamente.");
    }
}
