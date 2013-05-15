package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.forAll;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.range;
import static fi.solita.utils.functional.Functional.zip;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public abstract class Tuple {
    
    public static <T> List<T> asList(Tuple0 tuple) {
        return newList();
    }
    
    public static <T> List<T> asList(Tuple1<T> tuple) {
        return newList(tuple._1);
    }

    public static <T, T1 extends T, T2 extends T> List<T> asList(Tuple2<T1, T2> tuple) {
        return newList(tuple._1, tuple._2);
    }
    
    public static <T, T1 extends T, T2 extends T, T3 extends T> List<T> asList(Tuple3<T1, T2, T3> tuple) {
        return newList(tuple._1, tuple._2, tuple._3);
    }
    
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T> List<T> asList(Tuple4<T1, T2, T3, T4> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4);
    }
    
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T> List<T> asList(Tuple5<T1, T2, T3, T4, T5> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5);
    }
    
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T> List<T> asList(Tuple6<T1, T2, T3, T4, T5, T6> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T> List<T> asList(Tuple7<T1, T2, T3, T4, T5, T6, T7> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T> List<T> asList(Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T> List<T> asList(Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T> List<T> asList(Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T> List<T> asList(Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T, T12 extends T> List<T> asList(Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T, T12 extends T, T13 extends T> List<T> asList(Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T, T12 extends T, T13 extends T, T14 extends T> List<T> asList(Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T, T12 extends T, T13 extends T, T14 extends T, T15 extends T> List<T> asList(Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T, T12 extends T, T13 extends T, T14 extends T, T15 extends T, T16 extends T> List<T> asList(Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T, T12 extends T, T13 extends T, T14 extends T, T15 extends T, T16 extends T, T17 extends T> List<T> asList(Tuple17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T, T12 extends T, T13 extends T, T14 extends T, T15 extends T, T16 extends T, T17 extends T, T18 extends T> List<T> asList(Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T, T12 extends T, T13 extends T, T14 extends T, T15 extends T, T16 extends T, T17 extends T, T18 extends T, T19 extends T> List<T> asList(Tuple19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T, T12 extends T, T13 extends T, T14 extends T, T15 extends T, T16 extends T, T17 extends T, T18 extends T, T19 extends T, T20 extends T> List<T> asList(Tuple20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T, T12 extends T, T13 extends T, T14 extends T, T15 extends T, T16 extends T, T17 extends T, T18 extends T, T19 extends T, T20 extends T, T21 extends T> List<T> asList(Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T, T1 extends T, T2 extends T, T3 extends T, T4 extends T, T5 extends T, T6 extends T, T7 extends T, T8 extends T, T9 extends T, T10 extends T, T11 extends T, T12 extends T, T13 extends T, T14 extends T, T15 extends T, T16 extends T, T17 extends T, T18 extends T, T19 extends T, T20 extends T, T21 extends T, T22 extends T> List<T> asList(Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> tuple) {
        return newList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22);
    }
    
    private static Predicate<Object> isSerializable = new Predicate<Object>() {
        @Override
        public boolean accept(Object candidate) {
            return candidate instanceof Serializable || candidate != null && candidate.getClass().isPrimitive();
        }
    };
    
    public static Tuple0 of() {
        return new Tuple0();
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1> Tuple1<T1> of(T1 _1) {
        if (forAll(newList(_1), isSerializable)) {
            return (Tuple1<T1>) new Tuple1.SerializableTuple<Serializable>((Serializable)_1);
        }
        return new Tuple1<T1>(_1);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2> Tuple2<T1, T2> of(T1 _1, T2 _2) {
        if (forAll(newList(_1, _2), isSerializable)) {
            return (Tuple2<T1, T2>) new Tuple2.SerializableTuple<Serializable, Serializable>((Serializable)_1, (Serializable)_2);
        }
        return new Tuple2<T1, T2>(_1, _2);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 _1, T2 _2, T3 _3) {
        if (forAll(newList(_1, _2, _3), isSerializable)) {
            return (Tuple3<T1, T2, T3>) new Tuple3.SerializableTuple<Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3);
        }
        return new Tuple3<T1, T2, T3>(_1, _2, _3);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 _1, T2 _2, T3 _3, T4 _4) {
        if (forAll(newList(_1, _2, _3, _4), isSerializable)) {
            return (Tuple4<T1, T2, T3, T4>) new Tuple4.SerializableTuple<Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4);
        }
        return new Tuple4<T1, T2, T3, T4>(_1, _2, _3, _4);
    }

    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5) {
        if (forAll(newList(_1, _2, _3, _4, _5), isSerializable)) {
            return (Tuple5<T1, T2, T3, T4, T5>) new Tuple5.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5);
        }
        return new Tuple5<T1, T2, T3, T4, T5>(_1, _2, _3, _4, _5);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6), isSerializable)) {
            return (Tuple6<T1, T2, T3, T4, T5, T6>) new Tuple6.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6);
        }
        return new Tuple6<T1, T2, T3, T4, T5, T6>(_1, _2, _3, _4, _5, _6);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7), isSerializable)) {
            return (Tuple7<T1, T2, T3, T4, T5, T6, T7>) new Tuple7.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7);
        }
        return new Tuple7<T1, T2, T3, T4, T5, T6, T7>(_1, _2, _3, _4, _5, _6, _7);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8), isSerializable)) {
            return (Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>) new Tuple8.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8);
        }
        return new Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>(_1, _2, _3, _4, _5, _6, _7, _8);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9), isSerializable)) {
            return (Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>) new Tuple9.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9);
        }
        return new Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>(_1, _2, _3, _4, _5, _6, _7, _8, _9);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10), isSerializable)) {
            return (Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>) new Tuple10.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10);
        }
        return new Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11), isSerializable)) {
            return (Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>) new Tuple11.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11);
        }
        return new Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12), isSerializable)) {
            return (Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>) new Tuple12.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11, (Serializable)_12);
        }
        return new Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13), isSerializable)) {
            return (Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>) new Tuple13.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11, (Serializable)_12, (Serializable)_13);
        }
        return new Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14), isSerializable)) {
            return (Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>) new Tuple14.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11, (Serializable)_12, (Serializable)_13, (Serializable)_14);
        }
        return new Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15), isSerializable)) {
            return (Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>) new Tuple15.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11, (Serializable)_12, (Serializable)_13, (Serializable)_14, (Serializable)_15);
        }
        return new Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16), isSerializable)) {
            return (Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>) new Tuple16.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11, (Serializable)_12, (Serializable)_13, (Serializable)_14, (Serializable)_15, (Serializable)_16);
        }
        return new Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> Tuple17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17), isSerializable)) {
            return (Tuple17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>) new Tuple17.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11, (Serializable)_12, (Serializable)_13, (Serializable)_14, (Serializable)_15, (Serializable)_16, (Serializable)_17);
        }
        return new Tuple17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18), isSerializable)) {
            return (Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>) new Tuple18.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11, (Serializable)_12, (Serializable)_13, (Serializable)_14, (Serializable)_15, (Serializable)_16, (Serializable)_17, (Serializable)_18);
        }
        return new Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> Tuple19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19), isSerializable)) {
            return (Tuple19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>) new Tuple19.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11, (Serializable)_12, (Serializable)_13, (Serializable)_14, (Serializable)_15, (Serializable)_16, (Serializable)_17, (Serializable)_18, (Serializable)_19);
        }
        return new Tuple19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> Tuple20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20), isSerializable)) {
            return (Tuple20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>) new Tuple20.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11, (Serializable)_12, (Serializable)_13, (Serializable)_14, (Serializable)_15, (Serializable)_16, (Serializable)_17, (Serializable)_18, (Serializable)_19, (Serializable)_20);
        }
        return new Tuple20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21), isSerializable)) {
            return (Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>) new Tuple21.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11, (Serializable)_12, (Serializable)_13, (Serializable)_14, (Serializable)_15, (Serializable)_16, (Serializable)_17, (Serializable)_18, (Serializable)_19, (Serializable)_20, (Serializable)_21);
        }
        return new Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22) {
        if (forAll(newList(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22), isSerializable)) {
            return (Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>) new Tuple22.SerializableTuple<Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable, Serializable>((Serializable)_1, (Serializable)_2, (Serializable)_3, (Serializable)_4, (Serializable)_5, (Serializable)_6, (Serializable)_7, (Serializable)_8, (Serializable)_9, (Serializable)_10, (Serializable)_11, (Serializable)_12, (Serializable)_13, (Serializable)_14, (Serializable)_15, (Serializable)_16, (Serializable)_17, (Serializable)_18, (Serializable)_19, (Serializable)_20, (Serializable)_21, (Serializable)_22);
        }
        return new Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22);
    }
    
    public static interface _1<T> {
        T get_1();
    }
    
    public static interface _2<T> {
        T get_2();
    }
    
    public static interface _3<T> {
        T get_3();
    }
    
    public static interface _4<T> {
        T get_4();
    }
    
    public static interface _5<T> {
        T get_5();
    }
    
    public static interface _6<T> {
        T get_6();
    }
    
    public static interface _7<T> {
        T get_7();
    }
    
    public static interface _8<T> {
        T get_8();
    }
    
    public static interface _9<T> {
        T get_9();
    }
    
    public static interface _10<T> {
        T get_10();
    }
    
    public static interface _11<T> {
        T get_11();
    }
    
    public static interface _12<T> {
        T get_12();
    }
    
    public static interface _13<T> {
        T get_13();
    }
    
    public static interface _14<T> {
        T get_14();
    }
    
    public static interface _15<T> {
        T get_15();
    }
    
    public static interface _16<T> {
        T get_16();
    }
    
    public static interface _17<T> {
        T get_17();
    }
    
    public static interface _18<T> {
        T get_18();
    }
    
    public static interface _19<T> {
        T get_19();
    }
    
    public static interface _20<T> {
        T get_20();
    }
    
    public static interface _21<T> {
        T get_21();
    }
    
    public static interface _22<T> {
        T get_22();
    }

    public abstract Object[] toArray();
    
    @Override
    public String toString() {
        return "(" + mkString(",", map(toArray(), Transformers.toString)) + ")";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (Object o: toArray()) {
            result = prime * result + ((o == null) ? 0 : o.hashCode());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        for (Map.Entry<Object,Object> o: zip(toArray(), ((Tuple)obj).toArray())) {
            if (o.getKey() == null) {
                if (o.getValue() != null)
                    return false;
            } else if (!o.getKey().equals(o.getValue()))
                return false;
        }
        return true;
    }
    
    void doWriteObject(ObjectOutputStream out) throws IOException  {
        for (Object o: toArray()) {
            out.writeObject(o);
        }
    }

    void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        try {
            for (int i: range(1, toArray().length)) {
                Field f = getClass().getField("t" + i);
                f.setAccessible(true);
                f.set(this, in.readObject());
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
