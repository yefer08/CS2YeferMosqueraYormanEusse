/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Menu;

import app.domain.models.MedicalHistory;
import app.domain.models.Veterinarian;
import app.domain.services.MedicalHistoryService;
import java.util.List;

import java.util.Scanner;

public class VeterinarianMenu {
  /*  private final Scanner scanner = new Scanner(System.in);
    private MedicalHistoryService medicalHistoryService;

    public VeterinarianMenu(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }     

    void showVeterinarianMenu() {
        while (true) {
            System.out.println("\n--- 🏥 Menú Veterinario ---");
            System.out.println("1. Registrar historia clínica");
            System.out.println("2. Ver historial de pacientes");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    registrarHistoriaClinica();
                    break;
                case 2:
                    mostrarHistorialPacientes();
                    break;
                case 3:
                    System.out.println("🔴 Sesión cerrada.");
                    return; // Salir del menú
                default:
                    System.out.println("⚠️ Opción no válida. Intente de nuevo.");
            }
        }
    }

   private void registrarHistoriaClinica() {
        System.out.println("📄 Registrando historia clínica...");

        
        System.out.print("Ingrese ID de la mascota: ");
        String petId = scanner.nextLine();

        System.out.print("Ingrese cédula del veterinario: ");
        int cedule = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese nombre del veterinario: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese edad del veterinario: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese username del veterinario: ");
        String username = scanner.nextLine();

        System.out.print("Ingrese password del veterinario: ");
        String password = scanner.nextLine();

        System.out.print("Ingrese role del veterinario: ");
        String role = scanner.nextLine();

        Veterinarian veterinarian = new Veterinarian(cedule, name, age, username, password, role);

        System.out.print("Ingrese la razón de la consulta: ");
        String reason = scanner.nextLine();

        System.out.print("Ingrese los síntomas: ");
        String symptoms = scanner.nextLine();

        System.out.print("Ingrese diagnóstico: ");
        String diagnosis = scanner.nextLine();

        System.out.print("Ingrese procedimiento realizado: ");
        String procedure = scanner.nextLine();

        System.out.print("Ingrese medicamento recetado: ");
        String medication = scanner.nextLine();

        System.out.print("Ingrese dosis del medicamento: ");
        String medicationDose = scanner.nextLine();

        System.out.print("Ingrese ID de la orden médica: ");
        String idOrder = scanner.nextLine();

        System.out.print("Ingrese historial de vacunación: ");
        String vaccinationHistory = scanner.nextLine();

        System.out.print("Ingrese alergias del paciente: ");
        String allergies = scanner.nextLine();

        System.out.print("Ingrese detalles del procedimiento: ");
        String procedureDetails = scanner.nextLine();

        // Llamar al método del servicio
        medicalHistoryService.createMedicalHistory(
                petId, veterinarian, reason, symptoms, diagnosis, procedure,
                medication, medicationDose, idOrder, vaccinationHistory, allergies, procedureDetails
        );

        System.out.println("✅ Historia clínica registrada exitosamente.");
    }

   private void mostrarHistorialPacientes() {
    System.out.println("📂 Mostrando historial de pacientes...");

    // Obtener todos los historiales clínicos
    List<MedicalHistory> medicalHistories = medicalHistoryService.findAll();

    if (medicalHistories.isEmpty()) {
        System.out.println("⚠️ No hay historial de pacientes registrado.");
        return;
    }

    // Mostrar la información de cada historial clínico
    for (MedicalHistory history : medicalHistories) {
        System.out.println("──────────────────────────────────────────────");
        System.out.println("📌 ID Historia: " + history.getId());
        System.out.println("👨‍⚕️ Veterinario: " + history.getVeterinarian().getName());
        System.out.println("📅 Fecha: " + history.getDate());
        System.out.println("🩺 Razón: " + history.getReason());
        System.out.println("📝 Síntomas: " + history.getSymptoms());
        System.out.println("⚕️ Diagnóstico: " + history.getDiagnosis());
        System.out.println("🛠 Procedimiento: " + history.getProcedure());
        System.out.println("💊 Medicación: " + history.getMedication() + " (Dosis: " + history.getMedicationDose() + ")");
        System.out.println("💉 Vacunación: " + history.getVaccinationHistory());
        System.out.println("🚨 Alergias: " + history.getAllergies());
        System.out.println("🗒 Detalles: " + history.getProcedureDetails());
    }

    System.out.println("✅ Fin del historial de pacientes.");
}*/
   
}

