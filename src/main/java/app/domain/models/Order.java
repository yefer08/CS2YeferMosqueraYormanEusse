/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Order {
    private String Orderid;
    private Pet idpet;
    private Owner CeduleOwner;
    private Veterinarian CeduleVeterinarian;
    private MedicalHistory medication;
    private LocalDateTime date;

    public Order(String Orderid, Pet idpet, Owner CeduleOwner, Veterinarian CeduleVeterinarian, MedicalHistory medication, LocalDateTime date) {
        this.Orderid = Orderid;
        this.idpet = idpet;
        this.CeduleOwner = CeduleOwner;
        this.CeduleVeterinarian = CeduleVeterinarian;
        this.medication = medication;
        this.date = date;
    }
    
    
}
