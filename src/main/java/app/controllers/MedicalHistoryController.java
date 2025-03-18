/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controllers;

import app.domain.models.MedicalHistory;
import app.domain.services.MedicalHistoryService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping("/medical-history")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @PostMapping("/save")
    public String saveMedicalHistory(@RequestBody MedicalHistory history) {
        medicalHistoryService.saveMedicalHistory(history);
        return "Historial médico guardado con éxito";
    }
}



