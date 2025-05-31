/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package app.rest;

import app.Converted.MedicationOrderItemRequestConverter;
import app.Converted.UserConverter;
import app.Entities.UserEntity;
import app.Validator.OrderValidator;
import app.domain.models.MedicalHistory;
import app.domain.models.MedicationOrderItem;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import app.domain.services.OrderService;
import app.exception.InvalidRoleException;
import app.exception.OrderAlreadyExistsException;
import app.ports.MedicalHistoryPort;
import app.ports.PetPort;
import app.ports.Userport;
import app.rest.request.OrderRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PetPort petport;
    @Autowired
    private Userport userport;
    @Autowired
    private MedicalHistoryPort medicalHistoryport;

    @PostMapping("/crear-ordenes")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
        try {
            OrderValidator.validate(request);

            Pet pet = petport.findByidpet(request.getPetId());
            if (pet == null) {
                throw new RuntimeException("⚠️ Mascota no encontrada con ID: " + request.getPetId());
            }

            Owner owner = userport.findByid(request.getOwnerId());
            if (owner == null) {
                throw new RuntimeException("⚠️ Dueño no encontrado con ID: " + request.getOwnerId());
            }

            UserEntity veterinarianEntity = userport.findVeterinarianById(request.getVeterinarianId())
                    .orElseThrow(() -> new RuntimeException("⚠️ Veterinario no encontrado con ID: " + request.getVeterinarianId()));

            Veterinarian veterinarian = UserConverter.convertToVeterinarian(veterinarianEntity);

            MedicalHistory medicalHistory = medicalHistoryport.findById(request.getMedicalHistoryId())
                    .orElseThrow(() -> new RuntimeException("❌ Historial médico no encontrado con ID: " + request.getMedicalHistoryId()));
            


            Order order = new Order(
                    pet,
                    owner,
                    veterinarian,
                    medicalHistory,
                    request.getDate(),
                    request.getDescription(),
                    request.getCompleted()
            );

            orderService.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Orden creada exitosamente.");

        } catch (OrderAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("❌ " + e.getMessage());
        } catch (InvalidRoleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Error inesperado: " + e.getMessage());
        }
    }

}