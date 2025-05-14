/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yefer_cordoba
 */

@Setter
@Getter
public abstract class  User {
    protected Long id;
    protected String name;
    protected int age;
    protected String username;
    protected String password;
    protected String role;

    public User() {
    }
    
    

    public User( String name, int age, String username, String password, String role) {
        
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.role = role;
    }    
}
