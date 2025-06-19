package norisAdventure;

public class Warrior extends Human {

    public Warrior(String name) {
        super(name);
        this.setPosition(Position.WARRIOR);
        // 初期ステータス設定 (HPとちからが高い)
        this.setLv(1);
        this.setHp(50);
        this.setMp(10);
        this.setPower(20);
        this.setSpeed(10);
    }

    @Override
    public void updateAbility() {
        // 戦士のレベルアップ時のステータス上昇 (HPとちからの伸びが良い)
        int hpUp = 15;
        int mpUp = 2;
        int powerUp = 5;
        int speedUp = 2;

        int totalHp = getMaxHp() + hpUp;
        int totalMp = getMaxMp() + mpUp;

        setHp(totalHp);
        setMp(totalMp);
        setMaxHp(totalHp);
        setMaxMp(totalMp);
        setPower(getPower() + powerUp);
        setSpeed(getSpeed() + speedUp);
        System.out.println("HPが" + hpUp + ", MPが" + mpUp + ", ちからが" + powerUp + ", すばやさが" + speedUp + "上がった！");
    }

    @Override
    public void attack() {
        System.out.println(this.getName() + "は斧で力強く斬りつけた！");
    }
}