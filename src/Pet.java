public class Pet {
    private final String id;
    private final String name;
    private final String species;
    private final int age;
    private final String ownerName;

    public Pet(String id, String name, String species, int age, String ownerName) {
        this.id = normalize(id, "Pet ID");
        this.name = normalize(name, "Name");
        this.species = normalize(species, "Species");
        this.ownerName = normalize(ownerName, "Owner name");
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.age = age;
    }

    private static String normalize(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public String getOwnerName() { return ownerName; }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Name: " + name
                + " | Species: " + species
                + " | Age: " + age
                + " | Owner: " + ownerName;
    }
}
