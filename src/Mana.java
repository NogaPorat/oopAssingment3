public class Mana extends Resource{
    protected int manaPool;
    protected int currentMana;
    protected int manaCost;
    protected int spellPower;
    protected int hitsCount;
    protected int abilityRange;

    public Mana(int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange){
        this.manaPool = manaPool;
        currentMana = manaPool / 4;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    @Override
    public boolean enough() {
        return currentMana <= manaCost;
    }

    @Override
    public void levelUp(int level) {
        manaPool = manaPool + (25 * level);
        currentMana = Math.min(currentMana + (manaPool / 4), manaPool);
        spellPower = spellPower + (10 * level);
    }

    @Override
    public void cast() {
        currentMana = currentMana - manaCost;
        hitsCount = 0;
    }

    @Override
    public void gameTick(int level) {
        currentMana = Math.min(manaPool, currentMana + level);
    }


    public int GetHitsCount(){
        return hitsCount;
    }
}


