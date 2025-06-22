package norisAdventure;

import java.util.List;
import java.util.Scanner;

public class ItemShop {

    // 店で販売するすべてのアイテムのカタログ（マスターリスト）
    private static final List<Item> shopStock = List.of(
            // 回復アイテム
            RecoveryItem.YAKUSO,
            RecoveryItem.JO_YAKUSO,
            RecoveryItem.TOKUJO_YAKUSO,
            RecoveryItem.MAGIC_WATER,
            RecoveryItem.JO_MAGIC_WATER,
            RecoveryItem.TOKUJO_MAGIC_WATER,

            // 強化アイテム
            BuffItem.CHIKARA_DAMA,
            BuffItem.MAMORI_DAMA,
            BuffItem.SUBAYASA_DAMA,

            // 武器
            // Sword
            Sword.KINO_TSURUGI,
            Sword.HAGANE_NO_TSURUGI,
            Sword.MAJIN_NO_TSURUGI,
            Sword.EXCALIBUR,
            // Axe
            Axe.KINO_ONO,
            Axe.BATTLE_AXE,
            Axe.HAO_NO_ONO,
            Axe.MJOLNIR,
            // Wand
            Wand.KINO_TSUE,
            Wand.AURORA_NO_TSUE,
            Wand.KENJA_NO_TSUE,
            Wand.LAPLACE_NO_TSUE
    );

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * アイテムショップのメインメニューを開き、処理をループする
     */
    public static void openShopMenu() {
        System.out.println("\n--- アイテムショップへようこそ！ ---");
        loop: // whileループにラベルを付ける
        while (true) {
            System.out.println("\n現在の所持金: " + Human.getMoney() + "G");
            System.out.println("-------------- コマンドを選択してください --------------");
            System.out.println("1. 買う");
            System.out.println("2. 売る");
            System.out.println("3. ショップを出る");
            System.out.println("----------------------------------------------------");
            System.out.print("> ");
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    handleBuy(); // staticメソッドに変更したため呼び出し可能
                    break;
                case "2":
                    handleSell(); // staticメソッドに変更したため呼び出し可能
                    break;
                case "3":
                    System.out.println("ありがとうございました！");
                    break loop; // ラベル付きbreakでループを抜ける
                default:
                    System.out.println("正しいコマンドを入力してください。");
                    break;
            }
        }
    }

    /**
     * 商品一覧を表示し、購入処理を行う
     */
    private static void handleBuy() {
        while (true) {
            System.out.println("\n--- 商品リスト ---");
            for (int i = 0; i < shopStock.size(); i++) {
                Item item = shopStock.get(i);
                System.out.printf("%d. %-15s | %-5d G | %s\n", (i + 1), item.getName(), item.getPrice(), item.getDescription());
            }
            System.out.println("-----------------------------------------");
            System.out.println("購入したい商品の番号を入力してください (戻る場合は'q'を入力)");
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                break; // 購入ループを抜けてメニューに戻る
            }

            try {
                int choice = Integer.parseInt(input) - 1;
                if (choice >= 0 && choice < shopStock.size()) {
                    System.out.println("いくつ購入しますか？");
                    System.out.print("> ");
                    int itemNum = Integer.parseInt(scanner.nextLine());
                    Item selectedItem = shopStock.get(choice);
                    selectedItem.buy(itemNum);
                } else {
                    System.out.println("正しい番号を入力してください。");
                }
            } catch (NumberFormatException e) {
                System.out.println("数値または'q'を入力してください。");
            }
        }
    }

    /**
     * プレイヤーの持ち物を表示し、売却処理を行う
     */
    private static void handleSell() {
        while (true) {
            System.out.println("\n--- あなたの持ち物 ---");
            List<ItemRecord> inventory = Human.getInventory();
            if (inventory.isEmpty()) {
                System.out.println("売れるものがありません。");
                break; // メニューに戻る
            }

            for (int i = 0; i < inventory.size(); i++) {
                ItemRecord record = inventory.get(i);
                int sellPrice = record.item().getPrice() / 2; // 売値は半額
                System.out.printf("%d. %-15s x%-2d (売値: %d G)\n", (i + 1), record.item().getName(), record.posseesedNumber(), sellPrice);
            }
            System.out.println("-----------------------------------------");
            System.out.println("売却したいアイテムの番号を入力してください (戻る場合は'q'を入力)");
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                break; // 売却ループを抜けてメニューに戻る
            }

            try {
                int choice = Integer.parseInt(input) - 1;
                if (choice >= 0 && choice < inventory.size()) {
                    System.out.println("いくつ売却しますか？");
                    System.out.print("> ");
                    int itemNum = Integer.parseInt(scanner.nextLine());
                    ItemRecord selectedRecord = inventory.get(choice);
                    selectedRecord.item().sell(itemNum);
                } else {
                    System.out.println("正しい番号を入力してください。");
                }
            } catch (NumberFormatException e) {
                System.out.println("数値または'q'を入力してください。");
            }
        }
    }
}