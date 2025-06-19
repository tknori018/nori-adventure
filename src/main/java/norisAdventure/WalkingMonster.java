package norisAdventure;

public abstract class WalkingMonster extends Monster {
    public WalkingMonster(String name, Area area) {
        super(name, area);
        setFlyable(Flyable.FALSE);
    }
}
