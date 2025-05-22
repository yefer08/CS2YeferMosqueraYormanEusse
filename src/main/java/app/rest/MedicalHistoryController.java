/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.rest;

import app.Converted.UserConverter;
import app.Entities.UserEntity;
import app.Validator.MedicalHistoryValidator;
import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import app.domain.services.MedicalHistoryService;
import app.exception.InvalidDataException;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;
import app.rest.request.MedicalHistoryRequest;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/medicalHistory")
public class MedicalHistoryController {
    @Autowired
    private MedicalHistoryService medicalHistoryService;
    @Autowired
    private Userport userport;
    @Autowired
    private Orderport orderport;
    @Autowired
    private PetPort petport;
    
    @PostMapping("/crear-historiaclinica")
    public ResponseEntity<String> createMedicalHistory(@RequestBody MedicalHistoryRequest request) {
        try {
            //System.out.println("🧪 BODY RECIBIDO: " + request);
           // System.out.println("🧪 Veterinario: " + request.getVeterinarian());
            MedicalHistoryValidator.validate(request);
           // System.out.println("✅ Validación inicial completada.");
            
            //Optional<UserEntity> userEntityOpt = userport.findVeterinarianById(request.getVeterinarian());
            //System.out.println("🔍 Veterinario encontrado: " + userEntityOpt.isPresent());

            UserEntity userEntity = userport.findVeterinarianById(request.getVeterinarian())
                    .orElseThrow(() -> new InvalidDataException("⚠️ Veterinario no encontrado con ID: " + request.getVeterinarian()));
            
            //System.out.println("🔍 Veterinario UserEntity: " + userEntity.getName());

            Veterinarian veterinarian = UserConverter.convertToVeterinarian(userEntity);
            
            // System.out.println("✅ Veterinario convertido: " + veterinarian.getName());

            Order order = null;
            if (request.getOrder() != null && !request.getOrder().isEmpty()) {
                order = orderport.findByorderId(request.getOrder());
                if (order == null) {
                    throw new InvalidDataException("⚠️ Orden no encontrada.");
                }
                //System.out.println("✅ Orden encontrada: " + order.getId());
            }
            

            Pet pet = petport.findByidpet(request.getPet());
            
            //System.out.println("✅ Mascota encontrada: " + pet.getId());

            MedicalHistory medicalHistory = new MedicalHistory(
                    request.getDate(),
                    veterinarian,
                    request.getReason(),
                    request.getSymptoms(),
                    request.getDiagnosis(),
                    request.getMedicalProcedure(),
                    request.getMedication(),
                    request.getMedicationDose(),
                    order,
                    request.getVaccinationHistory(),
                    request.getAllergies(),
                    request.getProcedureDetails(),
                    request.getCanceled(),
                    pet
            );
            //System.out.println("📝 Objeto MedicalHistory creado: " + medicalHistory);


            medicalHistoryService.createMedicalHistory(medicalHistory);
           // System.out.println("✅ Historia clínica creada exitosamente.");
            

            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Historia clinica registrada exitosamente.");
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Error inesperado: " + e.getMessage());
        }
    }
}