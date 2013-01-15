package fi.solita.utils.functional;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

public class Pair<LEFT, RIGHT> extends Tuple2<LEFT, RIGHT> {

    @SuppressWarnings("unchecked")
    public static <LEFT, RIGHT> Pair<LEFT, RIGHT> of(LEFT left, RIGHT right) {
        if ((left instanceof Serializable || left != null && left.getClass().isPrimitive()) && 
            (right instanceof Serializable || right != null && right.getClass().isPrimitive())) {
            return (Pair<LEFT, RIGHT>) new SerializablePair<Serializable, Serializable>((Serializable)left, (Serializable)right);
        }
        return new Pair<LEFT, RIGHT>(left, right);
    }
    
    @SuppressWarnings("unchecked")
    public static <LEFT extends Serializable, RIGHT extends Serializable, R extends Pair<LEFT, RIGHT> & Serializable> R of(LEFT left, RIGHT right) {
        return (R)new SerializablePair<LEFT, RIGHT>(left, right);
    }
    
    public final LEFT left;
    public final RIGHT right;
    
    public Pair(LEFT left, RIGHT right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }
    
    Pair() {
        // Needed for deserialization
        this.left = null;
        this.right = null;
    }
    
    public static final class SerializablePair<LEFT extends Serializable, RIGHT extends Serializable> extends Pair<LEFT, RIGHT> implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private SerializablePair() {
            // for deserialization
            super(null, null);
        }

        private SerializablePair(LEFT left, RIGHT right) {
            super(left, right);
        }

        private void writeObject(ObjectOutputStream out) throws IOException  {
            out.writeObject(_1);
            out.writeObject(_2);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            try {
                Field _1 = Pair.class.getField("_1");
                Field _2 = Pair.class.getField("_2");
                Field f1 = Pair.class.getField("left");
                Field f2 = Pair.class.getField("right");
                _1.setAccessible(true);
                _2.setAccessible(true);
                f1.setAccessible(true);
                f2.setAccessible(true);
                Object left = in.readObject();
                Object right = in.readObject();
                _1.set(this, left);
                _2.set(this, right);
                f1.set(this, left);
                f2.set(this, right);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
