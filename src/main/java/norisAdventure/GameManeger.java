package norisAdventure;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    // GameManagerがパーティーリストを管理する
    private List<Human> party;

    public GameManager() {
        // パーティーを初期化
        this.party = new ArrayList<>();
        party.add(new Warrior("ノリス")); // 例: 初期メンバー
        party.add(new Healer("アリス"));
    }

    // 戦闘後などの経験値処理
    public void processAfterBattle(int earnedXp) {
        System.out.println("\n--- 戦闘終了: 経験値獲得 ---");

        // ★★★ パーティーメンバーの入れ替えが発生する可能性があるため、安全なループ処理を行う ★★★
        List<Human> nextParty = new ArrayList<>();
        for (Human member : this.party) {
            // 1. 経験値を与える
            member.gainExperience(earnedXp);

            // 2. 進化チェックとインスタンスの更新
            Human evolvedMember = member.evolve();

            // 3. 次のパーティーリストに、進化した（あるいはしなかった）メンバーを追加
            nextParty.add(evolvedMember);
        }

        // 4. 新しいパーティー構成でリストを更新する
        this.party = nextParty;

        System.out.println("\n--- 現在のパーティー ---");
        viewPartyStatus();
    }

    public void viewPartyStatus() {
        for (Human member : this.party) {
            member.viewAbility();
        }
    }

    // ゲームを実行するためのメインメソッド（テスト用）
//    public static void main(String[] args) {
//        GameManager game = new GameManager();
//        System.out.println("--- 冒険開始 ---");
//        game.viewPartyStatus();
//
//        // 大量の経験値を獲得させて、レベルアップと進化を発生させる
//        game.processAfterBattle(500000);
//    }
}