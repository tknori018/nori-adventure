package norisAdventure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static norisAdventure.ItemShop.openShopMenu;

public class GameManager {
    // GameManagerがパーティーリストを管理する
    public static List<Human> party;
    private Scanner scanner = new Scanner(System.in); // 入力処理も一元化

    /**
     * GameManagerのコンストラクタ
     * ゲームの初期化処理を行う
     */
    public GameManager() {
        System.out.println("--- Nori's Adventure ---");
        initializeParty(); // パーティー初期化メソッドを呼び出す
    }

    /**
     * パーティーメンバーの名前を入力させ、インスタンスを生成・初期化する
     */
    private void initializeParty() {
        party = new ArrayList<>();
        String[] positions = {Position.HERO.getLabel(), Position.WARRIOR.getLabel(), Position.WIZARD.getLabel(), Position.HEALER.getLabel()};
        String[] names = new String[4];

        for (int i = 0; i < names.length; i++) {
            String name;
            while (true) {
                // 名前の入力
                System.out.printf("「%s」の名前を入力してください (1~8文字) > ", positions[i]);
                name = scanner.nextLine();
                // 名前のバリデーション
                if (name.trim().isEmpty() || name.length() > 8) {
                    System.out.println("1~8文字で名前を入力してください。");
                } else {
                    names[i] = name;
                    break;
                }
            }
        }

        // 入力された名前でキャラクターを生成し、パーティーに追加
        party.add(new Hero(names[0]));
        party.add(new Warrior(names[1]));
        party.add(new Wizard(names[2]));
        party.add(new Healer(names[3]));

        System.out.println("\n--- パーティーが結成された！ ---");
        viewPartyStatus();
    }

    /**
     * ゲームのメインループ
     */
    public void gameLoop() {
        System.out.println("\n--- 冒険の始まり ---");
        while (true) {
            System.out.println("\n--------------コマンドを選択してください--------------");
            System.out.println("""
                    1. 戦う
                    2. ステータスを見る
                    3. もちもの
                    8. アイテムショップ
                    9. 冒険を終える
                    """);
            System.out.println("--------------------------------------------------");
            System.out.print("> ");
            String mainCommand = scanner.nextLine();
            switch (mainCommand) {
                case "1" -> {
                    // TODO: フィールドや場所に応じて出現モンスターを変える
                    BattleLoop(Area.NORMAL);
                }
                case "2" -> viewPartyStatus();
                case "3" -> {
                    // HumanクラスのshowInventoryメソッドが、表示からアイテム使用/装備までのループをすべて担当する
                    Human.showInventory();
                }
                case "8" -> openShopMenu();
                case "9" -> {
                    System.out.println("冒険を終了します。");
                    return; // ループを抜けてゲーム終了
                }
                default -> System.out.println("正しいコマンドを入力してください。");
            }
        }
    }

    /**
     * 戦闘フェーズ
     */
    public void BattleLoop(Area area) {
        System.out.println("\n--- 戦闘開始！ ---");

        // --- 戦闘準備 ---
        int earnedMoney = 0;
        int earnedXp = 0;
        List<Monster> monsters = createMonsters(area);
        for (Monster monster : monsters) {
            earnedMoney += monster.getMonsterMoney();
            earnedXp += monster.getMonsterXp();
        }

        // 戦闘に参加する全キャラクターのリストを作成
        List<Character> allCharacters = new ArrayList<>();
        allCharacters.addAll(party);
        allCharacters.addAll(monsters);

        // --- 戦闘ループ ---
        int turn = 1;
        while (true) {
            System.out.println("\n--- " + turn + "ターン目 ---");

            // --- 行動順決定 ---
            List<Character> orderOfAction = new ArrayList<>(allCharacters);
            orderOfAction.sort(Comparator.comparingInt(Character::getSpeed).reversed());

            // --- 各キャラクターの行動 ---
            for (Character character : orderOfAction) {
                // HPが0のキャラクターは行動不可
                if (character.getHp() <= 0) {
                    continue;
                }

                // 前のターンに防御していた場合、防御バフを元に戻す
                if (character instanceof Human human && human.isGardBoolean()) {
                    human.setDefenseBuff(human.getDefenseBuff() / 1.5); // guard()で掛けた倍率で元に戻す
                    human.setGardBoolean(false);
                    System.out.println(human.getName() + "は防御をやめた。");
                }

                // 行動選択
                battleAction(character, allCharacters);

                // --- 行動後の勝敗判定 ---
                long aliveMonsters = monsters.stream().filter(m -> m.getHp() > 0).count();
                if (aliveMonsters == 0) {
                    System.out.println("\nすべてのモンスターをやっつけた！");
                    processAfterBattle(earnedMoney, earnedXp);
                    return; // 戦闘終了
                }

                long aliveHumans = party.stream().filter(h -> h.getHp() > 0).count();
                if (aliveHumans == 0) {
                    System.out.println("\nパーティーは全滅してしまった...");
                    // ゲームオーバー処理(HPを回復、所持金を半分にする)
                    for (Human human : party) {
                        human.setHp(human.getMaxHp());
                    }
                    Human.setMoney(Human.getMoney() / 2);
                    return; // 戦闘終了
                }
            }
            turn++;
        }
    }

