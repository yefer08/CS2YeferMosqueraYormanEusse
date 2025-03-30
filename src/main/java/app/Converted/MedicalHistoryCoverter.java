/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Converted;

import app.Entities.MedicalHistoryEntity;
import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Veterinarian;

/**
 *
 * @author User
 */
public class MedicalHistoryCoverter {
    public static MedicalHistory convertToDomain(MedicalHistoryEntity entity) {
        if (entity == null) {
            return null; // Manejo de nulos
        }

        return new MedicalHistory(
                entity.getDate(), // ID único de la mascota
                entity.getVeterinarian()  != null ? (Veterinarian) UserConverter.convertToDomainUser(entity.getVeterinarian()) : null,
                entity.getReason(),
                entity.getSymptoms(),
                entity.getDiagnosis(),
                entity.getProcedure(),
                entity.getMedication(),
                entity.getMedicationDose(),
                entity.getOrder()!= null ? OrderConverter.convertToOrder(entity.getOrder()) : null,
                entity.getVaccinationHistory(),
                entity.getAllergies(),
                entity.getProcedureDetails(),
                entity.getCanceled(),
                entity.getPet() != null ? PetConverter.convertToDomainPet(entity.getPet()) : null
        );
    }
    
    public static MedicalHistoryEntity convertToEntity(MedicalHistory history) {
        if (history == null) {
            throw new IllegalArgumentException("⚠️ El historial médico no puede ser nulo.");
        }

        return new MedicalHistoryEntity(
                history.getDate(), // Fecha del historial médico
                history.getVeterinarian() != null ? UserConverter.convertToUserEntity(history.getVeterinarian()) : null, // Convertir Veterinarian a UserEntity
                history.getReason(), // Razón de la consulta
                history.getSymptoms(), // Síntomas observados
                history.getDiagnosis(), // Diagnóstico realizado
                history.getProcedure(), // Procedimiento realizado
                history.getMedication(), // Medicamento prescrito
                history.getMedicationDose(), // Dosis del medicamento
                history.getOrder() != null ? OrderConverter.convertToOrderEntity(history.getOrder()) : null, // Convertir Order a OrderEntity
                history.getVaccinationHistory(), // Historial de vacunación
                history.getAllergies(), // Alergias conocidas
                history.getProcedureDetails(), // Detalles del procedimiento
                history.getCanceled(), // Estado cancelado
                history.getPet() != null ? PetConverter.convertToPetEntity(history.getPet()) : null // Convertir Pet a PetEntity
        );
    }
}

