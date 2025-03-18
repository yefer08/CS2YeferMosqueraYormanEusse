/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Menu;

import app.domain.models.Users;
import app.exception.AuthenticationException;
import app.exception.UserNotFoundException;
import app.infrastructure.repositories.UsersRepository;

/**
 *
 * @author User
 */

import java.util.Optional;
import java.util.Scanner;

public class LoginService {
  /*  private final UsersRepository userRepository;
    private final MenuService menuService;
    private final Scanner scanner;

    public LoginService(UsersRepository userRepository, MenuService menuService, Scanner scanner) {
        this.userRepository = userRepository;
        this.menuService = menuService;
        this.scanner = scanner; // Se usa el scanner pasado como parámetro
    }

    public void login() {
        try {
            System.out.print("🔑 Ingrese su usuario: ");
            String username = scanner.nextLine().trim();
            System.out.print("🔒 Ingrese su contraseña: ");
            String password = scanner.nextLine().trim();

            // Validar que no estén vacíos
            if (username.isEmpty() || password.isEmpty()) {
                throw new AuthenticationException("⚠️ Usuario y contraseña no pueden estar vacíos.");
            }

            // Buscar usuario en la base de datos
            Optional<Users> userOptional = userRepository.findByUsername(username);
            if (userOptional.isEmpty()) {
                throw new UserNotFoundException("❌ El usuario '" + username + "' no existe.");
            }

            Users user = userOptional.get();

            // Validar la contraseña
            if (!user.getPassword().equals(password)) {
                throw new AuthenticationException("❌ Contraseña incorrecta.");
            }

            System.out.println("✅ Inicio de sesión exitoso. Bienvenido, " + user.getName() + "!");
            menuService.showMenu(user.getRole());

        } catch (AuthenticationException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️ Error inesperado en el inicio de sesión.");
        }
    }*/
}

