package Game;

abstract public class Enemy extends Unit {
    protected EnemyDeathCallBack enemyDeathCallBack;


    public Enemy(char character, Position pos, String name, int health, int Apoints, int Dpoints, int expirienceL){
        super(pos, name, health, Apoints, Dpoints);
        experience = expirienceL;
        this.character = character;
    }

    public void accept(Unit u){
        u.visit(this);
    }

     public void visit(Enemy e){
     }
     public void visit(Player p){
        combat(p);
     }


    // return is experiance and notify the board about death. need to make sure this is death
     public void callDeathOfUnit(){
         if(isDead()){
             sendMessage(this.name+ " was killed");
             enemyDeathCallBack.call(this);
         }
     }


    public void getExperianceOf(Unit u) {
    }

    public void setEnemyDeathCallBack(EnemyDeathCallBack enemyDeathCallBack) {
        this.enemyDeathCallBack = enemyDeathCallBack;
    }

    abstract public void gameTick(Player p);
}
