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
     
        if (invoice.getIdInvoice() == null || invoice.getIdInvoice().isBlank()) {
            invoice.setIdInvoice(UUID.randomUUID().toString());
        }


        Pet petDomain = petPort.findByidpet(invoice.getPet().getId());
        if (petDomain == null) {
            throw new InvalidInvoiceDataException("❌ Error: La mascota no existe.");
        }
        invoice.setPet(petDomain);

        
        Owner ownerDomain = userPort.findByid(invoice.getOwner().getId());
        if (ownerDomain == null) {
            throw new InvalidInvoiceDataException("❌ Error: El dueño no existe.");
        }
        invoice.setOwner(ownerDomain);

      
        if (invoice.getOrder() == null || invoice.getOrder().getId() == null || invoice.getOrder().getId().isBlank()) {
            throw new InvalidInvoiceDataException("❌ Error: La factura debe estar asociada a una orden médica válida. El ID de la orden no puede ser nulo o vacío.");
        }

        Optional<Order> orderOptional = orderPort.findById(invoice.getOrder().getId());

        if (orderOptional.isEmpty()) {
            throw new InvalidInvoiceDataException("❌ Error: La orden médica proporcionada no existe en el sistema.");
        }
        invoice.setOrder(orderOptional.get()); 
        
        invoicePort.save(invoice);
        System.out.println("✅ Factura generada con éxito. ID: " + invoice.getIdInvoice());
    }
}
