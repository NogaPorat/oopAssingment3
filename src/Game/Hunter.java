package Game;

import java.util.Collections;
import java.util.List;

public class Hunter extends Player{

    int range;




    public Hunter(Position pos, String name, int health, int Apoints, int Dpoints, int range){
        super(pos, name, health, Apoints, Dpoints);
        this.range = range;
        resource = new Arrows(playerLevel);
    }


    public void levelUp(){
        super.levelUp();
        attackPoints = attackPoints + (2 * this.playerLevel);
        defencePoints = defencePoints + this.playerLevel;
    }

    public void SpecialAbillity() {
        sa.SpecialAbility(range);
    }

    public void cast(List<Enemy> enemies){
        sendMessage("************* "+this.name + " cast special ability *************");
        if (enemies.isEmpty())
            sendMessage("************* There are no enemys in Range *************");
        else{
            resource.cast();
            Enemy closestEnemy = enemies.get(0);
            double minRange = pos.range(closestEnemy.pos);
            for (Enemy e: enemies){
                if(pos.range(e.pos) < minRange){
                    closestEnemy = e;
                    minRange= pos.range(e.pos);
                }
            }
            closestEnemy.defenseRoll(attackPoints);
            if (closestEnemy.isDead()){
                getExperianceOf(closestEnemy);
                closestEnemy.callDeathOfUnit();
            }
        }
    }
    public void castAttempts() {
        if (!resource.enough()) {
            sendMessage("not enough resource to make the action");
        } else {
            SpecialAbillity();
        }
    }

}

