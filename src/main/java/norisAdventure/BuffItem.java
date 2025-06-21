package norisAdventure;

public class BuffItem extends NotWeapon {
    // どのステータスを強化するかを定義するenum
    public enum BuffType {
        POWER,
        DEFENSE, // 受けるダメージを軽減する
        SPEED
    }

    private final BuffType buffType;
    private final int duration;

    public BuffItem(String name, String description, double itemPower, int price, ItemPosition itemPosition, BuffType buffType, int duration) {
        super(name, description, itemPower, price, itemPosition);
        this.buffType = buffType;
        this.duration = duration;
    }

    /**
     * バフアイテムの効果を対象に適用する
     * @param targetHuman 対象となるキャラクター
     */
    @Override
    public void effect(Human targetHuman) {
        String effectMessage = "";
        switch (this.buffType) {
            case POWER:
                // targetHumanの攻撃力にバフをかける処理
                targetHuman.setPowerBuff(this.getItemPower());
                targetHuman.setPowerUpDuration(getDuration());
                effectMessage = "ちからがぐんと上がった！";
                break;
            case DEFENSE:
                // targetHumanの防御力にバフをかける処理
                targetHuman.setDefenseBuff(this.getItemPower());
                targetHuman.setDefenseUpDuration(getDuration());
                effectMessage = "みのまもりが固くなった！";
                break;
            case SPEED:
                // targetHumanのすばやさにバフをかける処理
                targetHuman.setSpeedBuff(this.getItemPower());
                targetHuman.setSpeedUpDuration(getDuration());
                effectMessage = "すばやさが飛躍的に上がった！";
                break;
        }

        System.out.println(targetHuman.getName() + "は「" + this.getName() + "」を使った！");
        System.out.println(effectMessage + " (効果は3ターン)");
    }

    // --- 強化アイテムの定義 ---
    public static final Item CHIKARA_DAMA = new BuffItem("ちから玉", "3ターンの間、味方一人の攻撃力を上げる", 1.5, 120, ItemPosition.BUFF_ITEM, BuffType.POWER, 3);
    public static final Item MAMORI_DAMA = new BuffItem("まもり玉", "3ターンの間、味方一人の防御力を上げる", 1.5, 120, ItemPosition.BUFF_ITEM, BuffType.DEFENSE, 3);
    public static final Item SUBAYASA_DAMA = new BuffItem("すばやさ玉", "3ターンの間、味方一人のすばやさを上げる", 1.5, 120, ItemPosition.BUFF_ITEM, BuffType.SPEED, 3);

    public BuffType getBuffType() {
        return buffType;
    }

    public int getDuration() {
        return duration;
    }
}