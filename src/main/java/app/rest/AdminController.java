package app.rest;

import app.Validator.UserValidator;
import app.domain.models.User;
import app.domain.services.AdminService;
import app.exception.InvalidRoleException;
import app.exception.UserAlreadyExistsException;
import app.rest.request.UserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pong");
    }

    // GET para verificar el estado de la aplicación
    @GetMapping("/is-alive")
    public ResponseEntity<String> isAlive() {
       
        return ResponseEntity.ok("✅ Yes I live.");
    }

    @PostMapping("/crear-usuario")
    public ResponseEntity<String> createUser(@RequestBody UserRequest request) {
        try {
            UserValidator.validate(request);
            
            User user = adminService.factory(
                    request.getName(),
                    request.getAge(),
                    request.getUsername(),
                    request.getPassword(),
                    request.getRole()
            );
            adminService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Usuario creado exitosamente.");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("❌ " + e.getMessage());
        } catch (InvalidRoleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Error inesperado: " + e.getMessage());
        }
    }



}
