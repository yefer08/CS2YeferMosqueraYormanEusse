/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Validator;

import app.rest.request.UserRequest;


public class UserValidator {
    
    public static void ValidateId(Long id){
        if (id == 0) {
           throw new IllegalArgumentException("El id del usuario no puede ser 0.");  
            
        }
    }
    // Validar nombre de usuario
    public static void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
        if (username.length() < 3) {
            throw new IllegalArgumentException("El nombre de usuario debe tener al menos 3 caracteres.");
        }
    }

    // Validar edad
    public static void validateAge(Integer age) {
        if (age == null) {
            throw new IllegalArgumentException("La edad no puede ser nula.");
        }
        if (age < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
    }

    // Validar contraseña
    public static void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }
    }

    // Validar rol
    public static void validateRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede estar vacío.");
        }

        // Validar que el rol sea uno de los roles esperados
        if (!role.equalsIgnoreCase("veterinarian") && !role.equalsIgnoreCase("seller") && !role.equalsIgnoreCase("owner")) {
            throw new IllegalArgumentException("Rol inválido. Los roles válidos son: veterinarian, seller, owner.");
        }
    }

    // Método para validar todos los campos a la vez
    public static void validate(UserRequest request) {
        ValidateId(request.getId());
        validateUsername(request.getUsername());
        validateAge(request.getAge());
        validatePassword(request.getPassword());
        validateRole(request.getRole());
    }
}
