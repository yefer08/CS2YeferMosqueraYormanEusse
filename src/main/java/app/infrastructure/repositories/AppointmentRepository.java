/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.repositories;

import app.Entities.AppointmentEntity;
import app.domain.models.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, String> {

    
    List<AppointmentEntity> findByAppointmentDate(String appointmentDate); // Buscar por fecha

    public void save(Appointment appointment);

    public List<Appointment> findByDate(String appointmentDate);
}
