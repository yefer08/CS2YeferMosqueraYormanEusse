
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
public class Administrator extends User {

    public Administrator(String name, int age, String username, String password, String role) {
        super( name, age, username, password, role);
    }
}

    