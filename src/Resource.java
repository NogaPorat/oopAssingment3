abstract public class Resource {
    abstract public boolean enough();
    abstract public void gameTick(int level);
    abstract public void cast();
    abstract public void levelUp(int level);
}
