package fi.solita.utils.functional;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class Tuple2<T1, T2> extends Tuple implements Map.Entry<T1, T2>, Tuple._1<T1>, Tuple._2<T2> {

    public final T1 _1;
    public final T2 _2;
    
    Tuple2() {
        // Needed for deserialization
        this._1 = null;
        this._2 = null;
    }
    
    public Tuple2(T1 _1, T2 _2) {
        this._1 = _1;
        this._2 = _2;
    }
    
    public <T> Tuple3<T, T1, T2> prepend(T t) {
        return Tuple.of(t, _1, _2);
    }
    
    public <T> Tuple3<T1, T2, T> append(T t) {
        return Tuple.of(_1, _2, t);
    }

    @Override
    public T1 getKey() {
        return _1;
    }
    
    @Override
    public T2 getValue() {
        return _2;
    }
    
    @Override
    public T2 setValue(T2 value) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public T1 get_1() {
        return _1;
    }
    
    @Override
    public T2 get_2() {
        return _2;
    }
    
    @Override
    public Object[] toArray() {
        return new Object[]{_1, _2};
    }

    static final class SerializableTuple<T1 extends Serializable, T2 extends Serializable> extends Tuple2<T1, T2> implements Serializable {
        private static final long serialVersionUID = 1L;
        
        SerializableTuple() {
            // for deserialization
            super(null, null);
        }

        SerializableTuple(T1 _1, T2 _2) {
            super(_1, _2);
        }

        private void writeObject(ObjectOutputStream out) throws IOException  {
            doWriteObject(out);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            doReadObject(in);
        }
    }
    
}
