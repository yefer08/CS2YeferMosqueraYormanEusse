/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.rest;

import app.Validator.PetValidator;
import app.domain.services.PetService;
import app.exception.InvalidDataException;
import app.rest.request.PetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/crear-mascota")
    public ResponseEntity<String> createPet(@RequestBody PetRequest request) {
        try {
        
            PetValidator.validate(
                    request.getName(),
                    request.getOwnerId(),
                    request.getAge(),
                    request.getBreed(),
                    request.getCaracteristic(),
                    request.getWeight(),
                    request.getSpecies()
            );

           
            petService.registerPet(
                    request.getName(),
                    request.getOwnerId(),
                    request.getAge(),
                    request.getIdpet(),
                    request.getBreed(),
                    request.getCaracteristic(),
                    request.getWeight(),
                    request.getSpecies()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Mascota registrada exitosamente.");
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Error inesperado: " + e.getMessage());
        }
    }
}
