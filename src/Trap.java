public class Trap extends Enemy{
    protected int visibilityTime;
    protected int inVisibilityTime;
    protected int tickCount;
    protected boolean visible;

    public Trap(char s, Position pos, String name, int health, int Apoints, int Dpoints, int expirienceL, int visibilityTime, int inVisibilityTime) {
        super(s, pos, name, health, Apoints, Dpoints, expirienceL);
        this.visibilityTime = visibilityTime;
        this.inVisibilityTime = inVisibilityTime;
        tickCount = 0;
        visible = true;
    }

    public String toString(){
        if (visible == true){
            return super.toString();
        }
        return ".";
    }
    public void gameTick(Player p){
        visible = tickCount < visibilityTime;
        if (tickCount == visibilityTime + inVisibilityTime) {
            tickCount = 0;
        }
        else{
            tickCount = tickCount + 1;
        }
        if (range(p) < 2){
            combat(p);
        }

    }
    public void swapPositions(Unit u){}
}
