package norisAdventure;

public class Hero extends Human {

    public Hero(String name) {
        super(name);
        this.setPosition(Position.HERO);
        // 初期ステータス設定 (全体的にバランスが良い)
        this.setLv(1);
        this.setHp(45);
        this.setMp(20);
        this.setPower(18);
        this.setSpeed(15);
    }

    @Override
    public void updateAbility() {
        // 勇者のレベルアップ時のステータス上昇 (バランス良く成長)
        int hpUp = 12;
        int mpUp = 4;
        int powerUp = 4;
        int speedUp = 3;

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
        System.out.println(this.getName() + "は剣で攻撃した！");
    }
}