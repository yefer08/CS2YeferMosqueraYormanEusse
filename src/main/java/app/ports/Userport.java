/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ports;

import app.domain.models.Owner;
import app.domain.models.User;
import app.domain.models.Veterinarian;

public interface Userport {

    // 🔹 Buscar un usuario por ID
    User findById(String id);

    // 🔹 Guardar un nuevo usuario
    void save(User newUser);

    public Owner findByid(String id);
    
    Veterinarian findVeterinarianById(String id);
}

