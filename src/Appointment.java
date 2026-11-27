public class Appointment {
    public enum Status { PENDING, COMPLETED }

    private final int number;
    private final String petId;
    private final String reason;
    private Status status;

    public Appointment(int number, String petId, String reason) {
        this.number = number;
        this.petId = petId.trim();
        this.reason = reason.trim();
        this.status = Status.PENDING;
    }

    public int getNumber() { return number; }
    public String getPetId() { return petId; }
    public String getReason() { return reason; }
    public Status getStatus() { return status; }

    public void markCompleted() {
        status = Status.COMPLETED;
    }

    @Override
    public String toString() {
        return "Appointment #" + number
                + " | Pet ID: " + petId
                + " | Reason: " + reason
                + " | Status: " + status;
    }
}
