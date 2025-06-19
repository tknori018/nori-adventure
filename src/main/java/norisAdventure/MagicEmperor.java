package norisAdventure;

public class MagicEmperor extends Wizard {

    public MagicEmperor(String name) {
        super(name);
        this.setPosition(Position.MAGIC_EMPEROR);
    }

    /**
     * 進化時に呼ばれるコンストラクタ
     * @param originalCharacter 進化元のキャラクター
     */
    public MagicEmperor(Human originalCharacter) {
        super(originalCharacter.getName());

        // 元のステータスを引き継ぐ
        this.setLv(originalCharacter.getLv());
        this.setHp(originalCharacter.getHp());
        this.setMp(originalCharacter.getMp());
        this.setPower(originalCharacter.getPower());
        this.setSpeed(originalCharacter.getSpeed());
        this.setExperience(originalCharacter.getExperience());
        this.setExperienceToNextLevel(originalCharacter.getExperienceToNextLevel());
        this.setPosition(Position.MAGIC_EMPEROR);

        // 持ち物などもここで引き継ぐ


        // 進化ボーナスを追加 (MPとちからが大幅に上昇)
        int mpBonus = 180;
        int powerBonus = 100;
        this.setMp(this.getMp() + mpBonus);
        this.setPower(this.getPower() + powerBonus);
    }

    @Override
    public void updateAbility() {
        // 魔法帝のレベルアップ (魔法使いより成長率が高い)
        int hpUp = 9;
        int mpUp = 14;
        int powerUp = 9;
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
        System.out.println(this.getName() + "は魔法の矢を放った！");
    }
}