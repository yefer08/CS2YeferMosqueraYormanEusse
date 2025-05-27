
package app.domain.models;

import java.time.LocalDateTime;
import java.util.List; 
import lombok.NoArgsConstructor; 
import lombok.AllArgsConstructor; 


@NoArgsConstructor 
@AllArgsConstructor 
public class Order {
    private String id; 
    private Pet pet;
    private Owner owner;
    private Veterinarian veterinarian; 
    private MedicalHistory medicalHistory;
    private List<MedicationOrderItem> medicationItems;

    private LocalDateTime date;
    private String description; 
    private boolean completed;
    
    public Order(Pet pet, Owner owner, Veterinarian veterinarian, MedicalHistory medicalHistory, List<MedicationOrderItem> medicationItems, LocalDateTime date, String description, Boolean completed) {
        this.pet = pet;
        this.owner = owner;
        this.veterinarian = veterinarian;
        this.medicalHistory = medicalHistory;
        this.medicationItems = medicationItems;
        this.date = date;
        this.description = description;
        this.completed = completed;
    }

    public Order(String orderId) {
        this.id = orderId; 
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        this.veterinarian = veterinarian;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public List<MedicationOrderItem> getMedicationItems() {
        return medicationItems;
    }

    public void setMedicationItems(List<MedicationOrderItem> medicationItems) {
        this.medicationItems = medicationItems;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    
}
