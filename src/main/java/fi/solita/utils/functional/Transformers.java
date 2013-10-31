package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Predicates.not;

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
    
    public static final Transformer<CharSequence, String> prepend(final CharSequence prefix) { 
        return new Transformer<CharSequence, String>() {
            @Override
            public String transform(CharSequence source) {
                return prefix + source.toString();
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
    
    public static final Transformer<String, String> removeWhitespace = new Transformer<String, String>() {
        private final Predicate<Character> whitespace = new Predicate<Character>() {
            @Override
            public boolean accept(Character candidate) {
                return Character.isWhitespace(candidate);
            }
        };
        
        private final Transformer<Object, String> toString = new Transformer<Object, String>() {
            @Override
            public String transform(Object source) {
                return source.toString();
            }
        };
        
        @Override
        public String transform(String source) {
            if (source == null) {
                return null;
            }
            return Functional.mkString("", map(filter(it(source), not(whitespace)), toString));
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
    
    public static final Transformer<Tuple, String> mkString(final String delim, final Apply<Object,CharSequence> toString) {
          return new Transformer<Tuple, String>() {
              @Override
              public String transform(Tuple source) {
                  return Functional.mkString(delim, map(source.toArray(), toString));
              }
          };
      }

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
    
    public static final Transformer<Integer, Integer> mod(final Integer mod) {
        return new Transformer<Integer, Integer>() {
            @Override
            public Integer transform(Integer source) {
                  return source % mod;
            }
        };
    }
    
    
    
    public static final Transformer<Long, Long> negate = new Transformer<Long,Long>() {
        @Override
        public Long transform(Long source) {
            return -1*source;
        }
    };
    
    public static final Transformer<Integer, Integer> negateInt = new Transformer<Integer,Integer>() {
        @Override
        public Integer transform(Integer source) {
            return -1*source;
        }
    };
    
    public static final Transformer<Short, Short> negateShort = new Transformer<Short,Short>() {
        @Override
        public Short transform(Short source) {
            return (short)(-1*source);
        }
    };
    
    
    
    public static final Transformer<Short, Long> short2long = new Transformer<Short,Long>() {
        @Override
        public Long transform(Short source) {
            return source.longValue();
        }
    };
    
    public static final Transformer<Integer, Long> int2long = new Transformer<Integer,Long>() {
        @Override
        public Long transform(Integer source) {
            return source.longValue();
        }
    };
    
    public static final Transformer<Object,String> toString = new Transformer<Object,String>() {
        @Override
        public String transform(Object source) {
            return source.toString();
        }
    };
}
