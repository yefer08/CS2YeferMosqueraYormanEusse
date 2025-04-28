package app.Menu;

import app.Entities.MedicalHistoryEntity;
import app.domain.models.MedicalHistory;
import app.domain.models.Order;
import app.domain.models.Owner;
import app.domain.models.Pet;
import app.domain.models.Veterinarian;
import app.domain.services.MedicalHistoryService;
import app.domain.services.PetService;
import app.ports.Orderport;
import app.ports.PetPort;
import app.ports.Userport;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeterinarianMenu {

    @Autowired
    private PetPort petPort;
    @Autowired
    private Orderport orderPort;
    @Autowired
    private Userport userport;
    @Autowired
    private PetService petService; // 🔥 Nuevo: inyectamos PetService para registrar mascotas

    private final Scanner scanner = new Scanner(System.in);
    private final MedicalHistoryService medicalHistoryService;

    public VeterinarianMenu(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    public void showVeterinarianMenu() {
        while (true) {
            System.out.println("\n--- 🏥 Menú Veterinario ---");
            System.out.println("1. Registrar historia clínica");
            System.out.println("2. Ver historial de pacientes");
            System.out.println("3. Editar historia clínica");
            System.out.println("4. Eliminar historia clínica");
            System.out.println("5. Registrar nueva mascota"); 
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            try {
                int option = Integer.parseInt(scanner.nextLine().trim());

                switch (option) {
                    case 1 ->
                        registrarHistoriaClinica();
                    case 2 ->
                        mostrarHistorialPacientes();
                    case 3 ->
                        editarHistoriaClinica();
                    case 4 ->
                        eliminarHistoriaClinica();
                    case 5 ->
                        registrarMascota(); // 👈 Nueva funcionalidad
                    case 0 -> {
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

    private void registrarHistoriaClinica() {
        System.out.println("\n📄 Registrando historia clínica...");

        System.out.print("Ingrese ID de la mascota: ");
        String petId = scanner.nextLine().trim();
        Pet pet = petPort.findByidpet(petId);
        if (pet == null) {
            System.out.println("⚠️ ID de mascota no encontrado.");
            return;
        }

        System.out.print("Ingrese cédula del veterinario: ");
        String vetId = scanner.nextLine().trim();
        Veterinarian veterinarian = userport.findVeterinarianById(vetId);
        if (veterinarian == null) {
            System.out.println("⚠️ ID de veterinario no fue encontrado.");
            return;
        }

        System.out.print("Ingrese la razón de la consulta: ");
        String reason = scanner.nextLine().trim();

        System.out.print("Ingrese los síntomas: ");
        String symptoms = scanner.nextLine().trim();

        System.out.print("Ingrese diagnóstico: ");
        String diagnosis = scanner.nextLine().trim();

        System.out.print("Ingrese procedimiento realizado: ");
        String procedure = scanner.nextLine().trim();

        System.out.print("Ingrese medicamento recetado: ");
        String medication = scanner.nextLine().trim();

        System.out.print("Ingrese dosis del medicamento: ");
        String medicationDose = scanner.nextLine().trim();

        System.out.print("Ingrese ID de la orden médica: ");
        String idOrder = scanner.nextLine().trim();
        Order order = orderPort.findByorderId(idOrder);
        if (order == null) {
            System.out.println("⚠️ Orden no encontrada.");
            return;
        }

        System.out.print("Ingrese historial de vacunación: ");
        String vaccinationHistory = scanner.nextLine().trim();

        System.out.print("Ingrese alergias del paciente: ");
        String allergies = scanner.nextLine().trim();

        System.out.print("Ingrese detalles del procedimiento: ");
        String procedureDetails = scanner.nextLine().trim();

        LocalDateTime date = LocalDateTime.now();
        Boolean canceled = false;

        medicalHistoryService.createMedicalHistory(
                date, veterinarian, reason, symptoms, diagnosis, procedure, medication,
                medicationDose, idOrder, vaccinationHistory, allergies, procedureDetails, canceled, petId
        );

        System.out.println("✅ Historia clínica registrada exitosamente.");
    }

    private void mostrarHistorialPacientes() {
        System.out.println("\n📂 Mostrando historial de pacientes...");

        List<MedicalHistoryEntity> medicalHistories = medicalHistoryService.findAll();

        if (medicalHistories.isEmpty()) {
            System.out.println("⚠️ No hay historial de pacientes registrado.");
            return;
        }

        for (MedicalHistoryEntity history : medicalHistories) {
            System.out.println("──────────────────────────────────────────────");
            System.out.println("📌 ID Historia: " + history.getId());
            System.out.println("👨‍⚕️ Veterinario: " + history.getVeterinarian().getName());
            System.out.println("📅 Fecha: " + history.getDate());
            System.out.println("🩺 Razón: " + history.getReason());
            System.out.println("📝 Síntomas: " + history.getSymptoms());
            System.out.println("⚕️ Diagnóstico: " + history.getDiagnosis());
            System.out.println("🛠 Procedimiento: " + history.getMedicalProcedure());
            System.out.println("💊 Medicación: " + history.getMedication() + " (Dosis: " + history.getMedicationDose() + ")");
            System.out.println("💉 Vacunación: " + history.getVaccinationHistory());
            System.out.println("🚨 Alergias: " + history.getAllergies());
            System.out.println("🗒 Detalles: " + history.getProcedureDetails());
        }

        System.out.println("✅ Fin del historial de pacientes.");
    }

    private void editarHistoriaClinica() {
        System.out.println("\n✏️ Editar historia clínica...");
        System.out.print("Ingrese el ID de la historia clínica a editar: ");
        String historyId = scanner.nextLine().trim();

        MedicalHistory history = medicalHistoryService.findById(historyId);
        if (history == null) {
            System.out.println("⚠️ Historia clínica no encontrada.");
            return;
        }

        System.out.print("Ingrese nuevo diagnóstico (deje en blanco para mantener el actual): ");
        String newDiagnosis = scanner.nextLine().trim();
        if (!newDiagnosis.isEmpty()) {
            history.setDiagnosis(newDiagnosis);
        }

        System.out.print("Ingrese nuevo procedimiento (deje en blanco para mantener el actual): ");
        String newProcedure = scanner.nextLine().trim();
        if (!newProcedure.isEmpty()) {
            history.setMedicalProcedure(newProcedure);
        }

        System.out.print("Ingrese nueva medicación (deje en blanco para mantener la actual): ");
        String newMedication = scanner.nextLine().trim();
        if (!newMedication.isEmpty()) {
            history.setMedication(newMedication);
        }

        medicalHistoryService.updateMedicalHistory(history);
        System.out.println("✅ Historia clínica actualizada correctamente.");
    }

    private void eliminarHistoriaClinica() {
        System.out.println("\n🗑️ Eliminar historia clínica...");
        System.out.print("Ingrese el ID de la historia clínica a eliminar: ");
        String historyId = scanner.nextLine().trim();

        MedicalHistory history = medicalHistoryService.findById(historyId);
        if (history == null) {
            System.out.println("⚠️ Historia clínica no encontrada.");
            return;
        }

        medicalHistoryService.deleteMedicalHistory(historyId);
        System.out.println("✅ Historia clínica eliminada exitosamente.");
    }

    private void registrarMascota() {
        System.out.println("\n🐾 Registro de Nueva Mascota");

        System.out.print("Ingrese nombre de la mascota: ");
        String namepet = scanner.nextLine().trim();

        System.out.print("Ingrese edad de la mascota: ");
        int agepet = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Ingrese raza de la mascota: ");
        String racepet = scanner.nextLine().trim();

        System.out.print("Ingrese características de la mascota: ");
        String caracteristic = scanner.nextLine().trim();

        System.out.print("Ingrese peso de la mascota (kg): ");
        float weight = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Ingrese especie de la mascota: ");
        String species = scanner.nextLine().trim();

        System.out.print("Ingrese ID del dueño: ");
        String idOwner = scanner.nextLine().trim();

        Owner owner = (Owner) userport.findById(idOwner);

        if (owner == null) {
            System.out.println("⚠️ Dueño no encontrado. No se puede registrar la mascota.");
            return;
        }

        try {
            petService.registerPet(namepet, owner, agepet, racepet, caracteristic, weight, species);
            System.out.println("✅ Mascota registrada exitosamente.");
        } catch (Exception ex) {
            System.out.println("⚠️ Error al registrar la mascota: " + ex.getMessage());
        }
    }
}
