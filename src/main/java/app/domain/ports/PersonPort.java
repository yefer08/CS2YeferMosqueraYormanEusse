/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package veterinarianapp.port;

import Models.Person;

/**
 *
 * @author Yorman
 */
public interface PersonPort {
        public void registerPerson(Person person);
    Person findById(String cedula);

    
    
}
