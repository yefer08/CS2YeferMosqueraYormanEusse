package app.adapter;

import app.Converted.OrderConverter; 
import app.Converted.PetConverter;   
import app.Converted.UserConverter;  
import app.Entities.InvoiceEntity;
import app.Entities.PetEntity;   
import app.Entities.UserEntity;  
import app.Entities.OrderEntity; 

import app.domain.models.Invoices;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;


import app.infrastructure.repositories.InvoiceServiceRepository;
import app.infrastructure.repositories.OrderServiceRepository; 
import app.infrastructure.repositories.PetRepository;
import app.infrastructure.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.ports.InvoicePort;


@Component
public class InvoiceAdapter implements InvoicePort {

    @Autowired
    private InvoiceServiceRepository invoiceRepository;
    @Autowired
    private PetRepository petRepository;     
    @Autowired
    private UserRepository userRepository;  
    @Autowired
    private OrderServiceRepository orderRepository; 

    @Override
    public void save(Invoices invoiceDomain) { 
        InvoiceEntity invoiceEntity = convertToEntity(invoiceDomain); 
        invoiceRepository.save(invoiceEntity);
    }

    private InvoiceEntity convertToEntity(Invoices invoiceDomain) {

        PetEntity petEntity = petRepository.findById(invoiceDomain.getPet().getId())
                .orElseThrow(() -> new RuntimeException("Error interno: PetEntity no encontrada para el ID: " + invoiceDomain.getPet().getId()));

        UserEntity ownerEntity = userRepository.findById(invoiceDomain.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("Error interno: Owner (UserEntity) no encontrado para el ID: " + invoiceDomain.getOwner().getId()));
        
        OrderEntity orderEntity = null;
        if (invoiceDomain.getOrder() != null && invoiceDomain.getOrder().getId() != null) {
            orderEntity = orderRepository.findById(invoiceDomain.getOrder().getId())
                    .orElseThrow(() -> new RuntimeException("Error interno: OrderEntity no encontrada para el ID: " + invoiceDomain.getOrder().getId()));
        }
        return new InvoiceEntity(
                petEntity,
                ownerEntity,
                orderEntity, 
                invoiceDomain.getProductName(),
                invoiceDomain.getValue(),
                invoiceDomain.getQuantity(),
                invoiceDomain.getDate()
       
        );
    }

   
    private Invoices convertToDomain(InvoiceEntity entity) {
        return new Invoices(
                
                (Pet) PetConverter.convertToDomainPet(entity.getPet()),
                (Owner) UserConverter.convertToDomainUser(entity.getOwner()),
                (Order) OrderConverter.convertToOrder(entity.getOrder()),
                entity.getProductName(),
                entity.getValue(),
                entity.getQuantity(),
                entity.getDate()
     
        );
    }
}
