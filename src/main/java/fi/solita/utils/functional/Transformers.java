package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.not;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

public abstract class Transformers {
    
    public static final Function1<CharSequence, String> append(final CharSequence suffix) { 
        return new Function1<CharSequence, String>() {
            @Override
            public final String apply(CharSequence source) {
                return source == null ? null : source.toString() + suffix;
            }
        };
    }
    
    public static final <A,B> Function1<A, Pair<A,B>> append(final B suffix) { 
        return new Function1<A, Pair<A,B>>() {
            @Override
            public final Pair<A,B> apply(A source) {
                return source == null ? null : Pair.of(source, suffix);
            }
        };
    }
    
    public static final <A,B,C> Function1<Map.Entry<A,B>, Tuple3<A,B,C>> appendPair(final C suffix) { 
        return new Function1<Map.Entry<A,B>, Tuple3<A,B,C>>() {
            @Override
            public final Tuple3<A,B,C> apply(Map.Entry<A,B> source) {
                return source == null ? null : Tuple.of(source.getKey(), source.getValue(), suffix);
            }
        };
    }
    
    public static final Function1<CharSequence, String> prepend(final CharSequence prefix) { 
        return new Function1<CharSequence, String>() {
            @Override
            public final String apply(CharSequence source) {
                return prefix + source.toString();
            }
        };
    }
    
    public static final <A,B> Function1<B, Pair<A,B>> prepend(final A prefix) { 
        return new Function1<B, Pair<A,B>>() {
            @Override
            public final Pair<A,B> apply(B source) {
                return source == null ? null : Pair.of(prefix, source);
            }
        };
    }
    
    public static final <A,B,C> Function1<Map.Entry<B,C>, Tuple3<A,B,C>> prependPair(final A prefix) { 
        return new Function1<Map.Entry<B,C>, Tuple3<A,B,C>>() {
            @Override
            public final Tuple3<A,B,C> apply(Map.Entry<B,C> source) {
                return source == null ? null : Tuple.of(prefix, source.getKey(), source.getValue());
            }
        };
    }
    
    public static final Function1<String, String> replaceAll(final Pattern pattern, final String replacement) { 
        return new Function1<String, String>() {
            @Override
            public final String apply(String source) {
                return source == null ? null : pattern.matcher(source).replaceAll(replacement);
            }
        };
    }
    
    public static final Function1<String, String> removeWhitespace = new Function1<String, String>() {
        private final Predicate<Character> whitespace = new Predicate<Character>() {
            @Override
            public final boolean accept(Character candidate) {
                return Character.isWhitespace(candidate);
            }
        };
        
        @Override
        public final String apply(String source) {
            if (source == null) {
                return null;
            }
            return Functional.mkString("", Functional.map(toString, filter(not(whitespace), it(source))));
        }
    };
    
    public static final Function1<Map.Entry<?, ?>, String> join(final String delim) {
        return new Function1<Map.Entry<?, ?>, String>() {
            @Override
            public final String apply(Map.Entry<?, ?> source) {
                return source.getKey() + delim + source.getValue();
            }
        };
    }
    
    public static final Function1<Tuple, String> mkString(final String delim, final Apply<Object,CharSequence> toString) {
          return new Function1<Tuple, String>() {
              @Override
              public final String apply(Tuple source) {
                  return Functional.mkString(delim, Functional.map(toString, source.toArray()));
              }
          };
      }

    public static final Function1<String, String> prefix(final int chars) {
        if (chars <= 0) {
            throw new IllegalArgumentException("chars must be >= 1");
        }
        return new Function1<String, String>() {
            @Override
            public final String apply(String source) {
                return source.substring(0, Math.min(chars, source.length()));
            }
        };
    };
    
    private static final Function1<Map.Entry<?, ?>,Map.Entry<?, ?>> flip = new Function1<Map.Entry<?, ?>,Map.Entry<?, ?>>() {
        @Override
        public final Map.Entry<?, ?> apply(Map.Entry<?, ?> source) {
            return Tuple.of(source.getValue(), source.getKey());
        }
    };
    @SuppressWarnings("unchecked")
    public static final <K,V> Function1<Map.Entry<K, V>,Map.Entry<V, K>> flip() {
        return (Function1<Map.Entry<K, V>,Map.Entry<V, K>>)(Object)flip;
    };
    
