package norisAdventure;

public class Sword extends Weapon {
    public static final WeaponType type = WeaponType.Sword;

    public Sword(String name, String description, int itemPower, int price) {
        super(name, description, itemPower, price);
    }

    public static final Item KINO_TSURUGI = new Sword("木の剣", "木でできた簡単な剣", 15, 100);
}
