package fi.solita.utils.functional;

import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Predicates.whitespace;

import java.lang.reflect.Member;
import java.util.Map;
import java.util.regex.Pattern;

import fi.solita.utils.functional.Tuple._1;
import fi.solita.utils.functional.Tuple._10;
import fi.solita.utils.functional.Tuple._11;
import fi.solita.utils.functional.Tuple._12;
import fi.solita.utils.functional.Tuple._13;
import fi.solita.utils.functional.Tuple._14;
import fi.solita.utils.functional.Tuple._15;
import fi.solita.utils.functional.Tuple._16;
import fi.solita.utils.functional.Tuple._17;
import fi.solita.utils.functional.Tuple._18;
import fi.solita.utils.functional.Tuple._19;
import fi.solita.utils.functional.Tuple._2;
import fi.solita.utils.functional.Tuple._20;
import fi.solita.utils.functional.Tuple._21;
import fi.solita.utils.functional.Tuple._22;
import fi.solita.utils.functional.Tuple._3;
import fi.solita.utils.functional.Tuple._4;
import fi.solita.utils.functional.Tuple._5;
import fi.solita.utils.functional.Tuple._6;
import fi.solita.utils.functional.Tuple._7;
import fi.solita.utils.functional.Tuple._8;
import fi.solita.utils.functional.Tuple._9;

public abstract class Transformers {
    
    public static final Transformer<String, String> append(final String suffix) { 
        return new Transformer<String, String>() {
            @Override
            public String transform(String source) {
                return source == null ? null : source + suffix;
            }
        };
    }
    
    public static final Transformer<String, String> prepend(final String prefix) { 
        return new Transformer<String, String>() {
            @Override
            public String transform(String source) {
                return source == null ? null : prefix + source;
            }
        };
    }
    
    public static final Transformer<String, String> replaceAll(final Pattern pattern, final String replacement) { 
        return new Transformer<String, String>() {
            @Override
            public String transform(String source) {
                return source == null ? null : pattern.matcher(source).replaceAll(replacement);
            }
        };
    }

    public static final Transformer<String, String> trim = new Transformer<String, String>() {
        @Override
        public String transform(String source) {
            return source == null ? null : source.trim();
        }
    };
    
    public static Transformer<String, String> removeWhitespace = new Transformer<String, String>() {
        @Override
        public String transform(String source) {
            if (source == null) {
                return null;
            }
            Character[] arr = new Character[source.length()];
            System.arraycopy(source.toCharArray(), 0, arr, 0, source.length());
            return Functional.mkString("", map(filter(arr, not(whitespace)), toString));
        }
    };

    public static final <T> Transformer<Option<T>, T> optionGet() {
        return new Transformer<Option<T>, T>() {
            @Override
            public T transform(Option<T> source) {
                return source.get();
            }
        };
    }

    public static final <LEFT> Transformer<Map.Entry<? extends LEFT, ?>, LEFT> left() {
        return new Transformer<Map.Entry<? extends LEFT, ?>, LEFT>() {
            @Override
            public LEFT transform(Map.Entry<? extends LEFT, ?> source) {
                return source.getKey();
            }
        };
    }

    public static final <RIGHT> Transformer<Map.Entry<?, ? extends RIGHT>, RIGHT> right() {
        return new Transformer<Map.Entry<?, ? extends RIGHT>, RIGHT>() {
            @Override
            public RIGHT transform(Map.Entry<?, ? extends RIGHT> source) {
                return source.getValue();
            }
        };
    }

    public static final <T> Transformer<Option<T>, T> optionGetOrElse(final T orElse) {
        return new Transformer<Option<T>, T>() {
            @Override
            public T transform(Option<T> source) {
                return source.getOrElse(orElse);
            }
        };
    }

    public static final Transformer<String, String> toLowerCase = new Transformer<String, String>() {
        @Override
        public String transform(String source) {
            return source == null ? null : source.toLowerCase();
        }
    };
    
    public static final Transformer<String, String> toUpperCase = new Transformer<String, String>() {
        @Override
        public String transform(String source) {
            return source == null ? null : source.toUpperCase();
        }
    };
    
    public static final Transformer<Object, String> toString = new Transformer<Object, String>() {
        @Override
        public String transform(Object source) {
            return source == null ? null : source.toString();
        }
    };

    public static Transformer<Map.Entry<?, ?>, String> mkString(final String delim) {
        return new Transformer<Map.Entry<?, ?>, String>() {
            @Override
            public String transform(Map.Entry<?, ?> source) {
                return source.getKey() + delim + source.getValue();
            }
        };
    }

    public static final <K> Transformer<Map.Entry<K, ?>, K> keys() {
        return new Transformer<Map.Entry<K, ?>, K>() {
            @Override
            public K transform(Map.Entry<K, ?> source) {
                return source.getKey();
            }
        };
    }

    public static final <V> Transformer<Map.Entry<?, V>, V> values() {
        return new Transformer<Map.Entry<?, V>, V>() {
            @Override
            public V transform(Map.Entry<?, V> source) {
                return source.getValue();
            }
        };
    }

    public static final Transformer<Enum<?>, String> enumName = new Transformer<Enum<?>, String>() {
        @Override
        public String transform(Enum<?> source) {
            return source == null ? null : source.name();
        }
    };
    
    public static final <T> Transformer<Iterable<T>, Iterable<T>> take(final int amount) {
        return new Transformer<Iterable<T>, Iterable<T>>() {
            @Override
            public Iterable<T> transform(Iterable<T> source) {
                return Functional.take(source, amount);
            }
        };
    };

