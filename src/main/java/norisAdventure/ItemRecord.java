package norisAdventure;

public record ItemRecord(Item item, int posseesedNumber) { // inventoryの中身

    public void useInCombat(Human targetHuman) {
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
            if (this.posseesedNumber() == 1) {
                Human.getInventory().remove(this);
            } else {
                Human.getInventory().remove(this);
                Human.getInventory().add(new ItemRecord(this.item(), this.posseesedNumber() - 1));
                Weapon weapon = (Weapon) this.item();
                targetHuman.setWeapon(weapon);
            }
        } else {
            System.out.println("道具を装備することはできません");
        }
    }
}
