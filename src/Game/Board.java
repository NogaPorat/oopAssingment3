package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Board {
    private Player player;
    private List<Enemy> enemies;

    private List<Enemy> traps;
    private List<Tile> tiles;
    protected SendMessage sm;
    protected Position playerStartPos;

    public Board(Player p, List<String> lines){
        player = p;
        enemies = new ArrayList<Enemy>();
        traps = new ArrayList<Enemy>();
        tiles = convertStringToBoard(lines);

    }

    public void gameTick(Scanner s){
        boolean act = player.gameTick(s);
        if (act){
            for (Enemy e : enemies){
                e.gameTick(player);
            }
            for (Enemy t : traps)
                t.gameTick(player);
        }
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

    public void playerInit(Player p) {
        this.player = p;
        player.pos = playerStartPos;
        player.setSendMessage((msg) -> this.sendMessage(msg));
        player.setUnitMoveCallBack((position, unit) -> unit.interact(getTileInPos(position)));
        player.setSpecialAbilityInRange((range)-> castInRange(range));
        for(Enemy e:enemies){
            e.setSendMessage((msg) -> this.sendMessage(msg));
            e.setEnemyDeathCallBack((enemy) -> this.killedEnemy(enemy));
            e.setUnitMoveCallBack((position, unit) -> unit.interact(getTileInPos(position)));
        }
        for(Enemy t :traps){
            t.setSendMessage((msg) -> this.sendMessage(msg));
            t.setEnemyDeathCallBack((enemy) -> this.killedTrap(enemy));
            t.setUnitMoveCallBack((position, unit) -> unit.interact(getTileInPos(position)));
        }
    }

    public void killedEnemy(Enemy e){
        Tile newEmt = new Empty(e.pos);
        enemies.remove(e);
        tiles.remove(e);
        tiles.add(newEmt);
    }

    public void killedTrap(Enemy t){
        Tile newEmt = new Empty(t.pos);
        traps.remove(t);
        tiles.remove(t);
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
            if (player.range(e) <= range){
                enemiesInRange.add(e);
            }
        }
        for (Enemy e: traps){
            if (player.range(e) <= range){
                enemiesInRange.add(e);
        }
    }
        player.cast(enemiesInRange);
    }



    public boolean boardIsActiva(){
        return player.isAlive() & !enemies.isEmpty();

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
        ans = ans + "\n" + player.description();
        return ans;
    }

    public void setSendMessage(SendMessage sm){
        this.sm = sm;
    }

    public void sendMessage(String msg){
        sm.send(msg);
    }


    //convert char to tile, if it's an enemy, also adds him to enemy list.
    public Tile convertCharToTile(char c, int i, int y){
        Tile t = null;
        if (c == '.'){
            t = new Empty(new Position(i,y));
        }
        if (c == '#'){
            t = new Wall(new Position(i,y));
        }
        if (c == '@'){
            playerStartPos = new Position(i,y);
            t = player;
        }
        Monster e = null;
        if (c == 's'){
            e = new Monster(c, new Position(i,y), "Lannister Solider", 80, 8, 3, 25, 3);
        }
        if (c == 'k'){
            e = new Monster(c, new Position(i,y), "Lannister Knight", 200, 14, 8, 50, 4);
        }
        if (c == 'q'){
            e = new Monster(c, new Position(i,y), "Queen's Guard", 400, 20, 15, 100, 5);
        }
        if (c == 'z'){
            e = new Monster(c, new Position(i,y), "Wright", 600, 30, 15, 100, 3);
        }
        if (c == 'b'){
            e = new Monster(c, new Position(i,y), "Bear Wright", 1000, 75, 30, 250, 4);
        }
        if (c == 'g'){
            e = new Monster(c, new Position(i,y), "Giant Wright", 1500, 100, 40, 500, 5);
        }
        if (c == 'w'){
            e = new Monster(c, new Position(i,y), "White Walker", 2000, 150, 50, 1000, 6);
        }
        if (c == 'M'){
            e = new Monster(c, new Position(i,y), "The Mountain", 1000, 60, 25, 500, 6);
        }
        if (c == 'C'){
            e = new Monster(c, new Position(i,y), "Queen Cersei", 100, 10, 10, 1000, 1);
        }
        if (c == 'K'){
            e = new Monster(c, new Position(i,y), "Night's King", 5000, 300, 150, 5000, 8);
        }
        Trap trap = null;
        if (c == 'B'){
            trap = new Trap(c, new Position(i,y), "Bonus Game.Trap", 1, 1, 1, 250, 1, 5);
        }
        if (c == 'Q'){
            trap = new Trap(c, new Position(i,y), "Queen's Game.Trap", 250, 50, 10, 100, 3, 7);
        }
        if (c == 'D'){
            trap = new Trap(c, new Position(i,y), "Death Game.Trap", 500, 100, 20, 250, 1, 10);
        }
        if (e!= null){
            enemies.add(e);

            return e;
        }
        if (trap!=null){
            traps.add(trap);
            return trap;
        }
        return t;
    }
}
