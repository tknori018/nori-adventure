package norisAdventure;

public class Axe extends Weapon {
    public final WeaponType weaponType = WeaponType.AXE;

    public Axe(String name, String description, double itemPower, int price, ItemPosition itemPosition) {
        super(name, description,itemPower, price, itemPosition);
    }

    public static final Item KINO_ONO = new Axe("木の斧", "木でできた簡単な斧(ちからが1.1倍)", 1.1, 100, ItemPosition.WEAPON_ITEM);
}
