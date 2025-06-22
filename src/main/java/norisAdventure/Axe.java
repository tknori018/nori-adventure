package norisAdventure;

public class Axe extends Weapon {
    public final WeaponType weaponType = WeaponType.AXE;

    public Axe(String name, String description, double itemPower, int price, ItemPosition itemPosition) {
        super(name, description,itemPower, price, itemPosition);
    }

    // --- アイテム定義 ---
    public static final Item KINO_ONO = new Axe("木の斧", "木でできた簡単な斧(ちからが1.15倍)", 1.15, 100, ItemPosition.WEAPON_ITEM);
    public static final Item BATTLE_AXE = new Axe("バトルアックス", "実戦用に作られた戦斧(ちからが1.35倍)", 1.35, 1500, ItemPosition.WEAPON_ITEM);
    public static final Item HAO_NO_ONO = new Axe("覇王の斧", "大陸を制した覇王が愛用した巨大な斧(ちからが1.55倍)", 1.55, 12000, ItemPosition.WEAPON_ITEM);
    public static final Item MJOLNIR = new Axe("ムジョルニア", "雷神が振るったとされる伝説のハンマー(ちからが1.85倍)", 1.85, 65000, ItemPosition.WEAPON_ITEM);
}
