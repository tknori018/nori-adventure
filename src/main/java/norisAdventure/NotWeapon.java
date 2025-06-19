package norisAdventure;

public abstract class NotWeapon extends Item {
    public NotWeapon(String name, String description, int possessedNumber, int itemPower, int price) {
        super(name, description, itemPower, price);
        setAvailable(Available.TRUE);
        setEquipable(Equipable.FALSE);
    }

    @Override
    public void use(Human user, Human intendedUse,Item item) {

    }

    @Override
    public void equip(Human human, Item item) {
        System.out.println("道具を装備することはできません");
    }
}
