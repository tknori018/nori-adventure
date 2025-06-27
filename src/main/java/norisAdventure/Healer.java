package norisAdventure;

public class Healer extends Human {

    public Healer(String name) {
        super(name);
        this.setPosition(Position.HEALER);
        // 初期ステータス設定 (HPとMPが高い)
        this.setLv(1);
        this.setHp(35);
        this.setMp(50);
        this.setPower(8);
        this.setSpeed(12);
    }

    @Override
    public void updateAbility() {
        // ヒーラーのレベルアップ時のステータス上昇 (HPとMPの伸びが良い)
        int hpUp = 10;
        int mpUp = 8;
        int powerUp = 2;
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
    public void displayAttackMessage() {
        System.out.println(this.getName() + "は杖で攻撃した！");
    }
}