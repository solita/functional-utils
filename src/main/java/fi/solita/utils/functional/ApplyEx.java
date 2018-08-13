package fi.solita.utils.functional;

public abstract class ApplyEx {
    private ApplyEx() {
    }
    
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
    
    public static interface Ex11<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11;
    }
    
    public static interface Ex12<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12;
    }
    
    public static interface Ex13<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13;
    }
    
    public static interface Ex14<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14;
    }
    
    public static interface Ex15<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15;
    }
    
    public static interface Ex16<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16;
    }
    
    public static interface Ex17<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17;
    }
    
    public static interface Ex18<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18;
    }
    
    public static interface Ex19<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19;
    }
    
    public static interface Ex20<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20;
    }
    
    public static interface Ex21<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21;
    }
    
    public static interface Ex22<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable, E22 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22;
    }
    
    public static interface Ex23<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable, E22 extends Throwable, E23 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22, E23;
    }
    
    public static interface Ex24<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable, E22 extends Throwable, E23 extends Throwable, E24 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22, E23, E24;
    }
    
    public static interface Ex25<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable, E22 extends Throwable, E23 extends Throwable, E24 extends Throwable, E25 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22, E23, E24, E25;
    }
    
    public static interface Ex26<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable, E22 extends Throwable, E23 extends Throwable, E24 extends Throwable, E25 extends Throwable, E26 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22, E23, E24, E25, E26;
    }
    
    public static interface Ex27<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable, E22 extends Throwable, E23 extends Throwable, E24 extends Throwable, E25 extends Throwable, E26 extends Throwable, E27 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22, E23, E24, E25, E26, E27;
    }
    
    public static interface Ex28<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable, E22 extends Throwable, E23 extends Throwable, E24 extends Throwable, E25 extends Throwable, E26 extends Throwable, E27 extends Throwable, E28 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22, E23, E24, E25, E26, E27, E28;
    }
    
    public static interface Ex29<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable, E22 extends Throwable, E23 extends Throwable, E24 extends Throwable, E25 extends Throwable, E26 extends Throwable, E27 extends Throwable, E28 extends Throwable, E29 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22, E23, E24, E25, E26, E27, E28, E29;
    }
    
    public static interface Ex30<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable, E22 extends Throwable, E23 extends Throwable, E24 extends Throwable, E25 extends Throwable, E26 extends Throwable, E27 extends Throwable, E28 extends Throwable, E29 extends Throwable, E30 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22, E23, E24, E25, E26, E27, E28, E29, E30;
    }
    
    public static interface Ex31<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable, E22 extends Throwable, E23 extends Throwable, E24 extends Throwable, E25 extends Throwable, E26 extends Throwable, E27 extends Throwable, E28 extends Throwable, E29 extends Throwable, E30 extends Throwable, E31 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22, E23, E24, E25, E26, E27, E28, E29, E30, E31;
    }
    
    public static interface Ex32<T,R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable, E5 extends Throwable, E6 extends Throwable, E7 extends Throwable, E8 extends Throwable, E9 extends Throwable, E10 extends Throwable, E11 extends Throwable, E12 extends Throwable, E13 extends Throwable, E14 extends Throwable, E15 extends Throwable, E16 extends Throwable, E17 extends Throwable, E18 extends Throwable, E19 extends Throwable, E20 extends Throwable, E21 extends Throwable, E22 extends Throwable, E23 extends Throwable, E24 extends Throwable, E25 extends Throwable, E26 extends Throwable, E27 extends Throwable, E28 extends Throwable, E29 extends Throwable, E30 extends Throwable, E31 extends Throwable, E32 extends Throwable> {
        R apply(T t) throws E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22, E23, E24, E25, E26, E27, E28, E29, E30, E31, E32;
    }
}
