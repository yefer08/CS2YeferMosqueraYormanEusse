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
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class InvoiceValidator {

    @Autowired
    private static PetPort petPort;

    @Autowired
    private static Userport userPort;

    @Autowired
    private static Orderport orderPort;

    public static void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new InvalidInvoiceDataException("⚠ El ID de la factura no puede estar vacío.");
        }
    }

    public static void validateDate(LocalDate date) {
        if (date == null) {
            throw new InvalidInvoiceDataException("⚠ La fecha no puede estar vacía.");
        }
    }

    public static void validateOwner(Long owner) {
        if (owner == null) {
            throw new InvalidInvoiceDataException("⚠ El dueño no puede estar vacío.");
        }
    }

    public static void validatePet(String pet) {
        if (pet == null) {
            throw new InvalidInvoiceDataException("⚠ La mascota no puede estar vacía.");
        }
    }

    public static void validateOrder(String order) {
        if (order == null) {
            throw new InvalidInvoiceDataException("⚠ La orden médica no puede estar vacía.");
        }
    }

    public static void validate(InvoiceRequest invoiceRequest) {
        validateId(invoiceRequest.getIdInvoice());
        validateDate(invoiceRequest.getDate().toLocalDate());
        validateOwner(invoiceRequest.getOwner());
        validatePet(invoiceRequest.getPet());

        if (invoiceRequest.getOrder() != null) {
            validateOrder(invoiceRequest.getOrder());
        }

        // 🔹 Generar ID automáticamente si es nulo
        if (invoiceRequest.getIdInvoice() == null || invoiceRequest.getIdInvoice().isBlank()) {
            invoiceRequest.setIdInvoice(UUID.randomUUID().toString());
        }

        // 🔹 Validar existencia de la mascota
        Pet pet = petPort.findByidpet(invoiceRequest.getPet());
        if (pet == null) {
            throw new InvalidInvoiceDataException("❌ Error: La mascota no existe.");
        }

        // 🔹 Asignar ID del dueño a la factura
        invoiceRequest.setOwner(pet.getIdOwnwer().getId());

        // 🔹 Validar existencia del dueño
        Owner owner = userPort.findByid(invoiceRequest.getOwner());
        if (owner == null) {
            throw new InvalidInvoiceDataException("❌ Error: El dueño no existe.");
        }

        // 🔹 Validar orden médica SOLO si es medicamento
        if (invoiceRequest.getOrder() != null) {
            Order order = orderPort.findByorderId(invoiceRequest.getOrder());
            if (order == null) {
                throw new InvalidInvoiceDataException("❌ Error: La orden médica no existe.");
            }
        }
    }
}
