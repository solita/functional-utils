package fi.solita.utils.functional;


public class Tuple43<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> extends Tuple implements Tuple.Tailable<Tuple42<T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43>>, Tuple._1<T1>, Tuple._2<T2>, Tuple._3<T3>, Tuple._4<T4>, Tuple._5<T5>, Tuple._6<T6>, Tuple._7<T7>, Tuple._8<T8>, Tuple._9<T9>, Tuple._10<T10>, Tuple._11<T11>, Tuple._12<T12>, Tuple._13<T13>, Tuple._14<T14>, Tuple._15<T15>, Tuple._16<T16>, Tuple._17<T17>, Tuple._18<T18>, Tuple._19<T19>, Tuple._20<T20>, Tuple._21<T21>, Tuple._22<T22>, Tuple._23<T23>, Tuple._24<T24>, Tuple._25<T25>, Tuple._26<T26>, Tuple._27<T27>, Tuple._28<T28>, Tuple._29<T29>, Tuple._30<T30>, Tuple._31<T31>, Tuple._32<T32>, Tuple._33<T33>, Tuple._34<T34>, Tuple._35<T35>, Tuple._36<T36>, Tuple._37<T37>, Tuple._38<T38>, Tuple._39<T39>, Tuple._40<T40>, Tuple._41<T41>, Tuple._42<T42>, Tuple._43<T43> {

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
    public final T22 _22;
    public final T23 _23;
    public final T24 _24;
    public final T25 _25;
    public final T26 _26;
    public final T27 _27;
    public final T28 _28;
    public final T29 _29;
    public final T30 _30;
    public final T31 _31;
    public final T32 _32;
    public final T33 _33;
    public final T34 _34;
    public final T35 _35;
    public final T36 _36;
    public final T37 _37;
    public final T38 _38;
    public final T39 _39;
    public final T40 _40;
    public final T41 _41;
    public final T42 _42;
    public final T43 _43;

