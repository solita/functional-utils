package fi.solita.utils.functional;


public class Tuple0 extends Tuple {

    public Tuple0() {
    }
    
    public <T> Tuple1<T> prepend(T t) {
        return Tuple.of(t);
    }
    
    public <T> Tuple1<T> append(T t) {
        return Tuple.of(t);
    }

    @Override
    public Object[] toArray() {
        return new Object[]{};
    }
}
