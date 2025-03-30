/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Converted;

import app.Entities.PetEntity;
import app.domain.models.Owner;
import app.domain.models.Pet;

/**
 *
 * @author User
 */
public class PetConverter {
    public static Pet convertToDomainPet(PetEntity petEntity) {
        if (petEntity == null) {
            return null; // Manejo de nulos para evitar excepciones
        }

        return new Pet(
                petEntity.getName(), // Nombre de la mascota
                petEntity.getOwner() != null ? (Owner) UserConverter.convertToDomainUser(petEntity.getOwner()) : null, // Convertir UserEntity a Owner
                petEntity.getAge(), // Edad de la mascota
                petEntity.getId(), // ID único de la mascota
                petEntity.getBreed(), // Raza de la mascota
                petEntity.getCaracteristic(), // Características adicionales
                petEntity.getWeight(), // Peso de la mascota
                petEntity.getSpecies() // Especie de la mascota
        );
    }
    
    public static PetEntity convertToPetEntity(Pet pet) {
        if (pet == null) {
            return null; // Manejo de casos nulos para evitar excepciones
        }

        return new PetEntity(
                pet.getId(), // ID único de la mascota
                pet.getNamepet(), // Nombre de la mascota
                pet.getSpecies(), // Especie de la mascota
                pet.getRacepet(), // Raza
                pet.getAgepet(), // Edad de la mascota
                pet.getCaracteristic(), // Características adicionales
                pet.getWeight(), // Peso de la mascota
                UserConverter.convertToUserEntity(pet.getIdOwnwer()) // Convertir Owner a UserEntity
        );
    }
    
}
