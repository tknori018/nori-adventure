package norisAdventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        this.party = new ArrayList<>();
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
            System.out.println("\nコマンドを選択してください: 1. 戦う  2. ステータスを見る  9. 冒険を終える");
            System.out.print("> ");
            String command = scanner.nextLine();
            switch (command) {
                case "1" ->processAfterBattle(500000); // 仮の戦闘処理: 経験値を50万獲得する
                case "2" -> viewPartyStatus();
                case "9" -> {
                    System.out.println("冒険を終了します。");
                    return; // ループを抜けてゲーム終了
                }
                default -> System.out.println("正しいコマンドを入力してください。");
            }
        }
    }

    /**
     * 戦闘後などの経験値処理
     * @param earnedXp 獲得した経験値
     */
    public void processAfterBattle(int earnedXp) {
        System.out.println("\n--- 戦闘終了: " + earnedXp + " の経験値を獲得！ ---");

        List<Human> nextParty = new ArrayList<>();
        for (Human member : this.party) {
            // 経験値を与える
            member.gainExperience(earnedXp);
        }
    }

    /**
     * 現在のパーティーメンバーのステータスを表示する
     */
    public void viewPartyStatus() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        for (Human member : this.party) {
            member.viewAbility();
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}