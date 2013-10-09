package fi.solita.utils.codegen;

import java.lang.reflect.Method;

import fi.solita.utils.codegen.generators.Content;
import fi.solita.utils.functional.Function0;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function10;
import fi.solita.utils.functional.Function11;
import fi.solita.utils.functional.Function12;
import fi.solita.utils.functional.Function13;
import fi.solita.utils.functional.Function14;
import fi.solita.utils.functional.Function15;
import fi.solita.utils.functional.Function16;
import fi.solita.utils.functional.Function17;
import fi.solita.utils.functional.Function18;
import fi.solita.utils.functional.Function19;
import fi.solita.utils.functional.Function2;
import fi.solita.utils.functional.Function20;
import fi.solita.utils.functional.Function21;
import fi.solita.utils.functional.Function22;
import fi.solita.utils.functional.Function3;
import fi.solita.utils.functional.Function4;
import fi.solita.utils.functional.Function5;
import fi.solita.utils.functional.Function6;
import fi.solita.utils.functional.Function7;
import fi.solita.utils.functional.Function8;
import fi.solita.utils.functional.Function9;
import fi.solita.utils.functional.Tuple0;
import fi.solita.utils.functional.Tuple10;
import fi.solita.utils.functional.Tuple11;
import fi.solita.utils.functional.Tuple12;
import fi.solita.utils.functional.Tuple13;
import fi.solita.utils.functional.Tuple14;
import fi.solita.utils.functional.Tuple15;
import fi.solita.utils.functional.Tuple16;
import fi.solita.utils.functional.Tuple17;
import fi.solita.utils.functional.Tuple18;
import fi.solita.utils.functional.Tuple19;
import fi.solita.utils.functional.Tuple2;
import fi.solita.utils.functional.Tuple20;
import fi.solita.utils.functional.Tuple21;
import fi.solita.utils.functional.Tuple22;
import fi.solita.utils.functional.Tuple3;
import fi.solita.utils.functional.Tuple4;
import fi.solita.utils.functional.Tuple5;
import fi.solita.utils.functional.Tuple6;
import fi.solita.utils.functional.Tuple7;
import fi.solita.utils.functional.Tuple8;
import fi.solita.utils.functional.Tuple9;

public abstract class MetaMethod {
    static final Method doGetMember(Class<?> clazz, String name, Class<?>... argClasses) {
        try {
            Method method = (Method)(Object)clazz.getDeclaredMethod(name, argClasses);
            method.setAccessible(true);
            return method;
        } catch (Throwable $e) {
            throw Content.wrap($e);
        }
    }
    static final String doToString(Class<?> clazz, String name) {
        return clazz.getSimpleName() + "." + name;
    }
    
    public static abstract class F0<R> extends Function0<R> implements MethodMeta_<Tuple0,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F0(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F1<T1,R> extends Function1<T1,R> implements MethodMeta_<T1,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F1(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F2<T1,T2,R> extends Function2<T1,T2,R> implements MethodMeta_<Tuple2<T1,T2>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F2(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F3<T1,T2,T3,R> extends Function3<T1,T2,T3,R> implements MethodMeta_<Tuple3<T1,T2,T3>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F3(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F4<T1,T2,T3,T4,R> extends Function4<T1,T2,T3,T4,R> implements MethodMeta_<Tuple4<T1,T2,T3,T4>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F4(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F5<T1,T2,T3,T4,T5,R> extends Function5<T1,T2,T3,T4,T5,R> implements MethodMeta_<Tuple5<T1,T2,T3,T4,T5>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F5(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F6<T1,T2,T3,T4,T5,T6,R> extends Function6<T1,T2,T3,T4,T5,T6,R> implements MethodMeta_<Tuple6<T1,T2,T3,T4,T5,T6>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F6(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F7<T1,T2,T3,T4,T5,T6,T7,R> extends Function7<T1,T2,T3,T4,T5,T6,T7,R> implements MethodMeta_<Tuple7<T1,T2,T3,T4,T5,T6,T7>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F7(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F8<T1,T2,T3,T4,T5,T6,T7,T8,R> extends Function8<T1,T2,T3,T4,T5,T6,T7,T8,R> implements MethodMeta_<Tuple8<T1,T2,T3,T4,T5,T6,T7,T8>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F8(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R> extends Function9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R> implements MethodMeta_<Tuple9<T1,T2,T3,T4,T5,T6,T7,T8,T9>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F9(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R> extends Function10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R> implements MethodMeta_<Tuple10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F10(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,R> extends Function11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,R> implements MethodMeta_<Tuple11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F11(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,R> extends Function12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,R> implements MethodMeta_<Tuple12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F12(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,R> extends Function13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,R> implements MethodMeta_<Tuple13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F13(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,R> extends Function14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,R> implements MethodMeta_<Tuple14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F14(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,R> extends Function15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,R> implements MethodMeta_<Tuple15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F15(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,R> extends Function16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,R> implements MethodMeta_<Tuple16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F16(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,R> extends Function17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,R> implements MethodMeta_<Tuple17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F17(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,R> extends Function18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,R> implements MethodMeta_<Tuple18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F18(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,R> extends Function19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,R> implements MethodMeta_<Tuple19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F19(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,R> extends Function20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,R> implements MethodMeta_<Tuple20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F20(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,R> extends Function21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,R> implements MethodMeta_<Tuple21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F21(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
    public static abstract class F22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> extends Function22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> implements MethodMeta_<Tuple22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public F22(Class<?> clazz, String name, Class<?>... argClasses) {
            this.clazz = clazz;
            this.name = name;
            this.argClasses = argClasses;
        }
        @Override
        public final Method getMember() {
            if ($r == null) {
                $r = doGetMember(clazz, name, argClasses);
            }
            return $r;
        }
        public final String getName() {
            return name;
        }
        @Override
        public final String toString() {
            return doToString(clazz, name);
        }
    }
}