package fi.solita.utils.functional.lens;

import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Option.None;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fi.solita.utils.functional.*;

/**
 * A thing able to build an object given a constructor and a correspondingly ordered list of members.
 */
public final class Builder<T> {
    public static final class IncompleteException extends RuntimeException {
        public IncompleteException(Apply<?, ?> member) {
            super("Missing value for member " + member);
        }
    }

    private final Set<? extends Apply<? super T,? extends Object>> members;
    private final Iterable<Pair<? extends Apply<? super T, ? extends Object>, ? extends Object>> values;
    private final Apply<Tuple, T> constructor;
    private Class<T> resultTypeCache;

    @SuppressWarnings("unchecked")
    private Builder(Iterable<Pair<? extends Apply<? super T,? extends Object>,? extends Object>> values, Collection<? extends Apply<? super T, ? extends Object>> members, Apply<? extends Tuple, T> constructor) {
        this.members = new LinkedHashSet<Apply<? super T, ? extends Object>>(members);
        this.values = values;
        this.constructor = (Apply<Tuple, T>) constructor;
    }
    
    @SuppressWarnings("unchecked")
    private static <T> Builder<T> newBuilder(Collection<? extends Apply<? super T, ? extends Object>> members, Apply<?, T> constructor) {
        return new Builder<T>(Collections.<Pair<? extends Apply<? super T, ? extends Object>,? extends Object>>emptyList(), members, (Apply<? extends Tuple, T>) constructor);
    }
    
    private static <T> Builder<T> newBuilder(final ApplyZero<T> constructor) {
        return new Builder<T>(Collections.<Pair<? extends Apply<? super T, ? extends Object>,? extends Object>>emptyList(), Collections.<Apply<? super T, ? extends Object>>emptyCollection(), new Apply<Tuple0, T>() {
            public T apply(Tuple0 t) {
                return constructor.get();
            }
        });
    }
    
    public Collection<? extends Apply<? super T, ? extends Object>> getMembers() {
        return members;
    }
    
    /**
     * @return type this builder can build.
     */
    @SuppressWarnings("unchecked")
    public final Class<T> resultType() {
        if (resultTypeCache == null) {
            resultTypeCache = (Class<T>) buildAllowIncomplete().getClass();
        }
        return resultTypeCache;
    }

    /**
     * @return a copy of this builder initialized with data from given object.
     */
    public final Builder<T> init(final T t) {
        List<Pair<? extends Apply<? super T, ? extends Object>, ? extends Object>> newValues = newList(map(new Transformer<Apply<? super T,? extends Object>, Pair<? extends Apply<? super T,? extends Object>,? extends Object>>() {
            @SuppressWarnings("unchecked")
            @Override
            public Pair<? extends Apply<? super T,? extends Object>,? extends Object> transform(Apply<? super T,? extends Object> source) {
                return (Pair<Apply<? super T,? extends Object>,Object>)(Object)Pair.of(source, source.apply(t));
            }
        }, members));
        return new Builder<T>(newValues, members, constructor);
    }

    /**
     * @return a copy of this builder with {@code member} initialized to value {@code newValue}.
     */
    public final <F1> Builder<T> with(Apply<? super T,? super F1> member, F1 newValue) {
        checkMember(member);
        return new Builder<T>(cons(Pair.of(member, newValue), values), members, constructor);
    }
    
    /**
     * @return a copy of this builder with value for {@code member} removed.
     */
    public final Builder<T> without(Apply<T,? extends Option<?>> member) {
        checkMember(member);
        return new Builder<T>(cons(Pair.of(member, None()), values), members, constructor);
    }

    private void checkMember(Apply<? super T, ?> member) {
        if (!members.contains(member)) {
            throw new IllegalArgumentException(member.toString());
        }
    }
    
    /**
     * @return a lens from this builder and {@code getter}.
     */
    public final <U> Lens<T,U> toLens(Apply<? super T, U> getter) {
        return Lens.of(getter, this);
    }

    /**
     * Substitutes <i>null</i> for missing values. Might thus fail if there are primitives as constructor arguments.
     */
    public final T buildAllowIncomplete() {
        return build(true);
    }
    
    public final T build() throws IncompleteException {
        return build(false);
    }
    
