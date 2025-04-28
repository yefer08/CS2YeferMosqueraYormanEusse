/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

import app.domain.models.Administrator;
import app.domain.models.User;
import app.ports.Userport;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final Userport userport;

    public DataInitializer(Userport userport) {
        this.userport = userport;
    }

    @Override
    @Transactional
    public void run(String... args) {
        String adminUsername = "Admin"; 
        User existingAdmin = userport.findByUsername(adminUsername);

        if (existingAdmin == null) {
            Administrator admin = new Administrator(
                    "",
                    "carlos",
                    30,
                    "Admin",
                    "123", 
                    "Admin"
            );

            userport.save(admin);
            System.out.println("✅ Administrador creado automáticamente.");
        } else {
            System.out.println("ℹ️ El administrador ya existía, no se creó otro.");
        }
    }
}
