public class Monster extends Enemy {
    protected int visionRange;

    public Monster(Position pos, String name, Health health, int Apoints, int Dpoints, int expirienceL, int visionRangre) {
        super(pos, name, health, Apoints, Dpoints, expirienceL);
        this.visionRange = visionRangre;
    }

    public void attack(Unit u){
        u.damage(attackPoints);
    }

    public void gameTick(Player p){
        if (range(p) < visionRange) {
            int dx = pos.getX() - p.pos.getX();
            int dy = pos.getY() - p.pos.getY();
            if (dx > dy) {
                if (dx > 0) {
                    pos.set(pos.getX() - 1, pos.getY()); // move left???
                } else {
                    pos.set(pos.getX() + 1, pos.getY()); // move right???
                }
            } else {
                if (dy > 0) {
                    pos.set(pos.getX(), pos.getY() + 1); // move up???
                } else {
                    pos.set(pos.getX(), pos.getY() - 1); // move up???
                }
            }
        }
        else{
            // preform a random action
        }






}
