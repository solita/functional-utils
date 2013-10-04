package fi.solita.utils.functional;


public class Tuple4<T1, T2, T3, T4> extends Tuple implements Tuple._1<T1>, Tuple._2<T2>, Tuple._3<T3>, Tuple._4<T4> {

    public final T1 _1;
    public final T2 _2;
    public final T3 _3;
    public final T4 _4;
    
    public Tuple4(T1 _1, T2 _2, T3 _3, T4 _4) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
    }
    
    public <T> Tuple5<T, T1, T2, T3, T4> prepend(T t) {
        return Tuple.of(t, _1, _2, _3, _4);
    }
    
    public <T> Tuple5<T1, T2, T3, T4, T> append(T t) {
        return Tuple.of(_1, _2, _3, _4, t);
    }
    
    public Tuple2<T1, T2> take2() {
        return Tuple.of(_1, _2);
    }
    
    public Tuple3<T1, T2, T3> take3() {
        return Tuple.of(_1, _2, _3);
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
    public T4 get_4() {
        return _4;
    }
    
    @Override
    public Object[] toArray() {
        return new Object[]{_1, _2, _3, _4};
    }
}
