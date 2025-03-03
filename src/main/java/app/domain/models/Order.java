/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    private String orderId;  
    private Pet idpet;
    private Owner CeduleOwner;
    private Veterinarian CeduleVeterinarian;
    private MedicalHistory medication;
    private LocalDateTime date;

    
}
