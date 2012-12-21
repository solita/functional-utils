package fi.solita.utils.functional;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> extends Tuple implements Tuple._1<T1>, Tuple._2<T2>, Tuple._3<T3>, Tuple._4<T4>, Tuple._5<T5>, Tuple._6<T6>, Tuple._7<T7>, Tuple._8<T8>, Tuple._9<T9>, Tuple._10<T10>, Tuple._11<T11>, Tuple._12<T12>, Tuple._13<T13>, Tuple._14<T14>, Tuple._15<T15>, Tuple._16<T16>, Tuple._17<T17>, Tuple._18<T18>, Tuple._19<T19>, Tuple._20<T20>, Tuple._21<T21> {

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
    
    Tuple21() {
        // Needed for deserialization
        this._1 = null;
        this._2 = null;
        this._3 = null;
        this._4 = null;
        this._5 = null;
        this._6 = null;
        this._7 = null;
        this._8 = null;
        this._9 = null;
        this._10 = null;
        this._11 = null;
        this._12 = null;
        this._13 = null;
        this._14 = null;
        this._15 = null;
        this._16 = null;
        this._17 = null;
        this._18 = null;
        this._19 = null;
        this._20 = null;
        this._21 = null;
    }
    
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
    
    public <T> Tuple22<T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> prepend(T t) {
        return Tuple.of(t, _1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    public <T> Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T> append(T t) {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, t);
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
    
    public Tuple5<T1, T2, T3, T4, T5> take5() {
        return Tuple.of(_1, _2, _3, _4, _5);
    }
    
    public Tuple6<T1, T2, T3, T4, T5, T6> take6() {
        return Tuple.of(_1, _2, _3, _4, _5, _6);
    }
    
    public Tuple7<T1, T2, T3, T4, T5, T6, T7> take7() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7);
    }
    
    public Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> take8() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8);
    }
    
    public Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> take9() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9);
    }
    
    public Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> take10() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10);
    }

    public Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> take11() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11);
    }
    
    public Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> take12() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12);
    }
    
    public Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> take13() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13);
    }
    
    public Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> take14() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14);
    }
    
    public Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> take15() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15);
    }
    
    public Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> take16() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16);
    }
    
    public Tuple17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> take17() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17);
    }
    
    public Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> take18() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18);
    }
    
    public Tuple19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> take19() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19);
    }
    
    public Tuple20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> take20() {
        return Tuple.of(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20);
    }

    @Override
    public T1 get_1() {
        return _1;
    }
    
    @Override
    public T2 get_2() {
        return _2;
    }
    
    @Override
    public T3 get_3() {
        return _3;
    }
    
    @Override
    public T4 get_4() {
        return _4;
    }
    
    @Override
    public T5 get_5() {
        return _5;
    }
    
    @Override
    public T6 get_6() {
        return _6;
    }
    
    @Override
    public T7 get_7() {
        return _7;
    }
    
    @Override
    public T8 get_8() {
        return _8;
    }
    
    @Override
    public T9 get_9() {
        return _9;
    }
    
    @Override
    public T10 get_10() {
        return _10;
    }
    
    @Override
    public T11 get_11() {
        return _11;
    }
    
    @Override
    public T12 get_12() {
        return _12;
    }
    
    @Override
    public T13 get_13() {
        return _13;
    }
    
    @Override
    public T14 get_14() {
        return _14;
    }
    
    @Override
    public T15 get_15() {
        return _15;
    }
    
    @Override
    public T16 get_16() {
        return _16;
    }
    
    @Override
    public T17 get_17() {
        return _17;
    }
    
    @Override
    public T18 get_18() {
        return _18;
    }
    
    @Override
    public T19 get_19() {
        return _19;
    }
    
    @Override
    public T20 get_20() {
        return _20;
    }
    
    @Override
    public T21 get_21() {
        return _21;
    }
    
    @Override
    public Object[] toArray() {
        return new Object[]{_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21};
    }
    
    static final class SerializableTuple<T1 extends Serializable, T2 extends Serializable, T3 extends Serializable, T4 extends Serializable, T5 extends Serializable, T6 extends Serializable, T7 extends Serializable, T8 extends Serializable, T9 extends Serializable, T10 extends Serializable, T11 extends Serializable, T12 extends Serializable, T13 extends Serializable, T14 extends Serializable, T15 extends Serializable, T16 extends Serializable, T17 extends Serializable, T18 extends Serializable, T19 extends Serializable, T20 extends Serializable, T21 extends Serializable> extends Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> implements Serializable {
        private static final long serialVersionUID = 1L;
        
        SerializableTuple() {
            // for deserialization
            super(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        }

        SerializableTuple(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21) {
            super(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
        }

        private void writeObject(ObjectOutputStream out) throws IOException  {
            doWriteObject(out);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            doReadObject(in);
        }
    }
}
