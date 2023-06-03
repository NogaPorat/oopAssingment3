abstract public class Unit extends Tile{
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



}
