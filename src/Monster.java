public class Monster extends Enemy {
    protected int visionRange;

    public Monster(Position pos, String name, Health health, int Apoints, int Dpoints, int expirienceL, int visionRangre) {
        super(pos, name, health, Apoints, Dpoints, expirienceL);
        this.visionRange = visionRangre;
    }
}
