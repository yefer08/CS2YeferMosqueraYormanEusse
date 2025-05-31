
package app.domain.models;


public class MedicationOrderItem {
    private String id; 
    private String medicationName;
    private String dose;
    private int quantity;
    private String instructions; 

    public MedicationOrderItem( String medicationName, String dose, int quantity, String instructions) {
        this.medicationName = medicationName;
        this.dose = dose;
        this.quantity = quantity;
        this.instructions = instructions;
    }

    public MedicationOrderItem() {}
    
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
