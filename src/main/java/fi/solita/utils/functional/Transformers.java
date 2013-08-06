package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Predicates.whitespace;

import java.lang.reflect.Member;
import java.util.Map;
import java.util.regex.Pattern;

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
    
    public static final Transformer<String, String> removeWhitespace = new Transformer<String, String>() {
        @Override
        public String transform(String source) {
            if (source == null) {
                return null;
            }
            return Functional.mkString("", map(filter(it(source), not(whitespace)), toString));
        }
    };

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

    public static final Transformer<Map.Entry<?, ?>, String> join(final String delim) {
        return new Transformer<Map.Entry<?, ?>, String>() {
            @Override
            public String transform(Map.Entry<?, ?> source) {
                return source.getKey() + delim + source.getValue();
            }
        };
    }
    
    public static final Transformer<Tuple, String> mkString(final String delim) {
      return new Transformer<Tuple, String>() {
          @Override
          public String transform(Tuple source) {
              return Functional.mkString(delim, map(source.toArray(), toString));
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

    public static final <V> Transformer<Map.Entry<?,V>, V> values() {
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
    
    public static Transformer<Integer, Integer> mod(final Integer mod) {
        return new Transformer<Integer, Integer>() {
            @Override
            public Integer transform(Integer source) {
                  return source % mod;
            }
        };
    }
    
    public static final Transformer<Member, String> memberNames = new Transformer<Member, String>() {
        @Override
        public String transform(Member source) {
            return source.getName();
        }
    };
    
    public static final Transformer<String, Integer> length = new Transformer<String, Integer>() {
        @Override
        public Integer transform(String source) {
            return source.length();
        }
    };
    
}
