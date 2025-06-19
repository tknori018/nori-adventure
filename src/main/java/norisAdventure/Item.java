package norisAdventure;

public abstract class Item {
    private String name;
    private int possessedNumber;
    private int price;
    private Available available;
    private Equipable equipable;

    public abstract void use();
    public abstract void equip();

    public void buy() {
        int remainingAmount = Human.getMoney() - this.price;
        if (remainingAmount >= 0) {
            Human.setMoney(remainingAmount);
            this.possessedNumber++;
            System.out.println("「%s」を購入しました".formatted(this.name));
        } else {
            System.out.println("お金が足りません");
        }
    }

    public void sell() {
        if (this.possessedNumber > 0) {
            possessedNumber--;
            Human.setMoney(Human.getMoney() + this.price);
            System.out.println("「%s」を売却しました".formatted(this.name));
        }
    }

    // getter, setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPossessedNumber() {
        return possessedNumber;
    }

    public void setPossessedNumber(int possessedNumber) {
        this.possessedNumber = possessedNumber;
    }

    public Available getAvailable() {
        return available;
    }

    public void setAvailable(Available available) {
        this.available = available;
    }

    public Equipable getEquipable() {
        return equipable;
    }

    public void setEquipable(Equipable equipable) {
        this.equipable = equipable;
    }
}
