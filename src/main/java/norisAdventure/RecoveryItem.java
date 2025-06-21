package norisAdventure;

public class RecoveryItem extends NotWeapon {
    private Boolean effectedHp;

    public RecoveryItem(String name, String description, int itemPower, int price, ItemPosition itemPosition, Boolean effectedHp) {
        super(name, description, itemPower, price, itemPosition);
        this.effectedHp = effectedHp;
    }

    @Override
    public void effect(Human targetHuman) {
        if (effectedHp) {
            // HP回復
            int newHp = (int) (targetHuman.getHp() + this.getItemPower());
            // 最大HPを超えないようにする
            if (newHp > targetHuman.getMaxHp()) {
                newHp = targetHuman.getMaxHp();
            }
            targetHuman.setHp(newHp);
            System.out.println(targetHuman.getName() + "のHPが" + this.getItemPower() + "回復した！");
        } else {
            // MP回復
            int newMp = (int) (targetHuman.getMp() + this.getItemPower());
            // 最大MPを超えないようにする
            if (newMp > targetHuman.getMaxMp()) {
                newMp = targetHuman.getMaxMp();
            }
            targetHuman.setMp(newMp);
            System.out.println(targetHuman.getName() + "のMPが" + this.getItemPower() + "回復した！");
        }
    }

    // --- HP回復アイテム ---
    public static final Item YAKUSO = new RecoveryItem("やくそう", "HPを50回復する", 50, 10, ItemPosition.RECOVERY_ITEM, true);
    public static final Item JO_YAKUSO = new RecoveryItem("上やくそう", "HPを150回復する", 150, 50, ItemPosition.RECOVERY_ITEM, true);
    public static final Item TOKUJO_YAKUSO = new RecoveryItem("特上やくそう", "HPを300回復する", 300, 200, ItemPosition.RECOVERY_ITEM, true);

    // --- MP回復アイテム
    public static final Item MAGIC_WATER = new RecoveryItem("まほうのせいすい", "MPを20回復する", 20, 30, ItemPosition.RECOVERY_ITEM, false);
    public static final Item JO_MAGIC_WATER = new RecoveryItem("上まほうのせいすい", "MPを50回復する", 50, 100, ItemPosition.RECOVERY_ITEM, false);
    public static final Item TOKUJO_MAGIC_WATER = new RecoveryItem("特上まほうのせいすい", "MPを150回復する", 150, 400, ItemPosition.RECOVERY_ITEM, false);


    public Boolean getEffectedHp() {
        return effectedHp;
    }

    public void setEffectedHp(Boolean effectedHp) {
        this.effectedHp = effectedHp;
    }
}