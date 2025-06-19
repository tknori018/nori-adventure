package norisAdventure;

public abstract class Weapon extends Item {
    private int weaponPower;

    public Weapon() {
        super();
        setAvailable(Available.FALSE);
        setEquipable(Equipable.TRUE);
    }

    @Override
    public void use() {
        System.out.println("武器を使用することはできません");
    }

    @Override
    public void equip() {

    }
}
