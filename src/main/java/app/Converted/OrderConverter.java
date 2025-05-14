/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Converted;

import static app.Converted.PetConverter.convertToDomainPet;
import static app.Converted.MedicalHistoryCoverter.convertToDomain;
import static app.Converted.MedicalHistoryCoverter.convertToEntity;
import static app.Converted.PetConverter.convertToPetEntity;
import app.Entities.OrderEntity;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Veterinarian;

/**
 *
 * @author User
 */
public class OrderConverter {
    public static Order convertToOrder(OrderEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("⚠️ La entidad OrderEntity no puede ser nula.");
        }

        return new Order(
               
                convertToDomainPet(entity.getPet()), // Convertir PetEntity a Pet
                (Owner) UserConverter.convertToDomainUser(entity.getOwner()), // Convertir UserEntity a Owner
                (Veterinarian) UserConverter.convertToDomainUser(entity.getVeterinarian()), // Convertir UserEntity a Veterinarian
                convertToDomain(entity.getMedication()), // Medicamento asociado
                entity.getDate(), // Fecha de la orden
                entity.getDetails(), // Detalles de la orden
                entity.getCompleted() // Estado completado
        );
    }
    
    public static OrderEntity convertToOrderEntity(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("⚠️ La orden no puede ser nula.");
        }

        return new OrderEntity(
                order.getId(),
                order.getDate(), // Fecha de la orden
                UserConverter.convertToUserEntity(order.getOwner()), // Convertir Owner a UserEntity
                UserConverter.convertToUserEntity(order.getVeterinarian()), // Convertir Veterinarian a UserEntity
                convertToPetEntity(order.getPet()), // Convertir Pet a PetEntity
                order.getDetails(), 
                order.isCompleted(),
                convertToEntity(order.getMedication())
        );
    }
    

   
}
