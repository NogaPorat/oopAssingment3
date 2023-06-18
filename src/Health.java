public class Health extends Resource{
    protected int healthPoll;
    protected int healthAmount;


    public Health(int healthPoll){
        this.healthPoll = healthPoll;
        this.healthAmount = healthPoll;
    }
    public void levelUp(int level){
        healthPoll = healthPoll + (10 * level);
        healthAmount = healthPoll;
    }

    public void levelUpWarior(int level){
        healthPoll = healthPoll + (5 * level);
    }

    public void damage(int damage){
        if(damage>healthAmount){
            healthAmount = 0;
        }
        else {
        healthAmount = healthAmount - damage;}
    }


    public boolean isDead(){
        return healthAmount <= 0;
    }

    @Override
    public boolean enough() {
        return healthAmount > 0;
    }


    public void gameTick(int i){}

    @Override
    public void cast() {}


    public String toString(){
        String s = healthAmount + "/"+ healthPoll;
        return s;
    }

}





