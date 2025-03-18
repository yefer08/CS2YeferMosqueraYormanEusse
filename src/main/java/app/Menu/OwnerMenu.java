/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Menu;

import java.util.Scanner;

public class OwnerMenu {
    private final Scanner scanner = new Scanner(System.in);

    public void showOwnerMenu() {
        while (true) {
            System.out.println("\n--- 🐾 Menú Propietario ---");
            System.out.println("1. Ver historial de mascotas");
            System.out.println("2. Solicitar cita");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    System.out.println("📖 Mostrando historial de mascotas...");
                    break;
                case 2:
                    System.out.println("📅 Solicitando cita...");
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

