package norisAdventure;

public class Saint extends Healer {
    /**
     * 進化時に呼ばれるコンストラクタ
     * @param originalCharacter 進化元のキャラクター
     */
    public Saint(Human originalCharacter) {
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
        this.setPosition(Position.SAINT);

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
        // セイントのレベルアップ (ヒーラーより成長率が高い)
        int hpUp = 15;
        int mpUp = 12;
        int powerUp = 3;
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
    public void displayAttackMessage() {
        System.out.println(this.getName() + "は聖なる光を放った！");
    }
}