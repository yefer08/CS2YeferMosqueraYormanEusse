/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;


public class Pet {

    private String namepet;
    private Owner idOwnwer;
    private int agepet;
    private String id;
    private String racepet;
    private String caracteristic;
    private float weight;
    private String species; // Nueva propiedad para la especie

    // Constructor actualizado
    public Pet(String namepet, Owner idOwnwer, int agepet, String idpet, 
            String racepet, String caracteristic, float weight, String species) {
        this.namepet = namepet;
        this.idOwnwer = idOwnwer;
        this.agepet = agepet;
        this.id = idpet;
        this.racepet = racepet;
        this.caracteristic = caracteristic;
        this.weight = weight;
        this.species = species; // Asignar el valor de especie
    }

    public String getNamepet() {
        return namepet;
    }

    public void setNamepet(String namepet) {
        this.namepet = namepet;
    }

    public Owner getIdOwnwer() {
        return idOwnwer;
    }

    public void setIdOwnwer(Owner idOwnwer) {
        this.idOwnwer = idOwnwer;
    }

    public int getAgepet() {
        return agepet;
    }

    public void setAgepet(int agepet) {
        this.agepet = agepet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRacepet() {
        return racepet;
    }

    public void setRacepet(String racepet) {
        this.racepet = racepet;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
    
    

   
}
