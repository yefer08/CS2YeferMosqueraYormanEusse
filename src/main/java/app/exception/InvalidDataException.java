/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.exception;

/**
 *
 * @author User
 */
public class InvalidDataException extends RuntimeException {
    
    
    public InvalidDataException(String message) {
        super("Invalid Data: " + message);
    }
}
