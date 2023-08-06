package cardGame;

import cardGame.poker.PokerGame;

import java.util.Scanner;

public class GameController {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int input = 0;
        while (input < 1 || input > 2) {
            System.out.println("""
                Poker 5 Card Draw
                1 - New Game
                2 - Quit""");

            PokerGame fiveCardDraw = null;

            input = scanner.nextInt();
            switch (input) {

                case 1 -> {
                    System.out.print("No. of players: ");
                    int noOfPlayers = scanner.nextInt();
                    fiveCardDraw = new PokerGame(noOfPlayers, 5);
                    fiveCardDraw.startPlay();
                }
                case 2 -> {
                    System.out.println("Exiting game");
                    break;
                }
                default -> System.out.println("Invalid input. Please try again");
            }
        }

    }
}
