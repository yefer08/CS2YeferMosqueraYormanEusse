/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

import app.Converted.PetConverter;
import app.Converted.UserConverter;
import app.Entities.AppointmentEntity;
import app.domain.models.Appointment;
import app.infrastructure.repositories.AppointmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Método para programar una cita
    public void scheduleAppointment(Appointment appointment) throws Exception {
        if (appointment == null) {
            throw new IllegalArgumentException("La cita no puede ser nula");
        }

        // Validar conflicto de horario
        if (hasConflict(appointment)) {
            throw new Exception("Conflicto: ya existe una cita en ese horario.");
        }

        // Convertir `Appointment` a `AppointmentEntity`
        AppointmentEntity appointmentEntity = new AppointmentEntity(
                appointment.getId(),
                UserConverter.convertToUserEntity(appointment.getVeterinarianId()),
                UserConverter.convertToUserEntity(appointment.getOwnerId()),
                PetConverter.convertToPetEntity(appointment.getPetId()),
                appointment.getAppointmentDate(),
                appointment.getReason(),
                appointment.getSymptoms(),
                appointment.getDiagnosis(),
                appointment.getTreatment()
        );

        // Guardar en la base de datos
        appointmentRepository.save(appointmentEntity);
    }

    // Método para validar conflictos de horarios
    private boolean hasConflict(Appointment appointment) {
        List<AppointmentEntity> existingAppointments = appointmentRepository.findByAppointmentDate(appointment.getAppointmentDate());
        return existingAppointments.stream()
                .anyMatch(a -> a.getPetId().equals(appointment.getPetId())); // Verifica si hay una cita para la misma mascota
    }

    // Método para listar todas las citas
    public List<AppointmentEntity> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Método para cancelar una cita
    public void cancelAppointment(String appointmentId) throws Exception {
        if (!appointmentRepository.existsById(appointmentId)) {
            throw new Exception("No se encontró una cita con el ID proporcionado.");
        }
        appointmentRepository.deleteById(appointmentId);
    }

    // Método adicional para obtener todas las citas
    public List<AppointmentEntity> findAll() {
        return appointmentRepository.findAll();
    }
}
