package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.FunctionalImpl.map;
import static fi.solita.utils.functional.Option.None;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

public final class Builder<T> {
    public static final class IncompleteException extends RuntimeException {
        public IncompleteException(Apply<?, ?> member) {
            super("Missing value for member " + member);
        }
    }

    private final Collection<? extends Apply<? super T,? extends Object>> members;
    private final Map<Apply<? super T, ? extends Object>, Object> values;
    private final Apply<Tuple, T> constructor;

    @SuppressWarnings("unchecked")
    private <F1,F2> Builder(Map<Apply<? super T,? extends Object>,Object> values, Collection<? extends Apply<? super T, ? extends Object>> members, Apply<? extends Tuple, T> constructor) {
        this.members = members;
        this.values = values;
        this.constructor = (Apply<Tuple, T>) constructor;
    }
    
    private static <T> Builder<T> newBuilder(Collection<? extends Apply<? super T, ? extends Object>> members, Apply<? extends Tuple, T> constructor) {
        return new Builder<T>(Collections.<Apply<? super T, ? extends Object>,Object>emptyMap(), members, constructor);
    }

    public final Builder<T> init(final T t) {
        Map<Apply<? super T, ? extends Object>, Object> newValues = newMap(map(members, new Transformer<Apply<? super T,? extends Object>, Pair<Apply<? super T,? extends Object>,Object>>() {
            @SuppressWarnings("unchecked")
            @Override
            public Pair<Apply<? super T,? extends Object>,Object> transform(Apply<? super T,? extends Object> source) {
                return (Pair<Apply<? super T,? extends Object>,Object>)(Object)Pair.of(source, source.apply(t));
            }
        }));
        return new Builder<T>(newValues, members, constructor);
    }

    public final <F1> Builder<T> with(final Apply<T,? super F1> member, final F1 newValue) {
        checkMember(member);
        return new Builder<T>(Functional.with(member, newValue, values), members, constructor);
    }
    
    public final Builder<T> without(final Apply<T,? extends Option<?>> member) {
        checkMember(member);
        return new Builder<T>(Functional.with(member, None(), values), members, constructor);
    }

    private void checkMember(final Apply<T, ?> member) {
        if (!members.contains(member)) {
            throw new IllegalArgumentException(member.toString());
        }
    }

    public final T build() throws IncompleteException {
        return constructor.apply(Tuple.of(newArray(Object.class, map(members, new Transformer<Apply<? super T,? extends Object>,Object>() {
            @Override
            public Object transform(Apply<? super T, ? extends Object> member) {
                if (values.containsKey(member)) {
                    return values.get(member);
                } else {
                    try {
                        // argh, if the field happens to be generated by meta-utils, as expected...
                        Method fieldAccessor = member.getClass().getMethod("getMember");
                        Class<?> fieldType = ((Field)fieldAccessor.invoke(member)).getType();
                        if (Option.class.isAssignableFrom(fieldType)) {
                            return None();
                        }
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        // continue
                    } catch (NoSuchMethodException e) {
                        // continue...
                    }
                }
                throw new IncompleteException(member);
            }
        }))));
    }
    
