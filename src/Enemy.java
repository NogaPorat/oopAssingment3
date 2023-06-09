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


}
