package norisAdventure;

public class Main {
    public static void main(String[] args) {
        // GameManagerのインスタンスを生成してゲームを初期化
        GameManager game = new GameManager();

        // ゲームのメインループを開始
        game.gameLoop();
    }
}