/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Menu;

/**
 *
 * @author User
 */
import app.domain.models.User;
import app.domain.services.AdminService;
import app.exception.InvalidRoleException;
import app.exception.UserAlreadyExistsException;
import app.ports.Userport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class AdminMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final AdminService adminService; // Cambio consistente en el nombre
    private final Userport userPort;

    @Autowired
    public AdminMenu(AdminService adminService, Userport userPort) {
        this.adminService = adminService;
        this.userPort = userPort;
    }

    public void showAdminMenu() {
        while (true) {
            System.out.println("\n--- 🔐 Menú Administrador ---");
            System.out.println("1. Crear Veterinario");
            System.out.println("2. Crear Vendedor");
            System.out.println("3. Crear Dueño de Mascota");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            try {
                int option = Integer.parseInt(scanner.nextLine().trim());

                switch (option) {
                    case 1 ->
                        registrarUsuario("veterinarian");
                    case 2 ->
                        registrarUsuario("seller");
                    case 3 ->
                        registrarUsuario("owner");
                    case 4 -> {
                        System.out.println("🔴 Sesión cerrada.");
                        return;
                    }
                    default ->
                        System.out.println("⚠️ Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Por favor, ingrese un número.");
            }
        }
    }

    private void registrarUsuario(String role) {
        System.out.println("\n📝 Creando Usuario: " + role.toUpperCase());

        System.out.print("Ingrese el nombre: ");
        String name = scanner.nextLine().trim();

        System.out.print("Ingrese la edad: ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Edad inválida. Por favor, ingrese un número.");
            return;
        }

        System.out.print("Ingrese el nombre de usuario: ");
        String username = scanner.nextLine().trim();

        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine().trim();

        try {
            // Crear el usuario utilizando el método factory en AdminService
            String id = UUID.randomUUID().toString();
            User newUser = adminService.factory(id, name, age, username, password, role);
            adminService.createUser(newUser); 

            System.out.println("✅ Usuario creado con éxito: " + name);
        } catch (UserAlreadyExistsException | InvalidRoleException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
}
