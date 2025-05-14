/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.rest;

import app.Validator.InvoiceValidator;
import app.domain.models.Invoices;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.domain.services.InvoiceService;
import app.exception.InvalidInvoiceDataException;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;
import app.rest.request.InvoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    
    @Autowired
    private PetPort petPort;
    @Autowired
    private Userport userPort;

    @Autowired
    private Orderport orderPort;


    @PostMapping("/crear-factura")
    public ResponseEntity<String> createInvoice(@RequestBody InvoiceRequest request) {
        try {
            InvoiceValidator.validate(request);
             Pet pet = petPort.findByidpet(request.getPet());
            Owner owner = userPort.findByid(request.getOwner());
            Order order = orderPort.findByorderId(request.getOrder());

            Invoices invoice = new Invoices(
                    request.getIdInvoice(),
                    pet,
                    owner,
                    order,
                    request.getProductName(),
                    request.getValue(),
                    request.getQuantity(),
                    request.getDate()
                
            );

            invoiceService.generateInvoice(invoice);
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Factura creada exitosamente.");

        } catch (InvalidInvoiceDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Error inesperado: " + e.getMessage());
        }
    }
}
