package Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class GameManager {
    private List<Board> boards;
    private Player p;
    private int currentLevel;
    protected Board currentBoard;
    private Scanner scanner;
    public String path;
    public GameManager(String path){
        if (path == null){
            throw new IllegalArgumentException("path is null");
        }
        this.path = path;
        scanner = new Scanner(System.in);
    }


    public Board boardBuilder (int level, String path){
        try{
            File file = new File(path + "\\level"+level+".txt");
            Scanner sc = new Scanner(file);
            List <String> lines = new ArrayList<String>();
            while (sc.hasNextLine()){
                lines.add(sc.nextLine());
            }
            Board b = new Board(p, lines);
            b.setSendMessage((msg) -> System.out.print(msg));
            return b;
        }
        catch (IOException e) {
            return null;
        }
    }

    public boolean isGameActive(){
        return currentBoard != null && currentBoard.isActive();
    }

    public void start(){
        System.out.println("Welcome to the game!");
        Player JohnSnow = new Warrior(new Position(0, 0), "John Snow",300, 30, 4, 3);
        Player TheHound = new Warrior(new Position(0, 0), "The Hound",400, 20, 6, 5);
        Player Melisandre = new Mage(new Position(0, 0), "Melisandre",100, 5, 1, 300, 30, 15, 5, 6);
        Player ThorosOfMyr = new Mage(new Position(0, 0), "Thoros Of Myr",250, 25, 4, 150, 20, 20, 3, 4);
        Player AryaStark = new Rogue(new Position(0, 0), "Arya Stark",150, 40, 2, 20);
        Player Bronn = new Rogue(new Position(0, 0), "Bronn",250, 35, 3, 50);
        Player Ygritte = new Hunter(new Position(0,0),"Ygritte",220,30,2,6);
        Player [] players = {JohnSnow, TheHound, Melisandre, ThorosOfMyr, AryaStark, Bronn,Ygritte};

        int chosen = 0;
        while (chosen > players.length || chosen <= 0){
            System.out.println("Please select a player");
            System.out.println();
            for(int i = 0; i < players.length; i++){
                System.out.println( i+1 +". " + players[i].description());
                System.out.println();
            }
            chosen = scanner.nextInt();
        }
        p = players[chosen - 1];
        System.out.println("You choose " + p.getName() + "!");

        currentLevel = 1;
        int i = 1;
        Board b = boardBuilder(i, path);
        boards = new ArrayList<Board>();
        while(b!= null){
            boards.add(b);
            //b.setSendMessage((msg) -> System.out.println(msg));
            i = i + 1;
            b = boardBuilder(i, path);
        }
        currentBoard = boards.get(0);
        currentBoard.setSendMessage((msg) -> System.out.println(msg));
        currentBoard.playerInit(p);
        System.out.println("The game is ready!");

        while (currentBoard != null && p.isAlive() ){
            System.out.println("you made it to the " + currentLevel + " level!");
            while (currentBoard.boardIsActiva()){
                System.out.println(currentBoard.toString());
                currentBoard.gameTick(scanner);
            }
            if(p.isAlive()){
                if(boards.size()>currentLevel){
                currentBoard = boards.get(currentLevel);
                currentLevel = currentLevel + 1;
                currentBoard.setSendMessage((msg) -> System.out.println(msg));
                currentBoard.playerInit(p);}
                else
                    {currentBoard = null;}
            }
        }
        if (p.isDead()){
            System.out.println(boards.get(currentLevel-1).toString());
            System.out.println("YOU ARE THE LOSER!");
        }
        else {
            System.out.println("YOU ARE THE WINNER!");
        }

    }
}
