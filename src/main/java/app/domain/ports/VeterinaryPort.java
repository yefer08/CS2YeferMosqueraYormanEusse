/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package veterinarianapp.port;

import Models.ClinicalRecord;
import Models.Order;
import java.util.List;

/**
 *
 * @author Yorman
 */
public interface VeterinaryPort {
    
  public void registerPerson(String id, String name, int age, String role);  
  public void registerPet(String id, String name, String ownerId, int age, String species, String breed, String characteristics, double weight);
  public void addClinicalRecord(String petId, ClinicalRecord record);
  public void createOrder(String id, String petId, String ownerId, String vetId, String medication, String dose, String date);
  public void cancelOrder(String orderId);
  public void generateInvoice(String id, String petId, String ownerId, String orderId, String productName, double value, int quantity, String date);
    List<ClinicalRecord> getClinicalHistory(String petId);
    List<Order> getOrders();

}
