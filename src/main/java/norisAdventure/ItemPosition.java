package norisAdventure;

public enum ItemPosition {
    WEAPON_ITEM("武器アイテム"),
    RECOVERY_ITEM("回復アイテム"),
    BUFF_ITEM("強化アイテム");

    private final String label;

    ItemPosition(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
