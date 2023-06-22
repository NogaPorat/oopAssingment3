abstract public class Unit extends Tile{
    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defencePoints;
    protected int experience;
    protected UnitMoveCallBack unitMoveCallBack;
    protected SendMessage sm;

    public Unit(Position pos, String name, int health, int Apoints, int Dpoints){
        super(pos);
        this.name = name;
        this.health = new Health(health);
        this.attackPoints = Apoints;
        this.defencePoints = Dpoints;
    }

    public void visit(Empty e){
        swapPositions(e);
    }

    public void visit(Wall w){
    }


    abstract public void visit(Enemy e);
    abstract public void visit(Player p);

    public void swapPositions(Tile t){
        Position tmp = t.pos;
        t.pos = this.pos;
        this.pos = tmp;
    }


    //rolls an amount of defense
    public void defenseRoll(int attack){
        int defense = (int) (Math.random()*defencePoints);
        sendMessage(this.name + " rolled "+defense + " defense points");
        if (attack - defense > 0){
            damage(attack-defense);
        }


    }
    //rolls an amount of attack and attacks u
    public void attackRoll(Unit u){
         int myAttack=(int) (Math.random()*attackPoints);;
         sendMessage(this.name + " rolled "+myAttack + " attack points");
         u.defenseRoll(myAttack);

    }


    public void combat(Unit u) { // this attacker u defender
        sendMessage(this.name + " entered to combat with " + u.name);
        sendMessage(getName()+ " attacks "+ u.getName());
        sendMessage(this.description());
        sendMessage(u.description());
        attackRoll(u);
        if (u.isDead()){
            getExperianceOf(u);
            swapPositions(u);
            u.callDeathOfUnit();
        }
    }


    abstract public void getExperianceOf(Unit u); // checks if u id sead and takes the expirience


    //notify the board about death. need to make sure this is death
    abstract public void callDeathOfUnit();

    //damage the unit in damageValue
    public void damage(int damageValue){
        sendMessage(this.name + " was damage by "+ damageValue + " points");
        health.damage(damageValue);
    }




    //returns if this is dead
    public boolean isDead(){
        return health.isDead();
    }



    public void setSendMessage(SendMessage sm){
        this.sm = sm;
    }

    //sends massage
    public void sendMessage(String msg){
        sm.send(msg);
    }
//returns if this is alive
    public boolean isAlive(){
        return health.enough();
    }

    public void interact(Tile t){
        t.accept(this);
    }
    public void setUnitMoveCallBack(UnitMoveCallBack um){
        this.unitMoveCallBack = um;
    }

    public void Move(Position pos){
        unitMoveCallBack.move(pos, this);
    }

    public String toString(){
        return super.toString();
    }

    public String getName() {
        return name;
    }

    public String description(){
        return ("Name: " + name + " Health: " + health.toString() + " defence points: " + defencePoints + " attack points: " + attackPoints + " Experience: " + experience);
    }
}
