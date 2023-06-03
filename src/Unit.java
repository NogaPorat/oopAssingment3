public class Unit extends Tile{
    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defencePoints;
    protected int experience;


    public Unit(Position pos, String name, Health health, int Apoints, int Dpoints){
        super(pos);
        this.name = name;
        this.health = health;
        this.attackPoints = Apoints;
        this.defencePoints = Dpoints;
    }
}
