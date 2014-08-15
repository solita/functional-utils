package fi.solita.utils.functional;


public class Tuple1<T1> extends Tuple implements Tuple._1<T1> {

    public final T1 _1;
    
    public Tuple1(T1 t1) {
        this._1 = t1;
    }
    
    public <T> Tuple2<T, T1> prepend(T t) {
        return Tuple.of(t, _1);
    }
    
    public <T> Tuple2<T1, T> append(T t) {
        return Tuple.of(_1, t);
    }
    
    public T1 get_1() {
        return _1;
    }
    
    @Override
    public Object[] toArray() {
        return new Object[]{_1};
    }
}
