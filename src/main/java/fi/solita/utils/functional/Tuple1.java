package fi.solita.utils.functional;


public class Tuple1<T1> extends Tuple implements Tuple._1<T1> {

    public final T1 _1;
    
    Tuple1(T1 t1) {
        this._1 = t1;
    }
    
    /**
     * @return this tuple with {@code t} prepended to the left.
     */
    public <T> Tuple2<T, T1> prepend(T t) {
        return Tuple.of(t, _1);
    }
    
    /**
     * @return this tuple with {@code t} appended to the right.
     */
    public <T> Tuple2<T1, T> append(T t) {
        return Tuple.of(_1, t);
    }
    
    public <A1,A2> Tuple2<T1, A1> join(Tuple1<A1> t) {
        return Tuple.of(_1, t._1);
    }
    
    public <A1,A2> Tuple3<T1, A1, A2> join(Tuple2<A1,A2> t) {
        return Tuple.of(_1, t._1, t._2);
    }
    
    public <A1,A2,A3> Tuple4<T1,A1,A2,A3> join(Tuple3<A1,A2,A3> t) {
        return Tuple.of(_1, t._1, t._2, t._3);
    }
    
    public <A1,A2,A3,A4> Tuple5<T1,A1,A2,A3,A4> join(Tuple4<A1,A2,A3,A4> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4);
    }
    
    public <A1,A2,A3,A4,A5> Tuple6<T1,A1,A2,A3,A4,A5> join(Tuple5<A1,A2,A3,A4,A5> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5);
    }
    
    public <A1,A2,A3,A4,A5,A6> Tuple7<T1,A1,A2,A3,A4,A5,A6> join(Tuple6<A1,A2,A3,A4,A5,A6> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7> Tuple8<T1,A1,A2,A3,A4,A5,A6,A7> join(Tuple7<A1,A2,A3,A4,A5,A6,A7> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7);
    }

    public <A1,A2,A3,A4,A5,A6,A7,A8> Tuple9<T1,A1,A2,A3,A4,A5,A6,A7,A8> join(Tuple8<A1,A2,A3,A4,A5,A6,A7,A8> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9> Tuple10<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9> join(Tuple9<A1,A2,A3,A4,A5,A6,A7,A8,A9> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> Tuple11<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> join(Tuple10<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> Tuple12<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> join(Tuple11<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> Tuple13<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> join(Tuple12<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> Tuple14<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> join(Tuple13<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> Tuple15<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> join(Tuple14<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> Tuple16<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> join(Tuple15<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> Tuple17<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> join(Tuple16<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> Tuple18<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> join(Tuple17<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> Tuple19<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> join(Tuple18<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19> Tuple20<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19> join(Tuple19<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20> Tuple21<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20> join(Tuple20<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21> Tuple22<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21> join(Tuple21<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22> Tuple23<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22> join(Tuple22<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23> Tuple24<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23> join(Tuple23<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24> Tuple25<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24> join(Tuple24<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25> Tuple26<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25> join(Tuple25<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26> Tuple27<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26> join(Tuple26<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27> Tuple28<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27> join(Tuple27<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28> Tuple29<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28> join(Tuple28<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27, t._28);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29> Tuple30<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29> join(Tuple29<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27, t._28, t._29);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30> Tuple31<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30> join(Tuple30<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27, t._28, t._29, t._30);
    }
    
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31> Tuple32<T1,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31> join(Tuple31<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31> t) {
        return Tuple.of(_1, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27, t._28, t._29, t._30, t._31);
    }
    
    public T1 get_1() {
        return _1;
    }
    
    @Override
    public Object[] toArray() {
        return new Object[]{_1};
    }
}
