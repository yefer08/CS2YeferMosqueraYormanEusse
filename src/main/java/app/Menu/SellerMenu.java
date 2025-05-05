/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Menu;

import app.domain.models.Invoices;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.domain.services.InvoiceService;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SellerMenu {
  

    private PetPort petPort;
    private Userport Userport;
    private Orderport orderport;
    private final Scanner scanner = new Scanner(System.in);
    private final InvoiceService invoiceService; 
    
    @Autowired
    public SellerMenu(PetPort petPort, Userport Userport, Orderport orderport, InvoiceService invoiceService) {
        this.petPort = petPort;
        this.Userport = Userport;
        this.orderport = orderport;
        this.invoiceService = invoiceService;
    }
    
    public void showSellerMenu() {
        while (true) {
            System.out.println("\n--- 🛒 Menú Vendedor ---");
            System.out.println("1. Registrar venta");
            System.out.println("2. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            try {
                int option = Integer.parseInt(scanner.nextLine().trim());

                switch (option) {
                    case 1 ->
                        registrarVenta();
                    case 2 -> {
                        System.out.println("🔴 Sesión cerrada.");
                        return;
                    }
                    default ->
                        System.out.println("⚠️ Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Ingrese un número válido.");
            }
        }
    }

  
   private void registrarVenta() {
        System.out.println("\n📝 Registrando venta...");
        System.out.print("¿Es un medicamento? (si/no): ");
        String isMedication = scanner.nextLine().trim().toLowerCase();

        if (isMedication.equals("si")) {
            registrarVentaMedicamento();
        } else if (isMedication.equals("no")) {
            registrarVentaProducto();
        } else {
            System.out.println("⚠️ Respuesta inválida. Por favor, escriba 'si' o 'no'.");
        }
    }

    private void registrarVentaMedicamento() {
        System.out.print("Ingrese el ID de la mascota: ");
        String petId = scanner.nextLine().trim();
        Pet pet = petPort.findByidpet(petId);
        if (pet == null) {
            System.out.println("❌ Mascota no encontrada con ID: " + petId);
            return;
        }

        System.out.print("Ingrese el ID del dueño: ");
        long ownerId = scanner.nextLong();
        Owner owner = Userport.findByid(ownerId);
        if (owner == null) {
            System.out.println("❌ Dueño no encontrado con ID: " + ownerId);
            return;
        }

        System.out.print("Ingrese el ID de la orden médica: ");
        String orderIdInput = scanner.nextLine().trim();
        Order order = orderport.findByorderId(orderIdInput);
        if (order == null) {
            System.out.println("❌ Orden médica no encontrada.");
            return;
        }

        generarFactura(pet, owner, order, true);
    }

    private void registrarVentaProducto() {
        System.out.print("Ingrese el ID de la mascota: ");
        String petId = scanner.nextLine().trim();
        Pet pet = petPort.findByidpet(petId);
        if (pet == null) {
            System.out.println("❌ Mascota no encontrada con ID: " + petId);
            return;
        }

        System.out.print("Ingrese el ID del dueño: ");
        long ownerId = scanner.nextLong();
        Owner owner = Userport.findByid(ownerId);
        if (owner == null) {
            System.out.println("❌ Dueño no encontrado con ID: " + ownerId);
            return;
        }

        generarFactura(pet, owner, null, false);
    }

    private void generarFactura(Pet pet, Owner owner, Order order, boolean isMedication) {
        System.out.print("Ingrese el nombre del producto: ");
        String productName = scanner.nextLine().trim();

        System.out.print("Ingrese el valor del producto: ");
        double value;
        try {
            value = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Valor inválido. Intente de nuevo.");
            return;
        }

        System.out.print("Ingrese la cantidad: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Cantidad inválida. Intente de nuevo.");
            return;
        }

        
        Invoices invoice = new Invoices(UUID.randomUUID().toString(), pet, owner, order, productName, (float) value, quantity, LocalDateTime.now());

        try {
            invoiceService.generateInvoice(invoice);
            System.out.println("✅ Factura generada con éxito.");
        } catch (Exception e) {
            System.out.println("❌ Error al generar la factura: " + e.getMessage());
        }
    }

}

