package fi.solita.utils.functional.lens;

import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMutableLinkedMap;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.FunctionalA.filter;
import static fi.solita.utils.functional.FunctionalA.zip;
import static fi.solita.utils.functional.Option.None;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
import fi.solita.utils.functional.Apply41;
import fi.solita.utils.functional.Apply42;
import fi.solita.utils.functional.Apply43;
import fi.solita.utils.functional.Apply44;
import fi.solita.utils.functional.Apply5;
import fi.solita.utils.functional.Apply6;
import fi.solita.utils.functional.Apply7;
import fi.solita.utils.functional.Apply8;
import fi.solita.utils.functional.Apply9;
import fi.solita.utils.functional.ApplyBi;
import fi.solita.utils.functional.ApplyZero;
import fi.solita.utils.functional.Collections;
import fi.solita.utils.functional.Option;
import fi.solita.utils.functional.Pair;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Transformers;
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
import fi.solita.utils.functional.Tuple41;
import fi.solita.utils.functional.Tuple42;
import fi.solita.utils.functional.Tuple43;
import fi.solita.utils.functional.Tuple44;
import fi.solita.utils.functional.Tuple5;
import fi.solita.utils.functional.Tuple6;
import fi.solita.utils.functional.Tuple7;
import fi.solita.utils.functional.Tuple8;
import fi.solita.utils.functional.Tuple9;

/**
 * A thing able to build an object given a constructor and a correspondingly ordered list of members.
 */
public final class Builder<T> {
    public static final class MapType<K> implements Type {
        private final Set<K> keys;

        public MapType(Set<K> keys) {
            this.keys = keys;
        }

        @Override
        public String getTypeName() {
            return "Map<'" + mkString("'|'", map(Transformers.toString, keys)) + "',?>";
        }
        
        @Override
        public String toString() {
            return getTypeName();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof MapType && this.keys.equals(((MapType<?>)obj).keys);
        }
    }

    public static final class IncompleteException extends RuntimeException {
        public IncompleteException(Apply<?, ?> member) {
            super("Missing value for member " + member);
        }
    }

    private final LinkedHashSet<? extends Apply<? super T,? extends Object>> members;
    private final List<Pair<? extends Apply<? super T, ? extends Object>, ? extends Object>> values;
    private final Apply<Object[], T> constructor;
    private Type resultTypeCache;

    @SuppressWarnings("unchecked")
    private Builder(Iterable<Pair<? extends Apply<? super T,? extends Object>,? extends Object>> values, LinkedHashSet<? extends Apply<? super T, ? extends Object>> members, Apply<? extends Object[], T> constructor) {
        this.members = members;
        this.values = newList(values);
        this.constructor = (Apply<Object[], T>) constructor;
    }
    
    @SuppressWarnings("unchecked")
    private static <T> Builder<T> newBuilder(Collection<? extends Apply<? super T, ? extends Object>> members, Apply<?, T> constructor) {
        return new Builder<T>(Collections.<Pair<? extends Apply<? super T, ? extends Object>,? extends Object>>emptyList(), new LinkedHashSet<Apply<? super T, ? extends Object>>(members), (Apply<? extends Object[], T>) constructor);
    }
    
    private static <T> Builder<T> newBuilder(final ApplyZero<T> constructor) {
        return new Builder<T>(Collections.<Pair<? extends Apply<? super T, ? extends Object>,? extends Object>>emptyList(), new LinkedHashSet<Apply<? super T, ? extends Object>>(), new Apply<Object[], T>() {
            public T apply(Object[] t) {
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
    public final Type resultType() {
        if (resultTypeCache == null) {
            resultTypeCache = buildAllowIncomplete().getClass();
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
        T ret = constructor.apply(newArray(Object.class, map(new Transformer<Apply<? super T,? extends Object>,Object>() {
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
        }, members)));
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
        
        return newBuilder(members, new Apply<Object[], T>() {
            @Override
            public T apply(Object[] t) {
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
                for (Pair<PropertyDescriptor, Object> descriptor: zip(descriptors, t)) {
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
    public static <K,V> Builder<Map<K,V>> ofMap(final Set<K> keys, List<? extends Apply<Map<K,V>, ? extends Object>> members) {
        Apply<?, Map<K,V>> constructor = new Apply<Object[], Map<K,V>>() {
            @Override
            public Map<K, V> apply(Object[] t) {
                Map<K, V> ret = newMutableLinkedMap();
                for (Pair<K,Object> p: zip(keys, t)) {
                    ret.put(p.getKey(), (V)p.getValue());
                }
                return ret;
            }
        };
        Builder<Map<K, V>> ret = new Builder<Map<K,V>>(Collections.<Pair<? extends Apply<? super Map<K,V>, ?>,? extends Object>>emptyList(),
                                     new LinkedHashSet<Apply<? super Map<K,V>, ?>>(members),
                                     (Apply<? extends Object[], Map<K,V>>) constructor);
        ret.resultTypeCache = new MapType<K>(keys);
        return ret;
    }
    
    @SuppressWarnings("unchecked")
    public static <T,TUP extends Tuple> Builder<T> ofUnsafe(TUP members, final Apply<TUP, T> constructor) {
        return newBuilder((List<? extends Apply<? super T, ?>>)(Object)newList(members.toArray()), new Apply<Object[], T>() {
            @Override
            public T apply(Object[] t) {
                return constructor.apply((TUP)Tuple.of(t));
            }
        });
    }
    
    public static <T> Builder<T> of(Tuple0 members, ApplyZero<T> constructor) {
        return newBuilder(constructor);
    }
    
    public static <T,F1> Builder<T> of(Tuple1<? extends Apply<? super T,F1>> members, final Apply<? super F1,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0]);
            }});
    }
    
    public static <T,F1,F2> Builder<T> of(
            Tuple2<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>> members, final ApplyBi<? super F1,? super F2,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1]);
            }});
    }
    
