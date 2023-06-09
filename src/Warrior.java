import java.util.List;
import java.util.Collections;



public class Warrior extends Player  {

    public Warrior(Position pos, String name, Health health, int Apoints, int Dpoints, int ability){
        super(pos, name, health, Apoints, Dpoints);
        resource = new Cooldown(ability);
    }
    public void cast(List<Enemy> enemies){
        if (!enemies.isEmpty()){
            Collections.shuffle(enemies);
            Enemy e = enemies.get(0);
            e.damage((int) (this.health.healthPoll * 0.1));
        }


    }
    public void levelUp(int level){
        super.levelUp();
        health.levelUpWarior(level);
        attackPoints = attackPoints + (2 * level);
        defencePoints = defencePoints + level;
    }

    @Override
    public void SpecialAbillity() {
        sa.SpecialAbility(3);
    }
}
