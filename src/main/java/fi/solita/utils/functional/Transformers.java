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
    
    public static final Transformer<CharSequence, String> append(final CharSequence suffix) { 
        return new Transformer<CharSequence, String>() {
            @Override
            public final String transform(CharSequence source) {
                return source == null ? null : source.toString() + suffix;
            }
        };
    }
    
    public static final <A,B> Transformer<A, Pair<A,B>> append(final B suffix) { 
        return new Transformer<A, Pair<A,B>>() {
            @Override
            public final Pair<A,B> transform(A source) {
                return source == null ? null : Pair.of(source, suffix);
            }
        };
    }
    
    public static final <A,B,C> Transformer<Map.Entry<A,B>, Tuple3<A,B,C>> appendPair(final C suffix) { 
        return new Transformer<Map.Entry<A,B>, Tuple3<A,B,C>>() {
            @Override
            public final Tuple3<A,B,C> transform(Map.Entry<A,B> source) {
                return source == null ? null : Tuple.of(source.getKey(), source.getValue(), suffix);
            }
        };
    }
    
    public static final Transformer<CharSequence, String> prepend(final CharSequence prefix) { 
        return new Transformer<CharSequence, String>() {
            @Override
            public final String transform(CharSequence source) {
                return prefix + source.toString();
            }
        };
    }
    
    public static final <A,B> Transformer<B, Pair<A,B>> prepend(final A prefix) { 
        return new Transformer<B, Pair<A,B>>() {
            @Override
            public final Pair<A,B> transform(B source) {
                return source == null ? null : Pair.of(prefix, source);
            }
        };
    }
    
    public static final <A,B,C> Transformer<Map.Entry<B,C>, Tuple3<A,B,C>> prependPair(final A prefix) { 
        return new Transformer<Map.Entry<B,C>, Tuple3<A,B,C>>() {
            @Override
            public final Tuple3<A,B,C> transform(Map.Entry<B,C> source) {
                return source == null ? null : Tuple.of(prefix, source.getKey(), source.getValue());
            }
        };
    }
    
    public static final Transformer<String, String> replaceAll(final Pattern pattern, final String replacement) { 
        return new Transformer<String, String>() {
            @Override
            public final String transform(String source) {
                return source == null ? null : pattern.matcher(source).replaceAll(replacement);
            }
        };
    }
    
    public static final Transformer<String, String> removeWhitespace = new Transformer<String, String>() {
        private final Predicate<Character> whitespace = new Predicate<Character>() {
            @Override
            public final boolean accept(Character candidate) {
                return Character.isWhitespace(candidate);
            }
        };
        
        @Override
        public final String transform(String source) {
            if (source == null) {
                return null;
            }
            return Functional.mkString("", Functional.map(toString, filter(not(whitespace), it(source))));
        }
    };
    
    public static final Transformer<Map.Entry<?, ?>, String> join(final String delim) {
        return new Transformer<Map.Entry<?, ?>, String>() {
            @Override
            public final String transform(Map.Entry<?, ?> source) {
                return source.getKey() + delim + source.getValue();
            }
        };
    }
    
    public static final Transformer<Tuple, String> mkString(final String delim, final Apply<Object,CharSequence> toString) {
          return new Transformer<Tuple, String>() {
              @Override
              public final String transform(Tuple source) {
                  return Functional.mkString(delim, Functional.map(toString, source.toArray()));
              }
          };
      }

    public static final Transformer<String, String> prefix(final int chars) {
        if (chars <= 0) {
            throw new IllegalArgumentException("chars must be >= 1");
        }
        return new Transformer<String, String>() {
            @Override
            public final String transform(String source) {
                return source.substring(0, Math.min(chars, source.length()));
            }
        };
    };
    
    private static final Transformer<Map.Entry<?, ?>,Map.Entry<?, ?>> flip = new Transformer<Map.Entry<?, ?>,Map.Entry<?, ?>>() {
        @Override
        public final Map.Entry<?, ?> transform(Map.Entry<?, ?> source) {
            return Tuple.of(source.getValue(), source.getKey());
        }
    };
    @SuppressWarnings("unchecked")
    public static final <K,V> Transformer<Map.Entry<K, V>,Map.Entry<V, K>> flip() {
        return (Transformer<Map.Entry<K, V>,Map.Entry<V, K>>)(Object)flip;
    };
    
    public static final Transformer<Integer, Integer> mod(final Integer mod) {
        return new Transformer<Integer, Integer>() {
            @Override
            public final Integer transform(Integer source) {
                  return source % mod;
            }
        };
    }
    
    
    
    public static final Transformer<Long, Long> negate = new Transformer<Long,Long>() {
        @Override
        public final Long transform(Long source) {
            return -1*source;
        }
    };
    
    public static final Transformer<Integer, Integer> negateInt = new Transformer<Integer,Integer>() {
        @Override
        public final Integer transform(Integer source) {
            return -1*source;
        }
    };
    
    public static final Transformer<Short, Short> negateShort = new Transformer<Short,Short>() {
        @Override
        public final Short transform(Short source) {
            return (short)(-1*source);
        }
    };
    
    
    
    public static final Transformer<Short, Long> short2long = new Transformer<Short,Long>() {
        @Override
        public final Long transform(Short source) {
            return source.longValue();
        }
    };
    
    public static final Transformer<Integer, Long> int2long = new Transformer<Integer,Long>() {
        @Override
        public final Long transform(Integer source) {
            return source.longValue();
        }
    };
    
    public static final Transformer<Object,String> toString = new Transformer<Object,String>() {
        @Override
        public final String transform(Object source) {
            return source == null ? null : source.toString();
        }
    };
    
    private static final Transformer<Map.Entry<?,?>,?> left = new Transformer<Map.Entry<?,?>,Object>() {
        @Override
        public final Object transform(Map.Entry<?,?> source) {
            return source.getKey();
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Map.Entry<T,?>,T> left() {
        return (Transformer<Map.Entry<T,?>,T>)(Object)left;
    }
    @SuppressWarnings("unchecked")
    public static final <K,V> Transformer<Map.Entry<K,V>,K> key() {
        return (Transformer<Map.Entry<K,V>,K>)(Object)left;
    }
    
    private static final Transformer<Map.Entry<?,?>,?> right = new Transformer<Map.Entry<?,?>,Object>() {
        @Override
        public final Object transform(Map.Entry<?,?> source) {
            return source.getValue();
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Map.Entry<?,T>,T> right() {
        return (Transformer<Map.Entry<?,T>,T>)(Object)right;
    }
    @SuppressWarnings("unchecked")
    public static final <K,V> Transformer<Map.Entry<K,V>,V> value() {
        return (Transformer<Map.Entry<K,V>,V>)(Object)right;
    }

    public static final <K,V,R> Transformer<Map.Entry<K,V>,Map.Entry<R,V>> first(final Apply<K,R> f) {
        return new Transformer<Map.Entry<K,V>,Map.Entry<R,V>>() {
            @Override
            public final Map.Entry<R,V> transform(Map.Entry<K,V> source) {
                return Pair.of(f.apply(source.getKey()), source.getValue());
            }
        };
    }
    
    public static final <K,V,R> Transformer<Map.Entry<K,V>,Map.Entry<K,R>> second(final Apply<V,R> f) {
        return new Transformer<Map.Entry<K,V>,Map.Entry<K,R>>() {
            @Override
            public final Map.Entry<K,R> transform(Map.Entry<K,V> source) {
                return Pair.of(source.getKey(), f.apply(source.getValue()));
            }
        };
    }
    
    private static final Transformer<Either<?,?>,Option<?>> eitherLeft = new Transformer<Either<?,?>,Option<?>>() {
        @Override
        public final Option<?> transform(Either<?,?> source) {
            return source.left;
        }
    };
    private static final Transformer<Either<?,?>,Option<?>> eitherRight = new Transformer<Either<?,?>,Option<?>>() {
        @Override
        public final Option<?> transform(Either<?,?> source) {
            return source.right;
        }
    };
    
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Either<T,?>,Option<T>> eitherLeft() {
        return (Transformer<Either<T,?>,Option<T>>)(Object)eitherLeft;
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Either<?,T>,Option<T>> eitherRight() {
        return (Transformer<Either<?,T>,Option<T>>)(Object)eitherRight;
    }
    
    private static final Transformer<Either3<?,?,?>,Option<?>> either3Left = new Transformer<Either3<?,?,?>,Option<?>>() {
        @Override
        public final Option<?> transform(Either3<?,?,?> source) {
            return source.left;
        }
    };
    private static final Transformer<Either3<?,?,?>,Option<?>> either3Middle = new Transformer<Either3<?,?,?>,Option<?>>() {
        @Override
        public final Option<?> transform(Either3<?,?,?> source) {
            return source.middle;
        }
    };
    private static final Transformer<Either3<?,?,?>,Option<?>> either3Right = new Transformer<Either3<?,?,?>,Option<?>>() {
        @Override
        public final Option<?> transform(Either3<?,?,?> source) {
            return source.right;
        }
    };
    
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Either3<T,?,?>,Option<T>> either3Left() {
        return (Transformer<Either3<T,?,?>,Option<T>>)(Object)either3Left;
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Either3<?,T,?>,Option<T>> either3Middle() {
        return (Transformer<Either3<?,T,?>,Option<T>>)(Object)either3Middle;
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Either3<?,?,T>,Option<T>> either3Right() {
        return (Transformer<Either3<?,?,T>,Option<T>>)(Object)either3Right;
    }
    
    private static final Transformer<Map<Object,Object>,Set<Map.Entry<Object,Object>>> mapEntrySet = new Transformer<Map<Object,Object>,Set<Map.Entry<Object,Object>>>() {
        @Override
        public final Set<Map.Entry<Object,Object>> transform(Map<Object,Object> source) {
            return source.entrySet();
        }
    };
    @SuppressWarnings("unchecked")
    public static final <K,V> Transformer<Map<K,V>,Set<Map.Entry<K,V>>> mapEntrySet() {
        return (Transformer<Map<K,V>,Set<Map.Entry<K,V>>>)(Object)mapEntrySet;
    }
    
    private static final Transformer<?,Option<?>> some = new Transformer<Object,Option<?>>() {
        @Override
        public final Option<?> transform(Object source) {
            return Some(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<T,Option<T>> some() {
        return (Transformer<T,Option<T>>)(Object)some;
    }
    
    private static final Transformer<Option<?>,?> get = new Transformer<Option<?>,Object>() {
        @Override
        public final Object transform(Option<?> source) {
            return source.get();
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Option<T>,T> get() {
        return (Transformer<Option<T>,T>)(Object)get;
    }
    
    public static final <T> Transformer<Option<T>,T> getOrElse(final T elseValue) {
        return new Transformer<Option<T>,T>() {
            @Override
            public final T transform(Option<T> source) {
                return source.getOrElse(elseValue);
            }
        };
    }
    
    public static final Transformer<String,Iterable<Character>> it = new Transformer<String,Iterable<Character>>() {
        @Override
        public final Iterable<Character> transform(String source) {
            return it(source);
        }
    };
    
    private static final Transformer<Iterable<?>, Object> head = new Transformer<Iterable<?>,Object>() {
        @Override
        public final Object transform(Iterable<?> source) {
            return Functional.head(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Iterable<T>,T> head() {
        return (Transformer<Iterable<T>,T>)(Object)head;
    }
    
    private static final Transformer<Iterable<?>, Object> tail = new Transformer<Iterable<?>,Object>() {
        @Override
        public final Object transform(Iterable<?> source) {
            return Functional.tail(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Iterable<T>,Iterable<T>> tail() {
        return (Transformer<Iterable<T>,Iterable<T>>)(Object)tail;
    }
    
    public static final Transformer<String, String> tailStr = new Transformer<String,String>() {
        @Override
        public final String transform(String source) {
            return Functional.tail(source);
        }
    };
    
    public static final <T,R> Transformer<Iterable<? extends T>,Iterable<R>> map(final Apply<? super T, R> f) {
        return new Transformer<Iterable<? extends T>,Iterable<R>>() {
            @Override
            public final Iterable<R> transform(Iterable<? extends T> source) {
                return Functional.map(f, source);
            }
        };
    }
    
    public static final <T,R> Transformer<Option<? extends T>,Option<R>> mapOption(final Apply<? super T, R> f) {
        return new Transformer<Option<? extends T>,Option<R>>() {
            @Override
            public final Option<R> transform(Option<? extends T> source) {
                return source.map(f);
            }
        };
    }
      
    public static final <T,R> Transformer<Iterable<? extends T>,Iterable<R>> flatMap(final Apply<? super T, ? extends Iterable<R>> f) {
        return new Transformer<Iterable<? extends T>,Iterable<R>>() {
            @Override
            public final Iterable<R> transform(Iterable<? extends T> source) {
                return Functional.flatMap(f, source);
            }
        };
    }
    
    public static final <R,T extends Iterable<R>> Transformer<Iterable<? extends T>,Iterable<R>> flatten() {
        return new Transformer<Iterable<? extends T>,Iterable<R>>() {
            @Override
            public final Iterable<R> transform(Iterable<? extends T> source) {
                return Functional.flatten(source);
            }
        };
    }
    
    private static final Transformer<Object, List<Object>> newList = new Transformer<Object,List<Object>>() {
        @Override
        public final List<Object> transform(Object source) {
            return Collections.newList(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<T,List<T>> newList() {
        return (Transformer<T,List<T>>)(Object)newList;
    }
    
    private static final Transformer<Object, Set<Object>> newSet = new Transformer<Object,Set<Object>>() {
        @Override
        public final Set<Object> transform(Object source) {
            return Collections.newSet(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<T,Set<T>> newSet() {
        return (Transformer<T,Set<T>>)(Object)newSet;
    }
    
    private static final Transformer<Tuple2<Object,Object>, Map<Object,Object>> newMap = new Transformer<Tuple2<Object,Object>,Map<Object,Object>>() {
        @Override
        public final Map<Object,Object> transform(Tuple2<Object,Object> source) {
            return Collections.newMap(source);
        }
    };
    @SuppressWarnings("unchecked")
    public static final <K,V,T extends Tuple2<K,V>> Transformer<T,Map<K,V>> newMap() {
        return (Transformer<T,Map<K,V>>)(Object)newMap;
    }
   
    public static final Transformer<Iterable<?>, Long> size = new Transformer<Iterable<?>,Long>() {
        @Override
        public final Long transform(Iterable<?> source) {
            return Functional.size(source);
        }
    };
    
    public static <A,B,T extends Tuple._1<A> & Tuple._2<B>> Transformer<T,Tuple2<A,B>> take2() {
        return new Transformer<T, Tuple2<A,B>>() {
            @Override
            public Tuple2<A, B> transform(T source) {
                return Tuple.of(source.get_1(), source.get_2());
            }
        };
    }
    
    public static <A,B,C,T extends Tuple._1<A> & Tuple._2<B> & Tuple._3<C>> Transformer<T,Tuple3<A,B,C>> take3() {
        return new Transformer<T, Tuple3<A,B,C>>() {
            @Override
            public Tuple3<A, B, C> transform(T source) {
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
    
    private static final Transformer<Tuple._1<?>,?> _1 = new Transformer<Tuple._1<?>,Object>() {
        @Override
        public final Object transform(Tuple._1<?> source) {
            return source.get_1();
        }
    };
    private static final Transformer<Tuple._2<?>,?> _2 = new Transformer<Tuple._2<?>,Object>() {
        @Override
        public final Object transform(Tuple._2<?> source) {
            return source.get_2();
        }
    };
    private static final Transformer<Tuple._3<?>,?> _3 = new Transformer<Tuple._3<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._3<?> source) {
            return source.get_3();
        }
    };
    private static final Transformer<Tuple._4<?>,?> _4 = new Transformer<Tuple._4<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._4<?> source) {
            return source.get_4();
        }
    };
    private static final Transformer<Tuple._5<?>,?> _5 = new Transformer<Tuple._5<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._5<?> source) {
            return source.get_5();
        }
    };
    private static final Transformer<Tuple._6<?>,?> _6 = new Transformer<Tuple._6<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._6<?> source) {
            return source.get_6();
        }
    };
    private static final Transformer<Tuple._7<?>,?> _7 = new Transformer<Tuple._7<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._7<?> source) {
            return source.get_7();
        }
    };
    private static final Transformer<Tuple._8<?>,?> _8 = new Transformer<Tuple._8<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._8<?> source) {
            return source.get_8();
        }
    };
    private static final Transformer<Tuple._9<?>,?> _9 = new Transformer<Tuple._9<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._9<?> source) {
            return source.get_9();
        }
    };
    private static final Transformer<Tuple._10<?>,?> _10 = new Transformer<Tuple._10<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._10<?> source) {
            return source.get_10();
        }
    };
    private static final Transformer<Tuple._11<?>,?> _11 = new Transformer<Tuple._11<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._11<?> source) {
            return source.get_11();
        }
    };
    private static final Transformer<Tuple._12<?>,?> _12 = new Transformer<Tuple._12<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._12<?> source) {
            return source.get_12();
        }
    };
    private static final Transformer<Tuple._13<?>,?> _13 = new Transformer<Tuple._13<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._13<?> source) {
            return source.get_13();
        }
    };
    private static final Transformer<Tuple._14<?>,?> _14 = new Transformer<Tuple._14<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._14<?> source) {
            return source.get_14();
        }
    };
    private static final Transformer<Tuple._15<?>,?> _15 = new Transformer<Tuple._15<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._15<?> source) {
            return source.get_15();
        }
    };
    private static final Transformer<Tuple._16<?>,?> _16 = new Transformer<Tuple._16<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._16<?> source) {
            return source.get_16();
        }
    };
    private static final Transformer<Tuple._17<?>,?> _17 = new Transformer<Tuple._17<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._17<?> source) {
            return source.get_17();
        }
    };
    private static final Transformer<Tuple._18<?>,?> _18 = new Transformer<Tuple._18<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._18<?> source) {
            return source.get_18();
        }
    };
    private static final Transformer<Tuple._19<?>,?> _19 = new Transformer<Tuple._19<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._19<?> source) {
            return source.get_19();
        }
    };
    private static final Transformer<Tuple._20<?>,?> _20 = new Transformer<Tuple._20<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._20<?> source) {
            return source.get_20();
        }
    };
    private static final Transformer<Tuple._21<?>,?> _21 = new Transformer<Tuple._21<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._21<?> source) {
            return source.get_21();
        }
    };
    private static final Transformer<Tuple._22<?>,?> _22 = new Transformer<Tuple._22<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._22<?> source) {
            return source.get_22();
        }
    };
    private static final Transformer<Tuple._23<?>,?> _23 = new Transformer<Tuple._23<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._23<?> source) {
            return source.get_23();
        }
    };
    private static final Transformer<Tuple._24<?>,?> _24 = new Transformer<Tuple._24<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._24<?> source) {
            return source.get_24();
        }
    };
    private static final Transformer<Tuple._25<?>,?> _25 = new Transformer<Tuple._25<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._25<?> source) {
            return source.get_25();
        }
    };
    private static final Transformer<Tuple._26<?>,?> _26 = new Transformer<Tuple._26<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._26<?> source) {
            return source.get_26();
        }
    };
    private static final Transformer<Tuple._27<?>,?> _27 = new Transformer<Tuple._27<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._27<?> source) {
            return source.get_27();
        }
    };
    private static final Transformer<Tuple._28<?>,?> _28 = new Transformer<Tuple._28<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._28<?> source) {
            return source.get_28();
        }
    };
    private static final Transformer<Tuple._29<?>,?> _29 = new Transformer<Tuple._29<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._29<?> source) {
            return source.get_29();
        }
    };
    private static final Transformer<Tuple._30<?>,?> _30 = new Transformer<Tuple._30<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._30<?> source) {
            return source.get_30();
        }
    };
    private static final Transformer<Tuple._31<?>,?> _31 = new Transformer<Tuple._31<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._31<?> source) {
            return source.get_31();
        }
    };
    private static final Transformer<Tuple._32<?>,?> _32 = new Transformer<Tuple._32<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._32<?> source) {
            return source.get_32();
        }
    };
    private static final Transformer<Tuple._33<?>,?> _33 = new Transformer<Tuple._33<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._33<?> source) {
            return source.get_33();
        }
    };
    private static final Transformer<Tuple._34<?>,?> _34 = new Transformer<Tuple._34<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._34<?> source) {
            return source.get_34();
        }
    };
    private static final Transformer<Tuple._35<?>,?> _35 = new Transformer<Tuple._35<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._35<?> source) {
            return source.get_35();
        }
    };
    private static final Transformer<Tuple._36<?>,?> _36 = new Transformer<Tuple._36<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._36<?> source) {
            return source.get_36();
        }
    };
    private static final Transformer<Tuple._37<?>,?> _37 = new Transformer<Tuple._37<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._37<?> source) {
            return source.get_37();
        }
    };
    private static final Transformer<Tuple._38<?>,?> _38 = new Transformer<Tuple._38<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._38<?> source) {
            return source.get_38();
        }
    };
    private static final Transformer<Tuple._39<?>,?> _39 = new Transformer<Tuple._39<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._39<?> source) {
            return source.get_39();
        }
    };
    private static final Transformer<Tuple._40<?>,?> _40 = new Transformer<Tuple._40<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._40<?> source) {
            return source.get_40();
        }
    };
    private static final Transformer<Tuple._41<?>,?> _41 = new Transformer<Tuple._41<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._41<?> source) {
            return source.get_41();
        }
    };
    private static final Transformer<Tuple._42<?>,?> _42 = new Transformer<Tuple._42<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._42<?> source) {
            return source.get_42();
        }
    };
    private static final Transformer<Tuple._43<?>,?> _43 = new Transformer<Tuple._43<?>,Object>() {
        @Override
        public final Object transform(fi.solita.utils.functional.Tuple._43<?> source) {
            return source.get_43();
        }
    };
    
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._1<T>,T> _1() {
        return (Transformer<Tuple._1<T>,T>)(Object)_1;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._2<T>,T> _2() {
        return (Transformer<Tuple._2<T>,T>)(Object)_2;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._3<T>,T> _3() {
        return (Transformer<Tuple._3<T>,T>)(Object)_3;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._4<T>,T> _4() {
        return (Transformer<Tuple._4<T>,T>)(Object)_4;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._5<T>,T> _5() {
        return (Transformer<Tuple._5<T>,T>)(Object)_5;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._6<T>,T> _6() {
        return (Transformer<Tuple._6<T>,T>)(Object)_6;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._7<T>,T> _7() {
        return (Transformer<Tuple._7<T>,T>)(Object)_7;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._8<T>,T> _8() {
        return (Transformer<Tuple._8<T>,T>)(Object)_8;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._9<T>,T> _9() {
        return (Transformer<Tuple._9<T>,T>)(Object)_9;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._10<T>,T> _10() {
        return (Transformer<Tuple._10<T>,T>)(Object)_10;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._11<T>,T> _11() {
        return (Transformer<Tuple._11<T>,T>)(Object)_11;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._12<T>,T> _12() {
        return (Transformer<Tuple._12<T>,T>)(Object)_12;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._13<T>,T> _13() {
        return (Transformer<Tuple._13<T>,T>)(Object)_13;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._14<T>,T> _14() {
        return (Transformer<Tuple._14<T>,T>)(Object)_14;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._15<T>,T> _15() {
        return (Transformer<Tuple._15<T>,T>)(Object)_15;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._16<T>,T> _16() {
        return (Transformer<Tuple._16<T>,T>)(Object)_16;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._17<T>,T> _17() {
        return (Transformer<Tuple._17<T>,T>)(Object)_17;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._18<T>,T> _18() {
        return (Transformer<Tuple._18<T>,T>)(Object)_18;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._19<T>,T> _19() {
        return (Transformer<Tuple._19<T>,T>)(Object)_19;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._20<T>,T> _20() {
        return (Transformer<Tuple._20<T>,T>)(Object)_20;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._21<T>,T> _21() {
        return (Transformer<Tuple._21<T>,T>)(Object)_21;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._22<T>,T> _22() {
        return (Transformer<Tuple._22<T>,T>)(Object)_22;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._23<T>,T> _23() {
        return (Transformer<Tuple._23<T>,T>)(Object)_23;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._24<T>,T> _24() {
        return (Transformer<Tuple._24<T>,T>)(Object)_24;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._25<T>,T> _25() {
        return (Transformer<Tuple._25<T>,T>)(Object)_25;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._26<T>,T> _26() {
        return (Transformer<Tuple._26<T>,T>)(Object)_26;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._27<T>,T> _27() {
        return (Transformer<Tuple._27<T>,T>)(Object)_27;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._28<T>,T> _28() {
        return (Transformer<Tuple._28<T>,T>)(Object)_28;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._29<T>,T> _29() {
        return (Transformer<Tuple._29<T>,T>)(Object)_29;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._30<T>,T> _30() {
        return (Transformer<Tuple._30<T>,T>)(Object)_30;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._31<T>,T> _31() {
        return (Transformer<Tuple._31<T>,T>)(Object)_31;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._32<T>,T> _32() {
        return (Transformer<Tuple._32<T>,T>)(Object)_32;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._33<T>,T> _33() {
        return (Transformer<Tuple._33<T>,T>)(Object)_33;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._34<T>,T> _34() {
        return (Transformer<Tuple._34<T>,T>)(Object)_34;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._35<T>,T> _35() {
        return (Transformer<Tuple._35<T>,T>)(Object)_35;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._36<T>,T> _36() {
        return (Transformer<Tuple._36<T>,T>)(Object)_36;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._37<T>,T> _37() {
        return (Transformer<Tuple._37<T>,T>)(Object)_37;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._38<T>,T> _38() {
        return (Transformer<Tuple._38<T>,T>)(Object)_38;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._39<T>,T> _39() {
        return (Transformer<Tuple._39<T>,T>)(Object)_39;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._40<T>,T> _40() {
        return (Transformer<Tuple._40<T>,T>)(Object)_40;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._41<T>,T> _41() {
        return (Transformer<Tuple._41<T>,T>)(Object)_41;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._42<T>,T> _42() {
        return (Transformer<Tuple._42<T>,T>)(Object)_42;
    }
    @SuppressWarnings("unchecked")
    public static final <T> Transformer<Tuple._43<T>,T> _43() {
        return (Transformer<Tuple._43<T>,T>)(Object)_43;
    }
}
