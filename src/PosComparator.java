import java.util.Comparator;
public class PosComparator implements Comparator<Tile>{
        public PosComparator(){
        }

        public int compare(Tile t1, Tile t2) {
            Position post1 = t1.pos;
            Position post2 = t2.pos;
            if (post1.getY() == post2.getY()){
                return post1.getX() - post2.getX();
            }
            return (post1.getY() - post2.getY());
        }
}
