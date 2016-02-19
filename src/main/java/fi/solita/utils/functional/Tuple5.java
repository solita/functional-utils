package fi.solita.utils.functional;


public class Tuple5<T1, T2, T3, T4, T5> extends Tuple implements Tuple._1<T1>, Tuple._2<T2>, Tuple._3<T3>, Tuple._4<T4>, Tuple._5<T5> {

    public final T1 _1;
    public final T2 _2;
    public final T3 _3;
    public final T4 _4;
    public final T5 _5;
    
    public Tuple5(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
    }
    
    public <T> Tuple6<T, T1, T2, T3, T4, T5> prepend(T t) {
        return Tuple.of(t, _1, _2, _3, _4, _5);
    }
    
    public <T> Tuple6<T1, T2, T3, T4, T5, T> append(T t) {
        return Tuple.of(_1, _2, _3, _4, _5, t);
    }
    
    public <A1,A2> Tuple7<T1,T2,T3,T4,T5,A1,A2> join(Tuple2<A1,A2> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2);
    }
    
    public <A1,A2,A3> Tuple8<T1,T2,T3,T4,T5,A1,A2,A3> join(Tuple3<A1,A2,A3> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3);
    }
    
    public <A1,A2,A3,A4> Tuple9<T1,T2,T3,T4,T5,A1,A2,A3,A4> join(Tuple4<A1,A2,A3,A4> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4);
    }
    
    public <A1,A2,A3,A4,A5> Tuple10<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5> join(Tuple5<A1,A2,A3,A4,A5> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5);
    }
    
    public <A1,A2,A3,A4,A5,A6> Tuple11<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6> join(Tuple6<A1,A2,A3,A4,A5,A6> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7> Tuple12<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7> join(Tuple7<A1,A2,A3,A4,A5,A6,A7> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7);
    }

    public <A1,A2,A3,A4,A5,A6,A7,A8> Tuple13<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7,A8> join(Tuple8<A1,A2,A3,A4,A5,A6,A7,A8> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9> Tuple14<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7,A8,A9> join(Tuple9<A1,A2,A3,A4,A5,A6,A7,A8,A9> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> Tuple15<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> join(Tuple10<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> Tuple16<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> join(Tuple11<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> Tuple17<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> join(Tuple12<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> Tuple18<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> join(Tuple13<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> Tuple19<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> join(Tuple14<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> Tuple20<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> join(Tuple15<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> Tuple21<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> join(Tuple16<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> Tuple22<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> join(Tuple17<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> Tuple23<T1,T2,T3,T4,T5,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> join(Tuple18<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> t) {
        return Tuple.of(_1, _2, _3, _4, _5, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18);
    }
    
    public Tuple2<T1, T2> take2() {
        return Tuple.of(_1, _2);
    }
    
    public Tuple3<T1, T2, T3> take3() {
        return Tuple.of(_1, _2, _3);
    }
    
    public Tuple4<T1, T2, T3, T4> take4() {
        return Tuple.of(_1, _2, _3, _4);
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
    
    public T4 get_4() {
        return _4;
    }
    
    public T5 get_5() {
        return _5;
    }
    
    @Override
    public Object[] toArray() {
        return new Object[]{_1, _2, _3, _4, _5};
    }
}
