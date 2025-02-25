
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package app.domain.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Administrator extends Users {  
    
    public Administrator(int cedule, String name, int age, String username, String password, String role) {
        super(cedule, name, age, username = "Admin" , password = "123456", role = "Admin" );
    }
    
}
    