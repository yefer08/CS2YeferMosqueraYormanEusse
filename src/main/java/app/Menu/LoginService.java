/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Menu;

import app.Entities.UserEntity;
import app.exception.AuthenticationException;
import app.exception.UserNotFoundException;
import app.infrastructure.repositories.UserRepository;


public class LoginService {
    private final UserRepository userRepository;
    private final MenuService menuService;

    public LoginService(UserRepository userRepository, MenuService menuService) {
        this.userRepository = userRepository;
        this.menuService = menuService;
    }

    public void login(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            throw new AuthenticationException("⚠️ Usuario y contraseña no pueden estar vacíos.");
        }

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("❌ El usuario '" + username + "' no existe."));

        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("❌ Contraseña incorrecta.");
        }

        String role = user.getRole();
        String ownerId = role.equalsIgnoreCase("owner") ? user.getId() : null;
        
        System.out.println("✅ Inicio de sesión exitoso. Bienvenido, " + user.getName() + "!");
        menuService.showMenu(role, ownerId);
    }
}


