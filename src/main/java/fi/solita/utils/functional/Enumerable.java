package fi.solita.utils.functional;

public interface Enumerable<T> {
    public Option<T> succ(T t);
    public Option<T> pred(T t);
}
