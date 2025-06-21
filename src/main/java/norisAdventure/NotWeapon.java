package norisAdventure;

public abstract class NotWeapon extends Item {
    public NotWeapon(String name, String description, double itemPower, int price, ItemPosition itemPosition) {
        super(name, description, itemPower, price, itemPosition);
    }

    public abstract void effect(Human targetHuman);
}
