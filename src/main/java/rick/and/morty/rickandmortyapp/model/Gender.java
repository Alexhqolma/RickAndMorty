package rick.and.morty.rickandmortyapp.model;

public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    GENDERLESS("Genderless"),
    UNKNOWN("Unknown");

    private final String value;

    Gender(String value) {
        this.value = value;
    }
}
