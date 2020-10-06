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
    
    /**
     * @return this tuple with {@code t} prepended to the left.
     */
    public <T> Tuple4<T, T1, T2, T3> prepend(T t) {
        return Tuple.of(t, _1, _2, _3);
    }
    
    /**
     * @return this tuple with {@code t} appended to the right.
     */
    public <T> Tuple4<T1, T2, T3, T> append(T t) {
        return Tuple.of(_1, _2, _3, t);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1> Tuple4<T1, T2, T3, A1> join(Tuple1<A1> t) {
        return Tuple.of(_1, _2, _3, t._1);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2> Tuple5<T1,T2,T3,A1,A2> join(Tuple2<A1,A2> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3> Tuple6<T1,T2,T3,A1,A2,A3> join(Tuple3<A1,A2,A3> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4> Tuple7<T1,T2,T3,A1,A2,A3,A4> join(Tuple4<A1,A2,A3,A4> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5> Tuple8<T1,T2,T3,A1,A2,A3,A4,A5> join(Tuple5<A1,A2,A3,A4,A5> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6> Tuple9<T1,T2,T3,A1,A2,A3,A4,A5,A6> join(Tuple6<A1,A2,A3,A4,A5,A6> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6);
    }

    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7> Tuple10<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7> join(Tuple7<A1,A2,A3,A4,A5,A6,A7> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7);
    }

    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8> Tuple11<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8> join(Tuple8<A1,A2,A3,A4,A5,A6,A7,A8> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9> Tuple12<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9> join(Tuple9<A1,A2,A3,A4,A5,A6,A7,A8,A9> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> Tuple13<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> join(Tuple10<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> Tuple14<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> join(Tuple11<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> Tuple15<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> join(Tuple12<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> Tuple16<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> join(Tuple13<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> Tuple17<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> join(Tuple14<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> Tuple18<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> join(Tuple15<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> Tuple19<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> join(Tuple16<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> Tuple20<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> join(Tuple17<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> Tuple21<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> join(Tuple18<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19> Tuple22<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19> join(Tuple19<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20> Tuple23<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20> join(Tuple20<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21> Tuple24<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21> join(Tuple21<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22> Tuple25<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22> join(Tuple22<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23> Tuple26<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23> join(Tuple23<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24> Tuple27<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24> join(Tuple24<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25> Tuple28<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25> join(Tuple25<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26> Tuple29<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26> join(Tuple26<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27> Tuple30<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27> join(Tuple27<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28> Tuple31<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28> join(Tuple28<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27, t._28);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29> Tuple32<T1,T2,T3,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29> join(Tuple29<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29> t) {
        return Tuple.of(_1, _2, _3, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27, t._28, t._29);
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
    public Tuple2<T1, T2> take2() {
        return Tuple.of(_1, _2);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple2<T2, T3> drop1() {
        return Tuple.of(_2, _3);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple1<T3> drop2() {
        return Tuple.of(_3);
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
