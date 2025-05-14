
package app.Validator;

import app.exception.InvalidDataException;
import app.rest.request.OrderRequest;
import java.time.LocalDate;


public class OrderValidator {
    
   
    public static void validatedate(LocalDate date) {
        if (date == null) {
            throw new InvalidDataException("⚠ la fecha no puede estar vacia");

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
     
        validatedate(orderRequest.getDate().toLocalDate());
        validateOwner(orderRequest.getOwnerId());
        validateVeterinarian(orderRequest.getVeterinarianId());
        validatepet(orderRequest.getPetId());
        validatemeidicalHistory(orderRequest.getMedicalHistoryId());
  
    }
    
    
    
}