    public static <T,F1> Builder<T> of(Tuple1<? extends Apply<? super T,F1>> members, Apply<F1,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor).tuppled());
    }

    public static <T,F1,F2> Builder<T> of(
            Tuple2<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>> members, Apply<Tuple2<F1,F2>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3> Builder<T> of(
            Tuple3<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>> members, Apply<Tuple3<F1,F2,F3>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4> Builder<T> of(
            Tuple4<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>> members, Apply<Tuple4<F1,F2,F3,F4>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5> Builder<T> of(
            Tuple5<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>> members, Apply<Tuple5<F1,F2,F3,F4,F5>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6> Builder<T> of(
            Tuple6<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>> members, Apply<Tuple6<F1,F2,F3,F4,F5,F6>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7> Builder<T> of(
            Tuple7<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>,
                   ? extends Apply<? super T,F7>> members, Apply<Tuple7<F1,F2,F3,F4,F5,F6,F7>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8> Builder<T> of(
            Tuple8<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>,
                   ? extends Apply<? super T,F7>,
                   ? extends Apply<? super T,F8>> members, Apply<Tuple8<F1,F2,F3,F4,F5,F6,F7,F8>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9> Builder<T> of(
            Tuple9<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>,
                   ? extends Apply<? super T,F7>,
                   ? extends Apply<? super T,F8>,
                   ? extends Apply<? super T,F9>> members, Apply<Tuple9<F1,F2,F3,F4,F5,F6,F7,F8,F9>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10> Builder<T> of(
            Tuple10<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>> members, Apply<Tuple10<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11> Builder<T> of(
            Tuple11<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>> members, Apply<Tuple11<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12> Builder<T> of(
            Tuple12<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>,
                    ? extends Apply<? super T,F12>> members, Apply<Tuple12<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13> Builder<T> of(
            Tuple13<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>,
                    ? extends Apply<? super T,F12>,
                    ? extends Apply<? super T,F13>> members, Apply<Tuple13<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14> Builder<T> of(
            Tuple14<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>,
                    ? extends Apply<? super T,F12>,
                    ? extends Apply<? super T,F13>,
                    ? extends Apply<? super T,F14>> members, Apply<Tuple14<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15> Builder<T> of(
            Tuple15<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>,
                    ? extends Apply<? super T,F12>,
                    ? extends Apply<? super T,F13>,
                    ? extends Apply<? super T,F14>,
                    ? extends Apply<? super T,F15>> members, Apply<Tuple15<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16> Builder<T> of(
            Tuple16<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>,
                    ? extends Apply<? super T,F12>,
                    ? extends Apply<? super T,F13>,
                    ? extends Apply<? super T,F14>,
                    ? extends Apply<? super T,F15>,
                    ? extends Apply<? super T,F16>> members, Apply<Tuple16<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17> Builder<T> of(
            Tuple17<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>,
                    ? extends Apply<? super T,F12>,
                    ? extends Apply<? super T,F13>,
                    ? extends Apply<? super T,F14>,
                    ? extends Apply<? super T,F15>,
                    ? extends Apply<? super T,F16>,
                    ? extends Apply<? super T,F17>> members, Apply<Tuple17<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18> Builder<T> of(
            Tuple18<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>,
                    ? extends Apply<? super T,F12>,
                    ? extends Apply<? super T,F13>,
                    ? extends Apply<? super T,F14>,
                    ? extends Apply<? super T,F15>,
                    ? extends Apply<? super T,F16>,
                    ? extends Apply<? super T,F17>,
                    ? extends Apply<? super T,F18>> members, Apply<Tuple18<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19> Builder<T> of(
            Tuple19<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>,
                    ? extends Apply<? super T,F12>,
                    ? extends Apply<? super T,F13>,
                    ? extends Apply<? super T,F14>,
                    ? extends Apply<? super T,F15>,
                    ? extends Apply<? super T,F16>,
                    ? extends Apply<? super T,F17>,
                    ? extends Apply<? super T,F18>,
                    ? extends Apply<? super T,F19>> members, Apply<Tuple19<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20> Builder<T> of(
            Tuple20<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>,
                    ? extends Apply<? super T,F12>,
                    ? extends Apply<? super T,F13>,
                    ? extends Apply<? super T,F14>,
                    ? extends Apply<? super T,F15>,
                    ? extends Apply<? super T,F16>,
                    ? extends Apply<? super T,F17>,
                    ? extends Apply<? super T,F18>,
                    ? extends Apply<? super T,F19>,
                    ? extends Apply<? super T,F20>> members, Apply<Tuple20<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21> Builder<T> of(
            Tuple21<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>,
                    ? extends Apply<? super T,F12>,
                    ? extends Apply<? super T,F13>,
                    ? extends Apply<? super T,F14>,
                    ? extends Apply<? super T,F15>,
                    ? extends Apply<? super T,F16>,
                    ? extends Apply<? super T,F17>,
                    ? extends Apply<? super T,F18>,
                    ? extends Apply<? super T,F19>,
                    ? extends Apply<? super T,F20>,
                    ? extends Apply<? super T,F21>> members, Apply<Tuple21<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22> Builder<T> of(
            Tuple22<? extends Apply<? super T,F1>,
                    ? extends Apply<? super T,F2>,
                    ? extends Apply<? super T,F3>,
                    ? extends Apply<? super T,F4>,
                    ? extends Apply<? super T,F5>,
                    ? extends Apply<? super T,F6>,
                    ? extends Apply<? super T,F7>,
                    ? extends Apply<? super T,F8>,
                    ? extends Apply<? super T,F9>,
                    ? extends Apply<? super T,F10>,
                    ? extends Apply<? super T,F11>,
                    ? extends Apply<? super T,F12>,
                    ? extends Apply<? super T,F13>,
                    ? extends Apply<? super T,F14>,
                    ? extends Apply<? super T,F15>,
                    ? extends Apply<? super T,F16>,
                    ? extends Apply<? super T,F17>,
                    ? extends Apply<? super T,F18>,
                    ? extends Apply<? super T,F19>,
                    ? extends Apply<? super T,F20>,
                    ? extends Apply<? super T,F21>,
                    ? extends Apply<? super T,F22>> members, Apply<Tuple22<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
}