package app.Validator;

import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.exception.InvalidInvoiceDataException;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;
import app.rest.request.InvoiceRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Component
public class InvoiceValidator {

    private final PetPort petPort;
    private final Userport userPort;
    private final Orderport orderPort;

    // 🔹 Inyección por constructor (Spring lo hace automáticamente)
    public InvoiceValidator(PetPort petPort, Userport userPort, Orderport orderPort) {
        this.petPort = petPort;
        this.userPort = userPort;
        this.orderPort = orderPort;
    }

    public void validate(InvoiceRequest invoiceRequest) {

        validateDate(invoiceRequest.getDate().toLocalDate());
        validateOwner(invoiceRequest.getOwner());
        validatePet(invoiceRequest.getPet());

        if (invoiceRequest.getOrder() != null) {
            validateOrder(invoiceRequest.getOrder());
        }

        if (invoiceRequest.getIdInvoice() == null || invoiceRequest.getIdInvoice().isBlank()) {
            invoiceRequest.setIdInvoice(UUID.randomUUID().toString());
        }
        Pet pet = petPort.findByidpet(invoiceRequest.getPet());
        if (pet == null) {
            throw new InvalidInvoiceDataException("❌ Error: La mascota no existe.");
        }
        
        invoiceRequest.setOwner(pet.getIdOwnwer().getId());

        Owner owner = userPort.findByid(invoiceRequest.getOwner());
        if (owner == null) {
            throw new InvalidInvoiceDataException("❌ Error: El dueño no existe.");
        }

     
        if (invoiceRequest.getOrder() != null && !invoiceRequest.getOrder().isEmpty()) {
            Optional<Order> optionalOrder = orderPort.findById(invoiceRequest.getOrder());
            if (optionalOrder.isEmpty()) {
                throw new InvalidInvoiceDataException("❌ Error: La orden médica no existe.");
            }
        }
    }


    private void validateDate(LocalDate date) {
        if (date == null) {
            throw new InvalidInvoiceDataException("⚠ La fecha no puede estar vacía.");
        }
    }

    private void validateOwner(Long owner) {
        if (owner == null) {
            throw new InvalidInvoiceDataException("⚠ El dueño no puede estar vacío.");
        }
    }

    private void validatePet(String pet) {
        if (pet == null) {
            throw new InvalidInvoiceDataException("⚠ La mascota no puede estar vacía.");
        }
    }

    private void validateOrder(String order) {
        if (order == null) {
            throw new InvalidInvoiceDataException("⚠ La orden médica no puede estar vacía.");
        }
    }
}
