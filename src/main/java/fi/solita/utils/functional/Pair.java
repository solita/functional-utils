package fi.solita.utils.functional;


public class Pair<LEFT, RIGHT> extends Tuple2<LEFT, RIGHT> {

    public static <LEFT, RIGHT> Pair<LEFT, RIGHT> of(LEFT left, RIGHT right) {
        return new Pair<LEFT, RIGHT>(left, right);
    }
    
    public final LEFT left;
    public final RIGHT right;
    
    public Pair(LEFT left, RIGHT right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }
}