    @SuppressWarnings("unchecked")
    private final T build(final boolean allowIncomplete) throws IncompleteException {
        T ret = constructor.apply(Tuple.of(newArray(Object.class, map(new Transformer<Apply<? super T,? extends Object>,Object>() {
            @Override
            public Object transform(Apply<? super T, ? extends Object> member) {
                for (Pair<? extends Apply<? super T, ? extends Object>, ? extends Object> o: values) {
                    if (o._1.equals(member)) {
                        return o._2;
                    }
                }
                if (!allowIncomplete) {
                    // substitutes Options automatically as None if a complete instance is required
                    try {
                        // argh, if the field happens to be generated by meta-utils, as expected...
                        if (member.getClass().getName().equals("fi.solita.utils.meta.MetaMember")) {
                            Method fieldAccessor = member.getClass().getMethod("getMember");
                            Class<?> fieldType = ((Field)fieldAccessor.invoke(member)).getType();
                            if (Option.class.isAssignableFrom(fieldType)) {
                                return None();
                            }
                        }
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        // continue
                    } catch (NoSuchMethodException e) {
                        // continue...
                    }
                }
                if (allowIncomplete) {
                    return null;
                } else {
                    throw new IncompleteException(member);
                }
            }
        }, members))));
        if (resultTypeCache == null) {
            resultTypeCache = (Class<T>) ret.getClass();
        }
        return ret;
    }
    
    public static <T> Builder<T> of(Tuple0 members, ApplyZero<T> constructor) {
        return newBuilder(constructor);
    }
    
