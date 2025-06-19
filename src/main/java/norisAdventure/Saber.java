package norisAdventure;

public class Saber extends Hero {

    public Saber(String name) {
        super(name);
        this.setPosition(Position.SABER);
    }

    /**
     * 進化時に呼ばれるコンストラクタ
     * @param originalCharacter 進化元のキャラクター
     */
    public Saber(Human originalCharacter) {
        super(originalCharacter.getName());

        // 元のステータスを引き継ぐ
        this.setLv(originalCharacter.getLv());
        this.setHp(originalCharacter.getHp());
        this.setMp(originalCharacter.getMp());
        this.setPower(originalCharacter.getPower());
        this.setSpeed(originalCharacter.getSpeed());
        this.setExperience(originalCharacter.getExperience());
        this.setExperienceToNextLevel(originalCharacter.getExperienceToNextLevel());
        this.setPosition(Position.SABER);

        // 持ち物などもここで引き継ぐ


        // 進化ボーナスを追加 (全体的に大幅に上昇)
        int hpBonus = 100;
        int mpBonus = 50;
        int powerBonus = 60;
        int speedBonus = 40;

        this.setHp(this.getHp() + hpBonus);
        this.setMp(this.getMp() + mpBonus);
        this.setPower(this.getPower() + powerBonus);
        this.setSpeed(this.getSpeed() + speedBonus);
    }

    @Override
    public void updateAbility() {
        // セイバーのレベルアップ (勇者より成長率が高い)
        int hpUp = 18;
        int mpUp = 6;
        int powerUp = 7;
        int speedUp = 5;

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
        System.out.println(this.getName() + "は空中から剣で攻撃した！");
    }
}