public class PetLinkedList {
    private PetNode head;
    private int size;

    public boolean add(Pet pet) {
        if (pet == null || findById(pet.getId()) != null) {
            return false;
        }

        PetNode newNode = new PetNode(pet);
        if (head == null) {
            head = newNode;
        } else {
            PetNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    // Sequential search: visit each node from the head until a match is found.
    public Pet findById(String id) {
        if (id == null) return null;
        String searchedId = id.trim();
        PetNode current = head;
        while (current != null) {
            if (current.pet.getId().equalsIgnoreCase(searchedId)) {
                return current.pet;
            }
            current = current.next;
        }
        return null;
    }

    public boolean removeById(String id) {
        if (id == null || head == null) return false;
        String searchedId = id.trim();

        if (head.pet.getId().equalsIgnoreCase(searchedId)) {
            head = head.next;
            size--;
            return true;
        }

        PetNode previous = head;
        PetNode current = head.next;
        while (current != null) {
            if (current.pet.getId().equalsIgnoreCase(searchedId)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public String displayAll() {
        if (head == null) return "No pets are registered.";
        StringBuilder result = new StringBuilder("Registered pets:\n");
        PetNode current = head;
        while (current != null) {
            result.append("- ").append(current.pet).append('\n');
            current = current.next;
        }
        return result.toString();
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }
}
