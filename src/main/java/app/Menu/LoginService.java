package app.Menu;

import app.Entities.UserEntity;
import app.exception.AuthenticationException;
import app.exception.UserNotFoundException;
import app.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Component;

/*@Component
public class LoginService {

    private final UserRepository userRepository;
    //private final MenuService menuService;

    // Constructor
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
        long ownerId = "owner".equalsIgnoreCase(role) ? user.getId() : -1L;  // Usar -1 como valor predeterminado para roles no "owner"

        System.out.println("✅ Inicio de sesión exitoso. Bienvenido, " + user.getName() + "!");
       // menuService.showMenu(role, ownerId);
    }
}*/
