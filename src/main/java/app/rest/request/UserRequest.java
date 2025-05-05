/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
   
    private String name;
    private Integer age;
    private String username;
    private String password;
    private String role;

    @Override
    public String toString() {
        return "UserRequest{" + "name=" + name + ", age=" + age + ", username=" + username + ", password=" + password + ", role=" + role + '}';
    }
    
    
}
