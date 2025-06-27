package norisAdventure;

import java.util.Optional;

import static norisAdventure.Item.findItemRecordInInventory;

public record ItemRecord(Item item, int posseesedNumber) { // inventoryの中身

    public void useInBattle(Human targetHuman) {
        Human.showInventory();
        if (this.item().getItemPosition() == ItemPosition.WEAPON_ITEM) {
            System.out.println("武器は使用できません");
        } else {
            use(targetHuman);
        }
    }

    public void useInBack(Human targetHuman) {
        switch (this.item().getItemPosition()) {
            case BUFF_ITEM -> System.out.println("ここでは強化アイテムは使用できません");
            case RECOVERY_ITEM -> use(targetHuman);
            case WEAPON_ITEM -> System.out.println("武器は使用できません");
        }
    }

    public void use(Human targetHuman) {
        if (this.posseesedNumber() == 1) {
            Human.getInventory().remove(this);
        } else {
            Human.getInventory().remove(this);
            Human.getInventory().add(new ItemRecord(this.item(), this.posseesedNumber() - 1));
            NotWeapon notWeapon = (NotWeapon) this.item();
            notWeapon.effect(targetHuman);
        }
    }

    public void equip(Human targetHuman) {
        if (this.item().getItemPosition() == ItemPosition.WEAPON_ITEM) {
            // 装備されていた場合は装備を外す
            if (targetHuman.getWeapon() != null) {
                Optional<ItemRecord> existingRecordOpt = findItemRecordInInventory(targetHuman.getWeapon());
                if (existingRecordOpt.isPresent()) {
                    // インベントリにある場合
                    ItemRecord record = existingRecordOpt.get();
                    Human.getInventory().remove(record);
                    Human.getInventory().add(new ItemRecord(record.item(), record.posseesedNumber() + 1));
                } else {
                    // インベントリにない場合
                    Human.getInventory().add(new ItemRecord(targetHuman.getWeapon(), 1));
                }
                // 上昇していた攻撃力をリセット
                targetHuman.setPower( (int) (targetHuman.getPower() / targetHuman.getWeapon().getItemPower()));
            }

            // 武器を装備
            Weapon weapon = (Weapon) this.item();
            targetHuman.setWeapon(weapon);
            targetHuman.setPower( (int) (targetHuman.getPower() * this.item().getItemPower()));
            System.out.println(targetHuman.getName() + "は" + weapon.getName() + "を装備した！");

            // 所持数を変える
            if (this.posseesedNumber() == 1) {
                Human.getInventory().remove(this);
            } else {
                Human.getInventory().remove(this);
                Human.getInventory().add(new ItemRecord(this.item(), this.posseesedNumber() - 1));
            }
        } else {
            System.out.println("道具を装備することはできません");
        }
    }
}
