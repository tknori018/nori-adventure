package norisAdventure;

public abstract class FlyingMonster extends Monster {
    public FlyingMonster(String name, Area area) {
        super(name, area);
        setFlyable(Flyable.TRUE);
    }
}
