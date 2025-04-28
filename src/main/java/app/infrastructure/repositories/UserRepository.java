/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.repositories;

import app.Entities.UserEntity;
import app.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {


    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    public void save(User user);

    @Override
    public Optional<UserEntity> findById(String id);

    @Override
    public boolean existsById(String id);

    @Override
    public void deleteById(String id);

}


