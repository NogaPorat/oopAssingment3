import java.util.Collections;
import java.util.List;

public class Rogue extends Player {




    public Rogue(Position pos, String name, int health, int Apoints, int Dpoints, int ability){
        super(pos, name, health, Apoints, Dpoints);
        resource = new Energy(ability);}

    public void cast(List<Enemy> enemies) {
        sendMessage("************* "+this.name + " cast special ability *************");
        for (Enemy e : enemies) {
            sendMessage(getName() + " attacks "+ e.getName()+ " on ability cast");
            e.defenseRoll(this.attackPoints);
            if (e.isDead()) {
                getExperianceOf(e);
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
