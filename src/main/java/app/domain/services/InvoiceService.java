/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package app.domain.services;

import app.domain.models.Invoices;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.exception.InvalidInvoiceDataException;
import app.ports.Orderport;
import app.ports.PetPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.ports.Userport;
import app.ports.InvoicePort;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author User
 */
@Service
public class InvoiceService {

    private final InvoicePort invoicePort;
    private final PetPort petPort;
    private final Userport userPort;
    private final Orderport orderPort;

    @Autowired
    public InvoiceService(InvoicePort invoicePort, PetPort petPort, Userport userPort, Orderport orderPort) {
        this.invoicePort = invoicePort;
        this.petPort = petPort;
        this.userPort = userPort;
        this.orderPort = orderPort;
    }

    public void generateInvoice(Invoices invoice) {
        // 🔹 Generar ID automáticamente si es nulo
        if (invoice.getIdInvoice() == null || invoice.getIdInvoice().isBlank()) {
            invoice.setIdInvoice(UUID.randomUUID().toString());
        }

        // 🔹 Validar existencia de la mascota
        Pet pet = petPort.findByidpet(invoice.getPet().getId());
        if (pet == null) {
            throw new InvalidInvoiceDataException("❌ Error: La mascota no existe.");
        }

        // 🔹 Asignar ID del dueño a la factura
        invoice.setOwner(pet.getIdOwnwer());

        // 🔹 Validar existencia del dueño
        Owner owner = userPort.findByid(invoice.getOwner().getId());
        if (owner == null) {
            throw new InvalidInvoiceDataException("❌ Error: El dueño no existe.");
        }

        // 🔹 Validar orden médica SOLO si es medicamento
        if (invoice.getOrder() != null && invoice.getOrder().getId() != null) {
            Optional<Order> order = orderPort.findById(invoice.getOrder().getId());

            if (order.isEmpty()) {
                throw new InvalidInvoiceDataException("❌ Error: La orden médica no existe.");
            }

            // Opcional: podrías setear la orden completa si la necesitas luego
            invoice.setOrder(order.get());
        }


        // 🔹 Guardar la factura
        invoicePort.save(invoice);
        System.out.println("✅ Factura generada con éxito. ID: " + invoice.getIdInvoice());
    }
}
