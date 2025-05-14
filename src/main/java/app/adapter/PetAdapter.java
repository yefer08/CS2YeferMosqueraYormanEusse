package app.adapter;

import app.Converted.PetConverter;
import app.Converted.UserConverter;
import app.Entities.PetEntity;
import app.Entities.UserEntity;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.infrastructure.repositories.PetRepository;
import app.infrastructure.repositories.UserRepository;
import app.ports.PetPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetAdapter implements PetPort {

    @Autowired
    private PetRepository petRepository; // Repositorio para manejar entidades persistentes
    @Autowired
    private UserRepository userRepository;

    @Override
    public Pet findByidpet(String petId) {
        if (petId == null || petId.trim().isEmpty()) {
            throw new IllegalArgumentException("⚠️ El ID de la mascota no puede estar vacío.");
        }

        // Buscar la mascota en la base de datos
        PetEntity petEntity = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("⚠️ Mascota no encontrada con ID: " + petId));

        // Convertir PetEntity a Pet y retornar el resultado
        return convertToDomainPet(petEntity);
    }

    @Override
    public List<Pet> findByOwnerId(Long ownerId) {
        if (ownerId == null) {
            throw new IllegalArgumentException("⚠️ El ID del propietario no puede estar vacío.");
        }

        // Encuentra todas las mascotas asociadas al propietario en la base de datos
        List<PetEntity> petEntities = petRepository.findByOwnerId(ownerId);

        if (petEntities.isEmpty()) {
            throw new RuntimeException("⚠️ No se encontraron mascotas para el propietario con ID: " + ownerId);
        }

        // Convierte cada PetEntity a un objeto de dominio Pet y retorna la lista
        return petEntities.stream()
                .map(this::convertToDomainPet)
                .collect(Collectors.toList());
    }

    // Método auxiliar para convertir PetEntity a Pet
    private Pet convertToDomainPet(PetEntity petEntity) {
        if (petEntity == null) {
            return null;
        }

        Owner owner = petEntity.getOwner() != null ? (Owner) UserConverter.convertToDomainUser(petEntity.getOwner()) : null;

        return new Pet(
                petEntity.getName(), // Nombre de la mascota
                owner, // Convertir Owner si no es nulo
                petEntity.getAge(),
                petEntity.getId(),
                petEntity.getBreed(),
                petEntity.getCaracteristic(),
                petEntity.getWeight(),
                petEntity.getSpecies()
        );
    }

    @Override
    public void save(Pet pet) {
        PetEntity petEntity = PetConverter.convertToPetEntity(pet);

        // 💡 Aquí está la magia:
        if (pet.getIdOwnwer() != null && pet.getIdOwnwer().getId() != null) {
            UserEntity ownerEntity = userRepository.findById(pet.getIdOwnwer().getId())
                    .orElseThrow(() -> new RuntimeException("❌ Dueño no encontrado con ID: " + pet.getIdOwnwer().getId()));

            petEntity.setOwner(ownerEntity); // Asignas el dueño persistido
        }

        petRepository.save(petEntity);
    }

    @Override
    public void saveEntity(PetEntity petEntity) {
        petRepository.save(petEntity);
    }

}
