package fi.solita.utils.functional;

public abstract class Predicates {

    public static final <T> Predicate<T> not(final Function1<T, Boolean> filter) {
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

    public static <E, T extends Iterable<E>> Predicate<T> empty() {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return !candidate.iterator().hasNext();
            }
        };
    }

    public static <T> Predicate<T> equal(final T element) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return element.equals(candidate);
            }
        };
    }

    public static <T, F> Predicate<T> equal(final Function1<? super T, ? extends F> transformer, final F value) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return transformer.apply(candidate).equals(value);
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

    public static final Predicate<Character> whitespace = new Predicate<Character>() {
        @Override
        public boolean accept(Character candidate) {
            return Character.isWhitespace(candidate);
        }
    };
}
