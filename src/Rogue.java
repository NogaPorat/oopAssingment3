import java.util.Collections;
import java.util.List;

public class Rogue extends Player {




    public Rogue(Position pos, String name, Health health, int Apoints, int Dpoints, int ability){
        super(pos, name, health, Apoints, Dpoints);
        resource = new Energy(ability);}

    public void cast(List<Enemy> enemies){
        if (!enemies.isEmpty()){
            for(Enemy e:enemies){
                ;
            }
        }}

        public void SpecialAbillity() {
            sa.SpecialAbility(2);
        }


    }
