package norisAdventure;

import java.util.Optional;

public abstract class Item {
    private String name;
    private String description;
    private int price;
    private double itemPower; // 回復量や攻撃力上昇値など
    private ItemPosition itemPosition;

    public Item(String name, String description, double itemPower, int price, ItemPosition itemPosition) {
        this.name = name;
        this.description = description;
        this.itemPower = itemPower;
        this.price = price;
        this.itemPosition = itemPosition;
    }
    /**
     * インベントリ内から特定のアイテムのレコードを検索します。
     * @param targetItem 検索したいアイテム
     * @return 見つかった場合はItemRecordをOptionalで包んで返す。見つからなければ空のOptionalを返す。
     */
    private static Optional<ItemRecord> findItemRecordInInventory(Item targetItem) {
        for (ItemRecord record : Human.getInventory()) {
            if (record.item().equals(targetItem)) {
                return Optional.of(record); // 見つかったレコードを返す
            }
        }
        return Optional.empty(); // 見つからなかった
    }

    /**
     * このアイテムを指定数購入する。
     * 所持金を減らし、インベントリを更新する。
     */
    public void buy(int numberToBuy) {
        // お金が足りるかチェック
        int totalBuyPrice = this.getPrice() * numberToBuy;
        if (Human.getMoney() < totalBuyPrice) {
            System.out.println("お金が足りません！");
            return;
        }

        Optional<ItemRecord> existingRecordOpt = findItemRecordInInventory(this);
        // お金を減らす
        Human.setMoney(Human.getMoney() - this.price);

        if (existingRecordOpt.isPresent()) {
            // すでに持っている場合
            ItemRecord record = existingRecordOpt.get();
            // recordは不変なので、一度リストから削除して、新しいものを追加する
            Human.getInventory().remove(record);
            Human.getInventory().add(new ItemRecord(this, record.posseesedNumber() + numberToBuy));
        } else {
            // 初めて手に入れる場合
            Human.getInventory().add(new ItemRecord(this, numberToBuy));
        }
        System.out.println("「" + this.getName() + "」を" + numberToBuy + "個購入しました。");
    }

    /**
     * このアイテムを指定数売却する。
     * インベントリから減らし、所持金を増やす。
     */
    public void sell(int numberToSell) {
        if (numberToSell <= 0) {
            System.out.println("売却する個数は1以上の値を指定してください。");
            return;
        }

        // インベントリからこのアイテムのレコードを探す
        Optional<ItemRecord> existingRecordOpt = findItemRecordInInventory(this);

        // アイテムを持っていない、または所持数が足りない場合は処理を中断
        if (existingRecordOpt.isEmpty()) {
            System.out.println("「" + this.getName() + "」は持っていません。");
            return;
        }

        ItemRecord record = existingRecordOpt.get();
        if (record.posseesedNumber() < numberToSell) {
            System.out.println("「" + this.getName() + "」の所持数が足りません。(所持数: " + record.posseesedNumber() + ")");
            return;
        }

        // お金を増やす (売値は買値の半額とする)
        int sellPrice = this.getPrice() / 2;
        int totalSellPrice = sellPrice * numberToSell;
        Human.setMoney(Human.getMoney() + totalSellPrice);

        // インベントリを更新する
        int newNumber = record.posseesedNumber() - numberToSell;

        // まずインベントリから古いレコードを削除する
        Human.getInventory().remove(record);

        if (newNumber > 0) {
            // 売却後もアイテムが残る場合、個数を更新した新しいレコードを追加する
            Human.getInventory().add(new ItemRecord(this, newNumber));
        }
        // newNumberが0の場合は、削除したままで何もしない

        System.out.println("「" + this.getName() + "」を" + numberToSell + "個売却しました。 (" + totalSellPrice + "G)");
    }

    // getter, setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getItemPower() {
        return itemPower;
    }

    public void setItemPower(double itemPower) {
        this.itemPower = itemPower;
    }

    public ItemPosition getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(ItemPosition itemPosition) {
        this.itemPosition = itemPosition;
    }
}
