package app.domain.services;

import app.Converted.OrderConverter;
import app.Entities.OrderEntity;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.models.MedicalHistory;
import app.domain.models.Owner;
import app.domain.models.Veterinarian;
import app.exception.InvalidOrderDataException;
import app.infrastructure.repositories.OrderServiceRepository;
import app.ports.MedicalHistoryPort;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderService {

    private final PetPort petPort;
    private final Userport userport;
    private final Orderport orderport;
    private final MedicalHistoryPort medicalHistoryPort;

    public OrderService(PetPort petPort, Userport userport, Orderport orderport, MedicalHistoryPort medicalHistoryPort) {
        this.petPort = petPort;
        this.userport = userport;
        this.orderport = orderport;
        this.medicalHistoryPort = medicalHistoryPort;
    }
    @Autowired
    private OrderServiceRepository orderRepository; // este es el que extiende JpaRepository

    public Order createOrder(Order order) {
        if (order == null) {
            throw new InvalidOrderDataException("Error: La orden no puede ser nula.");
        }

        // Validar que la mascota exista
        if (order.getPet() == null || order.getPet().getId() == null) {
            throw new InvalidOrderDataException("Error: La mascota no puede ser nula y debe tener un ID válido.");
        }
        
        Pet pet = petPort.findByidpet(order.getPet().getId());
        if (pet == null) {
            throw new InvalidOrderDataException("Error: Mascota no encontrada con ID: " + order.getPet().getId());
        }
        order.setPet(pet);

        // Validar que el dueño exista
        if (order.getOwner() == null || order.getOwner().getId() == null) {
            throw new InvalidOrderDataException("Error: El dueño no puede ser nulo y debe tener un ID válido.");
        }
        
        User owner = userport.findById(order.getOwner().getId());
        if (owner == null) {
            throw new InvalidOrderDataException("Error: Dueño no encontrado con ID: " + order.getOwner().getId());
        }
        order.setOwner((Owner) owner);

        // Validar que el veterinario exista
        if (order.getVeterinarian() == null || order.getVeterinarian().getId() == null) {
            throw new InvalidOrderDataException("Error: El veterinario no puede ser nulo y debe tener un ID válido.");
        }
        
        User veterinarian = userport.findById(order.getVeterinarian().getId());
        if (veterinarian == null) {
            throw new InvalidOrderDataException("Error: Veterinario no encontrado con ID: " + order.getVeterinarian().getId());
        }
        order.setVeterinarian((Veterinarian) veterinarian);

        // Validar la historia clínica si existe
        if (order.getMedicalHistory() != null && order.getMedicalHistory().getId() != null) {
            Optional<MedicalHistory> optionalMedicalHistory = medicalHistoryPort.findById(order.getMedicalHistory().getId());
            if (optionalMedicalHistory.isEmpty()) {
                throw new InvalidOrderDataException("Error: Historia clínica no encontrada con ID: " + order.getMedicalHistory().getId());
            }
            order.setMedicalHistory(optionalMedicalHistory.get());
        } else {
            order.setMedicalHistory(null);
        }

        // Establecer la fecha si es nula
        if (order.getDate() == null) {
            order.setDate(LocalDateTime.now());
        }

        // Validar el resto de los campos
        validateOrder(order);

        // Guardar la orden
        return orderport.save(order);
    }

    private void validateOrder(Order order) {

        if (order.getPet() == null || order.getPet().getId() == null) {
            throw new InvalidOrderDataException("⚠️ La orden debe estar asociada a una mascota y su ID no puede ser nulo.");
        }
        if (order.getVeterinarian() == null || order.getVeterinarian().getId() == null) {
            throw new InvalidOrderDataException("⚠️ La orden debe tener un veterinario y su ID no puede ser nulo.");
        }
        if (order.getDate() == null) {
            throw new InvalidOrderDataException("⚠️ La fecha de la orden no puede ser nula.");
        }
    }
}
