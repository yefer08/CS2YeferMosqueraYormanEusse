/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.adapter;

import app.Converted.OrderConverter;
import app.Converted.PetConverter;
import app.Converted.UserConverter;
import app.Entities.InvoiceEntity;
import app.domain.models.Invoices;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.infrastructure.repositories.InvoiceServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.ports.InvoicePort;

@Component
public class InvoiceAdapter implements InvoicePort {

    @Autowired
    private InvoiceServiceRepository invoiceRepository;

    @Override
    public void save(Invoices invoice) {
        InvoiceEntity invoiceEntity = convertToEntity(invoice);
        invoiceRepository.save(invoiceEntity);
    }

    private InvoiceEntity convertToEntity(Invoices invoice) {
        return new InvoiceEntity(
            PetConverter.convertToPetEntity(invoice.getPet()),
            UserConverter.convertToUserEntity(invoice.getOwner()),
            OrderConverter.convertToOrderEntity(invoice.getOrder()),
            invoice.getProductName(),
            invoice.getValue(),
            invoice.getQuantity(),
            invoice.getDate()
        );
    }

    private Invoices convertToDomain(InvoiceEntity entity) {
        return  new Invoices(
         
            (Pet)PetConverter.convertToDomainPet(entity.getPet()),
            (Owner)UserConverter.convertToDomainUser(entity.getOwner()),
            (Order)OrderConverter.convertToOrder(entity.getOrder()),
            entity.getProductName(),
            entity.getValue(),
            entity.getQuantity(),
            entity.getDate()
        );
    }
}

