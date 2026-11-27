final class AppointmentNode {
    final Appointment appointment;
    AppointmentNode next;

    // AppointmentNode constructor that initializes the appointment and sets the next node to null.
    AppointmentNode(Appointment appointment) {
        this.appointment = appointment;
        this.next = null;
    }
}
