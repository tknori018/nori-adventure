package norisAdventure;

public class Bat extends FlyingMonster {

    public Bat(String name, Area area) {
        super(name, area);
        // コウモリのステータスを設定
        setHp(40);
        setMaxHp(40);
        setMp(5);
        setMaxMp(5);
        setPower(18);
        setSpeed(25);
        setMonsterXp(25);
        setMonsterMoney(20);
    }

    @Override
    public void displayAttackMessage() {
        System.out.println(this.getName() + "は鋭い牙で噛み付いてきた！");
    }
}