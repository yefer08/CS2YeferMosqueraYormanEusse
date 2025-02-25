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
    private int  idOwnwer;
    private int agepet;
    private String idpet;
    private String racepet;
    private String caracteristic;
    private float weight;

    public Pet(String namepet, int idOwnwer, int agepet, String idpet, String racepet, String caracteristic, float weight) {
        this.namepet = namepet;
        this.idOwnwer = idOwnwer;
        this.agepet = agepet;
        this.idpet = idpet;
        this.racepet = racepet;
        this.caracteristic = caracteristic;
        this.weight = weight;
    }
    
    
    
}
