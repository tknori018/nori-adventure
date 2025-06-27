package norisAdventure;

public interface Creature {
    void attack(Character character); // 回避要素あり、クリティカルあり
    void guard();
}
