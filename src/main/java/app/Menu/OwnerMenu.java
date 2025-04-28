/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Menu;

import app.Entities.MedicalHistoryEntity;
import app.domain.models.Pet;
import app.domain.services.MedicalHistoryService;
import app.ports.PetPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class OwnerMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final MedicalHistoryService medicalHistoryService;
    private final PetPort petPort;

    @Autowired
    public OwnerMenu(MedicalHistoryService medicalHistoryService, PetPort petPort) {
        this.medicalHistoryService = medicalHistoryService;
        this.petPort = petPort;
    }

    public void showOwnerMenu(String ownerId) {
        while (true) {
            System.out.println("\n--- 🐾 Menú Propietario ---");
            System.out.println("1. Ver historial clínico de mis mascotas");
            System.out.println("2. Solicitar cita");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            try {
                int option = Integer.parseInt(scanner.nextLine().trim());

                switch (option) {
                    case 1 ->
                        verHistorialClinico(ownerId);
                    //case 2 ->
                        //solicitarCita();
                    case 3 -> {
                        System.out.println("🔴 Sesión cerrada.");
                        return;
                    }
                    default ->
                        System.out.println("⚠️ Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Por favor, ingrese un número.");
            }
        }
    }

    private void verHistorialClinico(String ownerId) {
        System.out.println("\n📖 Mostrando historial clínico de sus mascotas...");
        List<Pet> pets = petPort.findByOwnerId(ownerId);

        if (pets == null || pets.isEmpty()) {
            System.out.println("⚠️ No tiene mascotas registradas.");
            return;
        }

        for (Pet pet : pets) {
            mostrarInformacionMascota(pet);
            List<MedicalHistoryEntity> medicalHistories = medicalHistoryService.findByPetId(pet.getId());
            mostrarHistorialClinico(medicalHistories);
        }
    }

    private void mostrarInformacionMascota(Pet pet) {
        System.out.println("\n🐕 Mascota: " + pet.getNamepet() + " (ID: " + pet.getId()+ ")");
    }

    private void mostrarHistorialClinico(List<MedicalHistoryEntity> medicalHistories) {
        if (medicalHistories.isEmpty()) {
            System.out.println("📭 No hay registros clínicos para esta mascota.");
        } else {
            for (MedicalHistoryEntity history : medicalHistories) {
                System.out.println("───────────────────────────────────");
                System.out.println("📅 Fecha: " + history.getDate());
                System.out.println("👨‍⚕️ Veterinario: " + history.getVeterinarian().getName());
                System.out.println("🩺 Razón: " + history.getReason());
                System.out.println("📝 Síntomas: " + history.getSymptoms());
                System.out.println("⚕️ Diagnóstico: " + history.getDiagnosis());
                System.out.println("🛠 Procedimiento: " + history.getMedicalProcedure());
                System.out.println("💊 Medicación: " + history.getMedication() + " (Dosis: " + history.getMedicationDose() + ")");
                System.out.println("💉 Vacunación: " + history.getVaccinationHistory());
                System.out.println("🚨 Alergias: " + history.getAllergies());
                System.out.println("🗒 Detalles: " + history.getProcedureDetails());
            }
        }
    }

  /*  private void solicitarCita() {
        System.out.println("\n📅 Solicitando cita...");
        System.out.print("Ingrese el ID de la mascota: ");
        String petId = scanner.nextLine().trim();

        Pet pet = petPort.findByidpet(petId);
        if (pet == null) {
            System.out.println("❌ Mascota no encontrada con ID: " + petId);
            return;
        }

        System.out.print("Ingrese la fecha de la cita (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine().trim();

        System.out.print("Ingrese el motivo de la cita: ");
        String reason = scanner.nextLine().trim();

        try {
            Appointment appointment = new Appointment(UUID.randomUUID().toString(), pet, LocalDate.parse(dateInput), reason);
            appointmentService.scheduleAppointment(appointment);
            System.out.println("✅ Cita solicitada con exito");
        }
    }*/
}