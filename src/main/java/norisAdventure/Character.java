package norisAdventure;

public abstract class Character {
    private String name;
    private int lv;
    private int hp;
    private int maxHp;
    private int mp;
    private int maxMp;
    private int power;
    private int speed;
    private Area area; // フィールド条件
    private Flyable flyable;
    private boolean gardBoolean;
    private double powerBuff;
    private double speedBuff;
    private double defenseBuff;
    private double powerUpDuration;
    private double speedUpDuration;
    private double defenseUpDuration;

    public Character(String name) {
        this.name = name;
        this.gardBoolean = false;
        this.powerBuff = 1;
        this.speedBuff = 1;
        this.defenseBuff = 1;
        this.powerUpDuration = 0;
        this.speedUpDuration = 0;
        this.defenseUpDuration = 0;
    }

    /**
     * 攻撃の共通処理（テンプレートメソッド）
     * @param target 攻撃対象のキャラクター
     */
    public void attack(Character target) {
        // 子クラス固有の攻撃メッセージを表示する
        displayAttackMessage();

        // 10%で攻撃を回避
        double randomAvoidance = Math.random();
        if (randomAvoidance < 0.1) {
            System.out.println(target.getName() + "は攻撃を回避した！");
            return;
        }

        // ダメージ計算（例：攻撃力の半分をベースに、±25%程度のランダム幅を持たせる）
        int baseDamage = this.getPower() / 2;
        int randomDamage = (int) (Math.random() * (this.getPower() / 4.0));
        // 守備力バフを考慮
        int totalDamage = (int) ((baseDamage + randomDamage) / target.getDefenseBuff());

        // 最低でも1ダメージは与える
        if (totalDamage <= 0) {
            totalDamage = 1;
        }

        // ダメージを相手に与える
        double randomCritical = Math.random();
        // 10%でクリティカルダメージ(3倍)
        if (randomCritical < 0.1) {
            totalDamage *= 3;
            System.out.println("!!クリティカル!!");
        }
        target.setHp(target.getHp() - totalDamage);
        System.out.println(target.getName() + " に " + totalDamage + " のダメージを与えた！");

        // 相手のHPが0以下になったかチェック
        if (target.getHp() <= 0) {
            target.setHp(0); // HPがマイナスにならないように調整
            System.out.println(target.getName() + " をやっつけた！");
        }
    }

    public void guard() {
        System.out.println(this.getName() + "は身を守った！");

        // 攻撃軽減処理
        setDefenseBuff((int) (getDefenseBuff() * 1.5));
        this.setGardBoolean(true);
    }

    /**
     * 子クラスで実装する攻撃メッセージ表示用の抽象メソッド
     */
    public abstract void displayAttackMessage();

    // 特殊攻撃


    // getter, setter
    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Flyable getFlyable() {
        return flyable;
    }

    public void setFlyable(Flyable flyable) {
        this.flyable = flyable;
    }

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

}