    public static final Function1<Integer, Integer> mod(final Integer mod) {
        return new Function1<Integer, Integer>() {
            @Override
            public final Integer apply(Integer source) {
                  return source % mod;
            }
        };
    }
    
    
    
    public static final Function1<Long, Long> negate = new Function1<Long,Long>() {
        @Override
        public final Long apply(Long source) {
            return -1*source;
        }
    };
    
    public static final Function1<Integer, Integer> negateInt = new Function1<Integer,Integer>() {
        @Override
        public final Integer apply(Integer source) {
            return -1*source;
        }
    };
    
    public static final Function1<Short, Short> negateShort = new Function1<Short,Short>() {
        @Override
        public final Short apply(Short source) {
            return (short)(-1*source);
        }
    };
    
    
    
    public static final Function1<Short, Long> short2long = new Function1<Short,Long>() {
        @Override
        public final Long apply(Short source) {
            return source.longValue();
        }
    };
    
    public static final Function1<Integer, Long> int2long = new Function1<Integer,Long>() {
        @Override
        public final Long apply(Integer source) {
            return source.longValue();
        }
    };
    
    public static final Function1<Object,String> toString = new Function1<Object,String>() {
        @Override
        public final String apply(Object source) {
            return source == null ? null : source.toString();
        }
    };
    
