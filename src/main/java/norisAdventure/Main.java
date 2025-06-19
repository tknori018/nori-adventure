package norisAdventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] partyList = {Position.HERO.getLabel(), Position.WARRIOR.getLabel(), Position.WIZARD.getLabel(), Position.HEALER.getLabel()};
        String[] partyNameList = new String[4];
        for (int i = 0; i < partyNameList.length; i++) {
            String name;
            while (true) {
                System.out.print("「%s」の名前を入力してください(変更不可、0~9文字) > ".formatted(partyList[i]));
                name = scanner.nextLine();
                if (!name.isEmpty() || name.length() < 9) {
                    System.out.println("0~9文字で入力してください");
                    continue;
                }
                partyNameList[i] = name;
                break;
            }
        }

        Hero hero = new Hero(partyNameList[0]);
        Warrior warrior = new Warrior(partyNameList[1]);
        Wizard wizard = new Wizard(partyNameList[2]);
        Healer healer = new Healer(partyNameList[3]);

        Human.party.add(hero);
        Human.party.add(warrior);
        Human.party.add(wizard);
        Human.party.add(healer);

        System.out.println("""
                ---------------------------------
                          ~冒険の始まり~
                ---------------------------------
                """);

        System.out.println("パーティーメンバー : %s".formatted(party.toString()));


    }
}
