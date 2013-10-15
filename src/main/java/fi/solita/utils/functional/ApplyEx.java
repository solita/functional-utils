package fi.solita.utils.functional;

public abstract class ApplyEx {
    public static interface Ex1<T,R,E1 extends Throwable> {
        R apply(T t) throws E1;
    }
    
    public static interface Ex2<T,R,E1 extends Throwable, E2 extends Throwable> {
        R apply(T t) throws E1, E2;
    }
    
    public static interface Ex3<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable> {
        R apply(T t) throws E1, E2, E3;
    }
    
    public static interface Ex4<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4;
    }
    
    public static interface Ex5<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5;
    }
    
    public static interface Ex6<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6;
    }
    
    public static interface Ex7<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7;
    }
    
    public static interface Ex8<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8;
    }
    
    public static interface Ex9<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9;
    }
    
    public static interface Ex10<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10;
    }
}
