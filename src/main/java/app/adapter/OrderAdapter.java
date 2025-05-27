/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.adapter;

import app.Converted.MedicalHistoryConverter;
import app.Converted.MedicationOrderItemConverter;
import app.Converted.UserConverter;
import app.Entities.MedicalHistoryEntity;
import app.Entities.OrderEntity;
import app.Entities.PetEntity;
import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import app.infrastructure.repositories.OrderServiceRepository;
import app.infrastructure.repositories.PetRepository;
import app.ports.Orderport;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;


@Component
public class OrderAdapter implements Orderport {

    @Autowired
    private OrderServiceRepository orderServiceRepository;
    @Autowired
    private PetRepository petRepository; // Inyecta el repo para acceder a la BD
    

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = convertToEntity(order);
        OrderEntity savedEntity = orderServiceRepository.save(orderEntity); // aquí obtienes el ID generado, etc.
        return convertToDomain(savedEntity); // y lo devuelves como modelo de dominio
    }


    // Convierte un Order a OrderEntity (objeto de dominio a persistente)
    private OrderEntity convertToEntity(Order order) {
        if (order == null) {
            return null;
        }

        MedicalHistoryEntity medicalHistoryEntity = null;

        if (order.getMedicalHistory() != null && order.getMedicalHistory().getId() != null) {
            medicalHistoryEntity = MedicalHistoryConverter.convertToEntity(order.getMedicalHistory());
        }

        return new OrderEntity(
                order.getDate(), // Fecha de la orden
                UserConverter.convertToUserEntity(order.getOwner()), // Convertir Owner a UserEntity
                UserConverter.convertToUserEntity(order.getVeterinarian()), // Convertir Veterinarian a UserEntity
                convertToPetEntity(order.getPet()), // Convertir Pet a PetEntity
                order.getDescription(), // Detalles
                order.isCompleted(),
                medicalHistoryEntity // Ahora puede ser null sin romper nada
        );
    }


    public Order findByorderId(String idOrder) {
        Optional<OrderEntity> orderEntityOptional = orderServiceRepository.findById(idOrder);
        return orderEntityOptional.map(this::convertToDomain)
                .orElse(null);
    }

    // Convierte un OrderEntity a Order (persistente a dominio)
    private Order convertToDomain(OrderEntity entity) {
        if (entity == null) {
            return null; // Manejo de nulos
        }

        MedicalHistory medicalHistory = null;
        if (entity.getMedicalHistory() != null) {
            medicalHistory = MedicalHistoryConverter.convertToDomain(entity.getMedicalHistory());
        }

        return new Order(
                convertToDomainPet(entity.getPet()), // Convertir PetEntity a Pet
                (Owner) UserConverter.convertToDomainUser(entity.getOwner()), // Convertir UserEntity a Owner
                (Veterinarian) UserConverter.convertToDomainUser(entity.getVeterinarian()), // Convertir UserEntity a Veterinarian
                medicalHistory, // Historia clínica (ahora protegida)
                MedicationOrderItemConverter.convertToDomainList(entity.getMedicationItems()),
                entity.getDate(), // Fecha de la orden
                entity.getDescription(), // Detalles
                entity.getCompleted() // Estado completado
        );
    }


    // Convierte un Pet a PetEntity (objeto de dominio a persistente)
    private PetEntity convertToPetEntity(Pet pet) {
        if (pet == null) {
            return null;
        }

        if (pet.getId()!= null) {
            // Intentamos buscar la mascota existente
            Optional<PetEntity> petEntityOptional = petRepository.findById(pet.getId());
            if (petEntityOptional.isPresent()) {
                return petEntityOptional.get();
            }
        }

        // Si no existe en BD o no tiene ID, creamos nueva entidad con datos del dominio
        return new PetEntity(
                pet.getNamepet(), // Nombre
                pet.getSpecies(), // Especie
                pet.getRacepet(), // Raza
                pet.getAgepet(), // Edad
                pet.getCaracteristic(), // Características
                pet.getWeight(), // Peso
                UserConverter.convertToUserEntity(pet.getIdOwnwer()) // Dueño convertido a entidad
        );
    }


    // Convierte un PetEntity a Pet (persistente a dominio)
    private Pet convertToDomainPet(PetEntity petEntity) {
        if (petEntity == null) {
            return null; // Manejo de nulos
        }

        return new Pet(
                petEntity.getName(), // Nombre de la mascota
                petEntity.getOwner() != null ? (Owner) UserConverter.convertToDomainUser(petEntity.getOwner()) : null, // Convertir UserEntity a Owner
                petEntity.getAge(), // Edad
                petEntity.getId(), // ID único
                petEntity.getBreed(), // Raza
                petEntity.getCaracteristic(), // Características adicionales
                petEntity.getWeight(), // Peso
                petEntity.getSpecies() // Especie
        );
    }
   

    @Override
    public Optional<Order> findById(String id) {
        return orderServiceRepository.findById(id)
                .map(this::convertToDomain); // convertir entidad a modelo de dominio
    }

    @Override
    public List<Order> findAll() {
        return orderServiceRepository.findAll().stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

}
