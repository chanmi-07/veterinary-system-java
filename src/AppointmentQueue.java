public class AppointmentQueue {
    private AppointmentNode front;
    private AppointmentNode rear;
    private int nextNumber;
    private int size;

    public AppointmentQueue() {
        front = null;
        rear = null;
        nextNumber = 1;
        size = 0;
    }

    public Appointment enqueue(String petId, String reason) {
        Appointment appointment = new Appointment(nextNumber++, petId, reason);
        AppointmentNode newNode = new AppointmentNode(appointment);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        return appointment;
    }

    public Appointment dequeue() {
        if (isEmpty()) return null;
        Appointment nextAppointment = front.appointment;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return nextAppointment;
    }

    public boolean hasPendingAppointmentFor(String petId) {
        if (petId == null) return false;
        String searchedId = petId.trim();
        AppointmentNode current = front;
        while (current != null) {
            if (current.appointment.getPetId().equalsIgnoreCase(searchedId)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public String displayPendingAppointments(PetLinkedList pets) {
        if (isEmpty()) return "There are no pending appointments.";
        StringBuilder result = new StringBuilder("Appointment queue (first in, first out):\n");
        AppointmentNode current = front;
        while (current != null) {
            Appointment appointment = current.appointment;
            Pet pet = pets.findById(appointment.getPetId());
            result.append("- ").append(appointment);
            if (pet != null) result.append(" | Pet: ").append(pet.getName());
            result.append('\n');
            current = current.next;
        }
        return result.toString();
    }

    public boolean isEmpty() { return front == null; }
    public int size() { return size; }
}
