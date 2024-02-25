package Game;

public class Arrows extends Resource{

    int arrowsCount;
    int ticksCount;

    public Arrows(int level){
        arrowsCount=10*level;
        ticksCount = 0;
    }



     public boolean enough(){
        return arrowsCount>0;}


    public void gameTick(int level){
        if(ticksCount==10){
            arrowsCount=arrowsCount+level;
            ticksCount=0;}
        else
            ticksCount++;

    }

    public void cast(){
        arrowsCount = arrowsCount-1;
    }
    public void levelUp(int level){
        arrowsCount = arrowsCount + (10*level);
}

    @Override
    public String toString() {
        return "arrows count:" + arrowsCount;
    }
}
