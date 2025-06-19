package norisAdventure;

public class Axe extends Weapon {
    public static final WeaponType type = WeaponType.Axe;

    public Axe(String name, String description, int itemPower, int price) {
        super(name, description, itemPower, price);
    }

    public static final Item KINO_ONO = new Axe("木の斧", "木でできた簡単な斧", 15, 100);
}
