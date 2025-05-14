/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ports;

import app.Entities.PetEntity;
import app.domain.models.Pet;
import java.util.List;

/**
 *
 * @author User
 */
public interface PetPort {


    public Pet findByidpet(String petId);

    public List<Pet> findByOwnerId(Long ownerId);

    public void save(Pet pet);

    public void saveEntity(PetEntity petEntity);
    
}
