public class Enemy extends Unit{
    public Enemy(char character, Position pos, String name, Health health, int Apoints, int Dpoints, int expirienceL){
        super(pos, name, health, Apoints, Dpoints);
        experience = expirienceL;
        this.character = character;
    }

    public void accept(Unit u){
        u.visit(this);
    }

    public void damage(int damage){
        health.damage(damage);
    }

     public void visit(Enemy e){
     }
     public void visit(Player p){
        combat(p);
     }

    public boolean isAlive(){
        return health.enough();
    }


    // return is experiance and notify the board about death. need to make sure this is death
     public void callDeathOfUnit(){
         if(isAlive()){}
         //needs to notify board for death
     }

    public void checkIfDead(Player p){
        if (isDead()){
            p.getExperianceOfEnemy(this);
            callDeathOfUnit();
        }
    }



}
