package norisAdventure;

public abstract class NotWeapon extends Item {
    public NotWeapon() {
        super();
        setAvailable(Available.TRUE);
        setEquipable(Equipable.FALSE);
    }

    @Override
    public void use() {

    }

    @Override
    public void equip() {
        System.out.println("道具を装備することはできません");
    }
}
