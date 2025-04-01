/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.adapter;

import app.Converted.MedicalHistoryCoverter;
import app.Converted.UserConverter;
import app.Entities.OrderEntity;
import app.Entities.PetEntity;
import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import app.infrastructure.repositories.OrderServiceRepository;
import app.ports.Orderport;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.Optional;
import org.springframework.stereotype.Component;


@Component
public class OrderAdapter implements Orderport {

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = convertToEntity(order);
        orderServiceRepository.save(orderEntity);
    }

    // Convierte un Order a OrderEntity (objeto de dominio a persistente)
    private OrderEntity convertToEntity(Order order) {
        if (order == null) {
            return null; 
        }

        return new OrderEntity(
                order.getId(),
                order.getDate(), // Fecha de la orden
                UserConverter.convertToUserEntity(order.getOwner()), // Convertir Owner a UserEnity
                UserConverter.convertToUserEntity(order.getVeterinarian()), // Convertir Veterinarian a UserEntity
                convertToPetEntity(order.getPet()), // Convertir Pet a PetEntity
                order.getDetails(), // Detalles
                order.isCompleted(),
                MedicalHistoryCoverter.convertToEntity(order.getMedication())// Estado completado
        );
    }

    @Override
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

        return new Order(
                entity.getId(), // ID del pedido
                convertToDomainPet(entity.getPet()), // Convertir PetEntity a Pet
                (Owner) UserConverter.convertToDomainUser(entity.getOwner()), // Convertir UserEntity a Owner
                (Veterinarian) UserConverter.convertToDomainUser(entity.getVeterinarian()), // Convertir UserEntity a Veterinarian
                (MedicalHistory)MedicalHistoryCoverter.convertToDomain(entity.getMedication()), // Medicación asociada
                entity.getDate(), // Fecha de la orden
                entity.getDetails(), // Detalles
                entity.getCompleted() // Estado completado
        );
    }

    // Convierte un Pet a PetEntity (objeto de dominio a persistente)
    private PetEntity convertToPetEntity(Pet pet) {
        if (pet == null) {
            return null; // Manejo de nulos
        }

        return new PetEntity(
                pet.getId(), // ID único de la mascota
                pet.getNamepet(), // Nombre de la mascota
                pet.getSpecies(), // Especie
                pet.getRacepet(), // Raza
                pet.getAgepet(), // Edad
                pet.getCaracteristic(), // Características adicionales
                pet.getWeight(), // Peso
                UserConverter.convertToUserEntity(pet.getIdOwnwer()) // Convertir Owner a UserEntity
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
}
