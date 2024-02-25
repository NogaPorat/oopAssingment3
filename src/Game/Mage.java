package Game;

import java.util.Collections;
import java.util.List;

public class Mage extends Player{


    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    public Mage(Position pos, String name, int health, int Apoints, int Dpoints, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange){
        super(pos, name, health, Apoints, Dpoints);
        this.spellPower= spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        resource = new Mana(manaPool, manaCost);}

    public void SpecialAbillity() {
        sa.SpecialAbility(abilityRange);
    }


    public void cast(List<Enemy> enemies){
        sendMessage("************* "+this.name + " cast special ability *************");
        int hits = 0;
        while(hits< hitsCount & !enemies.isEmpty()){
            Collections.shuffle(enemies);
            Enemy e = enemies.get(0);
            sendMessage(getName() + " attacks "+ e.getName()+ " on ability cast");
            e.damage(spellPower);
            if(e.isDead()){
                getExperianceOf(e);
                e.callDeathOfUnit();
                enemies.remove(e);
            }

        }

    }

    @Override
    public void levelUp() {
        super.levelUp();
        spellPower = spellPower+ 10*playerLevel;
    }

    public String description(){
        return super.description()+ "\n"+" Speell Power:"+spellPower +" Hits count:"+hitsCount+" Ability Range:"+ abilityRange;
    }
}
