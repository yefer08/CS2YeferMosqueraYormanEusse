/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.rest.request;


public class PetRequest {
    
    private String name;
    private String species;
    private String breed;
    private int age;
    private String idpet;
    private String caracteristic;
    private float weight;
    private Long ownerId; 

    public PetRequest(String name, String species, String breed, int age, String idpet, String caracteristic, float weight, Long ownerId) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.idpet = idpet;
        this.caracteristic = caracteristic;
        this.weight = weight;
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdpet() {
        return idpet;
    }

    public void setIdpet(String idpet) {
        this.idpet = idpet;
    }

    public String getCaracteristic() {
        return caracteristic;
    }

    public void setCaracteristic(String caracteristic) {
        this.caracteristic = caracteristic;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }


    @Override
    public String toString() {
        return "PetRequest{" + "name=" + name + ", species=" + species + 
                ", breed=" + breed + ", age=" + age + ", caracteristic=" + 
                caracteristic + ", weight=" + weight + ", ownerId=" + ownerId + '}';
    }

   

   
    
    
}
