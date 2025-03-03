/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package app.domain.services;

import app.domain.models.Invoices;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.infrastructure.repositories.InvoiceServiceRepository;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author User
 */
public class InvoiceService {
   
     private final InvoiceServiceRepository invoiceRepository;
     
      public InvoiceService(InvoiceServiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoices generateInvoice(Pet idpet, Owner ceduleOwner,Order Orderid,String nameproducr, float price, int quantity) {
        String invoiceId = UUID.randomUUID().toString();  
        LocalDateTime date = LocalDateTime.now();   
    
        Invoices invoice = new Invoices(invoiceId, idpet, ceduleOwner, Orderid, nameproducr, price, quantity, date);
         invoiceRepository.save(invoice);
        System.out.println("Factura generada con ID: " + invoice.getIdInvoice());
        return invoice; 
    }

}
