/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.repositories;

import app.Entities.PetEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PetRepository  extends JpaRepository<PetEntity,String> {

    public List<PetEntity> findByOwnerId(String ownerId);
    
}
