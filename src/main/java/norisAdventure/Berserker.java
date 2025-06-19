package norisAdventure;

public class Berserker extends Warrior {

    public Berserker(String name) {
        super(name);
        this.setPosition(Position.BERSERKER);
    }

    /**
     * 進化時に呼ばれるコンストラクタ
     * @param originalCharacter 進化元のキャラクター
     */
    public Berserker(Human originalCharacter) {
        super(originalCharacter.getName());

        // 元のステータスを引き継ぐ
        this.setLv(originalCharacter.getLv());
        this.setHp(originalCharacter.getHp());
        this.setMp(originalCharacter.getMp());
        this.setPower(originalCharacter.getPower());
        this.setSpeed(originalCharacter.getSpeed());
        this.setExperience(originalCharacter.getExperience());
        this.setExperienceToNextLevel(originalCharacter.getExperienceToNextLevel());
        this.setPosition(Position.BERSERKER);

        // 持ち物などもここで引き継ぐ

        // 進化ボーナスを追加 (HPとちからが大幅に上昇)
        int hpBonus = 120;
        int powerBonus = 80;
        this.setHp(this.getHp() + hpBonus);
        this.setPower(this.getPower() + powerBonus);
    }

    @Override
    public void updateAbility() {
        // 狂戦士のレベルアップ (戦士よりさらにHPとちからの成長率が高い)
        int hpUp = 20;
        int mpUp = 1; // MPはほとんど伸びない
        int powerUp = 8;
        int speedUp = 3;

        setHp(getHp() + hpUp);
        setMp(getMp() + mpUp);
        setPower(getPower() + powerUp);
        setSpeed(getSpeed() + speedUp);
        System.out.println("HPが" + hpUp + ", MPが" + mpUp + ", ちからが" + powerUp + ", すばやさが" + speedUp + "上がった！");
    }

    @Override
    public void attack() {
        System.out.println(this.getName() + "は巨大な斧を振り回した");
    }
}