    public static <T,F1,F2,F3> Builder<T> of(
            Tuple3<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>> members, final Apply3<? super F1,? super F2,? super F3,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2]);
            }});
    }
    
    public static <T,F1,F2,F3,F4> Builder<T> of(
            Tuple4<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>> members, final Apply4<? super F1,? super F2,? super F3,? super F4,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3]);
            }});
    }
    
    public static <T,F1,F2,F3,F4,F5> Builder<T> of(
            Tuple5<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>> members, final Apply5<? super F1,? super F2,? super F3,? super F4,? super F5,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4]);
            }});
    }
    
    public static <T,F1,F2,F3,F4,F5,F6> Builder<T> of(
            Tuple6<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>> members, final Apply6<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5]);
            }});
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7> Builder<T> of(
            Tuple7<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>,
                   ? extends Apply<? super T,F7>> members, final Apply7<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6]);
            }});
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8> Builder<T> of(
            Tuple8<? extends Apply<? super T,F1>,
                   ? extends Apply<? super T,F2>,
                   ? extends Apply<? super T,F3>,
                   ? extends Apply<? super T,F4>,
                   ? extends Apply<? super T,F5>,
                   ? extends Apply<? super T,F6>,
                   ? extends Apply<? super T,F7>,
                   ? extends Apply<? super T,F8>> members, final Apply8<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7]);
            }});
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
                   ? extends Apply<? super T,F9>> members, final Apply9<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8]);
            }});
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
                    ? extends Apply<? super T,F10>> members, final Apply10<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9]);
            }});
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
                    ? extends Apply<? super T,F11>> members, final Apply11<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10]);
            }});
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
                    ? extends Apply<? super T,F12>> members, final Apply12<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11]);
            }});
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
                    ? extends Apply<? super T,F13>> members, final Apply13<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12]);
            }});
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
                    ? extends Apply<? super T,F14>> members, final Apply14<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13]);
            }});
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
                    ? extends Apply<? super T,F15>> members, final Apply15<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14]);
            }});
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
                    ? extends Apply<? super T,F16>> members, final Apply16<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15]);
            }});
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
                    ? extends Apply<? super T,F17>> members, final Apply17<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16]);
            }});
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
                    ? extends Apply<? super T,F18>> members, final Apply18<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17]);
            }});
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
                    ? extends Apply<? super T,F19>> members, final Apply19<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18]);
            }});
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
                    ? extends Apply<? super T,F20>> members, final Apply20<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19]);
            }});
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
                    ? extends Apply<? super T,F21>> members, final Apply21<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20]);
            }});
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
                    ? extends Apply<? super T,F22>> members, final Apply22<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21]);
            }});
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
                    ? extends Apply<? super T,F23>> members, final Apply23<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22]);
            }});
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
                    ? extends Apply<? super T,F24>> members, final Apply24<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23]);
            }});
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
                    ? extends Apply<? super T,F25>> members, final Apply25<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24]);
            }});
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
                    ? extends Apply<? super T,F26>> members, final Apply26<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25]);
            }});
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
                    ? extends Apply<? super T,F27>> members, final Apply27<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26]);
            }});
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
                    ? extends Apply<? super T,F28>> members, final Apply28<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27]);
            }});
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
                    ? extends Apply<? super T,F29>> members, final Apply29<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28]);
            }});
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
                    ? extends Apply<? super T,F30>> members, final Apply30<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29]);
            }});
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
                    ? extends Apply<? super T,F31>> members, final Apply31<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30]);
            }});
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
                    ? extends Apply<? super T,F32>> members, final Apply32<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31]);
            }});
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
                    ? extends Apply<? super T,F33>> members, final Apply33<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32]);
            }});
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
                    ? extends Apply<? super T,F34>> members, final Apply34<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32], (F34)t[33]);
            }});
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
                    ? extends Apply<? super T,F35>> members, final Apply35<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32], (F34)t[33], (F35)t[34]);
            }});
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
                    ? extends Apply<? super T,F36>> members, final Apply36<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32], (F34)t[33], (F35)t[34], (F36)t[35]);
            }});
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
                    ? extends Apply<? super T,F37>> members, final Apply37<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32], (F34)t[33], (F35)t[34], (F36)t[35], (F37)t[36]);
            }});
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
                    ? extends Apply<? super T,F38>> members, final Apply38<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,? super F38,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32], (F34)t[33], (F35)t[34], (F36)t[35], (F37)t[36], (F38)t[37]);
            }});
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
                    ? extends Apply<? super T,F39>> members, final Apply39<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,? super F38,? super F39,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32], (F34)t[33], (F35)t[34], (F36)t[35], (F37)t[36], (F38)t[37], (F39)t[38]);
            }});
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
                    ? extends Apply<? super T,F40>> members, final Apply40<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,? super F38,? super F39,? super F40,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32], (F34)t[33], (F35)t[34], (F36)t[35], (F37)t[36], (F38)t[37], (F39)t[38], (F40)t[39]);
            }});
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33,F34,F35,F36,F37,F38,F39,F40,F41> Builder<T> of(
            Tuple41<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F40>,
                    ? extends Apply<? super T,F41>> members, final Apply41<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,? super F38,? super F39,? super F40,? super F41,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32], (F34)t[33], (F35)t[34], (F36)t[35], (F37)t[36], (F38)t[37], (F39)t[38], (F40)t[39], (F41)t[40]);
            }});
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33,F34,F35,F36,F37,F38,F39,F40,F41,F42> Builder<T> of(
            Tuple42<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F40>,
                    ? extends Apply<? super T,F41>,
                    ? extends Apply<? super T,F42>> members, final Apply42<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,? super F38,? super F39,? super F40,? super F41,? super F42,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32], (F34)t[33], (F35)t[34], (F36)t[35], (F37)t[36], (F38)t[37], (F39)t[38], (F40)t[39], (F41)t[40], (F42)t[41]);
            }});
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33,F34,F35,F36,F37,F38,F39,F40,F41,F42,F43> Builder<T> of(
            Tuple43<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F40>,
                    ? extends Apply<? super T,F41>,
                    ? extends Apply<? super T,F42>,
                    ? extends Apply<? super T,F43>> members, final Apply43<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,? super F38,? super F39,? super F40,? super F41,? super F42,? super F43,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32], (F34)t[33], (F35)t[34], (F36)t[35], (F37)t[36], (F38)t[37], (F39)t[38], (F40)t[39], (F41)t[40], (F42)t[41], (F43)t[42]);
            }});
    }
    
    public static <T,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32,F33,F34,F35,F36,F37,F38,F39,F40,F41,F42,F43,F44> Builder<T> of(
            Tuple44<? extends Apply<? super T,F1>,
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
                    ? extends Apply<? super T,F40>,
                    ? extends Apply<? super T,F41>,
                    ? extends Apply<? super T,F42>,
                    ? extends Apply<? super T,F43>,
                    ? extends Apply<? super T,F44>> members, final Apply44<? super F1,? super F2,? super F3,? super F4,? super F5,? super F6,? super F7,? super F8,? super F9,? super F10,? super F11,? super F12,? super F13,? super F14,? super F15,? super F16,? super F17,? super F18,? super F19,? super F20,? super F21,? super F22,? super F23,? super F24,? super F25,? super F26,? super F27,? super F28,? super F29,? super F30,? super F31,? super F32,? super F33,? super F34,? super F35,? super F36,? super F37,? super F38,? super F39,? super F40,? super F41,? super F42,? super F43,? super F44,T> constructor) {
        return newBuilder(Tuple.asList(members), new Apply<Object[],T>() {
            @SuppressWarnings("unchecked")
            public T apply(Object[] t) {
                return constructor.apply((F1)t[0], (F2)t[1], (F3)t[2], (F4)t[3], (F5)t[4], (F6)t[5], (F7)t[6], (F8)t[7], (F9)t[8], (F10)t[9], (F11)t[10], (F12)t[11], (F13)t[12], (F14)t[13], (F15)t[14], (F16)t[15], (F17)t[16], (F18)t[17], (F19)t[18], (F20)t[19], (F21)t[20], (F22)t[21], (F23)t[22], (F24)t[23], (F25)t[24], (F26)t[25], (F27)t[26], (F28)t[27], (F29)t[28], (F30)t[29], (F31)t[30], (F32)t[31], (F33)t[32], (F34)t[33], (F35)t[34], (F36)t[35], (F37)t[36], (F38)t[37], (F39)t[38], (F40)t[39], (F41)t[40], (F42)t[41], (F43)t[42], (F44)t[43]);
            }});
    }
}
