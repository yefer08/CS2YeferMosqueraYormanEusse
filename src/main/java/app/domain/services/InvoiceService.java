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
import app.infrastructure.repositories.InvoiceServiceRepository;
import app.ports.Invoicesport;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Usersport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class InvoiceService {
   
     
      public InvoiceService(InvoiceServiceRepository invoiceRepository) {
    }
    @Autowired
    private PetPort petPort;
    @Autowired
    private Usersport usersport;
    @Autowired
    private Orderport orderPort;
    @Autowired 
    private Invoicesport invoiceport;
    

    public void  generateInvoice(Invoices invoice) {
        
        Pet pet = petPort.findByidpet(invoice.getIdpet());
        if (pet == null) {
            throw new InvalidInvoiceDataException("Error: La mascota asociada no puede ser nula.");
        }
        invoice.setCeduleOwner(pet.getIdOwnwer());
        Owner owner = usersport.findByid(invoice.getCeduleOwner());
        if (owner == null) {
            throw new InvalidInvoiceDataException("Error: El dueño no puede ser nulo.");
        }
        Order order = orderPort.findByorderId(invoice.getOrderid().toString());
        if (order == null) {
            throw new InvalidInvoiceDataException("Error: La orden no puede ser nula.");
        }
        invoiceport.save(invoice);
    }

}
