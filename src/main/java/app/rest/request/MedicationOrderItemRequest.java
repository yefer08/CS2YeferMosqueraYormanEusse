/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationOrderItemRequest {

    private String id;
    private String medicationName;
    private String dose;
    private int quantity; 
    private String instructions;

    public MedicationOrderItemRequest(String id, String medicationName, String dose, int quantity, String instructions) {
        this.id = id;
        this.medicationName = medicationName;
        this.dose = dose;
        this.quantity = quantity;
        this.instructions = instructions;
    }
    
    
}
