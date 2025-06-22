package norisAdventure;

public class Sword extends Weapon {
    public final WeaponType weaponType = WeaponType.SWORD;

    public Sword(String name, String description, double itemPower, int price, ItemPosition itemPosition) {
        super(name, description,itemPower, price, itemPosition);
    }

    // --- アイテム定義 ---
    public static final Item KINO_TSURUGI = new Sword("木の剣", "木でできた簡単な剣(ちからが1.1倍)", 1.1, 100, ItemPosition.WEAPON_ITEM);
    public static final Item HAGANE_NO_TSURUGI = new Sword("はがねの剣", "はがねで作られた頑丈な剣(ちからが1.3倍)", 1.3, 1200, ItemPosition.WEAPON_ITEM);
    public static final Item MAJIN_NO_TSURUGI = new Sword("魔人の剣", "魔人の魂が宿る呪われた剣(ちからが1.5倍)", 1.5, 8800, ItemPosition.WEAPON_ITEM);
    public static final Item EXCALIBUR = new Sword("エクスカリバー", "伝説の王が扱ったとされる聖剣(ちからが1.8倍)", 1.8, 50000, ItemPosition.WEAPON_ITEM);
}
