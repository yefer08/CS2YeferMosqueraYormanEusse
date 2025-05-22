package app.Validator;

import app.exception.InvalidDataException;
import app.rest.request.MedicalHistoryRequest;

public class MedicalHistoryValidator {


    public static void validateVeterinarian(Long veterinarian) {
        if (veterinarian == null) {
            throw new InvalidDataException("⚠️ El veterinario no puede estar vacío.");
        }
    }


    public static void validatePet(String pet) {
        if (pet == null || pet.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ ID de mascota no encontrado.");
        }
    }


    public static void validateOrder(String order) {
        if (order == null || order.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ Orden no encontrada.");
        }
    }


    public static void validateReason(String reason) {
        if (reason == null || reason.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ La razón de la consulta no puede estar vacía.");
        }
    }


    public static void validateSymptoms(String symptoms) {
        if (symptoms == null || symptoms.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ Los síntomas no pueden estar vacíos.");
        }
    }


    public static void validateDiagnosis(String diagnosis) {
        if (diagnosis == null || diagnosis.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ El diagnóstico no puede estar vacío.");   
        }
    }


    public static void validateProcedure(String procedure) {
        if (procedure == null || procedure.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ El procedimiento no puede estar vacío.");
        }
    }


    public static void validate(MedicalHistoryRequest medicalHistoryRequest) {
        // Validamos cada campo
        validateVeterinarian(medicalHistoryRequest.getVeterinarian());
        validatePet(medicalHistoryRequest.getPet());
        validateReason(medicalHistoryRequest.getReason());
        validateSymptoms(medicalHistoryRequest.getSymptoms());
        validateDiagnosis(medicalHistoryRequest.getDiagnosis());
        validateProcedure(medicalHistoryRequest.getProcedureDetails());

        if (medicalHistoryRequest.getOrder() != null) {
            validateOrder(medicalHistoryRequest.getOrder());
        }
    }
}
