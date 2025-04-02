/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.adapter;

import app.Entities.AppointmentEntity;
import app.domain.models.Appointment;
import app.infrastructure.repositories.AppointmentRepository;
import app.ports.AppointmentPort;
import java.util.List;

public class AppointmentAdapter implements AppointmentPort {

    private final AppointmentRepository appointmentRepository;

    // Constructor: Inyección del repositorio
    public AppointmentAdapter(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Implementación del método save
    @Override
    public void save(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("La cita no puede ser nula");
        }
        appointmentRepository.save(appointment);
    }

    // Implementación del método findByDate
    @Override
    public List<Appointment> findByDate(String date) {
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía");
        }
        return appointmentRepository.findByDate(date);
    }

    // Implementación del método existsById
    @Override
    public boolean existsById(String appointmentId) {
        if (appointmentId == null || appointmentId.isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío");
        }
        return appointmentRepository.existsById(appointmentId);
    }

    // Implementación del método deleteById
    @Override
    public void deleteById(String appointmentId) {
        if (appointmentId == null || appointmentId.isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío");
        }
        appointmentRepository.deleteById(appointmentId);
    }


    @Override
    public List<Appointment> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
