import java.util.List;
abstract public class Player extends Unit {
    protected int playerLevel;
    protected Resource resource;
    protected SendMessage sm;

    protected SpecialAbillityInRange sa;

    public Player(Position pos, String name, Health health, int Apoints, int Dpoints){
        super(pos, name, health, Apoints, Dpoints);
        experience = 0;
        playerLevel = 1;
        character = '@';
    }

    public void levelUp(){
        experience = experience - (50 * playerLevel);
        playerLevel = playerLevel + 1;
        health.levelUp(playerLevel);
        attackPoints = attackPoints + (4 * playerLevel);
        defencePoints = defencePoints + playerLevel;
        resource.levelUp(playerLevel);
    }
    public void visit(Enemy e){
        //combat!!!!!!
    }
    public void visit(Player p){
    }
    abstract public void castSpecialAbility();

    public void accept(Unit u){
        u.visit(this);
    }

    public void castAttempts(){
        if (!resource.enough()){
            sendMessage("not anough resource to make the action");
        }
        else{
            SpecialAbillity();
            resource.cast();
        }


    }


    abstract void SpecialAbillity();

    abstract void cast(List<Enemy> enemys);

    public void gameTick(){
        resource.gameTick(playerLevel);
    }

    protected void tryLevelUp(){
        if (experience >= 50 * playerLevel){
            levelUp();
        }
    }

    public void setSpecialAbilityInRange(SpecialAbillityInRange sa){
        this.sa = sa;
    }


    public void setSendMessage(SendMessage sm){
        this.sm = sm;
    }

    public void sendMessage(String msg){
        sm.send(msg);
    }




}
