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

public abstract class MetaMethods {
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
    
    public static abstract class M0<R> extends Function0<R> implements MetaMethod<Tuple0,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M0(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M1<T1,R> extends Function1<T1,R> implements MetaMethod<T1,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M1(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M2<T1,T2,R> extends Function2<T1,T2,R> implements MetaMethod<Tuple2<T1,T2>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M2(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M3<T1,T2,T3,R> extends Function3<T1,T2,T3,R> implements MetaMethod<Tuple3<T1,T2,T3>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M3(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M4<T1,T2,T3,T4,R> extends Function4<T1,T2,T3,T4,R> implements MetaMethod<Tuple4<T1,T2,T3,T4>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M4(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M5<T1,T2,T3,T4,T5,R> extends Function5<T1,T2,T3,T4,T5,R> implements MetaMethod<Tuple5<T1,T2,T3,T4,T5>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M5(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M6<T1,T2,T3,T4,T5,T6,R> extends Function6<T1,T2,T3,T4,T5,T6,R> implements MetaMethod<Tuple6<T1,T2,T3,T4,T5,T6>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M6(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M7<T1,T2,T3,T4,T5,T6,T7,R> extends Function7<T1,T2,T3,T4,T5,T6,T7,R> implements MetaMethod<Tuple7<T1,T2,T3,T4,T5,T6,T7>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M7(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M8<T1,T2,T3,T4,T5,T6,T7,T8,R> extends Function8<T1,T2,T3,T4,T5,T6,T7,T8,R> implements MetaMethod<Tuple8<T1,T2,T3,T4,T5,T6,T7,T8>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M8(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R> extends Function9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R> implements MetaMethod<Tuple9<T1,T2,T3,T4,T5,T6,T7,T8,T9>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M9(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R> extends Function10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R> implements MetaMethod<Tuple10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M10(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,R> extends Function11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,R> implements MetaMethod<Tuple11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M11(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,R> extends Function12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,R> implements MetaMethod<Tuple12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M12(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,R> extends Function13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,R> implements MetaMethod<Tuple13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M13(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,R> extends Function14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,R> implements MetaMethod<Tuple14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M14(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,R> extends Function15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,R> implements MetaMethod<Tuple15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M15(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,R> extends Function16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,R> implements MetaMethod<Tuple16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M16(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,R> extends Function17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,R> implements MetaMethod<Tuple17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M17(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,R> extends Function18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,R> implements MetaMethod<Tuple18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M18(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,R> extends Function19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,R> implements MetaMethod<Tuple19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M19(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,R> extends Function20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,R> implements MetaMethod<Tuple20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M20(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,R> extends Function21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,R> implements MetaMethod<Tuple21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M21(Class<?> clazz, String name, Class<?>... argClasses) {
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
    public static abstract class M22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> extends Function22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> implements MetaMethod<Tuple22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22>,R> {
        private transient Method $r;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] argClasses;
        public M22(Class<?> clazz, String name, Class<?>... argClasses) {
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