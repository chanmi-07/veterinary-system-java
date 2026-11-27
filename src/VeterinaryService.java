public class VeterinaryService {
    private final PetLinkedList pets;
    private final AppointmentQueue appointments;

    public VeterinaryService() {
        pets = new PetLinkedList();
        appointments = new AppointmentQueue();
    }

    public boolean registerPet(Pet pet) {
        return pets.add(pet);
    }

    public Pet findPetById(String id) {
        return pets.findById(id);
    }

    public String displayPets() {
        return pets.displayAll();
    }

    public String deletePet(String id) {
        if (pets.findById(id) == null) return "No pet was found with that ID.";
        if (appointments.hasPendingAppointmentFor(id)) {
            return "The pet cannot be deleted because it has a pending appointment.";
        }
        pets.removeById(id);
        return "Pet deleted successfully.";
    }

    public Appointment scheduleAppointment(String petId, String reason) {
        if (pets.findById(petId) == null) return null;
        return appointments.enqueue(petId, reason);
    }

    public String displayAppointmentQueue() {
        return appointments.displayPendingAppointments(pets);
    }

    public Appointment serveNextPet() {
        Appointment appointment = appointments.dequeue();
        if (appointment != null) appointment.markCompleted();
        return appointment;
    }
}
