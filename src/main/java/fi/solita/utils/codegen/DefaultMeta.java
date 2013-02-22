package fi.solita.utils.codegen;

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
import fi.solita.utils.functional.Predicate;
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

public abstract class DefaultMeta {
    
    @SuppressWarnings("serial")
    public static abstract class DefaultMeta_<T, R> extends Function1<T, R> implements Meta_<T, R> {
        protected abstract R $do(T t);

        @Override
        public R apply(T t) {
            return $do(t);
        }
    }
    
    @SuppressWarnings("serial")
    public static abstract class DefaultPredicateMeta_<T, B> extends Predicate<T> implements Meta_<T, Boolean> {
        protected abstract Boolean $do(T t);
        
        @Override
        public boolean accept(T candidate) {
            return $do(candidate);
        }
    }
    
    @SuppressWarnings("serial")
    public static abstract class DefaultMethodMeta_<T, R> implements MethodMeta_<T, R> {
        protected abstract R $do(T t);
        
        @Override
        public R apply(T t) {
            return $do(t);
        }
    }

    @SuppressWarnings("serial")
    public static abstract class DefaultMethodPredicateMeta_<T, B> extends Predicate<T> implements MethodMeta_<T, Boolean> {
        protected abstract Boolean $do(T t);
        
        @Override
        public boolean accept(T candidate) {
            return $do(candidate);
        }
    }

    @SuppressWarnings("serial")
    public static abstract class DefaultConstructorMeta_ {
        public static abstract class F0<R> extends Function0<R> implements ConstructorMeta_<Tuple0,R> {}
        public static abstract class F1<T1,R> extends Function1<T1,R> implements ConstructorMeta_<T1,R> {}
        public static abstract class F2<T1,T2,R> extends Function2<T1,T2,R> implements ConstructorMeta_<Tuple2<T1,T2>,R> {}
        public static abstract class F3<T1,T2,T3,R> extends Function3<T1,T2,T3,R> implements ConstructorMeta_<Tuple3<T1,T2,T3>,R> {}
        public static abstract class F4<T1,T2,T3,T4,R> extends Function4<T1,T2,T3,T4,R> implements ConstructorMeta_<Tuple4<T1,T2,T3,T4>,R> {}
        public static abstract class F5<T1,T2,T3,T4,T5,R> extends Function5<T1,T2,T3,T4,T5,R> implements ConstructorMeta_<Tuple5<T1,T2,T3,T4,T5>,R> {}
        public static abstract class F6<T1,T2,T3,T4,T5,T6,R> extends Function6<T1,T2,T3,T4,T5,T6,R> implements ConstructorMeta_<Tuple6<T1,T2,T3,T4,T5,T6>,R> {}
        public static abstract class F7<T1,T2,T3,T4,T5,T6,T7,R> extends Function7<T1,T2,T3,T4,T5,T6,T7,R> implements ConstructorMeta_<Tuple7<T1,T2,T3,T4,T5,T6,T7>,R> {}
        public static abstract class F8<T1,T2,T3,T4,T5,T6,T7,T8,R> extends Function8<T1,T2,T3,T4,T5,T6,T7,T8,R> implements ConstructorMeta_<Tuple8<T1,T2,T3,T4,T5,T6,T7,T8>,R> {}
        public static abstract class F9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R> extends Function9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R> implements ConstructorMeta_<Tuple9<T1,T2,T3,T4,T5,T6,T7,T8,T9>,R> {}
        public static abstract class F10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R> extends Function10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R> implements ConstructorMeta_<Tuple10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>,R> {}
        public static abstract class F11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,R> extends Function11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,R> implements ConstructorMeta_<Tuple11<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11>,R> {}
        public static abstract class F12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,R> extends Function12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,R> implements ConstructorMeta_<Tuple12<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12>,R> {}
        public static abstract class F13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,R> extends Function13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,R> implements ConstructorMeta_<Tuple13<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13>,R> {}
        public static abstract class F14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,R> extends Function14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,R> implements ConstructorMeta_<Tuple14<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14>,R> {}
        public static abstract class F15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,R> extends Function15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,R> implements ConstructorMeta_<Tuple15<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15>,R> {}
        public static abstract class F16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,R> extends Function16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,R> implements ConstructorMeta_<Tuple16<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16>,R> {}
        public static abstract class F17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,R> extends Function17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,R> implements ConstructorMeta_<Tuple17<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17>,R> {}
        public static abstract class F18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,R> extends Function18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,R> implements ConstructorMeta_<Tuple18<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18>,R> {}
        public static abstract class F19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,R> extends Function19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,R> implements ConstructorMeta_<Tuple19<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19>,R> {}
        public static abstract class F20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,R> extends Function20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,R> implements ConstructorMeta_<Tuple20<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20>,R> {}
        public static abstract class F21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,R> extends Function21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,R> implements ConstructorMeta_<Tuple21<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21>,R> {}
        public static abstract class F22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> extends Function22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> implements ConstructorMeta_<Tuple22<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22>,R> {}
    }
}
