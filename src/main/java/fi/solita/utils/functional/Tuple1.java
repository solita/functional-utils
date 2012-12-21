package fi.solita.utils.functional;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Tuple1<T1> extends Tuple implements Tuple._1<T1> {

    public final T1 _1;
    
    Tuple1() {
        // Needed for deserialization
        this._1 = null;
    }
    
    public Tuple1(T1 t1) {
        this._1 = t1;
    }
    
    public <T> Tuple2<T, T1> prepend(T t) {
        return Tuple.of(t, _1);
    }
    
    public <T> Tuple2<T1, T> append(T t) {
        return Tuple.of(_1, t);
    }
    
    @Override
    public T1 get_1() {
        return _1;
    }
    
    @Override
    public Object[] toArray() {
        return new Object[]{_1};
    }
    
    static final class SerializableTuple<T1 extends Serializable> extends Tuple1<T1> implements Serializable {
        private static final long serialVersionUID = 1L;
        
        SerializableTuple() {
            // for deserialization
            super(null);
        }

        SerializableTuple(T1 t1) {
            super(t1);
        }

        private void writeObject(ObjectOutputStream out) throws IOException  {
            doWriteObject(out);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            doReadObject(in);
        }
    }
}
