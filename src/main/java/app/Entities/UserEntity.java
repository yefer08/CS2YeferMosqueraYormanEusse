/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yefer_cordoba
 */

@Getter
@Setter
@Entity
@Table(name = "users") 
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
     private int age;
    
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; 

    public UserEntity() {
       
    }

    public UserEntity( int age, String username, String password, String role, String name) {
       
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
    