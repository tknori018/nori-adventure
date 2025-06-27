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
        this.setMaxHp(originalCharacter.getMaxHp());
        this.setMaxMp(originalCharacter.getMaxMp());
        this.setPower(originalCharacter.getPower());
        this.setSpeed(originalCharacter.getSpeed());
        this.setExperience(originalCharacter.getExperience());
        this.setExperienceToNextLevel(originalCharacter.getExperienceToNextLevel());
        this.setWeapon(originalCharacter.getWeapon());
        this.setPosition(Position.SABER);

        // 進化ボーナスを追加 (全体的に大幅に上昇)
        int hpBonus = 100;
        int mpBonus = 50;
        int powerBonus = 60;
        int speedBonus = 40;

        // 最大値にもボーナスを適用
        this.setMaxHp(this.getMaxHp() + hpBonus);
        this.setMaxMp(this.getMaxMp() + mpBonus);

        // 現在のHPとMPを最大値まで回復
        this.setHp(this.getMaxHp());
        this.setMp(this.getMaxMp());

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

        // 最大値を更新
        int totalHp = getMaxHp() + hpUp;
        int totalMp = getMaxMp() + mpUp;
        setMaxHp(totalHp);
        setMaxMp(totalMp);

        // 現在のHP/MPを最大まで回復
        setHp(totalHp);
        setMp(totalMp);

        setPower(getPower() + powerUp);
        setSpeed(getSpeed() + speedUp);
        System.out.println("HPが" + hpUp + ", MPが" + mpUp + ", ちからが" + powerUp + ", すばやさが" + speedUp + "上がった！");
    }

    @Override
    public void displayAttackMessage() {
        System.out.println(this.getName() + "は空中から剣で攻撃した！");
    }
}