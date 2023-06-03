abstract public class Player extends Unit {
    protected int playerLevel;
    protected Resource resource;

    public Player(Position pos, String name, Health health, int Apoints, int Dpoints){
        super(pos, name, health, Apoints, Dpoints);
        experience = 0;
        playerLevel = 1;
        character = '@';
    }

    public void levelUp(){
        experience = experience - (50 * playerLevel);
        playerLevel = playerLevel + 1;
        health.healthPoll = health.healthPoll + (10 * playerLevel);
        health.healthAmount = health.healthPoll;
        attackPoints = attackPoints + (4 * playerLevel);
        defencePoints = defencePoints + playerLevel;
        resource.levelUp(playerLevel);
    }

    abstract public void castSpecialAbility();

    public void accept(Unit u){
        u.visit(this);
    }

    public void castAttempts(){
        if (!resource.enough()){
            throw new Exception("not anough resource to make the action");
        }
        cast();
        resource.cast();
    }

    abstract protected void cast();

    public void gameTick(){
        resource.gameTick(playerLevel);
    }



}
