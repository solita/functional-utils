package fi.solita.utils.functional;


public class Tuple3<T1, T2, T3> extends Tuple implements Tuple._1<T1>, Tuple._2<T2>, Tuple._3<T3> {

    public final T1 _1;
    public final T2 _2;
    public final T3 _3;
    
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
    
    public T1 get_1() {
        return _1;
    }
    
    public T2 get_2() {
        return _2;
    }
    
    public T3 get_3() {
        return _3;
    }
    
    @Override
    public Object[] toArray() {
        return new Object[]{_1, _2, _3};
    }
}
