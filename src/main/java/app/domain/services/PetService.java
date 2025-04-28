/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

import app.domain.models.Owner;
import app.domain.models.Pet;
import app.exception.InvalidDataException;
import app.ports.PetPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PetService {

    @Autowired
    private PetPort petPort;

    public void registerPet(String namepet, Owner owner, int agepet, String racepet,
            String caracteristic, float weight, String species) {

        // Validaciones
        if (namepet == null || namepet.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ El nombre de la mascota no puede estar vacío.");
        }

        if (owner == null || owner.getId() == null) {
            throw new InvalidDataException("⚠️ El dueño de la mascota no es válido.");
        }

        if (agepet <= 0) {
            throw new InvalidDataException("⚠️ La edad de la mascota debe ser mayor a cero.");
        }

        if (weight <= 0) {
            throw new InvalidDataException("⚠️ El peso de la mascota debe ser mayor a cero.");
        }

        if (species == null || species.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ La especie de la mascota no puede estar vacía.");
        }

        // Crear la mascota nueva
        Pet pet = new Pet(
                namepet, 
                owner, 
                agepet, 
                racepet,
                racepet, 
                caracteristic, 
                weight, 
                species
        );

        // Guardar en la base de datos
        petPort.save(pet);

        System.out.println("✅ Mascota registrada exitosamente.");
    }
}
