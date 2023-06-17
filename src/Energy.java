public class Energy extends Resource{
    private int currentEnergy;
    private int cost;
    public Energy(int cost){
        this.cost = cost;
        currentEnergy = 100;
    }

    @Override
    public boolean enough() {
        return currentEnergy >= cost;
    }

    @Override
    public void cast() {
        currentEnergy = currentEnergy - cost;
    }

    @Override
    public void gameTick(int level) {
        currentEnergy = Math.min(currentEnergy + 10, 100);
    }

    @Override
    public void levelUp(int level) {
        currentEnergy = 100;
    }

    public String toString(){
        return  "current energy: " + currentEnergy + "/" + cost;
    }
}
