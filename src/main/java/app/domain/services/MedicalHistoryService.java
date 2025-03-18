/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import app.exception.InvalidDataException;
import app.ports.MedicalHistoryport;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Usersport;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MedicalHistoryService {
    
    @Autowired
    private PetPort petPort;
    @Autowired
    private Orderport orderPort;
    @Autowired
    private MedicalHistoryport medicalHistoryPort;
    @Autowired
    private Usersport userport;

    


    public void createMedicalHistory(MedicalHistory history) {
        Pet pet = petPort.findByidpet(history.getPet());

        if (pet == null) {
            throw new InvalidDataException("ID de mascota no encontrado");
        }
        Veterinarian veterinarian = userport.findByid(history.getVeterinarian());
        if (veterinarian == null) {
            throw new InvalidDataException("Ingrese la información del veterinario.");
        }
        
        Order order = orderPort.findByorderId(history.getOrder().toString());
        if (order ==null) {
            throw  new InvalidDataException("orden no encontrada");
        }
       medicalHistoryPort.save(history);
    }

     public void saveMedicalHistory(MedicalHistory history) {
        if (history == null) {
            throw new IllegalArgumentException("Error: La historia médica no puede ser nula.");
        }
        medicalHistoryPort.save(history);

     } 

}

