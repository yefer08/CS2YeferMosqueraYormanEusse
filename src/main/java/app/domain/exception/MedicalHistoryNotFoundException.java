/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.exception;

/**
 *
 * @author User
 */
public class MedicalHistoryNotFoundException extends RuntimeException {
    
    public MedicalHistoryNotFoundException(String recordId) {
        super("No se encontró la historia médica con ID: " + recordId);
    }
}
