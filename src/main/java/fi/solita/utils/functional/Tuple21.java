package fi.solita.utils.functional;


public class Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> extends Tuple implements Tuple.Tailable<Tuple20<T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21>>, Tuple._1<T1>, Tuple._2<T2>, Tuple._3<T3>, Tuple._4<T4>, Tuple._5<T5>, Tuple._6<T6>, Tuple._7<T7>, Tuple._8<T8>, Tuple._9<T9>, Tuple._10<T10>, Tuple._11<T11>, Tuple._12<T12>, Tuple._13<T13>, Tuple._14<T14>, Tuple._15<T15>, Tuple._16<T16>, Tuple._17<T17>, Tuple._18<T18>, Tuple._19<T19>, Tuple._20<T20>, Tuple._21<T21> {

    public final T1 _1;
    public final T2 _2;
    public final T3 _3;
    public final T4 _4;
    public final T5 _5;
    public final T6 _6;
    public final T7 _7;
    public final T8 _8;
    public final T9 _9;
    public final T10 _10;
    public final T11 _11;
    public final T12 _12;
    public final T13 _13;
    public final T14 _14;
    public final T15 _15;
    public final T16 _16;
    public final T17 _17;
    public final T18 _18;
    public final T19 _19;
    public final T20 _20;
    public final T21 _21;
    
    public Tuple21(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
        this._7 = _7;
        this._8 = _8;
        this._9 = _9;
        this._10 = _10;
        this._11 = _11;
        this._12 = _12;
        this._13 = _13;
        this._14 = _14;
        this._15 = _15;
        this._16 = _16;
        this._17 = _17;
        this._18 = _18;
        this._19 = _19;
        this._20 = _20;
        this._21 = _21;
    }
    
    /**
     * @return this tuple with {@code t} prepended to the left.
     */
    public <T> Tuple22<T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> prepend(T t) {
        return Tuple.of(t, _1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return this tuple with {@code t} appended to the right.
     */
    public <T> Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T> append(T t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1> Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, A1> join(Tuple1<A1> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2> Tuple23<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,A1,A2> join(Tuple2<A1,A2> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3> Tuple24<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,A1,A2,A3> join(Tuple3<A1,A2,A3> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4> Tuple25<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,A1,A2,A3,A4> join(Tuple4<A1,A2,A3,A4> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4);
    }

    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5> Tuple26<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,A1,A2,A3,A4,A5> join(Tuple5<A1,A2,A3,A4,A5> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6> Tuple27<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,A1,A2,A3,A4,A5,A6> join(Tuple6<A1,A2,A3,A4,A5,A6> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5, t._6);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7> Tuple28<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,A1,A2,A3,A4,A5,A6,A7> join(Tuple7<A1,A2,A3,A4,A5,A6,A7> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5, t._6, t._7);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8> Tuple29<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,A1,A2,A3,A4,A5,A6,A7,A8> join(Tuple8<A1,A2,A3,A4,A5,A6,A7,A8> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9> Tuple30<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,A1,A2,A3,A4,A5,A6,A7,A8,A9> join(Tuple9<A1,A2,A3,A4,A5,A6,A7,A8,A9> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> Tuple31<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> join(Tuple10<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> Tuple32<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> join(Tuple11<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> Tuple33<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> join(Tuple12<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> Tuple34<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> join(Tuple13<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> Tuple35<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> join(Tuple14<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14);
    }
    
    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> Tuple36<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> join(Tuple15<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15);
    }

    /**
     * @return this tuple with {@code t} joined to the right.
     */
    public <A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> Tuple37<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> join(Tuple16<A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16> t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16);
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
     * @return a prefix of this tuple.
     */
    public Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> take9() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> take10() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10);
    }

    /**
     * @return a prefix of this tuple.
     */
    public Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> take11() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> take12() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> take13() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> take14() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> take15() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> take16() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> take17() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> take18() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> take19() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> take20() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20);
    }

    /**
     * @return a suffix of this tuple.
     */
    public Tuple20<T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> drop1() {
        return Tuple.of(_2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple19<T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> drop2() {
        return Tuple.of(_3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple18<T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> drop3() {
        return Tuple.of(_4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple17<T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> drop4() {
        return Tuple.of(_5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple16<T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> drop5() {
        return Tuple.of(_6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple15<T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> drop6() {
        return Tuple.of(_7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple14<T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> drop7() {
        return Tuple.of(_8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple13<T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> drop8() {
        return Tuple.of(_9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple12<T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> drop9() {
        return Tuple.of(_10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple11<T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> drop10() {
        return Tuple.of(_11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple10<T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> drop11() {
        return Tuple.of(_12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple9<T13, T14, T15, T16, T17, T18, T19, T20, T21> drop12() {
        return Tuple.of(_13, _14, _15, _16, _17, _18, _19, _20, _21);
    }

    /**
     * @return a suffix of this tuple.
     */
    public Tuple8<T14, T15, T16, T17, T18, T19, T20, T21> drop13() {
        return Tuple.of(_14, _15, _16, _17, _18, _19, _20, _21);
    }

    /**
     * @return a suffix of this tuple.
     */
    public Tuple7<T15, T16, T17, T18, T19, T20, T21> drop14() {
        return Tuple.of(_15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple6<T16, T17, T18, T19, T20, T21> drop15() {
        return Tuple.of(_16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple5<T17, T18, T19, T20, T21> drop16() {
        return Tuple.of(_17, _18, _19, _20, _21);
    }

    /**
     * @return a suffix of this tuple.
     */
    public Tuple4<T18, T19, T20, T21> drop17() {
        return Tuple.of(_18, _19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple3<T19, T20, T21> drop18() {
        return Tuple.of(_19, _20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Pair<T20, T21> drop19() {
        return Pair.of(_20, _21);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple1<T21> drop20() {
        return Tuple.of(_21);
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
    
    
    public T10 get_10() {
        return _10;
    }
    
    
    public T11 get_11() {
        return _11;
    }
    
    
    public T12 get_12() {
        return _12;
    }
    
    
    public T13 get_13() {
        return _13;
    }
    
    
    public T14 get_14() {
        return _14;
    }
    
    
    public T15 get_15() {
        return _15;
    }
    
    
    public T16 get_16() {
        return _16;
    }
    
    
    public T17 get_17() {
        return _17;
    }
    
    
    public T18 get_18() {
        return _18;
    }
    
    
    public T19 get_19() {
        return _19;
    }
    
    
    public T20 get_20() {
        return _20;
    }
    
    
    public T21 get_21() {
        return _21;
    }
    
    
    public Object[] toArray() {
        return new Object[]{_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21};
    }
}
