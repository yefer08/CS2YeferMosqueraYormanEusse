/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class  Users {
    protected int id;
    protected String name;
    protected int age;
    protected String username;
    protected String password;
    protected String role;

    public Users(int id, String name, int age, String username, String password, String role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    
   
    
    
}
