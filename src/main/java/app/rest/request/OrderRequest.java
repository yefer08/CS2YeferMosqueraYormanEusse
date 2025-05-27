
package app.rest.request;


import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private LocalDateTime date;
    private Long ownerId;
    private Long veterinarianId;
    private String petId;
    private String details;
    private Boolean completed;
    private String medicalHistoryId;
    private List<MedicationOrderItemRequest> medicationItems; 

    public OrderRequest(LocalDateTime date, Long ownerId,
            Long veterinarianId, String petId, String details, Boolean completed, String medicalHistoryId,
            List<MedicationOrderItemRequest> medicationItems) { 

        this.date = date != null ? date : LocalDateTime.now();
        this.ownerId = ownerId;
        this.veterinarianId = veterinarianId;
        this.petId = petId;
        this.details = details;
        this.completed = completed;
        this.medicalHistoryId = medicalHistoryId;
        this.medicationItems = medicationItems;
    }
}
