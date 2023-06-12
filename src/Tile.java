public class Tile {
    protected char character;
    protected Position pos;

    public Tile(Position pos){
        this.pos = pos;
    }

    public Position getPos(){
        return pos;
    }
    public String toString(){
        System.out.print(character);
    }
}

    public double range(Tile t){
      return pos.range(t.getPos());
    }
}
