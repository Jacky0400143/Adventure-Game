package me.synology.zankyonokaze;

import java.util.Scanner;

public class ADV_Aplha_001 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean gameOver = false;
        String nextRoom = "start";

        System.out.println("歡迎來到文字冒險遊戲！");

        while (!gameOver) {
            switch (nextRoom) {
                case "start":
                    System.out.println("你現在在一個房間裡。有一扇門在你面前。");
                    System.out.println("你想要打開門嗎？(是/否)");
                    String choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("是")) {
                        nextRoom = "outside";
                    } else {
                        System.out.println("你決定不打開門。遊戲結束。");
                        gameOver = true;
                    }
                    break;
                case "outside":
                    System.out.println("你走出房子，看到一片廣闊的草地。");
                    System.out.println("遊戲結束。");
                    gameOver = true;
                    break;
                default:
                    System.out.println("無效的房間。遊戲結束。");
                    gameOver = true;
                    break;
            }
        }
    }
}