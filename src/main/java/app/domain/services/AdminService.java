/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

import app.domain.models.Owner;
import app.domain.models.Seller;
import app.domain.models.Users;
import app.domain.models.Veterinarian;
import app.exception.InvalidRoleException;
import app.exception.UserAlreadyExistsException;
import app.ports.Usersport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService {
   
    @Autowired
    private Usersport userport;
    
   public void createUser(Users user) {
    
    Users existingUser = userport.findById(user.getId());
    if (existingUser != null) {
        throw new UserAlreadyExistsException("Error: El usuario ingresado ya existe.");
    }

    
    if (user.getRole() == null || user.getRole().isEmpty()) {
        throw new InvalidRoleException("Error: El usuario debe tener un rol asignado.");
    }

    String role = user.getRole().toLowerCase();

    
    Users newUser;
    switch (role) {
        case "veterinarian" -> newUser = new Veterinarian(user.getId(), user.getName(), user.getAge(), user.getUsername(), user.getPassword(), role);
        case "seller" -> newUser = new Seller(user.getId(), user.getName(), user.getAge(), user.getUsername(), user.getPassword(), role);
        case "owner" -> newUser = new Owner(user.getId(), user.getName(), user.getAge(), user.getUsername(), user.getPassword(), role);
        default -> throw new InvalidRoleException("Error: Rol no válido. Solo se permiten 'veterinarian', 'seller' y 'owner'.");
    }

    userport.save(newUser);
}




    

   
}

