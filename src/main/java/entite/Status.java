package entite;

import java.util.Arrays;

public enum Status {
    encour,
    TERMINER,
    ANNULER;

    public static Status fromString(String value) {
        return Arrays.stream(values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status: " + value));
    }

}
