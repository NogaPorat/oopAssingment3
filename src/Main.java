import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Player JohnSnow = new Warrior(new Position(0, 0), "John Snow",300, 30, 4, 3);
        Player TheHound = new Warrior(new Position(0, 0), "The Hound",400, 20, 6, 5);
        Player Melisandre = new Mage(new Position(0, 0), "Melisandre",100, 5, 1, 300, 30, 15, 5, 6);
        Player ThorosOfMyr = new Mage(new Position(0, 0), "Thoros Of Myr",250, 25, 4, 150, 20, 20, 3, 4);
        Player AryaStark = new Rogue(new Position(0, 0), "Arya Stark",150, 40, 2, 20);
        Player Bronn = new Rogue(new Position(0, 0), "Bronn",250, 35, 3, 50);
        Player [] players = {JohnSnow, TheHound, Melisandre, ThorosOfMyr, AryaStark, Bronn};
        Scanner se = new Scanner(System.in);
        System.out.println("Please select a player:");
        for(int i = 0; i < players.length; i++){
            System.out.println( i+1 +". " + players[i].description());
        }
        int chosen = se.nextInt();
        while (chosen > players.length){
            System.out.println("Please select a player again with a number between 1-6:");
            chosen = se.nextInt();
        }
        Player p = players[chosen - 1];
        System.out.println("You choose " + p.getName() + "!");
        GameManager gameManager = new GameManager(p, args[2]);





    }
}