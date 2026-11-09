package entity;

/**
 * The UofT libraries a quiz taker can be matched to.
 */
public enum Library {
    ROBARTS("Robarts Library"),
    GERSTEIN("Gerstein Library"),
    GRAHAM("Graham Library"),
    EMMANUEL("Emmanuel College Library"),
    UC("University College Library"),
    THOMAS_FISHER("Thomas Fisher Rare Book Library");

    private final String displayName;

    Library(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
