package norisAdventure;

public abstract class Weapon extends Item {
    public Weapon(String name, String description, int itemPower, int price) {
        super(name, description, itemPower, price);
        setAvailable(Available.FALSE);
        setEquipable(Equipable.TRUE);
    }

    @Override
    public void use(Human user, Human intendedUse,Item item) {
        System.out.println("武器を使用することはできません");
    }

    @Override
    public void equip(Human Human, Item item) {

    }
}
