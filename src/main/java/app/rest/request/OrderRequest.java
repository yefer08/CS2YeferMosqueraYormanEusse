
package app.rest.request;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private LocalDateTime date;
    private Long ownerId; //replace
    private Long veterinarianId;
    private String petId; //replace
    private String details;
    private Boolean completed;
    private String medicalHistoryId; //replace

    public OrderRequest(LocalDateTime date, Long ownerId, 
            Long veterinarianId, String petId, String details, Boolean completed, String medicalHistoryId) {
     
        this.date =  LocalDateTime.now();
        this.ownerId = ownerId;
        this.veterinarianId = veterinarianId;
        this.petId = petId;
        this.details = details;
        this.completed = completed;
        this.medicalHistoryId = medicalHistoryId;
    }

  
}