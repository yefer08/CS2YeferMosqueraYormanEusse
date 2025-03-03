/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.repositories;

import app.domain.models.Invoices;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class InvoiceServiceRepository {
    private List<Invoices> invoices = new ArrayList<>();
    private int invoiceCounter = 1;
    
    public void save(Invoices invoice) {
        invoices.add(invoice);
    }
    
    public Invoices findById(String id) {
    return invoices.stream()
                         .filter(r -> r.getIdInvoice().equals(id))  // Filtra por ID
                         .findFirst()                        // Toma el primer resultado encontrado
                         .orElse(null);                      // Si no encuentra nada, devuelve null
    
    
    
    }
    public List<Invoices>findAll(){
        return new ArrayList<>(invoices);
    }
    
    
}
