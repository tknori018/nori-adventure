package norisAdventure;

public class Wand extends Weapon {
    public final WeaponType weaponType = WeaponType.WAND;

    public Wand(String name, String description, double itemPower, int price, ItemPosition itemPosition) {
        super(name, description,itemPower, price, itemPosition);
    }

    public static final Item KINO_TUE = new Wand("木の杖", "木でできた簡単な杖", 15, 100, ItemPosition.WEAPON_ITEM);
}
