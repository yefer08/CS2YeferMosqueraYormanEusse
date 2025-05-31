// app.rest.request.OrderRequest
package app.rest.request;

import java.time.LocalDateTime;
import java.util.List;


public class OrderRequest {

    private LocalDateTime date;
    private Long ownerId;
    private Long veterinarianId;
    private String petId;
    private String description; 
    private Boolean completed;
    private String medicalHistoryId;
    private List<MedicationOrderItemRequest> medicationItems;

    public OrderRequest(LocalDateTime date, Long ownerId,
            Long veterinarianId, String petId, String description, Boolean completed, String medicalHistoryId, 
            List<MedicationOrderItemRequest> medicationItems) {

        this.date = date != null ? date : LocalDateTime.now();
        this.ownerId = ownerId;
        this.veterinarianId = veterinarianId;
        this.petId = petId;
        this.description = description;
        this.completed = completed;
        this.medicalHistoryId = medicalHistoryId;
        this.medicationItems = medicationItems;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getVeterinarianId() {
        return veterinarianId;
    }

    public void setVeterinarianId(Long veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(String medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    public List<MedicationOrderItemRequest> getMedicationItems() {
        return medicationItems;
    }

    public void setMedicationItems(List<MedicationOrderItemRequest> medicationItems) {
        this.medicationItems = medicationItems;
    }
    
    
}
