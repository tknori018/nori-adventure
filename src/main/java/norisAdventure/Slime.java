package norisAdventure;

public class Slime extends WalkingMonster {

    public Slime(String name, Area area) {
        super(name, area);
        // スライムのステータスを設定
        setHp(30);
        setMaxHp(30);
        setMp(10);
        setMaxMp(10);
        setPower(15);
        setSpeed(10);
        setMonsterXp(20);
        setMonsterMoney(15);
    }

    @Override
    public void displayAttackMessage() {
        System.out.println(this.getName() + "は体当たりをしてきた！");
    }
}