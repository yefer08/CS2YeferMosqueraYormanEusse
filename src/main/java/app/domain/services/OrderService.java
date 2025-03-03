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
import app.infrastructure.repositories.OrderServiceRepository;

/**
 *
 * @author User
 */
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderService {
    private final OrderServiceRepository orderRepository; 
    
    public OrderService(OrderServiceRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Pet idPet, Owner ceduleOwner, Veterinarian ceduleVeterinarian, MedicalHistory medication) {
        String orderId = UUID.randomUUID().toString();  // Generar un ID único
        LocalDateTime date = LocalDateTime.now();       // Fecha actual

        Order order = new Order(orderId, idPet, ceduleOwner, ceduleVeterinarian, medication, date);
        orderRepository.save(order); // Guardar en el repositorio

        System.out.println("Orden creada con ID: " + order.getOrderId());
        return order;
    }
    public Order findOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }
}
