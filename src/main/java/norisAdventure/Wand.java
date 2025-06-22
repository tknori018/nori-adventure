package norisAdventure;

public class Wand extends Weapon {
    public final WeaponType weaponType = WeaponType.WAND;

    public Wand(String name, String description, double itemPower, int price, ItemPosition itemPosition) {
        super(name, description,itemPower, price, itemPosition);
    }

    // --- アイテム定義 ---
    public static final Item KINO_TSUE = new Wand("木の杖", "木でできた簡単な杖(ちからが1.05倍)", 1.05, 100, ItemPosition.WEAPON_ITEM);
    public static final Item AURORA_NO_TSUE = new Wand("オーロラの杖", "オーロラの魔力が宿る美しい杖(ちからが1.25倍)", 1.25, 1300, ItemPosition.WEAPON_ITEM);
    public static final Item KENJA_NO_TSUE = new Wand("賢者の杖", "大賢者が作り出した万能の杖(ちからが1.45倍)", 1.45, 10000, ItemPosition.WEAPON_ITEM);
    public static final Item LAPLACE_NO_TSUE = new Wand("ラプラスの杖", "すべてを見通す魔女の杖(ちからが1.75倍)", 1.75, 55000, ItemPosition.WEAPON_ITEM);
}
