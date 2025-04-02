/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ports;

import app.domain.models.Appointment;
import java.util.List;

public interface AppointmentPort {

    // Guardar una cita
    void save(Appointment appointment);

    // Buscar una lista de citas según la fecha
    List<Appointment> findByDate(String date);

    // Verificar si existe una cita por ID
    boolean existsById(String appointmentId);

    // Eliminar una cita por ID
    void deleteById(String appointmentId);

    // Obtener todas las citas
    List<Appointment> findAll();
}
