package norisAdventure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static norisAdventure.GameManager.party;

public abstract class Human extends Character implements Creature {

    private Position position;
    private boolean gardBoolean;
    private double powerBuff;
    private double speedBuff;
    private double defenseBuff;
    private double powerUpDuration;
    private double speedUpDuration;
    private double defenseUpDuration;
    private int experience; // 現在の総経験値
    private int experienceToNextLevel; // 次のレベルまでに必要な経験値
    private static int money; // 所持金
    private static List<ItemRecord> inventory = new ArrayList<>();// 持ち物
    private Weapon weapon; // Humanクラスの武器

    public Human(String name) {
        super(name);
        this.gardBoolean = false;
        this.powerBuff = 1;
        this.speedBuff = 1;
        this.defenseBuff = 1;
        this.powerUpDuration = 0;
        this.speedUpDuration = 0;
        this.defenseUpDuration = 0;
        this.experience = 0;
        this.experienceToNextLevel = 100;
        setArea(Area.NORMAL);
        this.weapon = null;
        setFlyable(Flyable.FALSE);
    }

    @Override
    public void guard() {
        System.out.println(this.getName() + "は身を守った！");

        // 攻撃軽減処理
        setDefenseBuff((int) (getDefenseBuff() * 1.5));
        this.gardBoolean = true;
    }

    public void viewAbility() {
        System.out.printf("名前: %-10s | 役職: %-5s | Lv: %2d | HP: %4d | MP: %4d | 力: %3d | 速: %3d | 次のLvまで: %5d XP\n", getName(), getPosition().getLabel(), getLv(), getHp(), getMp(), getPower(), getSpeed(), Math.max(0, this.experienceToNextLevel - this.experience));
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
                party.set(0, saber);
            }
            case WARRIOR -> {
                Berserker berserker = (Berserker) evolvedCharacter;
                party.set(1, berserker);
            }
            case WIZARD -> {
                MagicEmperor magicEmperor = (MagicEmperor) evolvedCharacter;
                party.set(2, magicEmperor);
            }
            case HEALER -> {
                Saint saint = (Saint) evolvedCharacter;
                party.set(3, saint);
            }
        }
        ;
    }

    /**
     * パーティーの持ち物を、種類別・価格順にソートして綺麗に表示します。
     */
    public static void showInventory() {
        System.out.println("\n--- もちもの ---");
        List<ItemRecord> inventory = getInventory();

        if (inventory.isEmpty()) {
            System.out.println("なにももっていない");
            System.out.println("----------------");
            return;
        }

        // アイテムの種類(sortOrder)で昇順ソート
        // 種類が同じ場合は、価格(price)で昇順ソート
        Comparator<ItemRecord> itemComparator = Comparator.comparing((ItemRecord r) -> r.item().getItemPosition().getSortOrder()).thenComparing((ItemRecord r) -> r.item().getPrice());

        // Stream APIを使ってソートし、結果を表示
        inventory.stream().sorted(itemComparator).forEach(record -> {
            System.out.printf("・%-15s x%-2d | %s\n", record.item().getName(), record.posseesedNumber(), record.item().getDescription());
        });

        System.out.println("----------------");


        loop3: // コマンド3のループ
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("--------------コマンドを選択してください--------------");
            System.out.println("""
                    1. アイテムを使用
                    2. 武器を装備
                    3. 戻る
                    """);
            System.out.println("--------------------------------------------------");
            System.out.print("> ");
            String command = scanner.nextLine();
            switch (command) {
                case "1" -> {
                    // アイテムの選択
                    System.out.println("使用するアイテムを入力してください");
                    System.out.print("> ");
                    String itemName = scanner.nextLine();
                    for (ItemRecord itemRecord : Human.getInventory()) {
                        if (itemRecord.item().getName().equals(itemName)) {
                            // 使用対象の選択
                            System.out.println("誰に使用しますか？");
                            System.out.print("> ");
                            String targetHumanName = scanner.nextLine();
                            for (Human human : party) {
                                if (human.getName().equals(targetHumanName)) {
                                    itemRecord.useInBack(human);
                                    continue loop3;
                                }
                            }
                            System.out.println(targetHumanName + "はいません！");
                        }
                    }
                    System.out.println(itemName + "がみつかりません！");
                }
                case "2" -> {
                    // 武器の選択
                    System.out.println("使用する武器を入力してください");
                    System.out.print("> ");
                    String itemName = scanner.nextLine();
                    for (ItemRecord itemRecord : Human.getInventory()) {
                        if (itemRecord.item().getName().equals(itemName)) {
                            // 使用対象の選択
                            System.out.println("誰に装備しますか？");
                            System.out.print("> ");
                            String targetHumanName = scanner.nextLine();
                            for (Human human : party) {
                                if (human.getName().equals(targetHumanName)) {
                                    itemRecord.equip(human);
                                    continue loop3;
                                }
                            }
                            System.out.println(targetHumanName + "はいません！");
                        }
                    }
                    System.out.println(itemName + "がみつかりません！");
                }
                case "3" -> {
                    break loop3;
                }
                default -> System.out.println("正しいコマンドを入力してください。");
            }
        }
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

// getter, setter

    public boolean isGardBoolean() {
        return gardBoolean;
    }

    public void setGardBoolean(boolean gardBoolean) {
        this.gardBoolean = gardBoolean;
    }

    public double getPowerBuff() {
        return powerBuff;
    }

    public void setPowerBuff(double powerBuff) {
        this.powerBuff = powerBuff;
    }

    public double getSpeedBuff() {
        return speedBuff;
    }

    public void setSpeedBuff(double speedBuff) {
        this.speedBuff = speedBuff;
    }

    public double getDefenseBuff() {
        return defenseBuff;
    }

    public void setDefenseBuff(double defenseBuff) {
        this.defenseBuff = defenseBuff;
    }

    public double getPowerUpDuration() {
        return powerUpDuration;
    }

    public void setPowerUpDuration(double powerUpDuration) {
        this.powerUpDuration = powerUpDuration;
    }

    public double getSpeedUpDuration() {
        return speedUpDuration;
    }

    public void setSpeedUpDuration(double speedUpDuration) {
        this.speedUpDuration = speedUpDuration;
    }

    public double getDefenseUpDuration() {
        return defenseUpDuration;
    }

    public void setDefenseUpDuration(double defenseUpDuration) {
        this.defenseUpDuration = defenseUpDuration;
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
