package app.Validator;

import app.exception.InvalidDataException;
import app.rest.request.MedicationOrderItemRequest;
import app.rest.request.OrderRequest;
import java.time.LocalDateTime;
import java.util.List;

public class OrderValidator {

    public static void validatedate(LocalDateTime dateTime) {
        if (dateTime == null) {
            dateTime = LocalDateTime.now();
        }
        if (dateTime == null) {
            throw new InvalidDataException("⚠ la fecha no puede estar vacía");
        }
    }

    public static void validateOwner(Long owner) {
        if (owner == null) {
            throw new InvalidDataException("⚠ el dueño no puede estar vacía");
        }
    }

    public static void validateVeterinarian(Long veterinarian) {
        if (veterinarian == null) {
            throw new InvalidDataException("⚠ el veterinario no puede estar vacío");
        }
    }

    public static void validatepet(String pet) {
        if (pet == null) {
            throw new InvalidDataException("⚠ la mascota no puede estar vacía");
        }
    }

    public static void validateMedicationItems(List<MedicationOrderItemRequest> medicationItems) {
        if (medicationItems == null || medicationItems.isEmpty()) {
            throw new InvalidDataException("⚠ La lista de medicamentos no puede estar vacía");
        }

        for (MedicationOrderItemRequest item : medicationItems) {
            if (item == null) {
                throw new InvalidDataException("⚠ No se permiten elementos nulos en la lista de medicamentos");
            }
            if (item.getMedicationName() == null || item.getMedicationName().trim().isEmpty()) {
                throw new InvalidDataException("⚠ Cada medicamento debe tener un nombre válido");
            }
            if (item.getDose() == null || item.getDose().trim().isEmpty()) {
                throw new InvalidDataException("⚠ Cada medicamento debe tener una dosis válida");
            }
            // Aquí puedes añadir más validaciones específicas para el DTO
        }
    }

    public static void validate(OrderRequest orderRequest) {

        if (orderRequest.getDate() == null) {
            orderRequest.setDate(LocalDateTime.now());  // Asignar la fecha de hoy si es nula
        }

        validatedate(orderRequest.getDate());
        validateOwner(orderRequest.getOwnerId());
        validateVeterinarian(orderRequest.getVeterinarianId());
        validatepet(orderRequest.getPetId());
        validateMedicationItems(orderRequest.getMedicationItems());

    }
}
