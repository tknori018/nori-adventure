package norisAdventure;

public class Wand extends Weapon {
    public static final WeaponType type = WeaponType.Wand;

    public Wand(String name, String description, int itemPower, int price) {
        super(name, description, itemPower, price);
    }

    public static final Item KINO_TUE = new Wand("木の杖", "木でできた簡単な杖", 15, 100);
}
