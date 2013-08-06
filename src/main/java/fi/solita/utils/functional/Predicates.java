package fi.solita.utils.functional;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.regex.Pattern;

public abstract class Predicates {

    private static final class Impl {
        private static final Predicate<?> nulls = new Predicate<Object>() {
            @Override
            public boolean accept(Object candidate) {
                return candidate == null;
            }
        };
        
        private static final Predicate<Iterable<?>> empty = new Predicate<Iterable<?>>() {
            @Override
            public boolean accept(Iterable<?> candidate) {
                return !candidate.iterator().hasNext();
            }
        };
    }
    
    public static final <T> Predicate<T> not(final Apply<T, Boolean> filter) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return !filter.apply(candidate);
            }

        };
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Predicate<T> nulls() {
        return (Predicate<T>) Impl.nulls;
    }
    
    public static final Predicate<Integer> even = new Predicate<Integer>() {
        @Override
        public boolean accept(Integer candidate) {
            return candidate % 2 == 0;
        }
    };

    @SuppressWarnings("unchecked")
    public static final <E, T extends Iterable<E>> Predicate<T> empty() {
        return (Predicate<T>) Impl.empty;
    }
    
    public static final Predicate<String> empty = new Predicate<String>() {
        @Override
        public boolean accept(String candidate) {
            return candidate.isEmpty();
        }
    };

    public static final <T> Predicate<T> equalTo(final T element) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return candidate.equals(element);
            }
        };
    }
    
    public static final <T extends Comparable<T>> Predicate<T> greaterThan(final T value) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return candidate.compareTo(value) > 0;
            }
        };
    }
    
    public static final <T extends Comparable<T>> Predicate<T> lessThan(final T value) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return candidate.compareTo(value) < 0;
            }
        };
    }
    
    public static final <T extends Comparable<T>> Predicate<T> greaterThanOrEqualTo(final T value) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return candidate.compareTo(value) >= 0;
            }
        };
    }
    
    public static final <T extends Comparable<T>> Predicate<T> lessThanOrEqualTo(final T value) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return candidate.compareTo(value) <= 0;
            }
        };
    }

    public static final <T> Predicate<Class<?>> isInstance(final Class<T> clazz) {
        return new Predicate<Class<?>>() {
            @Override
            public boolean accept(Class<?> candidate) {
                return clazz.isAssignableFrom(candidate);
            }
        };
    }

    public static final <T> Predicate<T> instanceOf(final Class<? extends T> clazz) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return clazz.isInstance(candidate);
            }
        };
    }

    public static final Predicate<Option<?>> defined = new Predicate<Option<?>>() {
        @Override
        public boolean accept(Option<?> candidate) {
            return candidate.isDefined();
        }
    };
    
    public static final <T extends AnnotatedElement> Predicate<T> hasAnnotation(final Class<? extends Annotation> annotation) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return candidate.isAnnotationPresent(annotation);
            }
        };
    };
    
    public static final Predicate<String> matches(final Pattern pattern) {
        return new Predicate<String>() {
            @Override
            public boolean accept(String candidate) {
                return pattern.matcher(candidate).matches();
            }
        };
    }
    
    public static final Predicate<String> lookingAt(final Pattern pattern) {
        return new Predicate<String>() {
            @Override
            public boolean accept(String candidate) {
                return pattern.matcher(candidate).lookingAt();
            }
        };
    }

    public static final Predicate<Character> whitespace = new Predicate<Character>() {
        @Override
        public boolean accept(Character candidate) {
            return Character.isWhitespace(candidate);
        }
    };
    
    public static final Predicate<String> contains(final CharSequence infix) {
        return new Predicate<String>() {
                @Override
                public boolean accept(String candidate) {
                    return candidate.contains(infix);
                }
        };
    }
    
    public static final Predicate<String> startsWith(final String prefix) {
        return new Predicate<String>() {
                @Override
                public boolean accept(String candidate) {
                    return candidate.startsWith(prefix);
                }
        };
    }
    
    public static final Predicate<String> endsWith(final String prefix) {
        return new Predicate<String>() {
                @Override
                public boolean accept(String candidate) {
                    return candidate.endsWith(prefix);
                }
        };
    }
    
    public static final Predicate<Object> serializable = new Predicate<Object>() {
        @Override
        public boolean accept(Object candidate) {
            return candidate instanceof Serializable || candidate != null && candidate.getClass().isPrimitive();
        }
    };
}
