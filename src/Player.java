import java.util.List;
import java.util.Scanner;

abstract public class Player extends Unit {
    protected int playerLevel;
    protected Resource resource;
    protected PlayerDeathCallBack playerDeathCallBack;
    protected SpecialAbillityInRange sa;

    public Player(Position pos, String name, int health, int Apoints, int Dpoints) {
        super(pos, name, health, Apoints, Dpoints);
        experience = 0;
        playerLevel = 1;
        character = '@';
    }

    public void levelUp() {
        experience = experience - (50 * playerLevel);
        playerLevel = playerLevel + 1;
        health.levelUp(playerLevel);
        attackPoints = attackPoints + (4 * playerLevel);
        defencePoints = defencePoints + playerLevel;
        resource.levelUp(playerLevel);
    }

    public void visit(Enemy e) {
        combat(e);
    }

    public void visit(Player p) {
    }


    public void accept(Unit u) {
        u.visit(this);
    }

    public void castAttempts() {
        if (!resource.enough()) {
            sendMessage("not anough resource to make the action");
        } else {
            resource.cast();
            SpecialAbillity();
        }
    }

    public void getExperianceOf(Unit u) {
        this.experience = this.experience + u.experience;
        sendMessage(this.name + " gets " + u.experience + " experiance points from " + u.name);
        tryLevelUp();
    }

    abstract void SpecialAbillity();

    abstract void cast(List<Enemy> enemys);

    public boolean gameTick(Scanner s) {
        boolean act = gameTickByLetter(s);
        if (act){
            resource.gameTick(playerLevel);
        }
        else{
            sm.send("illegal input");
        }
        return act;
    }

    protected void tryLevelUp() {
        if (experience >= 50 * playerLevel) {
            levelUp();
        }
    }


    @Override
    public void callDeathOfUnit() {
        if (!isAlive()) {
            sendMessage("you are dead");
            playerDeathCallBack.call();
        }
    }

    public void setSpecialAbilityInRange(SpecialAbillityInRange sa) {
        this.sa = sa;
    }



    public void setPlayerCallDeath(PlayerDeathCallBack p) {
        this.playerDeathCallBack = p;
    }


    public boolean gameTickByLetter(Scanner s){
        char input = s.next().charAt(0);
        Position newPos;
        if (input == 'a'){
            newPos = new Position(pos.getX() - 1, pos.getY());
            this.Move(newPos);
            return true;
        }
        if (input == 'd'){
            newPos = new Position(pos.getX() + 1, pos.getY());
            this.Move(newPos);
            return true;
        }
        if (input == 'w'){
            newPos = new Position(pos.getX(), pos.getY() + 1);
            this.Move(newPos);
            return true;
        }
        if (input == 's'){
            newPos = new Position(pos.getX(), pos.getY() - 1);
            this.Move(newPos);
            return true;
        }
        if (input == 'e'){
            castAttempts();
            return true;
        }
        if (input == 'q') {
            return true;
        }
        return false;
    }

    @Override
    public String description() {
        return super.description() + " " + resource.toString();
    }
}
