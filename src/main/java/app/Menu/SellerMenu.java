/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Menu;

import java.util.Scanner;

public class SellerMenu {
    private final Scanner scanner = new Scanner(System.in);

    public void showSellerMenu() {
        while (true) {
            System.out.println("\n--- 🛒 Menú Vendedor ---");
            System.out.println("1. Registrar venta");
            System.out.println("2. Generar factura");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    System.out.println("📝 Registrando venta...");
                    break;
                case 2:
                    System.out.println("📜 Generando factura...");
                    break;
                case 3:
                    System.out.println("🔴 Sesión cerrada.");
                    return;
                default:
                    System.out.println("⚠️ Opción no válida. Intente de nuevo.");
            }
        }
    }
}
