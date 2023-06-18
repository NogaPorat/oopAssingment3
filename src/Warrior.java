import java.util.List;
import java.util.Collections;

public class Warrior extends Player  {

    public Warrior(Position pos, String name, int health, int Apoints, int Dpoints, int ability){
        super(pos, name, health, Apoints, Dpoints);
        resource = new Cooldown(ability);
    }



    //hits random enemy in range
        public void cast(List<Enemy> enemies){
            sendMessage(this.name+" cast special ability ");
            if (!enemies.isEmpty()){
                Collections.shuffle(enemies);
                Enemy e = enemies.get(0);
                e.damage((int) (this.health.healthPoll * 0.1));
                if (e.isDead()){
                    getExperianceOf(e);
                    e.callDeathOfUnit();
                }
            }
    }




    public void levelUp(){
        super.levelUp();
        health.levelUpWarior(this.playerLevel);
        attackPoints = attackPoints + (2 * this.playerLevel);
        defencePoints = defencePoints + this.playerLevel;
    }

    @Override
    public void SpecialAbillity() {
        sa.SpecialAbility(3);
    }
}
