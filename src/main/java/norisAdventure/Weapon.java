package norisAdventure;

public abstract class Weapon extends Item {
    public Weapon(String name, String description,double itemPower, int price, ItemPosition itemPosition) {
        super(name, description, itemPower, price, itemPosition);
    }
}
