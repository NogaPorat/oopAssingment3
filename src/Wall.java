public class Wall extends Tile{
    public Wall(Position pos){
        super(pos);
        this.character = '#';
    }

    public void accept(Unit u){
        u.visit(this);
    }
}