    /**
     * 戦闘中の行動選択
     */
    public void battleAction(Character character, List<Character> allCharacters) {
        // --- モンスターの行動 ---
        if (character instanceof Monster monster) {
            List<Human> availableTargets = party.stream()
                    .filter(h -> h.getHp() > 0)
                    .toList();

            if (!availableTargets.isEmpty()) {
                System.out.println("\n--- " + monster.getName() + " のターン ---");
                int targetIndex = (int) (Math.random() * availableTargets.size());
                Human target = availableTargets.get(targetIndex);
                // TODO: 攻撃手段を増やす
                monster.attack(target);
            }
            return;
        }

        // --- プレイヤー（Human）の行動 ---
        if (character instanceof Human currentHuman) {
            while (true) {
                System.out.println("\n--- " + currentHuman.getName() + " のターン ---");
                System.out.println("どうする？");
                System.out.println("1. 攻撃");
                System.out.println("2. 防御");
                System.out.println("3. 道具");
                System.out.println("4. ステータスを見る");
                System.out.print("> ");
                String command = scanner.nextLine();

                switch (command) {
                    case "1": // 攻撃
                        List<Monster> attackableMonsters = allCharacters.stream()
                                .filter(c -> c instanceof Monster && c.getHp() > 0)
                                .map(c -> (Monster) c)
                                .toList();

                        if (attackableMonsters.isEmpty()) {
                            System.out.println("攻撃できる相手がいない！");
                            continue;
                        }

                        System.out.println("誰に攻撃しますか？");
                        for (int i = 0; i < attackableMonsters.size(); i++) {
                            System.out.println((i + 1) + ". " + attackableMonsters.get(i).getName());
                        }
                        System.out.print("> ");
                        try {
                            int targetIndex = Integer.parseInt(scanner.nextLine()) - 1;
                            if (targetIndex >= 0 && targetIndex < attackableMonsters.size()) {
                                Monster target = attackableMonsters.get(targetIndex);
                                // TODO: 攻撃手段を増やす
                                currentHuman.attack(target);
                                return; // 行動終了
                            } else {
                                System.out.println("正しい番号を入力してください。");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("数値を入力してください。");
                        }
                        break;

                    case "2": // 防御
                        currentHuman.guard();
                        return; // 行動終了

                    case "3": // 道具
                        if (useItemInBattle(currentHuman)) {
                            return; // アイテム使用に成功したらターン終了
                        }
                        // 使用に失敗、またはキャンセルした場合はループ継続
                        break;

                    case "4": // ステータス
                        viewPartyStatus();
                        break;

                    default:
                        System.out.println("正しいコマンドを入力してください。");
                        break;
                }
            }
        }
    }

    /**
     * 戦闘中にアイテムを使用する処理
     * @param user 使用するキャラクター
     * @return アイテムの使用に成功したらtrue, 失敗またはキャンセルしたらfalse
     */
    public boolean useItemInBattle(Human user) {
        Human.showInventory(); // 持ち物一覧を表示
        if (Human.getInventory().isEmpty()) {
            return false; // 持ち物がなければ終了
        }

        System.out.println("使用するアイテム名を入力してください（やめる場合は'q'）");
        System.out.print("> ");
        String itemName = scanner.nextLine();

        if (itemName.equalsIgnoreCase("q")) {
            return false; // キャンセル
        }

        var itemRecordOpt = Human.getInventory().stream()
                .filter(ir -> ir.item().getName().equals(itemName))
                .findFirst();

        if (itemRecordOpt.isEmpty()) {
            System.out.println("そのアイテムは持っていません。");
            return false;
        }

        ItemRecord itemRecord = itemRecordOpt.get();
        if (itemRecord.item().getItemPosition() == ItemPosition.WEAPON_ITEM) {
            System.out.println("戦闘中に武器は使用できません。");
            return false;
        }

        System.out.println("誰に使用しますか？");
        List<Human> availableTarget = party.stream()
                .filter(human -> human.getHp() > 0)
                .collect(Collectors.toList());
        for (int i = 0; i < availableTarget.size(); i++) {
            System.out.println((i + 1) + ". " + availableTarget.get(i).getName());
        }
        System.out.print("> ");

        try {
            int targetIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (targetIndex >= 0 && targetIndex < availableTarget.size()) {
                Human target = availableTarget.get(targetIndex);
                itemRecord.useInBattle(target); // 戦闘中用のメソッドを呼び出す
                return true; // 使用成功
            } else {
                System.out.println("正しい番号を入力してください。");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("数値を入力してください。");
            return false;
        }
    }


    /**
     * 戦闘後などの経験値処理
     */
    public void processAfterBattle(int earnedMoney, int earnedXp) {
        System.out.println("\n--- 戦闘終了: " + earnedMoney + "G と " + earnedXp + "XP を獲得！ ---");
        Human.setMoney(Human.getMoney() + earnedMoney);
        party.forEach(member -> {
            if (member.getHp() > 0) { // 生き残っているメンバーのみ経験値を獲得
                member.gainExperience(earnedXp);
            }
        });
    }

    /**
     * Monsterを作成
     */
    public List<Monster> createMonsters(Area area) {
        // 将来的にareaによって出現モンスターを変える
        List<Monster> monsters = new ArrayList<>();
        // 例：モンスターを2体だけ出現させる
        monsters.add(new Slime("スライムA", area));
        monsters.add(new Bat("オオコウモリB", area));
        return monsters;
    }

    /**
     * 現在のパーティーメンバーのステータスを表示する
     */
    public void viewPartyStatus() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("所持金: " + Human.getMoney() + "G");
        for (Human member : party) {
            member.viewAbility();
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}