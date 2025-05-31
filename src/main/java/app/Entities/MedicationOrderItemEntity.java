
package app.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "medication_order_items")
public class MedicationOrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Genera un UUID para cada ítem de medicamento
    private String id;

    @Column(nullable = false)
    private String medicationName;

    @Column(nullable = false)
    private String dose;

    @Column(nullable = false)
    private int quantity;

    @Column(columnDefinition = "TEXT") // Puede ser un texto más largo
    private String instructions;

  
    public MedicationOrderItemEntity() {
    }

    public MedicationOrderItemEntity(String medicationName, String dose, int quantity, String instructions, OrderEntity order) {
        this.medicationName = medicationName;
        this.dose = dose;
        this.quantity = quantity;
        this.instructions = instructions;
   
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }



   

} 
