package norisAdventure;

public class Wizard extends Human {

    public Wizard(String name) {
        super(name);
        this.setPosition(Position.WIZARD);
        // 初期ステータス設定 (MPとちから(魔力)が高い)
        this.setLv(1);
        this.setHp(25);
        this.setMp(60);
        this.setPower(25);
        this.setSpeed(8);
    }

    @Override
    public void updateAbility() {
        // 魔法使いのレベルアップ時のステータス上昇 (MPとちからの伸びが良い)
        int hpUp = 6;
        int mpUp = 9;
        int powerUp = 6;
        int speedUp = 1;

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