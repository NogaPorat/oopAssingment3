import java.util.Collections;
import java.util.List;

public class Rogue extends Player {




    public Rogue(Position pos, String name, Health health, int Apoints, int Dpoints, int ability){
        super(pos, name, health, Apoints, Dpoints);
        resource = new Energy(ability);}

    public void cast(List<Enemy> enemies){
        sendMessage(this.name+" cast special ability ");
        for(Enemy e:enemies){
            e.defenseRoll(this.attackPoints);
            if (e.isDead()){
                getExperianceOfEnemy(e);
                e.callDeathOfUnit();
            }
        }
        }

        public void SpecialAbillity() {
            sa.SpecialAbility(2);
        }


        public void levelUp(){
        super.levelUp();
        attackPoints = attackPoints + 3*playerLevel;
        }


    }
