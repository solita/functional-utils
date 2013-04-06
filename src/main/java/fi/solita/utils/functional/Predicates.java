package fi.solita.utils.functional;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.regex.Pattern;

public abstract class Predicates {

    public static final <T> Predicate<T> not(final Apply<T, Boolean> filter) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return !filter.apply(candidate);
            }

        };
    }
    
    public static <T> Predicate<T> nulls() {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return candidate == null;
            }
        };
    }
    
    public static final Predicate<Integer> even = new Predicate<Integer>() {
        @Override
        public boolean accept(Integer candidate) {
            return candidate % 2 == 0;
        }
    };

    public static <E, T extends Iterable<E>> Predicate<T> empty() {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return !candidate.iterator().hasNext();
            }
        };
    }
    
    public static final Predicate<String> empty = new Predicate<String>() {
        @Override
        public boolean accept(String candidate) {
            return candidate.isEmpty();
        }
    };

    public static <T> Predicate<T> equalTo(final T element) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return element.equals(candidate);
            }
        };
    }
    
    public static <T extends Comparable<T>> Predicate<T> greaterThan(final T value) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return candidate.compareTo(value) > 0;
            }
        };
    }
    
    public static <T extends Comparable<T>> Predicate<T> lessThan(final T value) {
      return new Predicate<T>() {
          @Override
          public boolean accept(T candidate) {
              return candidate.compareTo(value) < 0;
          }
      };
  }

    public static <T> Predicate<Class<?>> isInstance(final Class<T> clazz) {
        return new Predicate<Class<?>>() {
            @Override
            public boolean accept(Class<?> candidate) {
                return clazz.isAssignableFrom(candidate);
            }
        };
    }

    public static <T> Predicate<T> instanceOf(final Class<? extends T> clazz) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return clazz.isInstance(candidate);
            }
        };
    }

    public static Predicate<Option<?>> defined() {
        return new Predicate<Option<?>>() {
            @Override
            public boolean accept(Option<?> candidate) {
                return candidate.isDefined();
            }
        };
    }
    
    public static <T extends AnnotatedElement> Predicate<T> hasAnnotation(final Class<? extends Annotation> annotation) {
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
}
