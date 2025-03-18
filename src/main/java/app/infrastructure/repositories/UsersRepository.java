/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.repositories;

import app.domain.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    // Buscar un usuario por su nombre de usuario
    Optional<Users> findByUsername(String username);

    // Verificar si existe un usuario con un username específico
    boolean existsByUsername(String username);
    
    Users findById(long id);
    
}


