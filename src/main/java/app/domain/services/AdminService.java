/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

import app.domain.models.Owner;
import app.domain.models.Seller;
import app.domain.models.User;
import app.domain.models.Veterinarian;
import app.exception.InvalidRoleException;
import app.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.ports.Userport;

@Service
public class AdminService {

    @Autowired
    private Userport userport;

    public void createUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }

        User existingUser = userport.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("Error: El usuario ingresado ya existe.");
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            throw new InvalidRoleException("Error: El usuario debe tener un rol asignado.");
        }

        String role = user.getRole().toLowerCase();
        User newUser = factory(user.getName(), user.getAge(), user.getUsername(), user.getPassword(), role);

        userport.save(newUser);
    }

    
    public User factory(String name, int age, String username, String password, String role) {
        return switch (role.toLowerCase()) {
            case "veterinarian" -> new Veterinarian( name, age, username, password, role);
            case "seller" -> new Seller( name, age, username, password, role);
            case "owner" -> new Owner( name, age, username, password, role);
            default -> throw new InvalidRoleException("Error: Rol no válido.");
        };
    }
}


