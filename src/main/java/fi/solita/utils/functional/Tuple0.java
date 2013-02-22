package fi.solita.utils.functional;

import java.io.Serializable;

public class Tuple0 extends Tuple implements Serializable {

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
