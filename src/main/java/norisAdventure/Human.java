package norisAdventure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static norisAdventure.GameManager.party;

public abstract class Human extends Character implements Creature {

    private Position position;
    private int experience; // 現在の総経験値
    private int experienceToNextLevel; // 次のレベルまでに必要な経験値
    private static int money; // 所持金
    private static List<ItemRecord> inventory = new ArrayList<>();// 持ち物
    private Weapon weapon; // Humanクラスの武器

    public Human(String name) {
        super(name);
        this.experience = 0;
        this.experienceToNextLevel = 100;
        setArea(Area.NORMAL);
        this.weapon = null;
        setFlyable(Flyable.FALSE);
        // 動作確認用
        money = 100000;
    }

    public void viewAbility() {
        System.out.printf("名前: %-10s | 役職: %-5s | Lv: %2d | HP: %4d/%4d | MP: %4d/%4d | 力: %3d | 速: %3d | 武器: %-8s | 次のLvまで: %5d XP\n", getName(), getPosition().getLabel(), getLv(), getHp(), getMaxHp(), getMp(), getMaxMp(), getPower(), getSpeed(), getWeapon(), Math.max(0, this.experienceToNextLevel - this.experience));
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
        int newExpToNext = (int) (this.experienceToNextLevel * 1.15);
        setExperienceToNextLevel(newExpToNext);

        // サブクラスで定義されたステータス上昇を呼び出す
        updateAbility();

        // レベル40以上で進化
        if (getLv() == 40) evolve();
    }

    public void gainExperience(int gainedExperience) {
        if (this.getLv() >= 99) return; // レベルキャップ

        System.out.println(this.getName() + " は " + gainedExperience + " の経験値を獲得した！");
        // 経験値を現在のオブジェクトに加算します。この値は進化後の新しいオブジェクトに引き継がれます。
        this.experience += gainedExperience;

        // パーティーリスト内での自分の現在のインデックスを探します。名前は進化後も変わらないため、識別に利用します。
        int partyIndex = -1;
        for (int i = 0; i < party.size(); i++) {
            if (party.get(i).getName().equals(this.getName())) {
                partyIndex = i;
                break;
            }
        }

        // 万が一リストに存在しない場合は処理を終了します。
        if (partyIndex == -1) {
            System.out.println("エラー: パーティーにキャラクターが見つかりません。");
            return;
        }

        // パーティーリストからキャラクターの最新のインスタンスを取得します。
        Human currentCharacter = party.get(partyIndex);

        // レベルアップのループ処理。進化によってインスタンスが変わりうるため、常にパーティーリストから最新の情報を取得してループを継続します。
        while (currentCharacter.getLv() < 99 && currentCharacter.getExperience() >= currentCharacter.getExperienceToNextLevel()) {
            currentCharacter.levelUp(); // levelUpは内部でevolveを呼び出し、partyリストを更新する可能性があります。

            // levelUpが実行された後、キャラクターが進化している可能性があるため、
            // partyリストから再度インスタンスを取得し直し、常に最新の状態でループの判定を行います。
            currentCharacter = party.get(partyIndex);
        }

        // 全てのレベルアップ処理が終わった後の最終的なステータスを表示します。
        currentCharacter.viewAbility();
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
            case SABER -> {
                Saber saber = (Saber) evolvedCharacter;
                party.set(0, saber);
            }
            case BERSERKER -> {
                Berserker berserker = (Berserker) evolvedCharacter;
                party.set(1, berserker);
            }
            case MAGIC_EMPEROR -> {
                MagicEmperor magicEmperor = (MagicEmperor) evolvedCharacter;
                party.set(2, magicEmperor);
            }
            case SAINT -> {
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
