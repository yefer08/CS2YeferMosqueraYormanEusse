/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package app.domain.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Owner extends Users {
    private Pet idpet;

    public Owner(int id, String name, int age, String username, String password, String role) {
        super(id, name, age, username, password, role);
    }
    
  
    
    
  
}
