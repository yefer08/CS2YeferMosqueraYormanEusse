// src/main/java/app/domain/services/OrderService.java
package app.domain.services;

import app.domain.models.Order;
import app.domain.models.MedicationOrderItem;
import app.domain.models.Pet; // Importar Pet de dominio
import app.domain.models.User; // Importar User de dominio
import app.domain.models.MedicalHistory; // Importar MedicalHistory de dominio
import app.domain.models.Owner;
import app.domain.models.Veterinarian;
import app.exception.InvalidOrderDataException;
import app.ports.MedicalHistoryPort;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime; // Para la fecha de la orden
import java.util.Optional;

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

    public Order createOrder(Order order) { // 'order' es el objeto de dominio que viene del controller
        
        if (order == null) {
            throw new InvalidOrderDataException("Error: La orden no puede ser nula.");
        }

        // --- 1. Cargar objetos de dominio completos para las relaciones ---
        // Esto es CRÍTICO para evitar TransientPropertyValueException.
        // Recuperamos los objetos de dominio completos usando sus IDs.
        // Mascota
        Pet pet = petPort.findByidpet(order.getPet().getId()); // Asegúrate de que findById devuelva un objeto de dominio Pet
        if (pet == null) {
            throw new InvalidOrderDataException("Error: Mascota no encontrada con ID: " + order.getPet().getId());
        }
        order.setPet(pet); // <--- ASIGNA EL OBJETO PET COMPLETO Y CARGADO AL OBJETO ORDEN

        // Dueño
        User owner = userport.findById(order.getOwner().getId()); // Asumo que findById devuelve un objeto de dominio User
        if (owner == null) {
            throw new InvalidOrderDataException("Error: Dueño no encontrado con ID: " + order.getOwner().getId());
        }
        order.setOwner((Owner) owner); // <--- ASIGNA EL OBJETO OWNER COMPLETO Y CARGADO

        // Veterinario
        User veterinarian = userport.findById(order.getVeterinarian().getId());
        if (veterinarian == null) {
            throw new InvalidOrderDataException("Error: Veterinario no encontrado con ID: " + order.getVeterinarian().getId());
        }
        order.setVeterinarian((Veterinarian) veterinarian); // <--- ASIGNA EL OBJETO VETERINARIAN COMPLETO Y CARGADO

        // Historia Clínica (si es proporcionada y no nula)
        if (order.getMedicalHistory() != null && order.getMedicalHistory().getId() != null) {
            // *** CAMBIO AQUÍ ***
            System.out.println("es" + order.getMedicalHistory().getId());
            Optional<MedicalHistory> optionalMedicalHistory = medicalHistoryPort.findById(order.getMedicalHistory().getId());

            if (optionalMedicalHistory.isEmpty()) { // Usa isEmpty() para verificar si no hay valor
                throw new InvalidOrderDataException("Error: Historia clínica no encontrada con ID: " + order.getMedicalHistory().getId());
            }
            System.out.println("aca" + optionalMedicalHistory.get());
            order.setMedicalHistory(optionalMedicalHistory.get()); // Usa get() para obtener el valor del Optional
            // *** FIN CAMBIO ***
        } else {
            order.setMedicalHistory(null);
        }

        // Asegurarse de que la fecha se establezca si no viene del DTO
        if (order.getDate() == null) {
            order.setDate(LocalDateTime.now());
        }

        // 2. Realizar las validaciones de negocio finales
        validateOrder(order);

        // 3. Guardar la orden.
        Order savedOrder = orderport.save(order);
        return savedOrder;
    }


    private void validateOrder(Order order) {
        // Tu lógica de validación de los datos del objeto Order
        // Estas validaciones están correctas asumiendo que order.getPet(), etc., ya son objetos completos.
        if (order.getPet() == null || order.getPet().getId() == null) {
            throw new InvalidOrderDataException("⚠️ La orden debe estar asociada a una mascota y su ID no puede ser nulo.");
        }
        // ... (rest of your validations)
        if (order.getVeterinarian() == null || order.getVeterinarian().getId() == null) {
            throw new InvalidOrderDataException("⚠️ La orden debe tener un veterinario y su ID no puede ser nulo.");
        }
        if (order.getDate() == null) {
            throw new InvalidOrderDataException("⚠️ La fecha de la orden no puede ser nula.");
        }
        if (order.getMedicationItems() == null || order.getMedicationItems().isEmpty()) {
            throw new InvalidOrderDataException("⚠️ Una orden de medicamentos debe contener al menos un ítem de medicamento.");
        }
        for (MedicationOrderItem item : order.getMedicationItems()) {
            // Validación del nombre del medicamento
            if (item.getMedicationName() == null || item.getMedicationName().trim().isEmpty()) {
                throw new InvalidOrderDataException("⚠️ El nombre del medicamento no puede estar vacío.");
            }
            // Validación de la dosis del medicamento
            if (item.getDose() == null || item.getDose().trim().isEmpty()) {
                throw new InvalidOrderDataException("⚠️ La dosis del medicamento no puede estar vacía.");
            }
            // Validación de la cantidad del medicamento
            if (item.getQuantity() <= 0) { // Esta condición estaba anidada incorrectamente
                throw new InvalidOrderDataException("⚠️ La cantidad del medicamento debe ser mayor que cero.");
            }
            // Nota: Si necesitas validar las instrucciones, añadirías otro if aquí.
        }
    }
}
