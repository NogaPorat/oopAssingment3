public class Tile {
    protected char character;
    protected Position pos;

    public Tile(Position pos){
        this.pos = pos;
    }

    public String toString(){
        System.out.print(character);
    }
}
