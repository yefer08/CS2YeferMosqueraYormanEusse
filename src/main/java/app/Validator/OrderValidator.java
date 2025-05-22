
package app.Validator;

import app.exception.InvalidDataException;
import app.rest.request.OrderRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class OrderValidator {
    
   
    public static void validatedate(LocalDateTime dateTime) {
        if (dateTime == null) {
            dateTime = LocalDateTime.now();
        }
        if (dateTime == null) {
            throw new InvalidDataException("⚠ la fecha no puede estar vacía");
        }
    }
    public static void validateOwner(Long owner){
        if (owner == null) {
            throw new InvalidDataException("⚠ el dueño no puede estar vacia");
            
        }
        
    }
    public static void validateVeterinarian(Long veterinarian){
        if (veterinarian == null) {
            throw new InvalidDataException("⚠ el veterinario no puede estar vacia");
            
        }
        
    }
    public static void validatepet(String pet){
        if (pet == null) {
            throw new InvalidDataException("⚠ la mascota no puede estar vacia");
            
        }
        
    }
    public static void validatemeidicalHistory(String medicalhistory){
        if (medicalhistory == null) {
            throw new InvalidDataException("⚠ la historia clinica no puede estar vacia");
            
        }
        
    }
    public static void validate(OrderRequest orderRequest){
     
        if (orderRequest.getDate() == null) {
            orderRequest.setDate(LocalDateTime.now());  // Asignar la fecha de hoy si es nula
        }

        validatedate(orderRequest.getDate());
        validateOwner(orderRequest.getOwnerId());
        validateVeterinarian(orderRequest.getVeterinarianId());
        validatepet(orderRequest.getPetId());
        validatemeidicalHistory(orderRequest.getMedicalHistoryId());
  
    }
    
    
    
}
