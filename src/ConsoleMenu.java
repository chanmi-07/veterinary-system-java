import java.util.Scanner;

public class ConsoleMenu {
    private final VeterinaryService service;
    private final Scanner input;

    public ConsoleMenu(VeterinaryService service, Scanner input) {
        this.service = service;
        this.input = input;
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            int option = readOption();
            switch (option) {
                case 1:
                    registerPet();
                    break;
                case 2:
                    searchPet();
                    break;
                case 3:
                    System.out.println(service.displayPets());
                    break;
                case 4:
                    deletePet();
                    break;
                case 5:
                    scheduleAppointment();
                    break;
                case 6:
                    System.out.println(service.displayAppointmentQueue());
                    break;
                case 7:
                    serveNextPet();
                    break;
                case 0:
                    running = false;
                    System.out.println("Program finished.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            if (running) pause();
        }
    }

    private void displayMenu() {
        System.out.println("\n=== VETERINARY CLINIC SYSTEM ===");
        System.out.println("1. Register pet");
        System.out.println("2. Search for a pet");
        System.out.println("3. Show registered pets");
        System.out.println("4. Delete a pet");
        System.out.println("5. Schedule an appointment");
        System.out.println("6. Show appointment queue");
        System.out.println("7. Serve the next pet");
        System.out.println("0. Exit");
    }

    private int readOption() {
        System.out.print("Select an option: ");
        String value = input.nextLine().trim();
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void registerPet() {
        System.out.println("\n--- Register pet ---");
        String id = readRequiredText("Pet ID: ");
        String name = readRequiredText("Name: ");
        String species = readRequiredText("Species: ");
        int age = readAge();
        String ownerName = readRequiredText("Owner name: ");

        Pet pet = new Pet(id, name, species, age, ownerName);
        if (service.registerPet(pet)) {
            System.out.println("Pet registered successfully.");
        } else {
            System.out.println("A pet with that ID is already registered.");
        }
    }

    private void searchPet() {
        String id = readRequiredText("Pet ID: ");
        Pet pet = service.findPetById(id);
        System.out.println(pet == null ? "Pet not found." : pet);
    }

    private void deletePet() {
        String id = readRequiredText("Pet ID to delete: ");
        System.out.println(service.deletePet(id));
    }

    private void scheduleAppointment() {
        String petId = readRequiredText("Pet ID: ");
        Pet pet = service.findPetById(petId);
        if (pet == null) {
            System.out.println("Appointment cannot be scheduled. Register the pet first.");
            return;
        }
        String reason = readRequiredText("Reason for the visit: ");
        Appointment appointment = service.scheduleAppointment(petId, reason);
        System.out.println("Appointment scheduled: " + appointment);
    }

    private void serveNextPet() {
        Appointment appointment = service.serveNextPet();
        if (appointment == null) {
            System.out.println("There are no pending appointments.");
            return;
        }
        Pet pet = service.findPetById(appointment.getPetId());
        System.out.println("Now serving: " + (pet == null ? "Pet not found" : pet.getName()));
        System.out.println(appointment);
    }

    private String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty.");
        }
    }

    private int readAge() {
        while (true) {
            System.out.print("Age in years: ");
            String value = input.nextLine().trim();
            try {
                int age = Integer.parseInt(value);
                if (age >= 0) return age;
            } catch (NumberFormatException ignored) {
                // Ask for the value again.
            }
            System.out.println("Enter a valid non-negative whole number.");
        }
    }

    private void pause() {
        System.out.print("\nPress Enter to continue...");
        input.nextLine();
    }
}
