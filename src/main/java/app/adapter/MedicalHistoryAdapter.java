/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.adapter;

import app.Converted.MedicalHistoryCoverter;
import app.Converted.UserConverter;
import app.Entities.MedicalHistoryEntity;
import app.Entities.OrderEntity;
import app.Entities.PetEntity;
import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import app.infrastructure.repositories.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import app.ports.MedicalHistoryPort;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
    
@Component
public class MedicalHistoryAdapter implements MedicalHistoryPort{

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;
    
    @Override
    public void save(MedicalHistory history) {
        
        MedicalHistoryEntity medicalHistoryEntity = convertToEntity(history);
        
        medicalHistoryRepository.save(medicalHistoryEntity);
    }
       private MedicalHistoryEntity convertToEntity(MedicalHistory history) {
        if (history == null) {
            throw new IllegalArgumentException("⚠️ El historial médico no puede ser nulo.");
        }

        return new MedicalHistoryEntity(
                history.getDate(),
                history.getVeterinarian() != null ? UserConverter.convertToUserEntity(history.getVeterinarian()) : null,
                history.getReason(),
                history.getSymptoms(),
                history.getDiagnosis(),
                history.getMedicalProcedure(),
                history.getMedication(),
                history.getMedicationDose(),
                history.getOrder() != null ? convertToOrderEntity(history.getOrder()) : null,
                history.getVaccinationHistory(),
                history.getAllergies(),
                history.getProcedureDetails(),
                history.getCanceled(),
                history.getPet() != null ? convertToPetEntity(history.getPet()) : null

        );
    }


    @Override
    public List<MedicalHistory> findAll() {
        // Obtén todas las entidades desde el repositorio
        List<MedicalHistoryEntity> entities = medicalHistoryRepository.findAll();
        // Convierte cada entidad en un objeto de dominio y devuélvelo como lista
        return entities.stream()
                .map(this::convertToDomain) // Convierte cada entidad a objeto de dominio
                .collect(Collectors.toList()); // Recolecta los objetos convertidos en una lista
    }


    @Override
    public MedicalHistory findById(String id) {
        Optional<MedicalHistoryEntity> optionalEntity = (Optional<MedicalHistoryEntity>) medicalHistoryRepository.findById(id);
        // Verificar si el Optional tiene un valor presente
        if (optionalEntity.isPresent()) {
            // Si está presente, convertir a objeto de dominio
            return convertToDomain(optionalEntity.get());
        } else {
            throw new RuntimeException("⚠️ Historia clínica no encontrada con ID: " + id);
        }
    }


    @Override
    public List<MedicalHistory> findByPetId(String petId) {
        if (petId == null || petId.trim().isEmpty()) {
            throw new IllegalArgumentException("⚠️ El ID de la mascota no puede estar vacío.");
        }

        List<MedicalHistoryEntity> entities = medicalHistoryRepository.findByPetId(petId);
        return entities.stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }



    @Override
    public void deleteById(String id) {
        medicalHistoryRepository.deleteById(id);
    }

    private MedicalHistory convertToDomain(MedicalHistoryEntity entity) {
        return new MedicalHistory(
                entity.getDate(),
                entity.getVeterinarian() != null ? (Veterinarian) UserConverter.convertToDomainUser(entity.getVeterinarian()) : null,
                entity.getReason(),
                entity.getSymptoms(),
                entity.getDiagnosis(),
                entity.getMedicalProcedure(),
                entity.getMedication(),
                entity.getMedicationDose(),
                entity.getOrder() != null ? convertToOrder(entity.getOrder()): null,
                entity.getVaccinationHistory(),
                entity.getAllergies(),
                entity.getProcedureDetails(),
                entity.getCanceled(),
                entity.getPet() != null ? convertToDomainPet(entity.getPet()) : null
        );
    }
    
    private OrderEntity convertToOrderEntity(Order order) {
        if (order == null) {
            return null;
        }

        return new OrderEntity(
                order.getId(),
                order.getDate(),
                UserConverter.convertToUserEntity(order.getOwner()),
                UserConverter.convertToUserEntity(order.getVeterinarian()),
                convertToPetEntity(order.getPet()),
                order.getDetails(),
                order.isCompleted(),
                MedicalHistoryCoverter.convertToEntity(order.getMedication())
        );
    }

    private Order convertToOrder(OrderEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Order(
                entity.getId(),
                convertToDomainPet(entity.getPet()),
                (Owner) UserConverter.convertToDomainUser(entity.getOwner()),
                (Veterinarian) UserConverter.convertToDomainUser(entity.getVeterinarian()),
                (MedicalHistory)MedicalHistoryCoverter.convertToDomain(entity.getMedication()),
                entity.getDate(),
                entity.getDetails(),
                entity.getCompleted()
        );
    }

    private PetEntity convertToPetEntity(Pet pet) {
        if (pet == null) {
            return null;
        }

        return new PetEntity(
                pet.getId(),
                pet.getNamepet(),
                pet.getSpecies(),
                pet.getRacepet(),
                pet.getAgepet(),
                pet.getCaracteristic(),
                pet.getWeight(),
                UserConverter.convertToUserEntity(pet.getIdOwnwer())
        );
    }

    private Pet convertToDomainPet(PetEntity petEntity) {
        if (petEntity == null) {
            return null;
        }

        return new Pet(
                petEntity.getName(),
                petEntity.getOwner() != null ? (Owner) UserConverter.convertToDomainUser(petEntity.getOwner()) : null,
                petEntity.getAge(),
                petEntity.getId(),
                petEntity.getBreed(),
                petEntity.getCaracteristic(),
                petEntity.getWeight(),
                petEntity.getSpecies()
        );
    }


}