    public static <T,F1> Builder<T> of(Tuple1<? extends Apply<? super T,F1>> members, Apply<? extends F1,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor).tuppled());
    }

    public static <T,F1,F2> Builder<T> of(
            Tuple2<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>> members, Apply<? extends Map.Entry<? super F1,? super F2>,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2> Builder<T> of(
            Tuple2<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>> members, final ApplyBi<? super F1,? super F2,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Tuple2<F1,F2>,T>() {
            public T apply(Tuple2<F1,F2> t) {
                return constructor.apply(t._1, t._2);
            }});
    }
    
    public static <T,F1,F2,F3> Builder<T> of(
            Tuple3<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>> members, Function3<? super F1,? super F2,? super F3,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4> Builder<T> of(
            Tuple4<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>> members, Function4<? super F1,? super F2,? super F3,? super F4,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5> Builder<T> of(
            Tuple5<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>> members, Function5<? super F1,? super F2,? super F3,? super F4,? super F5,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6> Builder<T> of(
            Tuple6<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>> members, Function6<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7> Builder<T> of(
            Tuple7<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>,
                   ? extends Apply<? super T,F7>> members, Function7<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,T> constructor) {
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
                   ? extends Apply<? super T,F8>> members, Function8<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,T> constructor) {
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
                   ? extends Apply<? super T,F9>> members, Function9<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,T> constructor) {
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
                    ? extends Apply<? super T,F10>> members, Function10<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,T> constructor) {
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
                    ? extends Apply<? super T,F11>> members, Function11<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,T> constructor) {
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
                    ? extends Apply<? super T,F12>> members, Function12<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,T> constructor) {
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
                    ? extends Apply<? super T,F13>> members, Function13<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,T> constructor) {
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
                    ? extends Apply<? super T,F14>> members, Function14<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,T> constructor) {
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
                    ? extends Apply<? super T,F15>> members, Function15<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,T> constructor) {
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
                    ? extends Apply<? super T,F16>> members, Function16<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,T> constructor) {
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
                    ? extends Apply<? super T,F17>> members, Function17<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,T> constructor) {
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
                    ? extends Apply<? super T,F18>> members, Function18<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,T> constructor) {
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
                    ? extends Apply<? super T,F19>> members, Function19<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,T> constructor) {
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
                    ? extends Apply<? super T,F20>> members, Function20<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,T> constructor) {
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
                    ? extends Apply<? super T,F21>> members, Function21<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,T> constructor) {
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
                    ? extends Apply<? super T,F22>> members, Function22<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23> Builder<T> of(
            Tuple23<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>> members, Function23<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24> Builder<T> of(
            Tuple24<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>> members, Function24<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25> Builder<T> of(
            Tuple25<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>> members, Function25<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26> Builder<T> of(
            Tuple26<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>> members, Function26<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27> Builder<T> of(
            Tuple27<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>,
                    ? extends Apply<? super T,F27>> members, Function27<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28> Builder<T> of(
            Tuple28<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>,
                    ? extends Apply<? super T,F27>,
                    ? extends Apply<? super T,F28>> members, Function28<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29> Builder<T> of(
            Tuple29<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>,
                    ? extends Apply<? super T,F27>,
                    ? extends Apply<? super T,F28>,
                    ? extends Apply<? super T,F29>> members, Function29<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30> Builder<T> of(
            Tuple30<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>,
                    ? extends Apply<? super T,F27>,
                    ? extends Apply<? super T,F28>,
                    ? extends Apply<? super T,F29>,
                    ? extends Apply<? super T,F30>> members, Function30<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31> Builder<T> of(
            Tuple31<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>,
                    ? extends Apply<? super T,F27>,
                    ? extends Apply<? super T,F28>,
                    ? extends Apply<? super T,F29>,
                    ? extends Apply<? super T,F30>,
                    ? extends Apply<? super T,F31>> members, Function31<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32> Builder<T> of(
            Tuple32<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>,
                    ? extends Apply<? super T,F27>,
                    ? extends Apply<? super T,F28>,
                    ? extends Apply<? super T,F29>,
                    ? extends Apply<? super T,F30>,
                    ? extends Apply<? super T,F31>,
                    ? extends Apply<? super T,F32>> members, Function32<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33> Builder<T> of(
            Tuple33<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>,
                    ? extends Apply<? super T,F27>,
                    ? extends Apply<? super T,F28>,
                    ? extends Apply<? super T,F29>,
                    ? extends Apply<? super T,F30>,
                    ? extends Apply<? super T,F31>,
                    ? extends Apply<? super T,F32>,
                    ? extends Apply<? super T,F33>> members, Function33<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33,F34> Builder<T> of(
            Tuple34<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>,
                    ? extends Apply<? super T,F27>,
                    ? extends Apply<? super T,F28>,
                    ? extends Apply<? super T,F29>,
                    ? extends Apply<? super T,F30>,
                    ? extends Apply<? super T,F31>,
                    ? extends Apply<? super T,F32>,
                    ? extends Apply<? super T,F33>,
                    ? extends Apply<? super T,F34>> members, Function34<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33,F34,F35> Builder<T> of(
            Tuple35<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>,
                    ? extends Apply<? super T,F27>,
                    ? extends Apply<? super T,F28>,
                    ? extends Apply<? super T,F29>,
                    ? extends Apply<? super T,F30>,
                    ? extends Apply<? super T,F31>,
                    ? extends Apply<? super T,F32>,
                    ? extends Apply<? super T,F33>,
                    ? extends Apply<? super T,F34>,
                    ? extends Apply<? super T,F35>> members, Function35<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33,F34,F35,F36> Builder<T> of(
            Tuple36<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>,
                    ? extends Apply<? super T,F27>,
                    ? extends Apply<? super T,F28>,
                    ? extends Apply<? super T,F29>,
                    ? extends Apply<? super T,F30>,
                    ? extends Apply<? super T,F31>,
                    ? extends Apply<? super T,F32>,
                    ? extends Apply<? super T,F33>,
                    ? extends Apply<? super T,F34>,
                    ? extends Apply<? super T,F35>,
                    ? extends Apply<? super T,F36>> members, Function36<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }

    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33,F34,F35,F36,F37> Builder<T> of(
            Tuple37<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F22>,
                    ? extends Apply<? super T,F23>,
                    ? extends Apply<? super T,F24>,
                    ? extends Apply<? super T,F25>,
                    ? extends Apply<? super T,F26>,
                    ? extends Apply<? super T,F27>,
                    ? extends Apply<? super T,F28>,
                    ? extends Apply<? super T,F29>,
                    ? extends Apply<? super T,F30>,
                    ? extends Apply<? super T,F31>,
                    ? extends Apply<? super T,F32>,
                    ? extends Apply<? super T,F33>,
                    ? extends Apply<? super T,F34>,
                    ? extends Apply<? super T,F35>,
                    ? extends Apply<? super T,F36>,
                    ? extends Apply<? super T,F37>> members, Function37<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,T> constructor) {
        return newBuilder(Tuple.asList(members), constructor);
    }
}
