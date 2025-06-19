package norisAdventure;

public class Saint extends Healer {

    public Saint(String name) {
        super(name);
        this.setPosition(Position.SAINT);
    }

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
        this.setPower(originalCharacter.getPower());
        this.setSpeed(originalCharacter.getSpeed());
        this.setExperience(originalCharacter.getExperience());
        this.setExperienceToNextLevel(originalCharacter.getExperienceToNextLevel());
        this.setPosition(Position.SAINT);

        // 持ち物などもここで引き継ぐ


        // 進化ボーナスを追加 (MPとHPが大幅に上昇)
        int hpBonus = 80;
        int mpBonus = 150;
        this.setHp(this.getHp() + hpBonus);
        this.setMp(this.getMp() + mpBonus);
    }

    @Override
    public void updateAbility() {
        // セイントのレベルアップ (ヒーラーより成長率が高い)
        int hpUp = 15;
        int mpUp = 12;
        int powerUp = 3;
        int speedUp = 3;

        setHp(getHp() + hpUp);
        setMp(getMp() + mpUp);
        setPower(getPower() + powerUp);
        setSpeed(getSpeed() + speedUp);
        System.out.println("HPが" + hpUp + ", MPが" + mpUp + ", ちからが" + powerUp + ", すばやさが" + speedUp + "上がった！");
    }

    @Override
    public void attack() {
        System.out.println(this.getName() + "は聖なる光を放った！");
    }
}