    public Tuple43(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32, T33 _33, T34 _34, T35 _35, T36 _36, T37 _37, T38 _38, T39 _39, T40 _40, T41 _41, T42 _42, T43 _43) {
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
        this._22 = _22;
        this._23 = _23;
        this._24 = _24;
        this._25 = _25;
        this._26 = _26;
        this._27 = _27;
        this._28 = _28;
        this._29 = _29;
        this._30 = _30;
        this._31 = _31;
        this._32 = _32;
        this._33 = _33;
        this._34 = _34;
        this._35 = _35;
        this._36 = _36;
        this._37 = _37;
        this._38 = _38;
        this._39 = _39;
        this._40 = _40;
        this._41 = _41;
        this._42 = _42;
        this._43 = _43;
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
     * @return a prefix of this tuple.
     */
    public Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> take21() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> take22() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple23<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23> take23() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple24<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24> take24() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple25<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25> take25() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple26<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26> take26() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple27<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27> take27() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple28<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28> take28() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple29<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29> take29() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple30<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30> take30() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple31<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31> take31() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31);
    }

    /**
     * @return a prefix of this tuple.
     */
    public Tuple32<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32> take32() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31, _32);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple33<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33> take33() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31, _32, _33);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple34<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34> take34() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31, _32, _33, _34);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple35<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35> take35() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31, _32, _33, _34, _35);
    }

    /**
     * @return a prefix of this tuple.
     */
    public Tuple36<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36> take36() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple37<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37> take37() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple38<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38> take38() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple39<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39> take39() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple40<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40> take40() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple41<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41> take41() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41);
    }
    
    /**
     * @return a prefix of this tuple.
     */
    public Tuple42<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42> take42() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25 ,_26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple42<T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop1() {
        return Tuple.of(_2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple41<T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop2() {
        return Tuple.of(_3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple40<T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop3() {
        return Tuple.of(_4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple39<T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop4() {
        return Tuple.of(_5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple38<T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop5() {
        return Tuple.of(_6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple37<T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop6() {
        return Tuple.of(_7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple36<T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop7() {
        return Tuple.of(_8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple35<T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop8() {
        return Tuple.of(_9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple34<T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop9() {
        return Tuple.of(_10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple33<T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop10() {
        return Tuple.of(_11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple32<T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop11() {
        return Tuple.of(_12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple31<T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop12() {
        return Tuple.of(_13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }

    /**
     * @return a suffix of this tuple.
     */
    public Tuple30<T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop13() {
        return Tuple.of(_14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }

    /**
     * @return a suffix of this tuple.
     */
    public Tuple29<T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop14() {
        return Tuple.of(_15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple28<T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop15() {
        return Tuple.of(_16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple27<T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop16() {
        return Tuple.of(_17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }

    /**
     * @return a suffix of this tuple.
     */
    public Tuple26<T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop17() {
        return Tuple.of(_18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple25<T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop18() {
        return Tuple.of(_19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple24<T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop19() {
        return Tuple.of(_20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple23<T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop20() {
        return Tuple.of(_21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple22<T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop21() {
        return Tuple.of(_22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple21<T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop22() {
        return Tuple.of(_23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple20<T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop23() {
        return Tuple.of(_24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple19<T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop24() {
        return Tuple.of(_25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple18<T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop25() {
        return Tuple.of(_26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple17<T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop26() {
        return Tuple.of(_27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple16<T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop27() {
        return Tuple.of(_28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple15<T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop28() {
        return Tuple.of(_29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple14<T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop29() {
        return Tuple.of(_30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple13<T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop30() {
        return Pair.of(_31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple12<T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop31() {
        return Pair.of(_32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple11<T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop32() {
        return Pair.of(_33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple10<T34, T35, T36, T37, T38, T39, T40, T41, T42, T43> drop33() {
        return Tuple.of(_34, _35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple9<T35, T36, T37, T38, T39, T40, T41, T42, T43> drop34() {
        return Tuple.of(_35, _36, _37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple8<T36, T37, T38, T39, T40, T41, T42, T43> drop35() {
        return Tuple.of(_36, _37, _38, _39, _40, _41, _42, _43);
    }

    /**
     * @return a suffix of this tuple.
     */
    public Tuple7<T37, T38, T39, T40, T41, T42, T43> drop36() {
        return Pair.of(_37, _38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple6<T38, T39, T40, T41, T42, T43> drop37() {
        return Tuple.of(_38, _39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple5<T39, T40, T41, T42, T43> drop38() {
        return Tuple.of(_39, _40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple4<T40, T41, T42, T43> drop39() {
        return Tuple.of(_40, _41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple3<T41, T42, T43> drop40() {
        return Tuple.of(_41, _42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Pair<T42, T43> drop41() {
        return Pair.of(_42, _43);
    }
    
    /**
     * @return a suffix of this tuple.
     */
    public Tuple1<T43> drop42() {
        return Tuple.of(_43);
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
    
    
    public T22 get_22() {
        return _22;
    }
    
    public T23 get_23() {
        return _23;
    }
    
    public T24 get_24() {
        return _24;
    }
    
    public T25 get_25() {
        return _25;
    }
    
    public T26 get_26() {
        return _26;
    }
    
    public T27 get_27() {
        return _27;
    }
    
    public T28 get_28() {
        return _28;
    }
    
    public T29 get_29() {
        return _29;
    }
    
    public T30 get_30() {
        return _30;
    }
    
    public T31 get_31() {
        return _31;
    }
    
    public T32 get_32() {
        return _32;
    }
    
    public T33 get_33() {
        return _33;
    }
    
    public T34 get_34() {
        return _34;
    }
    
    public T35 get_35() {
        return _35;
    }
    
    public T36 get_36() {
        return _36;
    }

    public T37 get_37() {
        return _37;
    }
    
    public T38 get_38() {
        return _38;
    }
    
    public T39 get_39() {
        return _39;
    }
    
    public T40 get_40() {
        return _40;
    }
    
    public T41 get_41() {
        return _41;
    }
    
    public T42 get_42() {
        return _42;
    }
    
    public T43 get_43() {
        return _43;
    }
    
    public Object[] toArray() {
        return new Object[]{_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42, _43};
    }
}
