import Game.GameManager;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        GameManager gameManager = new GameManager(args[0]);
        gameManager.start();
    }
}