    private static final Function1<Map.Entry<?,?>,?> left = new Function1<Map.Entry<?,?>,Object>() {
        @Override
        public final Object apply(Map.Entry<?,?> source) {
            return source.getKey();
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Map.Entry<? extends T,?>,T> left() {
        return (Function1<Map.Entry<? extends T,?>,T>)(Object)left;
    }
    @SuppressWarnings("unchecked")
    public static final <K,V> Function1<Map.Entry<K,V>,K> key() {
        return (Function1<Map.Entry<K,V>,K>)(Object)left;
    }
    
    private static final Function1<Map.Entry<?,?>,?> right = new Function1<Map.Entry<?,?>,Object>() {
        @Override
        public final Object apply(Map.Entry<?,?> source) {
            return source.getValue();
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Map.Entry<?,? extends T>,T> right() {
        return (Function1<Map.Entry<?,? extends T>,T>)(Object)right;
    }
    @SuppressWarnings("unchecked")
    public static final <K,V> Function1<Map.Entry<K,V>,V> value() {
        return (Function1<Map.Entry<K,V>,V>)(Object)right;
    }

    public static final <K,V,R> Function1<Map.Entry<K,V>,Map.Entry<R,V>> first(final Apply<? super K,? extends R> f) {
        return new Function1<Map.Entry<K,V>,Map.Entry<R,V>>() {
            @Override
            public final Map.Entry<R,V> apply(Map.Entry<K,V> source) {
                return Pair.of(f.apply(source.getKey()), source.getValue());
            }
        };
    }
    
    public static final <K,V,R> Function1<Map.Entry<K,V>,Map.Entry<K,R>> second(final Apply<? super V,? extends R> f) {
        return new Function1<Map.Entry<K,V>,Map.Entry<K,R>>() {
            @Override
            public final Map.Entry<K,R> apply(Map.Entry<K,V> source) {
                return Pair.of(source.getKey(), f.apply(source.getValue()));
            }
        };
    }
    
    private static final Function1<Either<?,?>,Option<?>> eitherLeft = new Function1<Either<?,?>,Option<?>>() {
        @Override
        public final Option<?> apply(Either<?,?> source) {
            return source.left;
        }
    };
    private static final Function1<Either<?,?>,Option<?>> eitherRight = new Function1<Either<?,?>,Option<?>>() {
        @Override
        public final Option<?> apply(Either<?,?> source) {
            return source.right;
        }
    };
    
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Either<T,?>,Option<T>> eitherLeft() {
        return (Function1<Either<T,?>,Option<T>>)(Object)eitherLeft;
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Either<?,T>,Option<T>> eitherRight() {
        return (Function1<Either<?,T>,Option<T>>)(Object)eitherRight;
    }
    
    private static final Function1<Either3<?,?,?>,Option<?>> either3Left = new Function1<Either3<?,?,?>,Option<?>>() {
        @Override
        public final Option<?> apply(Either3<?,?,?> source) {
            return source.left;
        }
    };
    private static final Function1<Either3<?,?,?>,Option<?>> either3Middle = new Function1<Either3<?,?,?>,Option<?>>() {
        @Override
        public final Option<?> apply(Either3<?,?,?> source) {
            return source.middle;
        }
    };
    private static final Function1<Either3<?,?,?>,Option<?>> either3Right = new Function1<Either3<?,?,?>,Option<?>>() {
        @Override
        public final Option<?> apply(Either3<?,?,?> source) {
            return source.right;
        }
    };
    
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Either3<T,?,?>,Option<T>> either3Left() {
        return (Function1<Either3<T,?,?>,Option<T>>)(Object)either3Left;
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Either3<?,T,?>,Option<T>> either3Middle() {
        return (Function1<Either3<?,T,?>,Option<T>>)(Object)either3Middle;
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Either3<?,?,T>,Option<T>> either3Right() {
        return (Function1<Either3<?,?,T>,Option<T>>)(Object)either3Right;
    }
    
    private static final Function1<Map<Object,Object>,Set<Map.Entry<Object,Object>>> mapEntrySet = new Function1<Map<Object,Object>,Set<Map.Entry<Object,Object>>>() {
        @Override
        public final Set<Map.Entry<Object,Object>> apply(Map<Object,Object> source) {
            return source.entrySet();
        }
    };
    @SuppressWarnings("unchecked")
    public static final <K,V> Function1<Map<K,V>,Set<Map.Entry<K,V>>> mapEntrySet() {
        return (Function1<Map<K,V>,Set<Map.Entry<K,V>>>)(Object)mapEntrySet;
    }
    
    private static final Function1<?,Option<?>> some = new Function1<Object,Option<?>>() {
        @Override
        public final Option<?> apply(Object source) {
            return Some(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Function1<T,Option<T>> some() {
        return (Function1<T,Option<T>>)(Object)some;
    }
    
    private static final Function1<Option<?>,?> get = new Function1<Option<?>,Object>() {
        @Override
        public final Object apply(Option<?> source) {
            return source.get();
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Option<T>,T> get() {
        return (Function1<Option<T>,T>)(Object)get;
    }
    
    public static final <T> Function1<Option<T>,T> getOrElse(final T elseValue) {
        return new Function1<Option<T>,T>() {
            @Override
            public final T apply(Option<T> source) {
                return source.getOrElse(elseValue);
            }
        };
    }
    
    public static final Function1<String,Iterable<Character>> it = new Function1<String,Iterable<Character>>() {
        @Override
        public final Iterable<Character> apply(String source) {
            return it(source);
        }
    };
    
    private static final Function1<Iterable<?>, Object> head = new Function1<Iterable<?>,Object>() {
        @Override
        public final Object apply(Iterable<?> source) {
            return Functional.head(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Iterable<T>,T> head() {
        return (Function1<Iterable<T>,T>)(Object)head;
    }
    
    private static final Function1<Iterable<?>, Object> tail = new Function1<Iterable<?>,Object>() {
        @Override
        public final Object apply(Iterable<?> source) {
            return Functional.tail(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Iterable<T>,Iterable<T>> tail() {
        return (Function1<Iterable<T>,Iterable<T>>)(Object)tail;
    }
    
    public static final Function1<String, String> tailStr = new Function1<String,String>() {
        @Override
        public final String apply(String source) {
            return Functional.tail(source);
        }
    };
    
    public static final <T,R> Function1<Iterable<? extends T>,Iterable<R>> map(final Apply<? super T, ? extends R> f) {
        return new Function1<Iterable<? extends T>,Iterable<R>>() {
            @Override
            public final Iterable<R> apply(Iterable<? extends T> source) {
                return Functional.map(f, source);
            }
        };
    }
    
    public static final <T,R> Function1<Option<? extends T>,Option<R>> mapOption(final Apply<? super T, ? extends R> f) {
        return new Function1<Option<? extends T>,Option<R>>() {
            @Override
            public final Option<R> apply(Option<? extends T> source) {
                return source.map(f);
            }
        };
    }
      
    public static final <T,R> Function1<Iterable<? extends T>,Iterable<R>> flatMap(final Apply<? super T, ? extends Iterable<R>> f) {
        return new Function1<Iterable<? extends T>,Iterable<R>>() {
            @Override
            public final Iterable<R> apply(Iterable<? extends T> source) {
                return Functional.flatMap(f, source);
            }
        };
    }
    
    public static final <R,T extends Iterable<R>> Function1<Iterable<? extends T>,Iterable<R>> flatten() {
        return new Function1<Iterable<? extends T>,Iterable<R>>() {
            @Override
            public final Iterable<R> apply(Iterable<? extends T> source) {
                return Functional.flatten(source);
            }
        };
    }
    
    private static final Function1<Object, List<Object>> newList = new Function1<Object,List<Object>>() {
        @Override
        public final List<Object> apply(Object source) {
            return Collections.newList(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Function1<T,List<T>> newList() {
        return (Function1<T,List<T>>)(Object)newList;
    }
    
    private static final Function1<Object, Set<Object>> newSet = new Function1<Object,Set<Object>>() {
        @Override
        public final Set<Object> apply(Object source) {
            return Collections.newSet(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Function1<T,Set<T>> newSet() {
        return (Function1<T,Set<T>>)(Object)newSet;
    }
    
    private static final Function1<Tuple2<Object,Object>, Map<Object,Object>> newMap = new Function1<Tuple2<Object,Object>,Map<Object,Object>>() {
        @Override
        public final Map<Object,Object> apply(Tuple2<Object,Object> source) {
            return Collections.newMap(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <K,V,T extends Tuple2<K,V>> Function1<T,Map<K,V>> newMap() {
        return (Function1<T,Map<K,V>>)(Object)newMap;
    }
   
    public static final Function1<Iterable<?>, Long> size = new Function1<Iterable<?>,Long>() {
        @Override
        public final Long apply(Iterable<?> source) {
            return Functional.size(source);
        }
    };
    
    public static <A,B,T extends Tuple._1<A> & Tuple._2<B>> Function1<T,Tuple2<A,B>> take2() {
        return new Function1<T, Tuple2<A,B>>() {
            @Override
            public Tuple2<A, B> apply(T source) {
                return Tuple.of(source.get_1(), source.get_2());
            }
        };
    }
    
    public static <A,B,C,T extends Tuple._1<A> & Tuple._2<B> & Tuple._3<C>> Function1<T,Tuple3<A,B,C>> take3() {
        return new Function1<T, Tuple3<A,B,C>>() {
            @Override
            public Tuple3<A, B, C> apply(T source) {
                return Tuple.of(source.get_1(), source.get_2(), source.get_3());
            }
        };
    }
    
    public static <A,B> Function2<Iterable<A>,B,Iterable<Pair<A,B>>> zipTo() {
        return new Function2<Iterable<A>, B, Iterable<Pair<A,B>>>() {
            @Override
            public Iterable<Pair<A, B>> apply(Iterable<A> t1, B t2) {
                return Functional.zipTo(t1, t2);
            }
        };
    }
    
    public static <A,B,C> Function2<Iterable<? extends Map.Entry<A, B>>, C, Iterable<Tuple3<A, B, C>>> zipToPair() {
        return new Function2<Iterable<? extends Map.Entry<A,B>>, C, Iterable<Tuple3<A,B,C>>>() {
            @Override
            public Iterable<Tuple3<A, B, C>> apply(Iterable<? extends Entry<A, B>> t1, C t2) {
                return Functional.zipToPair(t1, t2);
            }
        };
    }
    
    private static final Function1<Tuple._1<?>,?> _1 = new Function1<Tuple._1<?>,Object>() {
        @Override
        public final Object apply(Tuple._1<?> source) {
            return source.get_1();
        }
    };
    private static final Function1<Tuple._2<?>,?> _2 = new Function1<Tuple._2<?>,Object>() {
        @Override
        public final Object apply(Tuple._2<?> source) {
            return source.get_2();
        }
    };
    private static final Function1<Tuple._3<?>,?> _3 = new Function1<Tuple._3<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._3<?> source) {
            return source.get_3();
        }
    };
    private static final Function1<Tuple._4<?>,?> _4 = new Function1<Tuple._4<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._4<?> source) {
            return source.get_4();
        }
    };
    private static final Function1<Tuple._5<?>,?> _5 = new Function1<Tuple._5<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._5<?> source) {
            return source.get_5();
        }
    };
    private static final Function1<Tuple._6<?>,?> _6 = new Function1<Tuple._6<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._6<?> source) {
            return source.get_6();
        }
    };
    private static final Function1<Tuple._7<?>,?> _7 = new Function1<Tuple._7<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._7<?> source) {
            return source.get_7();
        }
    };
    private static final Function1<Tuple._8<?>,?> _8 = new Function1<Tuple._8<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._8<?> source) {
            return source.get_8();
        }
    };
    private static final Function1<Tuple._9<?>,?> _9 = new Function1<Tuple._9<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._9<?> source) {
            return source.get_9();
        }
    };
    private static final Function1<Tuple._10<?>,?> _10 = new Function1<Tuple._10<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._10<?> source) {
            return source.get_10();
        }
    };
    private static final Function1<Tuple._11<?>,?> _11 = new Function1<Tuple._11<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._11<?> source) {
            return source.get_11();
        }
    };
    private static final Function1<Tuple._12<?>,?> _12 = new Function1<Tuple._12<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._12<?> source) {
            return source.get_12();
        }
    };
    private static final Function1<Tuple._13<?>,?> _13 = new Function1<Tuple._13<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._13<?> source) {
            return source.get_13();
        }
    };
    private static final Function1<Tuple._14<?>,?> _14 = new Function1<Tuple._14<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._14<?> source) {
            return source.get_14();
        }
    };
    private static final Function1<Tuple._15<?>,?> _15 = new Function1<Tuple._15<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._15<?> source) {
            return source.get_15();
        }
    };
    private static final Function1<Tuple._16<?>,?> _16 = new Function1<Tuple._16<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._16<?> source) {
            return source.get_16();
        }
    };
    private static final Function1<Tuple._17<?>,?> _17 = new Function1<Tuple._17<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._17<?> source) {
            return source.get_17();
        }
    };
    private static final Function1<Tuple._18<?>,?> _18 = new Function1<Tuple._18<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._18<?> source) {
            return source.get_18();
        }
    };
    private static final Function1<Tuple._19<?>,?> _19 = new Function1<Tuple._19<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._19<?> source) {
            return source.get_19();
        }
    };
    private static final Function1<Tuple._20<?>,?> _20 = new Function1<Tuple._20<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._20<?> source) {
            return source.get_20();
        }
    };
    private static final Function1<Tuple._21<?>,?> _21 = new Function1<Tuple._21<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._21<?> source) {
            return source.get_21();
        }
    };
    private static final Function1<Tuple._22<?>,?> _22 = new Function1<Tuple._22<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._22<?> source) {
            return source.get_22();
        }
    };
    private static final Function1<Tuple._23<?>,?> _23 = new Function1<Tuple._23<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._23<?> source) {
            return source.get_23();
        }
    };
    private static final Function1<Tuple._24<?>,?> _24 = new Function1<Tuple._24<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._24<?> source) {
            return source.get_24();
        }
    };
    private static final Function1<Tuple._25<?>,?> _25 = new Function1<Tuple._25<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._25<?> source) {
            return source.get_25();
        }
    };
    private static final Function1<Tuple._26<?>,?> _26 = new Function1<Tuple._26<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._26<?> source) {
            return source.get_26();
        }
    };
    private static final Function1<Tuple._27<?>,?> _27 = new Function1<Tuple._27<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._27<?> source) {
            return source.get_27();
        }
    };
    private static final Function1<Tuple._28<?>,?> _28 = new Function1<Tuple._28<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._28<?> source) {
            return source.get_28();
        }
    };
    private static final Function1<Tuple._29<?>,?> _29 = new Function1<Tuple._29<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._29<?> source) {
            return source.get_29();
        }
    };
    private static final Function1<Tuple._30<?>,?> _30 = new Function1<Tuple._30<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._30<?> source) {
            return source.get_30();
        }
    };
    private static final Function1<Tuple._31<?>,?> _31 = new Function1<Tuple._31<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._31<?> source) {
            return source.get_31();
        }
    };
    private static final Function1<Tuple._32<?>,?> _32 = new Function1<Tuple._32<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._32<?> source) {
            return source.get_32();
        }
    };
    private static final Function1<Tuple._33<?>,?> _33 = new Function1<Tuple._33<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._33<?> source) {
            return source.get_33();
        }
    };
    private static final Function1<Tuple._34<?>,?> _34 = new Function1<Tuple._34<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._34<?> source) {
            return source.get_34();
        }
    };
    private static final Function1<Tuple._35<?>,?> _35 = new Function1<Tuple._35<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._35<?> source) {
            return source.get_35();
        }
    };
    private static final Function1<Tuple._36<?>,?> _36 = new Function1<Tuple._36<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._36<?> source) {
            return source.get_36();
        }
    };
    private static final Function1<Tuple._37<?>,?> _37 = new Function1<Tuple._37<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._37<?> source) {
            return source.get_37();
        }
    };
    private static final Function1<Tuple._38<?>,?> _38 = new Function1<Tuple._38<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._38<?> source) {
            return source.get_38();
        }
    };
    private static final Function1<Tuple._39<?>,?> _39 = new Function1<Tuple._39<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._39<?> source) {
            return source.get_39();
        }
    };
    private static final Function1<Tuple._40<?>,?> _40 = new Function1<Tuple._40<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._40<?> source) {
            return source.get_40();
        }
    };
    private static final Function1<Tuple._41<?>,?> _41 = new Function1<Tuple._41<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._41<?> source) {
            return source.get_41();
        }
    };
    private static final Function1<Tuple._42<?>,?> _42 = new Function1<Tuple._42<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._42<?> source) {
            return source.get_42();
        }
    };
    private static final Function1<Tuple._43<?>,?> _43 = new Function1<Tuple._43<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._43<?> source) {
            return source.get_43();
        }
    };
    private static final Function1<Tuple._44<?>,?> _44 = new Function1<Tuple._44<?>,Object>() {
        @Override
        public final Object apply(fi.solita.utils.functional.Tuple._44<?> source) {
            return source.get_44();
        }
    };
    
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._1<? extends T>,T> _1() {
        return (Function1<Tuple._1<? extends T>,T>)(Object)_1;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._2<? extends T>,T> _2() {
        return (Function1<Tuple._2<? extends T>,T>)(Object)_2;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._3<? extends T>,T> _3() {
        return (Function1<Tuple._3<? extends T>,T>)(Object)_3;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._4<? extends T>,T> _4() {
        return (Function1<Tuple._4<? extends T>,T>)(Object)_4;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._5<? extends T>,T> _5() {
        return (Function1<Tuple._5<? extends T>,T>)(Object)_5;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._6<? extends T>,T> _6() {
        return (Function1<Tuple._6<? extends T>,T>)(Object)_6;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._7<? extends T>,T> _7() {
        return (Function1<Tuple._7<? extends T>,T>)(Object)_7;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._8<? extends T>,T> _8() {
        return (Function1<Tuple._8<? extends T>,T>)(Object)_8;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._9<? extends T>,T> _9() {
        return (Function1<Tuple._9<? extends T>,T>)(Object)_9;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._10<? extends T>,T> _10() {
        return (Function1<Tuple._10<? extends T>,T>)(Object)_10;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._11<? extends T>,T> _11() {
        return (Function1<Tuple._11<? extends T>,T>)(Object)_11;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._12<? extends T>,T> _12() {
        return (Function1<Tuple._12<? extends T>,T>)(Object)_12;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._13<? extends T>,T> _13() {
        return (Function1<Tuple._13<? extends T>,T>)(Object)_13;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._14<? extends T>,T> _14() {
        return (Function1<Tuple._14<? extends T>,T>)(Object)_14;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._15<? extends T>,T> _15() {
        return (Function1<Tuple._15<? extends T>,T>)(Object)_15;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._16<? extends T>,T> _16() {
        return (Function1<Tuple._16<? extends T>,T>)(Object)_16;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._17<? extends T>,T> _17() {
        return (Function1<Tuple._17<? extends T>,T>)(Object)_17;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._18<? extends T>,T> _18() {
        return (Function1<Tuple._18<? extends T>,T>)(Object)_18;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._19<? extends T>,T> _19() {
        return (Function1<Tuple._19<? extends T>,T>)(Object)_19;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._20<? extends T>,T> _20() {
        return (Function1<Tuple._20<? extends T>,T>)(Object)_20;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._21<? extends T>,T> _21() {
        return (Function1<Tuple._21<? extends T>,T>)(Object)_21;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._22<? extends T>,T> _22() {
        return (Function1<Tuple._22<? extends T>,T>)(Object)_22;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._23<? extends T>,T> _23() {
        return (Function1<Tuple._23<? extends T>,T>)(Object)_23;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._24<? extends T>,T> _24() {
        return (Function1<Tuple._24<? extends T>,T>)(Object)_24;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._25<? extends T>,T> _25() {
        return (Function1<Tuple._25<? extends T>,T>)(Object)_25;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._26<? extends T>,T> _26() {
        return (Function1<Tuple._26<? extends T>,T>)(Object)_26;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._27<? extends T>,T> _27() {
        return (Function1<Tuple._27<? extends T>,T>)(Object)_27;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._28<? extends T>,T> _28() {
        return (Function1<Tuple._28<? extends T>,T>)(Object)_28;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._29<? extends T>,T> _29() {
        return (Function1<Tuple._29<? extends T>,T>)(Object)_29;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._30<? extends T>,T> _30() {
        return (Function1<Tuple._30<? extends T>,T>)(Object)_30;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._31<? extends T>,T> _31() {
        return (Function1<Tuple._31<? extends T>,T>)(Object)_31;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._32<? extends T>,T> _32() {
        return (Function1<Tuple._32<? extends T>,T>)(Object)_32;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._33<? extends T>,T> _33() {
        return (Function1<Tuple._33<? extends T>,T>)(Object)_33;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._34<? extends T>,T> _34() {
        return (Function1<Tuple._34<? extends T>,T>)(Object)_34;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._35<? extends T>,T> _35() {
        return (Function1<Tuple._35<? extends T>,T>)(Object)_35;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._36<? extends T>,T> _36() {
        return (Function1<Tuple._36<? extends T>,T>)(Object)_36;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._37<? extends T>,T> _37() {
        return (Function1<Tuple._37<? extends T>,T>)(Object)_37;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._38<? extends T>,T> _38() {
        return (Function1<Tuple._38<? extends T>,T>)(Object)_38;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._39<? extends T>,T> _39() {
        return (Function1<Tuple._39<? extends T>,T>)(Object)_39;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._40<? extends T>,T> _40() {
        return (Function1<Tuple._40<? extends T>,T>)(Object)_40;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._41<? extends T>,T> _41() {
        return (Function1<Tuple._41<? extends T>,T>)(Object)_41;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._42<? extends T>,T> _42() {
        return (Function1<Tuple._42<? extends T>,T>)(Object)_42;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._43<? extends T>,T> _43() {
        return (Function1<Tuple._43<? extends T>,T>)(Object)_43;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Function1<Tuple._44<? extends T>,T> _44() {
        return (Function1<Tuple._44<? extends T>,T>)(Object)_44;
    }
}
