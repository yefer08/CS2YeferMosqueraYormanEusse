/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ports;

import app.domain.models.Owner;
import app.domain.models.Users;
import app.domain.models.Veterinarian;

/**
 *
 * @author User
 */
public interface Usersport {

    

    public Veterinarian findByid(Veterinarian veterinarian);
    public Owner findByid(Owner ownwe);

    public Users findByid(int id);

    public Users findById(int id);

    public void save(Users newUser);
    
}
