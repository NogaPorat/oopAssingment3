public class Mana extends Resource{
    protected int manaPool;
    protected int currentMana;
    protected int manaCost;


    public Mana(int manaPool, int manaCost) {
        this.manaPool = manaPool;
        currentMana = manaPool / 4;
        this.manaCost = manaCost;
    }

    @Override
    public boolean enough() {
        return currentMana <= manaCost;
    }

    @Override
    public void levelUp(int level) {
        manaPool = manaPool + (25 * level);
        currentMana = Math.min(currentMana + (manaPool / 4), manaPool);

    }

    @Override
    public void cast() {
        currentMana = currentMana - manaCost;

    }

    @Override
    public void gameTick(int level) {
        currentMana = Math.min(manaPool, currentMana + level);
    }




    @Override
    public String toString() {
        return "Mana:" + manaPool+"/"+manaCost;
    }

}


