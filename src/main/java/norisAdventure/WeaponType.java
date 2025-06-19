package norisAdventure;

public enum WeaponType {
    Sword("剣"),
    Axe("斧"),
    Wand("杖");

    private final String label;

    WeaponType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
