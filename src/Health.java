public class Health extends Resource{
    protected int healthPoll;
    protected int healthAmount;


    public Health(){

    }
    public void levelUp(int level){
        healthPoll = healthPoll + (10 * level);
        healthAmount = healthPoll;
    }

    public void levelUpWarior(int level){
        healthPoll = healthPoll + (5 * level);
    }

    public void damage(int damage){
        healthAmount = healthAmount - damage;
    }


    public boolean isDead(){
        return healthAmount > 0;
    }

}





