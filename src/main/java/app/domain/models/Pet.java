/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
    public Pet(String namepet, Owner idOwnwer, int agepet, String idpet, String racepet, String caracteristic, float weight, String species) {
        this.namepet = namepet;
        this.idOwnwer = idOwnwer;
        this.agepet = agepet;
        this.id = idpet;
        this.racepet = racepet;
        this.caracteristic = caracteristic;
        this.weight = weight;
        this.species = species; // Asignar el valor de especie
    }
}
