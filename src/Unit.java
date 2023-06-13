abstract public class Unit extends Tile{
    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defencePoints;
    protected int experience;

    protected SendMessage sm;

    public Unit(Position pos, String name, Health health, int Apoints, int Dpoints){
        super(pos);
        this.name = name;
        this.health = health;
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



    // this is attacker , u is defender
    public void combat(Unit u){
        sendMessage(this.name + " entered to combat with "+ u.name);
        attackRoll(u);
        if (u.isDead()){
            getExperianceOfEnemy(u);
            u.callDeathOfUnit();
        }
    }



    //takes experiance from death unit
    abstract public void getExperianceOfEnemy(Unit u);




    // return is experiance and notify the board about death. need to make sure this is death
    abstract public void callDeathOfUnit();

    //damage the unit in damageValue
    public void damage(int damageValue){
        sendMessage(this.name + "was damage by "+ damageValue + "points");
        health.damage(damageValue);
    }


    public boolean isDead(){
        return health.isDead();
    }




    public void setSendMessage(SendMessage sm){
        this.sm = sm;
    }

    public void sendMessage(String msg){
        sm.send(msg);
    }


}
