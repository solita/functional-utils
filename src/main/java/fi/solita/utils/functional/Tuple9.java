package fi.solita.utils.functional;


public class Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> extends Tuple implements Tuple.Tailable<Tuple8<T2,T3,T4,T5,T6,T7,T8,T9>>, Tuple._1<T1>, Tuple._2<T2>, Tuple._3<T3>, Tuple._4<T4>, Tuple._5<T5>, Tuple._6<T6>, Tuple._7<T7>, Tuple._8<T8>, Tuple._9<T9> {

    public final T1 _1;
    public final T2 _2;
    public final T3 _3;
    public final T4 _4;
    public final T5 _5;
    public final T6 _6;
    public final T7 _7;
    public final T8 _8;
    public final T9 _9;
    
    public Tuple9(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
        this._7 = _7;
        this._8 = _8;
        this._9 = _9;
    }
    
    /**
     * @return this tuple with {@code t} prepended to the left.
     */
    public <T> Tuple10<T, T1, T2, T3, T4, T5, T6, T7, T8, T9> prepend(T t) {
        return Tuple.of(t, _1, _2, _3, _4, _5, _6, _7, _8, _9);
    }
    
    /**
     * @return this tuple with {@code t} appended to the right.
     */
    public <T> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T> append(T t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, A1> join(Tuple1<A1> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2> Tuple11<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2> join(Tuple2<A1,A2> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3> Tuple12<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3> join(Tuple3<A1,A2,A3> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4> Tuple13<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4> join(Tuple4<A1,A2,A3,A4> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5> Tuple14<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5> join(Tuple5<A1,A2,A3,A4,A5> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6> Tuple15<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6> join(Tuple6<A1,A2,A3,A4,A5,A6> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7> Tuple16<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7> join(Tuple7<A1,A2,A3,A4,A5,A6,A7> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7);
    }

    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8> Tuple17<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8> join(Tuple8<A1,A2,A3,A4,A5,A6,A7,A8> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9> Tuple18<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9> join(Tuple9<A1,A2,A3,A4,A5,A6,A7,A8,A9> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> Tuple19<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> join(Tuple10<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10);
    }

    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> Tuple20<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> join(Tuple11<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> Tuple21<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> join(Tuple12<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> Tuple22<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> join(Tuple13<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> Tuple23<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> join(Tuple14<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> Tuple24<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> join(Tuple15<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> Tuple25<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> join(Tuple16<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> Tuple26<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> join(Tuple17<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> Tuple27<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> join(Tuple18<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19> Tuple28<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19> join(Tuple19<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20> Tuple29<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20> join(Tuple20<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21> Tuple30<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21> join(Tuple21<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22> Tuple31<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22> join(Tuple22<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23> Tuple32<T1,T2,T3,T4,T5,T6,T7,T8,T9,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23> join(Tuple23<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24> Tuple33<T1, T2, T3, T4, T5, T6, T7, T8, T9, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24> join(Tuple24<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25> Tuple34<T1, T2, T3, T4, T5, T6, T7, T8, T9, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25> join(Tuple25<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26> Tuple35<T1, T2, T3, T4, T5, T6, T7, T8, T9, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26> join(Tuple26<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27> Tuple36<T1, T2, T3, T4, T5, T6, T7, T8, T9, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27> join(Tuple27<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27);
    }

    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28> Tuple37<T1, T2, T3, T4, T5, T6, T7, T8, T9, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28> join(Tuple28<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27, t._28);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29> Tuple38<T1, T2, T3, T4, T5, T6, T7, T8, T9, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29> join(Tuple29<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27, t._28, t._29);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30> Tuple39<T1, T2, T3, T4, T5, T6, T7, T8, T9, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29, A30> join(Tuple30<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27, t._28, t._29, t._30);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31,A32> Tuple41<T1, T2, T3, T4, T5, T6, T7, T8, T9, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29, A30, A31, A32> join(Tuple32<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31,A32> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27, t._28, t._29, t._30, t._31, t._32);
    }

    /**
     * @return a prefix of this tuple.
     */
    public Tuple1<T1> take1() {
        return Tuple.of(_1);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Pair<T1, T2> take2() {
        return Pair.of(_1, _2);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple3<T1, T2, T3> take3() {
        return Tuple.of(_1, _2, _3);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple4<T1, T2, T3, T4> take4() {
        return Tuple.of(_1, _2, _3, _4);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple5<T1, T2, T3, T4, T5> take5() {
        return Tuple.of(_1, _2, _3, _4, _5);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple6<T1, T2, T3, T4, T5, T6> take6() {
        return Tuple.of(_1, _2, _3, _4, _5, _6);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple7<T1, T2, T3, T4, T5, T6, T7> take7() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> take8() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple8<T2, T3, T4, T5, T6, T7, T8, T9> drop1() {
        return Tuple.of(_2, _3, _4, _5, _6, _7, _8, _9);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple7<T3, T4, T5, T6, T7, T8, T9> drop2() {
        return Tuple.of(_3, _4, _5, _6, _7, _8, _9);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple6<T4, T5, T6, T7, T8, T9> drop3() {
        return Tuple.of(_4, _5, _6, _7, _8, _9);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple5<T5, T6, T7, T8, T9> drop4() {
        return Tuple.of(_5, _6, _7, _8, _9);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple4<T6, T7, T8, T9> drop5() {
        return Tuple.of(_6, _7, _8, _9);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple3<T7, T8, T9> drop6() {
        return Tuple.of(_7, _8, _9);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Pair<T8, T9> drop7() {
        return Pair.of(_8, _9);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple1<T9> drop8() {
        return Tuple.of(_9);
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
    
    public T6 get_6() {
        return _6;
    }
    
    public T7 get_7() {
        return _7;
    }
    
    public T8 get_8() {
        return _8;
    }
    
    public T9 get_9() {
        return _9;
    }
    
    @Override
    public Object[] toArray() {
        return new Object[]{_1, _2, _3, _4, _5, _6, _7, _8, _9};
    }
}
