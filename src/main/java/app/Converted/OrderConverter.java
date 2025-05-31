package app.Converted;

import static app.Converted.PetConverter.convertToDomainPet;
import static app.Converted.MedicalHistoryConverter.convertToDomain;
import static app.Converted.MedicalHistoryConverter.convertToEntity;
import static app.Converted.PetConverter.convertToPetEntity;
import app.Entities.OrderEntity;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Veterinarian;

public class OrderConverter {

    public static Order convertToOrder(OrderEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("⚠️ La entidad OrderEntity no puede ser nula.");
        }

        return new Order(
                convertToDomainPet(entity.getPet()),
                (Owner) UserConverter.convertToDomainUser(entity.getOwner()),
                (Veterinarian) UserConverter.convertToDomainUser(entity.getVeterinarian()),
                convertToDomain(entity.getMedicalHistory()),
                entity.getDate(),
                entity.getDescription(),
                entity.getCompleted()
        );
    }

    public static OrderEntity convertToOrderEntity(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("⚠️ La orden no puede ser nula.");
        }

        OrderEntity orderEntity = new OrderEntity(
                order.getDate(),
                UserConverter.convertToUserEntity(order.getOwner()),
                UserConverter.convertToUserEntity(order.getVeterinarian()),
                convertToPetEntity(order.getPet()),
                order.getDescription(),
                order.isCompleted(),
                order.getMedicalHistory() != null
                ? convertToEntity(order.getMedicalHistory())
                : null
        );

        return orderEntity;
    }
}
