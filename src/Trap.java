public class Trap extends Enemy{
    protected int visibilityTime;
    protected int inVisibilityTime;
    protected int tickCount;
    protected boolean visible;

    public Trap(Position pos, String name, Health health, int Apoints, int Dpoints, int expirienceL, int visibilityTime, int inVisibilityTime) {
        super(pos, name, health, Apoints, Dpoints, expirienceL);
        this.visibilityTime = visibilityTime;
        this.inVisibilityTime = inVisibilityTime;
        tickCount = 0;
        visible = true;
    }
}
