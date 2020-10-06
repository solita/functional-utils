package fi.solita.utils.functional;

/**
 * Empty tuple. Quite useless.
 */
public class Tuple0 extends Tuple {

    public Tuple0() {
    }
    
    /**
     * @return this tuple with {@code t} prepended to the left.
     */
    public <T> Tuple1<T> prepend(T t) {
        return Tuple.of(t);
    }
    
    /**
     * @return this tuple with {@code t} appended to the right.
     */
    public <T> Tuple1<T> append(T t) {
        return Tuple.of(t);
    }

    @Override
    public Object[] toArray() {
        return new Object[]{};
    }
}
