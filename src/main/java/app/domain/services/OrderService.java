/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package app.domain.services;



import app.Converted.UserConverter;
import app.Entities.UserEntity;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import app.exception.InvalidOrderDataException;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
   
    @Autowired
    private PetPort petPort;
    
    @Autowired
    private Userport userport;
    
    @Autowired
    private Orderport orderport;

    public void createOrder(Order order) {
        if (order == null) {
            throw new InvalidOrderDataException("Error: La orden no puede ser nula.");
        }

        // Buscar la mascota por ID (revisar el nombre del método)
        Pet pet = petPort.findByidpet(order.getPet().getId());
        if (pet == null) {
            throw new InvalidOrderDataException("Error: La mascota asociada no puede ser nula.");
        }

        // Asignar el dueño de la mascota a la orden
        order.setOwner(pet.getIdOwnwer());

        // Buscar veterinario por ID
        UserEntity userEntity = userport.findVeterinarianById(order.getVeterinarian().getId())
                .orElseThrow(() -> new InvalidOrderDataException("Error: El veterinario no puede ser nulo."));

        Veterinarian veterinarian = UserConverter.convertToVeterinarian(userEntity);


        // Guardar la orden
        orderport.save(order);
    }

}

