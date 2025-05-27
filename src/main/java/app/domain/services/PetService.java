package app.domain.services;

import app.Converted.PetConverter;
import app.Entities.PetEntity;
import app.Entities.UserEntity;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.exception.InvalidDataException;
import app.ports.PetPort;
import app.ports.Userport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private Userport userport;

    @Autowired
    private PetPort petPort;

    public void registerPet(String namepet, long ownerId, int agepet, String idpet, String racepet,
            String caracteristic, float weight, String species) {

        // 🔍 Validaciones básicas
        if (namepet == null || namepet.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ El nombre de la mascota no puede estar vacío.");
        }
        if (agepet <= 0 || agepet > 150) {
            throw new InvalidDataException("⚠️ La edad de la mascota debe estar entre 1 y 150 años.");
        }
        if (racepet == null || racepet.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ La raza de la mascota no debe estar vacía.");
        }
        if (caracteristic == null || caracteristic.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ Las características de la mascota no deben estar vacías.");
        }
        if (weight <= 0 || weight > 200) {
            throw new InvalidDataException("⚠️ El peso de la mascota debe estar entre 0.1 y 200 kg.");
        }
        if (species == null || species.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ La especie de la mascota no puede estar vacía.");
        }

        // 🧠 Buscar Owner desde la base
        Owner owner = userport.findByid(ownerId);
        if (owner == null) {
            throw new InvalidDataException("❌ No se encontró un dueño con el ID proporcionado.");
        }

        // 🐶 Crear el modelo de dominio (Pet)
        Pet pet = new Pet(
                namepet,
                owner,
                agepet,
                idpet,
                racepet,
                caracteristic,
                weight,
                species
        );

        // 🏗️ Convertir a entidad sin el owner seteado aún
        PetEntity petEntity = PetConverter.convertToPetEntity(pet);

        // 🔁 Buscar el UserEntity persistido (el dueño real en la BD)
        UserEntity ownerEntity = userport.findEntityById(ownerId);
        if (ownerEntity == null) {
            throw new InvalidDataException("❌ No se encontró el UserEntity persistido.");
        }

        // 🔗 Asignar el owner persistido a la entidad
        petEntity.setOwner(ownerEntity);

    
        petPort.saveEntity(petEntity);

        System.out.println("✅ Mascota registrada exitosamente.");
    }
}
