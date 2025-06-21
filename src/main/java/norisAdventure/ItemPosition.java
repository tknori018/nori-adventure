package norisAdventure;

public enum ItemPosition {
    WEAPON_ITEM(1, "武器"),
    RECOVERY_ITEM(2, "回復アイテム"),
    BUFF_ITEM(3, "強化アイテム");

    private final int sortOrder;
    private final String label;

    ItemPosition(int sortOrder, String label) {
        this.sortOrder = sortOrder;
        this.label = label;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public String getLabel() {
        return label;
    }
}
