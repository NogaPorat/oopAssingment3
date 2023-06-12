public class Empty extends Tile{
    public Empty(Position pos){
        super(pos);
        this.character = '.';
    }

    public void accept(Unit u){
        u.visit(this);
    }
}
