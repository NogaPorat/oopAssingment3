package Game;

public class Monster extends Enemy {
    protected int visionRange;

    public Monster(char s, Position pos, String name, int health, int Apoints, int Dpoints, int expirienceL, int visionRangre) {
        super(s, pos, name, health, Apoints, Dpoints, expirienceL);
        this.visionRange = visionRangre;
    }

    public void gameTick(Player p) {
        Position moveLeft = new Position(pos.getX() - 1, pos.getY());
        Position moveRight = new Position(pos.getX() + 1, pos.getY());
        Position moveUp = new Position(pos.getX(), pos.getY() - 1);
        Position moveDown = new Position(pos.getX(), pos.getY() + 1);
        Position [] positions = {moveLeft, moveRight, moveUp, moveDown};
        Position newPos;
        if (range(p) <= visionRange) {
            int dx = pos.getX() - p.pos.getX();
            int dy = pos.getY() - p.pos.getY();
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    newPos = positions[0];
                } else {
                    newPos =  positions[1];
                }
            } else {
                if (dy > 0) {
                    newPos = positions[2];
                } else {
                    newPos = positions[3];
                }
            }
        }
        else {
            int i = (int)(4 * Math.random());
            newPos = positions[i];
        }
        this.Move(newPos);
    }
}
