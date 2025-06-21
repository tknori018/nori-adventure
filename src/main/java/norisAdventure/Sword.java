package norisAdventure;

public class Sword extends Weapon {
    public final WeaponType weaponType = WeaponType.SWORD;

    public Sword(String name, String description, double itemPower, int price, ItemPosition itemPosition) {
        super(name, description,itemPower, price, itemPosition);
    }

    public static final Item KINO_TSURUGI = new Sword("木の剣", "木でできた簡単な剣", 15, 100, ItemPosition.WEAPON_ITEM);
}
