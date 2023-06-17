import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Board {
    private Player player;
    private List<Enemy> enemies;
    private List<Tile> tiles;
    protected SendMessage sm;

    public Board(Player p, String path){
        player = p;
        List<String> lines = readAllLines(path);
        tiles = convertStringToBoard(lines);



        SpecialAbillityInRange sa= (range) -> {castInRange(range);};
        Player p = new Player();
        p.setSpecialAbilityInRange(sa);
        Enemy e = new Enemy();
        e.setUnitMoveCallBack((position) -> {e.interact(getTileInPos(position));});
        p.setUnitMoveCallBack((position) -> {p.interact(getTileInPos(position));});
        SendMessage unitMessage = (msg) -> {sm.send(msg);};
        p.setSendMessage(unitMessage);



    }

    public List<String> readAllLines(String path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }
        return lines;
    }

    public List<Tile> convertStringToBoard(List<String> lines) {
        int y = 0;
        List<Tile> ans = new ArrayList<Tile>();
        for (String s: lines){
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                Tile t = convertCharToTile(c, i, y);
                ans.add(t);
            }
            y = y + 1;
        }
        return ans;
    }






    public void killedEnemy(Enemy e){
        Tile newEmt = new Empty(e.pos);
        enemies.remove(e);
        tiles.remove(e);
        tiles.add(newEmt);
    }

    public boolean isActive(){
        return (player.isAlive() && !enemies.isEmpty());
    }

    public Tile getTileInPos(Position pos){
        for (Tile t : tiles){
            if (t.pos.equals(pos)){
                return t;
            }
        }
        return null;
    }

    public void castInRange(double range){
        List<Enemy> enemiesInRange= new ArrayList<Enemy>();
        for (Enemy e: enemies){
            if (player.range(e) < range){
                enemiesInRange.add(e);
            }
        }
        player.cast(enemiesInRange);
    }

    public void gameTick(Scanner s){
        boolean act = player.gameTick(s);
        if (act) {
            for (Enemy e : enemies) {
                e.gameTick(player);
            }
        }
    }

    public String toString(){
        String ans = new String();
        Collections.sort(tiles, new PosComparator());
        int y = 0;
        for (Tile t: tiles){
            if(t.getPos().getY() == y){
                ans = ans + t.toString();
            }
            else{
                ans = ans + "\n" + t.toString();
                y = t.getPos().getY();
            }
        }
    }

    public void setSendMessage(SendMessage sm){
        this.sm = sm;
    }

    public void sendMessage(String msg){
        sm.send(msg);
    }

    public Tile convertCharToTile(char c, int i, int y){
        Tile t = null;
        if (c == '.'){
            t = new Empty(new Position(i,y));
        }
        if (c == '#'){
            t = new Wall(new Position(i,y));
        }
        if (c == '@'){
            player.pos = new Position(i,y);
            t = player;
        }
        if (c == '.'){
            t = new Empty(new Position(i,y));
        }
        if (c == 's'){
            t = new Monster(c, new Position(i,y), "Lannister Solider", 80, 8, 3, 25, 3);
            enemies.add(t);
        }
        if (c == 'k'){
            t = new Monster(c, new Position(i,y), "Lannister Knight", 200, 14, 8, 50, 4);
        }
        if (c == 'q'){
            t = new Monster(c, new Position(i,y), "Queen's Guard", 400, 20, 15, 100, 5);
        }
        if (c == 'z'){
            t = new Monster(c, new Position(i,y), "Wright", 600, 30, 15, 100, 3);
        }
        if (c == 'b'){
            t = new Monster(c, new Position(i,y), "Bear Wright", 1000, 75, 30, 250, 4);
        }
        if (c == 'g'){
            t = new Monster(c, new Position(i,y), "Giant Wright", 1500, 100, 40, 500, 5);
        }
        if (c == 'w'){
            t = new Monster(c, new Position(i,y), "White Walker", 2000, 150, 50, 1000, 6);
        }
        if (c == 'M'){
            t = new Monster(c, new Position(i,y), "The Mountain", 1000, 60, 25, 500, 6);
        }
        if (c == 'C'){
            t = new Monster(c, new Position(i,y), "Queen Cersei", 100, 10, 10, 1000, 1);
        }
        if (c == 'K'){
            t = new Monster(c, new Position(i,y), "Night's King", 5000, 300, 150, 5000, 8);
        }
        if (c == 'B'){
            t = new Trap(c, new Position(i,y), "Bonus Trap", 1, 1, 1, 250, 1, 5);
        }
        if (c == 'Q'){
            t = new Trap(c, new Position(i,y), "Queen's Trap", 250, 50, 10, 100, 3, 7);
        }
        if (c == 'D'){
            t = new Trap(c, new Position(i,y), "Death Trap", 500, 100, 20, 250, 1, 10);
        }
        return t;
    }
}
