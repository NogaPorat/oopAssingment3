public class Player extends Unit {
    protected int playerLevel;
    protected Ability ability;

    public Player(Position pos, String name, Health health, int Apoints, int Dpoints){
        super(pos, name, health, Apoints, Dpoints);
        experience = 0;
        playerLevel = 1;
        character = '@';
    }
}
