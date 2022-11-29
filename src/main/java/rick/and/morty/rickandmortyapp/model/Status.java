package rick.and.morty.rickandmortyapp.model;

public enum Status {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}
