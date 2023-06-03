public class Position {
    private int X;
    private int Y;

    public Position(int x, int y){
        this.X = x;
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY(){
        return Y;
    }

    public double range(Position P){
        return (Math.sqrt((Math.pow(X- P.getX(), 2) + Math.pow(Y- P.getY(), 2))));
    }

    public void move(int moveX, int moveY){
        X = X + moveX;
        Y = Y + moveY;
    }
}
