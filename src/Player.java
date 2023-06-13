import java.util.List;
abstract public class Player extends Unit {
    protected int playerLevel;
    protected Resource resource;


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
        combat(e);
    }
    public void visit(Player p){
    }


    public void accept(Unit u){
        u.visit(this);
    }

    public void castAttempts(){
        if (!resource.enough()){
            sendMessage("not anough resource to make the action");
        }
        else{
            resource.cast();
            SpecialAbillity();
        }


    }

    public void getExperianceOfEnemy(Enemy e){
        this.experience= this.experience+ e.experience;
        sendMessage(this.name + " gets "+ e.experience + " experiance points from "+ e.name );
        tryLevelUp();
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


    @Override
    public void callDeathOfUnit() {
        if(isAlive()){}
        //needs to notify board for death
    }

    public void setSpecialAbilityInRange(SpecialAbillityInRange sa){
        this.sa = sa;
    }

    public boolean isAlive() {
        return health.enough();
    }

    public void checkIfDead(Enemy e){
        if (isDead()){
            //לעשות איקס בבורד ונמחק השחקן
        }
    }




}
