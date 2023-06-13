import java.util.Collections;
import java.util.List;

public class Mage extends Player{



    public Mage(Position pos, String name, Health health, int Apoints, int Dpoints,int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange){
        super(pos, name, health, Apoints, Dpoints);
        resource = new Mana(manaPool, manaCost,spellPower,hitsCount,abilityRange);}

    public void SpecialAbillity() {
        sa.SpecialAbility(3);
    }


    public void cast(List<Enemy> enemies){
        int hits = 0;
        Collections.shuffle(enemies);
    }


}
