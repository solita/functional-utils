package fi.solita.utils.functional;

import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Predicates.whitespace;

import java.util.Map;

public abstract class Transformers {
    
    public static final <ID> Transformer<Identifiable<? extends ID>, ID> id() {
        return new Transformer<Identifiable<? extends ID>, ID>() {
            @Override
            public ID transform(Identifiable<? extends ID> source) {
                return source.getId();
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

    public static final <LEFT> Transformer<Map.Entry<LEFT, ?>, LEFT> left() {
        return new Transformer<Map.Entry<LEFT, ?>, LEFT>() {
            @Override
            public LEFT transform(Map.Entry<LEFT, ?> source) {
                return source.getKey();
            }
        };
    }

    public static final <RIGHT> Transformer<Map.Entry<?, RIGHT>, RIGHT> right() {
        return new Transformer<Map.Entry<?, RIGHT>, RIGHT>() {
            @Override
            public RIGHT transform(Map.Entry<?, RIGHT> source) {
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

    public static Transformer<Map.Entry<String, String>, String> mkString(final String delim) {
        return new Transformer<Map.Entry<String, String>, String>() {
            @Override
            public String transform(Map.Entry<String, String> source) {
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
}
