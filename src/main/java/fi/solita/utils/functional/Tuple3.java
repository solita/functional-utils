package fi.solita.utils.functional;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Tuple3<T1, T2, T3> extends Tuple implements Tuple._1<T1>, Tuple._2<T2>, Tuple._3<T3> {

    public final T1 _1;
    public final T2 _2;
    public final T3 _3;
    
    Tuple3() {
        // Needed for deserialization
        this._1 = null;
        this._2 = null;
        this._3 = null;
    }
    
    public Tuple3(T1 _1, T2 _2, T3 _3) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
    }
    
    public <T> Tuple4<T, T1, T2, T3> prepend(T t) {
        return Tuple.of(t, _1, _2, _3);
    }
    
    public <T> Tuple4<T1, T2, T3, T> append(T t) {
        return Tuple.of(_1, _2, _3, t);
    }
    
    public Tuple2<T1, T2> take2() {
        return Tuple.of(_1, _2);
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
    public T3 get_3() {
        return _3;
    }
    
    @Override
    public Object[] toArray() {
        return new Object[]{_1, _2, _3};
    }
    
    static final class SerializableTuple<T1 extends Serializable, T2 extends Serializable, T3 extends Serializable> extends Tuple3<T1, T2, T3> implements Serializable {
        private static final long serialVersionUID = 1L;
        
        SerializableTuple() {
            // for deserialization
            super(null, null, null);
        }

        SerializableTuple(T1 _1, T2 _2, T3 _3) {
            super(_1, _2, _3);
        }

        private void writeObject(ObjectOutputStream out) throws IOException  {
            doWriteObject(out);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            doReadObject(in);
        }
    }
}
