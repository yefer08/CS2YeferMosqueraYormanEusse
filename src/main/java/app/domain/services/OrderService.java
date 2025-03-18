/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package app.domain.services;

import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import app.exception.InvalidOrderDataException;
import app.exception.OrderNotFoundException;
import app.infrastructure.repositories.OrderServiceRepository;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Usersport;

/**
 *
 * @author User
 */
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrderService {
   
    
    @Autowired
    private PetPort petPort;
    @Autowired
    private Usersport usersport;
    @Autowired
    private Orderport orderport;

    
  
    public void createOrder(Order order) {
        
        Pet pet = petPort.findByidpet(order.getPet());
        if (pet == null) {
            throw new InvalidOrderDataException("Error: La mascota asociada no puede ser nula.");
        }
        order.setCeduleOwner(pet.getIdOwnwer());
        Veterinarian veterinarian = usersport.findByid(order.getCeduleVeterinarian());
        if ( veterinarian== null) {
            throw new InvalidOrderDataException("Error: El veterinario no puede ser nulo.");
        }
        orderport.save(order);
    }

    public void saveOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Error: La orden médica no puede ser nula.");
        }
        orderport.save(order);
    }
}
