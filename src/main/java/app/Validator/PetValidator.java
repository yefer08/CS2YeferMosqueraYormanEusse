/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Validator;

import app.exception.InvalidDataException;

public class PetValidator {

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ El nombre de la mascota no puede estar vacío.");
        }
    }

    public static void validateOwnerId(Long idowner) {
        if (idowner == null) {
            throw new InvalidDataException("⚠️ El dueño de la mascota no es válido.");
        }
    }

    public static void validateAge(int age) {
        if (age <= 0) {
            throw new InvalidDataException("⚠️ La edad de la mascota debe ser mayor a cero.");
        }
    }

    public static void validateBreed(String breed) {
        if (breed == null || breed.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ La raza de la mascota no debe estar vacía.");
        }
    }

    public static void validateCharacteristic(String characteristic) {
        if (characteristic == null || characteristic.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ Las características de la mascota no deben estar vacías.");
        }
    }

    public static void validateWeight(float weight) {
        if (weight <= 0) {
            throw new InvalidDataException("⚠️ El peso de la mascota debe ser mayor a cero.");
        }
    }

    public static void validateSpecies(String species) {
        if (species == null || species.trim().isEmpty()) {
            throw new InvalidDataException("⚠️ La especie de la mascota no puede estar vacía.");
        }
    }

    // Método general para validar todo junto (ideal para requests)
    public static void validate(String name, Long idowner, int age, String breed,
            String characteristic, float weight, String species) {
        validateName(name);
        validateOwnerId(idowner);
        validateAge(age);
        validateBreed(breed);
        validateCharacteristic(characteristic);
        validateWeight(weight);
        validateSpecies(species);
    }
}
