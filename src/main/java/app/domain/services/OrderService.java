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
import app.infrastructure.repositories.OrderServiceRepository;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;

import java.time.LocalDateTime;
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

        // Buscar la mascota por ID
        Pet pet = petPort.findByidpet(order.getPet().getId());
        if (pet == null) {
            throw new InvalidOrderDataException("Error: La mascota asociada no puede ser nula.");
        }

        // Asignar el dueño de la mascota a la orden
        order.setOwner(pet.getIdOwnwer());  // Corregido getIdOwner()

        // Buscar veterinario por ID
        Veterinarian veterinarian = userport.findVeterinarianById(order.getVeterinarian().getId());
        if (veterinarian == null) {
            throw new InvalidOrderDataException("Error: El veterinario no puede ser nulo.");
        }

        // Guardar la orden
        orderport.save(order);
    }

    public void saveOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Error: La orden médica no puede ser nula.");
        }
        orderport.save(order);
    }
}