    public static final <T> Transformer<String, String> prefix(final int chars) {
        if (chars <= 0) {
            throw new IllegalArgumentException("chars must be >= 1");
        }
        return new Transformer<String, String>() {
            @Override
            public String transform(String source) {
                return source.substring(0, Math.min(chars, source.length()));
            }
        };
    };
    
    public static final <K,V> Transformer<Map.Entry<K, V>,Map.Entry<V, K>> flip() {
        return new Transformer<Map.Entry<K, V>,Map.Entry<V, K>>() {
            @Override
            public Map.Entry<V, K> transform(Map.Entry<K, V> source) {
                return Tuple.of(source.getValue(), source.getKey());
            }
        };
    };
    
    public static final Transformer<Short, Integer> short2int = new Transformer<Short, Integer>() {
        @Override
        public Integer transform(Short source) {
            return source == null ? null : source.intValue();
        }
    };
    
    public static final Transformer<Integer, Long> int2long = new Transformer<Integer, Long>() {
        @Override
        public Long transform(Integer source) {
            return source == null ? null : source.longValue();
        }
    };
    
    public static final Transformer<Member, String> memberNames = new Transformer<Member, String>() {
        @Override
        public String transform(Member source) {
            return source.getName();
        }
    };
    
    public static final <T> Transformer<_1<T>, T> _1() {
        return new Transformer<_1<T>, T>() {
            @Override
            public T transform(_1<T> source) {
                return source.get_1();
            }
        };
    }
    
    public static final <T> Transformer<_2<T>, T> _2() {
        return new Transformer<_2<T>, T>() {
            @Override
            public T transform(_2<T> source) {
                return source.get_2();
            }
        };
    }
    
    public static final <T> Transformer<_3<T>, T> _3() {
        return new Transformer<_3<T>, T>() {
            @Override
            public T transform(_3<T> source) {
                return source.get_3();
            }
        };
    }
    
    public static final <T> Transformer<_4<T>, T> _4() {
        return new Transformer<_4<T>, T>() {
            @Override
            public T transform(_4<T> source) {
                return source.get_4();
            }
        };
    }
    
    public static final <T> Transformer<_5<T>, T> _5() {
        return new Transformer<_5<T>, T>() {
            @Override
            public T transform(_5<T> source) {
                return source.get_5();
            }
        };
    }
    
    public static final <T> Transformer<_6<T>, T> _6() {
        return new Transformer<_6<T>, T>() {
            @Override
            public T transform(_6<T> source) {
                return source.get_6();
            }
        };
    }
    
    public static final <T> Transformer<_7<T>, T> _7() {
        return new Transformer<_7<T>, T>() {
            @Override
            public T transform(_7<T> source) {
                return source.get_7();
            }
        };
    }
    
    public static final <T> Transformer<_8<T>, T> _8() {
        return new Transformer<_8<T>, T>() {
            @Override
            public T transform(_8<T> source) {
                return source.get_8();
            }
        };
    }
    
    public static final <T> Transformer<_9<T>, T> _9() {
        return new Transformer<_9<T>, T>() {
            @Override
            public T transform(_9<T> source) {
                return source.get_9();
            }
        };
    }
    
    public static final <T> Transformer<_10<T>, T> _10() {
        return new Transformer<_10<T>, T>() {
            @Override
            public T transform(_10<T> source) {
                return source.get_10();
            }
        };
    }
    
    public static final <T> Transformer<_11<T>, T> _11() {
        return new Transformer<_11<T>, T>() {
            @Override
            public T transform(_11<T> source) {
                return source.get_11();
            }
        };
    }
    
    public static final <T> Transformer<_12<T>, T> _12() {
        return new Transformer<_12<T>, T>() {
            @Override
            public T transform(_12<T> source) {
                return source.get_12();
            }
        };
    }
    
    public static final <T> Transformer<_13<T>, T> _13() {
        return new Transformer<_13<T>, T>() {
            @Override
            public T transform(_13<T> source) {
                return source.get_13();
            }
        };
    }
    
    public static final <T> Transformer<_14<T>, T> _14() {
        return new Transformer<_14<T>, T>() {
            @Override
            public T transform(_14<T> source) {
                return source.get_14();
            }
        };
    }
    
    public static final <T> Transformer<_15<T>, T> _15() {
        return new Transformer<_15<T>, T>() {
            @Override
            public T transform(_15<T> source) {
                return source.get_15();
            }
        };
    }
    
    public static final <T> Transformer<_16<T>, T> _16() {
        return new Transformer<_16<T>, T>() {
            @Override
            public T transform(_16<T> source) {
                return source.get_16();
            }
        };
    }
    
    public static final <T> Transformer<_17<T>, T> _17() {
        return new Transformer<_17<T>, T>() {
            @Override
            public T transform(_17<T> source) {
                return source.get_17();
            }
        };
    }
    
    public static final <T> Transformer<_18<T>, T> _18() {
        return new Transformer<_18<T>, T>() {
            @Override
            public T transform(_18<T> source) {
                return source.get_18();
            }
        };
    }
    
    public static final <T> Transformer<_19<T>, T> _19() {
        return new Transformer<_19<T>, T>() {
            @Override
            public T transform(_19<T> source) {
                return source.get_19();
            }
        };
    }
    
    public static final <T> Transformer<_20<T>, T> _20() {
        return new Transformer<_20<T>, T>() {
            @Override
            public T transform(_20<T> source) {
                return source.get_20();
            }
        };
    }
    
    public static final <T> Transformer<_21<T>, T> _21() {
        return new Transformer<_21<T>, T>() {
            @Override
            public T transform(_21<T> source) {
                return source.get_21();
            }
        };
    }
    
    public static final <T> Transformer<_22<T>, T> _22() {
        return new Transformer<_22<T>, T>() {
            @Override
            public T transform(_22<T> source) {
                return source.get_22();
            }
        };
    }
}
