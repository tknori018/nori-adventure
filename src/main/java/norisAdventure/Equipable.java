package norisAdventure;

public enum Equipable {
    TRUE("装備可"),
    EQUIPPED("装備中"),
    FALSE("装備不可");

    private final String label;

    Equipable(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
