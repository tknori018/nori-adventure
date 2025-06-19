package norisAdventure;

public enum Position {
    HERO("勇者"),
    WARRIOR("戦士"),
    WIZARD("魔法使い"),
    HEALER("ヒーラー"),
    SABER("セイバー"),
    BERSERKER("狂戦士"),
    MAGIC_EMPEROR("魔法帝"),
    SAINT("聖職者");

    private final String label;

    Position(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
