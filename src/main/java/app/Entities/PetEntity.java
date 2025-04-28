/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Entities;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author yefer_cordoba
 */

@Getter
@Setter
@Entity
@Table(name = "pets")
public class PetEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Genera automáticamente un UUID único
    private String id;

    @Column(nullable = false)
    private String name; // Nombre de la mascota

    @Column(nullable = false)
    private String species; // Especie (por ejemplo, perro, gato)

    @Column(nullable = false)
    private String breed; // Raza de la mascota

    @Column(nullable = false)
    private int age; // Edad de la mascota

    @Column(nullable = true) // Características adicionales, opcionales
    private String caracteristic;

    @Column(nullable = true) // Peso de la mascota
    private float weight;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false) // Relación con el dueño
    private UserEntity owner;

    
    public PetEntity() {
    }

    // Constructor con todos los argumentos
    public PetEntity(String id, String name, String species, String breed, int age, String caracteristic, float weight, UserEntity owner) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.caracteristic = caracteristic;
        this.weight = weight;
        this.owner = owner;
    }

  
}
