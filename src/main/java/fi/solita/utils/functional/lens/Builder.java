package fi.solita.utils.functional.lens;

import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.FunctionalA.filter;
import static fi.solita.utils.functional.FunctionalA.zip;
import static fi.solita.utils.functional.Option.None;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Apply10;
import fi.solita.utils.functional.Apply11;
import fi.solita.utils.functional.Apply12;
import fi.solita.utils.functional.Apply13;
import fi.solita.utils.functional.Apply14;
import fi.solita.utils.functional.Apply15;
import fi.solita.utils.functional.Apply16;
import fi.solita.utils.functional.Apply17;
import fi.solita.utils.functional.Apply18;
import fi.solita.utils.functional.Apply19;
import fi.solita.utils.functional.Apply20;
import fi.solita.utils.functional.Apply21;
import fi.solita.utils.functional.Apply22;
import fi.solita.utils.functional.Apply23;
import fi.solita.utils.functional.Apply24;
import fi.solita.utils.functional.Apply25;
import fi.solita.utils.functional.Apply26;
import fi.solita.utils.functional.Apply27;
import fi.solita.utils.functional.Apply28;
import fi.solita.utils.functional.Apply29;
import fi.solita.utils.functional.Apply3;
import fi.solita.utils.functional.Apply30;
import fi.solita.utils.functional.Apply31;
import fi.solita.utils.functional.Apply32;
import fi.solita.utils.functional.Apply33;
import fi.solita.utils.functional.Apply34;
import fi.solita.utils.functional.Apply35;
import fi.solita.utils.functional.Apply36;
import fi.solita.utils.functional.Apply37;
import fi.solita.utils.functional.Apply38;
import fi.solita.utils.functional.Apply39;
import fi.solita.utils.functional.Apply4;
import fi.solita.utils.functional.Apply40;
import fi.solita.utils.functional.Apply5;
import fi.solita.utils.functional.Apply6;
import fi.solita.utils.functional.Apply7;
import fi.solita.utils.functional.Apply8;
import fi.solita.utils.functional.Apply9;
import fi.solita.utils.functional.ApplyBi;
import fi.solita.utils.functional.ApplyZero;
import fi.solita.utils.functional.Collections;
import fi.solita.utils.functional.Function;
import fi.solita.utils.functional.Option;
import fi.solita.utils.functional.Pair;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Tuple;
import fi.solita.utils.functional.Tuple0;
import fi.solita.utils.functional.Tuple1;
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
import fi.solita.utils.functional.Tuple23;
import fi.solita.utils.functional.Tuple24;
import fi.solita.utils.functional.Tuple25;
import fi.solita.utils.functional.Tuple26;
import fi.solita.utils.functional.Tuple27;
import fi.solita.utils.functional.Tuple28;
import fi.solita.utils.functional.Tuple29;
import fi.solita.utils.functional.Tuple3;
import fi.solita.utils.functional.Tuple30;
import fi.solita.utils.functional.Tuple31;
import fi.solita.utils.functional.Tuple32;
import fi.solita.utils.functional.Tuple33;
import fi.solita.utils.functional.Tuple34;
import fi.solita.utils.functional.Tuple35;
import fi.solita.utils.functional.Tuple36;
import fi.solita.utils.functional.Tuple37;
import fi.solita.utils.functional.Tuple38;
import fi.solita.utils.functional.Tuple39;
import fi.solita.utils.functional.Tuple4;
import fi.solita.utils.functional.Tuple40;
import fi.solita.utils.functional.Tuple5;
import fi.solita.utils.functional.Tuple6;
import fi.solita.utils.functional.Tuple7;
import fi.solita.utils.functional.Tuple8;
import fi.solita.utils.functional.Tuple9;

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
    
    public static final List<PropertyDescriptor> readableWritableBeanDescriptors(Class<?> beanClass) {
        try {
            return newList(filter(new Predicate<PropertyDescriptor>() {
                @Override
                public boolean accept(PropertyDescriptor candidate) {
                    return candidate.getReadMethod() != null && candidate.getWriteMethod() != null;
                }
            }, Introspector.getBeanInfo(beanClass).getPropertyDescriptors()));
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> Builder<T> of(final Class<T> beanClass, final Apply<PropertyDescriptor,Apply<T, ?>> memberProvider) {
        final List<PropertyDescriptor> descriptors = readableWritableBeanDescriptors(beanClass);
        
        List<? extends Apply<? super T, ?>> members = newList(map(memberProvider, descriptors));
        
        return newBuilder(members, new Apply<Tuple, T>() {
            @Override
            public T apply(Tuple t) {
                T ret;
                try {
                    ret = beanClass.getConstructor().newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (SecurityException e) {
                    throw new RuntimeException(e);
                }
                for (Pair<PropertyDescriptor, Object> descriptor: zip(descriptors, t.toArray())) {
                    try {
                        descriptor.left().getWriteMethod().invoke(ret, descriptor.right());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
                return ret;
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    public static <T,TUP extends Tuple> Builder<T> ofUnsafe(TUP members, Apply<TUP, T> constructor) {
        return newBuilder((List<? extends Apply<? super T, ?>>)(Object)newList(members.toArray()), constructor);
    }
    
    public static <T> Builder<T> of(Tuple0 members, ApplyZero<T> constructor) {
        return newBuilder(constructor);
    }
    
    public static <T,F1> Builder<T> of(Tuple1<? extends Apply<? super T,F1>> members, Apply<? extends F1,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor).tuppled());
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
                   ? extends Apply<? super T,F3>> members, Apply3<? super F1,? super F2,? super F3,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
    }
    
    public static <T,F1,F2,F3,F4> Builder<T> of(
            Tuple4<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>> members, Apply4<? super F1,? super F2,? super F3,? super F4,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
    }
    
    public static <T,F1,F2,F3,F4,F5> Builder<T> of(
            Tuple5<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>> members, Apply5<? super F1,? super F2,? super F3,? super F4,? super F5,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
    }
    
    public static <T,F1,F2,F3,F4,F5,F6> Builder<T> of(
            Tuple6<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>> members, Apply6<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7> Builder<T> of(
            Tuple7<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>,
                   ? extends Apply<? super T,F7>> members, Apply7<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8> Builder<T> of(
            Tuple8<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>,
                   ? extends Apply<? super T,F7>,
                   ? extends Apply<? super T,F8>> members, Apply8<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                   ? extends Apply<? super T,F9>> members, Apply9<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F10>> members, Apply10<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F11>> members, Apply11<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F12>> members, Apply12<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F13>> members, Apply13<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F14>> members, Apply14<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F15>> members, Apply15<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F16>> members, Apply16<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F17>> members, Apply17<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F18>> members, Apply18<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F19>> members, Apply19<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F20>> members, Apply20<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F21>> members, Apply21<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F22>> members, Apply22<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F23>> members, Apply23<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F24>> members, Apply24<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F25>> members, Apply25<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F26>> members, Apply26<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F27>> members, Apply27<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F28>> members, Apply28<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F29>> members, Apply29<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F30>> members, Apply30<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F31>> members, Apply31<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F32>> members, Apply32<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F33>> members, Apply33<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F34>> members, Apply34<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F35>> members, Apply35<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F36>> members, Apply36<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
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
                    ? extends Apply<? super T,F37>> members, Apply37<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33,F34,F35,F36,F37,F38> Builder<T> of(
            Tuple38<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F37>,
                    ? extends Apply<? super T,F38>> members, Apply38<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,? super F38,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33,F34,F35,F36,F37,F38,F39> Builder<T> of(
            Tuple39<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F37>,
                    ? extends Apply<? super T,F38>,
                    ? extends Apply<? super T,F39>> members, Apply39<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,? super F38,? super F39,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33,F34,F35,F36,F37,F38,F39,F40> Builder<T> of(
            Tuple40<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F37>,
                    ? extends Apply<? super T,F38>,
                    ? extends Apply<? super T,F39>,
                    ? extends Apply<? super T,F40>> members, Apply40<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,? super F38,? super F39,? super F40,T> constructor) {
        return newBuilder(Tuple.asList(members), Function.of(constructor));
    }
}
