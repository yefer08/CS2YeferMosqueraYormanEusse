/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.exception;

public class OrderAlreadyExistsException extends RuntimeException {

    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}
