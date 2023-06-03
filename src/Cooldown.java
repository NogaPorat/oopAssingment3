public class Cooldown extends Resource{
    protected int ability;
    protected int remaining;

    public Cooldown(int ability){
        this.ability = ability;
        remaining = 0;
    }
    @Override
    public boolean enough() {
        return remaining == 0;
    }

    @Override
    public void gameTick(int level) {
        if (remaining > 0){
            remaining = remaining - 1;
        }
    }

    @Override
    public void cast() {
        remaining = ability;
    }

    @Override
    public void levelUp(int level) {
        remaining = 0;
    }
}
