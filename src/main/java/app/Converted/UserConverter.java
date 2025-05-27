/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Converted;

import app.Entities.UserEntity;
import app.domain.models.Administrator;
import app.domain.models.Owner;
import app.domain.models.Seller;
import app.domain.models.Veterinarian;

/**
 *
 * @author User
 */

public class UserConverter {

    // Convierte un UserEntity al objeto de dominio correspondiente
    public static Object convertToDomainUser(UserEntity userEntity) {
        if (userEntity == null) {
            throw new IllegalArgumentException("⚠️ El objeto UserEntity no puede ser nulo.");
        }

        switch (userEntity.getRole().toLowerCase()) {
            case "veterinarian":
                return new Veterinarian(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getAge(),
                        userEntity.getUsername(),
                        userEntity.getPassword(),
                        userEntity.getRole()
                );
            case "owner":
                return new Owner(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getAge(),
                        userEntity.getUsername(),
                        userEntity.getPassword(),
                        userEntity.getRole()
                );
            case "seller":
                return new Seller(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getAge(),
                        userEntity.getUsername(),
                        userEntity.getPassword(),
                        userEntity.getRole()
                );
                
            case "admin":
                return new Administrator( 
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getAge(),
                        userEntity.getUsername(),
                        userEntity.getPassword(),
                        userEntity.getRole()
                );
                
            default:
                throw new IllegalArgumentException("⚠️ Rol no reconocido: " + userEntity.getRole());
        }
    }

    // Convierte un Owner al UserEntity para persistencia
    public static UserEntity convertToUserEntity(Owner owner) {
        if (owner == null) {
            throw new IllegalArgumentException("⚠️ El objeto Owner no puede ser nulo.");
        }

        return new UserEntity(
                owner.getId(),
                owner.getAge(),
                owner.getUsername(),
                owner.getPassword(),
                owner.getName(),
                "owner"
        );
    }

    // Convierte un Veterinarian al UserEntity para persistencia
    public static UserEntity convertToUserEntity(Veterinarian veterinarian) {
        if (veterinarian == null) {
            throw new IllegalArgumentException("⚠️ El objeto Veterinarian no puede ser nulo.");
        }

        return new UserEntity(
                veterinarian.getId(),
                veterinarian.getAge(),
                veterinarian.getUsername(),
                veterinarian.getPassword(),
                veterinarian.getName(),
                "veterinarian"
        );
    }

    // Convierte un Seller al UserEntity para persistencia
    public static UserEntity convertToUserEntity(Seller seller) {
        if (seller == null) {
            throw new IllegalArgumentException("⚠️ El objeto Seller no puede ser nulo.");
        }

        return new UserEntity(
                seller.getId(),
                seller.getAge(),
                seller.getUsername(),
                seller.getPassword(),
                seller.getName(),
                "seller"
        );
    }
    public static Veterinarian convertToVeterinarian(UserEntity userEntity) {
        if (userEntity == null) {
            throw new IllegalArgumentException("⚠️ El objeto UserEntity no puede ser nulo.");
        }

        if (!"veterinarian".equalsIgnoreCase(userEntity.getRole())) {
            throw new IllegalArgumentException("⚠️ El usuario no tiene el rol de veterinario.");
        }

        return new Veterinarian(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getAge(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRole()
        );
    }

}
