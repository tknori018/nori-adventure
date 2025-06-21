package norisAdventure;

public enum WeaponType {
    SWORD("剣"),
    AXE("斧"),
    WAND("杖");

    private final String label;

    WeaponType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
