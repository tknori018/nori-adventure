package norisAdventure;

public abstract class Monster extends Character implements Creature{
    private int monsterXp;
    private int monsterMoney;

    public Monster(String name, Area area) {
        super(name);
        setArea(area);
    }

    public int getMonsterXp() {
        return monsterXp;
    }

    public void setMonsterXp(int monsterXp) {
        this.monsterXp = monsterXp;
    }

    public int getMonsterMoney() {
        return monsterMoney;
    }

    public void setMonsterMoney(int monsterMoney) {
        this.monsterMoney = monsterMoney;
    }
}
