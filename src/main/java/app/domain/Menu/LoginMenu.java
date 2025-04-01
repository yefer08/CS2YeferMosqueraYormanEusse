/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package app.Menu;

import app.exception.AuthenticationException;
import app.exception.UserNotFoundException;
import java.util.Scanner;

public class LoginMenu {
    private final LoginService loginService;
    private final Scanner scanner = new Scanner(System.in);

    public LoginMenu(LoginService loginService) {
        this.loginService = loginService;
    }

    public void showLoginMenu() {
        while (true) {
            System.out.println("\n--- 🔑 Inicio de Sesión ---");
            System.out.print("Usuario: ");
            String username = scanner.nextLine().trim();

            System.out.print("Contraseña: ");
            String password = scanner.nextLine().trim();

            try {
                loginService.login(username, password);
                break; 
            } catch (AuthenticationException | UserNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
