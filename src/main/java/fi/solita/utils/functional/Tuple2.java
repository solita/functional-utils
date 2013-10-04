package fi.solita.utils.functional;

import java.util.Map;

public class Tuple2<T1, T2> extends Tuple implements Map.Entry<T1, T2>, Tuple._1<T1>, Tuple._2<T2> {

    public final T1 _1;
    public final T2 _2;
    
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
}
