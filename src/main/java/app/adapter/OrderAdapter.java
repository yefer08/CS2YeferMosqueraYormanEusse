package app.adapter;

import app.Converted.MedicalHistoryConverter;
import app.Converted.UserConverter;
import app.Entities.MedicalHistoryEntity;
import app.Entities.OrderEntity;
import app.Entities.PetEntity;
import app.Entities.UserEntity;
import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import app.infrastructure.repositories.MedicalHistoryRepository;
import app.infrastructure.repositories.OrderServiceRepository;
import app.infrastructure.repositories.PetRepository;
import app.infrastructure.repositories.UserRepository;
import app.ports.Orderport;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAdapter implements Orderport {

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;  // Asegúrate de tener este repo para usuarios

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = convertToEntity(order);
        OrderEntity savedEntity = orderServiceRepository.save(orderEntity);
        return convertToDomain(savedEntity);
    }

    private OrderEntity convertToEntity(Order order) {
        if (order == null) {
            return null;
        }

        // Asegurarse de que la mascota exista en la base de datos
        PetEntity petEntity = petRepository.findById(order.getPet().getId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + order.getPet().getId()));

        // Asegurarse de que el dueño exista en la base de datos
        UserEntity ownerEntity = userRepository.findById(order.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("Dueño no encontrado con ID: " + order.getOwner().getId()));

        // Asegurarse de que el veterinario exista en la base de datos
        UserEntity veterinarianEntity = userRepository.findById(order.getVeterinarian().getId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con ID: " + order.getVeterinarian().getId()));

        MedicalHistoryEntity medicalHistoryEntity = null;
        if (order.getMedicalHistory() != null && order.getMedicalHistory().getId() != null) {
            medicalHistoryEntity = medicalHistoryRepository.findById(order.getMedicalHistory().getId())
                    .orElseThrow(() -> new RuntimeException("Historia clínica no encontrada con ID: " + order.getMedicalHistory().getId()));
        }

        return new OrderEntity(
                order.getDate(),
                ownerEntity,
                veterinarianEntity,
                petEntity,
                order.getDescription(),
                order.isCompleted(),
                medicalHistoryEntity
        );
    }

    private Order convertToDomain(OrderEntity entity) {
        if (entity == null) {
            return null;
        }

        MedicalHistory medicalHistory = null;
        if (entity.getMedicalHistory() != null) {
            medicalHistory = MedicalHistoryConverter.convertToDomain(entity.getMedicalHistory());
        }

        return new Order(
                convertToDomainPet(entity.getPet()),
                (Owner) UserConverter.convertToDomainUser(entity.getOwner()),
                (Veterinarian) UserConverter.convertToDomainUser(entity.getVeterinarian()),
                medicalHistory,
                entity.getDate(),
                entity.getDescription(),
                entity.getCompleted()
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


    @Override
    public Optional<Order> findById(String id) {
        return orderServiceRepository.findById(id).map(this::convertToDomain);
    }

    @Override
    public List<Order> findAll() {
        return orderServiceRepository.findAll().stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

}
