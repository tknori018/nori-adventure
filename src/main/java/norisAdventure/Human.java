package norisAdventure;

import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Array.set;

public abstract class Human extends Character implements Creature {
    public record ItemRecord(Item item, int number) {} // inventoryの中身

    public static List<Human> party = new ArrayList<>();
    private Position position;
    private int experience; // 現在の総経験値
    private int experienceToNextLevel; // 次のレベルまでに必要な経験値
    private static int money; // 所持金
    private static List<ItemRecord> inventory; // 持ち物
    private Weapon weapon; // Humanクラスの武器

    public Human(String name) {
        super(name);
        this.experience = 0;
        this.experienceToNextLevel = 100;
        setArea(Area.NORMAL);
        this.weapon = null;
        setFlyable(Flyable.FALSE);
    }

    @Override
    public void guard() {
        System.out.println(this.getName() + "は身を守った！");

        // TODO: 攻撃軽減処理
    }

    public void viewAbility() {
        System.out.printf("名前: %-10s | 役職: %-5s | Lv: %2d | HP: %4d | MP: %4d | 力: %3d | 速: %3d | 次のLvまで: %5d XP\n",
                getName(), getPosition().getLabel(), getLv(), getHp(), getMp(), getPower(), getSpeed(),
                Math.max(0, this.experienceToNextLevel - this.experience));
    }

    // 各サブクラスで実装するレベルアップ時のステータス上昇処理
    public abstract void updateAbility();

    public void levelUp() {
        // 経験値の繰り越し処理
        this.experience -= this.experienceToNextLevel;

        // レベルを1上げる
        setLv(getLv() + 1);
        System.out.println("★☆★ " + getName() + " は レベル " + getLv() + " に上がった！ ★☆★");

        // 次のレベルアップに必要な経験値を更新
        int newExpToNext = (int) (this.experienceToNextLevel * 1.2);
        setExperienceToNextLevel(newExpToNext);

        // サブクラスで定義されたステータス上昇を呼び出す
        updateAbility();

        // レベル40以上で進化
        if (getLv() == 40) evolve();
    }

    public void gainExperience(int gainedExperience) {
        if (this.getLv() >= 99) return; // レベルキャップ

        System.out.println(this.getName() + " は " + gainedExperience + " の経験値を獲得した！");
        this.experience += gainedExperience;

        while (this.experience >= this.experienceToNextLevel) {
            levelUp();
            if (this.getLv() >= 99) {
                this.experience = 0;
                break;
            }
        }
        viewAbility(); // レベルアップ後のステータスを表示
    }

    public void evolve() {
        Position currentPosition = this.getPosition();
        Human evolvedCharacter = switch (currentPosition) {
            case HERO -> new Saber(this);
            case WARRIOR -> new Berserker(this);
            case WIZARD -> new MagicEmperor(this);
            case HEALER -> new Saint(this);
            default -> this;
        };

        if (evolvedCharacter != this) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("おめでとう！ " + this.getName() + " は");
            System.out.println(currentPosition.getLabel() + " から " + evolvedCharacter.getPosition().getLabel() + " に進化した！");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("ステータスが大幅に上昇した！");
        }

        switch (evolvedCharacter.getPosition()) {
            case HERO -> {
                Saber saber = (Saber) evolvedCharacter;
                GameManager.party.set(0, saber);
            }
            case WARRIOR -> {
                Berserker berserker = (Berserker) evolvedCharacter;
                GameManager.party.set(1, berserker);
            }
            case WIZARD -> {
                MagicEmperor magicEmperor = (MagicEmperor) evolvedCharacter;
                GameManager.party.set(2, magicEmperor);
            }
            case HEALER -> {
                Saint saint = (Saint) evolvedCharacter;
                GameManager.party.set(3, saint);
            }
        };
    }

    // getter, setter
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperienceToNextLevel() {
        return experienceToNextLevel;
    }

    public void setExperienceToNextLevel(int experienceToNextLevel) {
        this.experienceToNextLevel = experienceToNextLevel;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        Human.money = money;
    }

    public static List<ItemRecord> getInventory() {
        return inventory;
    }

    public static void setInventory(List<ItemRecord> inventory) {
        Human.inventory = inventory;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
