package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;

abstract class Iterables {
    static final class RangeIterable extends PossiblySizeAwareIterable<Integer> {
        private final int from;
        private final int toInclusive;

        public RangeIterable(int from) {
            this.from = from;
            this.toInclusive = Integer.MAX_VALUE;
        }

        public RangeIterable(int from, int toInclusive) {
            if (toInclusive < from) {
                throw new IllegalArgumentException("toInclusive must be gte from");
            }
            this.from = from;
            this.toInclusive = toInclusive;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                int i = from;
                @Override
                public boolean hasNext() {
                    return i <= toInclusive;
                }

                @Override
                public Integer next() {
                    if (i > toInclusive) {
                        throw new NoSuchElementException();
                    }
                    return i++;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public Option<Integer> size() {
            if (toInclusive == Integer.MAX_VALUE) {
                return None();
            } else {
                return Some(toInclusive - from + 1);
            }
        }
    }

    static final class RepeatingIterable<T> extends PossiblySizeAwareIterable<T> {
        private final T value;
        private final Option<Integer> amount;

        public RepeatingIterable(T value) {
            this.value = value;
            this.amount = None();
        }

        public RepeatingIterable(T value, int amount) {
            this.value = value;
            this.amount = Some(amount);
        }

        @Override
        public Option<Integer> size() {
            return amount.isDefined() ? amount : Option.<Integer>None();
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                int current = 0;
                @Override
                public boolean hasNext() {
                    return current < amount.getOrElse(Integer.MAX_VALUE);
                }

                @Override
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    current++;
                    return value;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    static abstract class PossiblySizeAwareIterable<T> implements Iterable<T> {
        public abstract Option<Integer> size();
        
        @Override
        public String toString() {
            return getClass().getName() + Collections.newList(this).toString();
        }
    }

    static class ZippingIterable<A,B> extends PossiblySizeAwareIterable<Map.Entry<A, B>> {
        private final Iterable<A> elements1;
        private final Iterable<B> elements2;

        public ZippingIterable(Iterable<A> elements1, Iterable<B> elements2) {
            this.elements1 = elements1;
            this.elements2 = elements2;
        }

        @Override
        public Iterator<Map.Entry<A, B>> iterator() {
            return new Iterator<Map.Entry<A, B>>() {
                Iterator<A> it1 = elements1.iterator();
                Iterator<B> it2 = elements2.iterator();
                @Override
                public boolean hasNext() {
                    return it1.hasNext() && it2.hasNext();
                }

                @Override
                public Map.Entry<A, B> next() {
                    return new Map.Entry<A, B>() {
                        private A key = it1.next();
                        private B value = it2.next();
                        @Override
                        public A getKey() {
                            return key;
                        }

                        @Override
                        public B getValue() {
                            return value;
                        }

                        @Override
                        public B setValue(Object value) {
                            throw new UnsupportedOperationException();
                        }
                    };
                }

                @Override
                public void remove() {
                    it1.remove();
                    it2.remove();
                }
            };
        }

        @Override
        public Option<Integer> size() {
            for (int a: Iterables.resolveSize(elements1)) {
                for (int b: Iterables.resolveSize(elements2)) {
                    return Some(Functional.min(a,b));
                }
            }
            return None();
        }
    }

    static class ConcatenatingIterable<T>  extends PossiblySizeAwareIterable<T> {
        private final Iterable<? extends Iterable<? extends T>> elements;

        public ConcatenatingIterable(Iterable<? extends Iterable<? extends T>> elements) {
            this.elements = elements;
        }

        @Override
        public Option<Integer> size() {
            int size = 0;
            for (Iterable<? extends T> it: elements) {
                Option<Integer> resolvedSize = Iterables.resolveSize(it);
                if (resolvedSize.isDefined()) {
                    size += resolvedSize.get();
                } else {
                    return None();
                }
            }
            return Some(size);
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                Iterator<Iterator<? extends T>> it = Functional.map(elements, new Transformer<Iterable<? extends T>, Iterator<? extends T>>() {
                    @Override
                    public Iterator<? extends T> transform(Iterable<? extends T> source) {
                        return source.iterator();
                    }
                }).iterator();

                private Iterator<? extends T> lastUsed = it.hasNext() ? it.next() : java.util.Collections.<T>emptyList().iterator();

                @Override
                public boolean hasNext() {
                    while (!lastUsed.hasNext() && it.hasNext()) {
                        lastUsed = it.next();
                    }
                    return lastUsed.hasNext();
                }

                @Override
                public T next() {
                    hasNext();
                    return lastUsed.next();
                }

                @Override
                public void remove() {
                    lastUsed.remove();
                }
            };
        }
    }

    static class FilteringIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<T> iterable;
        private final Function1<? super T, Boolean> filter;

        public FilteringIterable(Iterable<T> iterable, Function1<? super T, Boolean> filter) {
            this.iterable = iterable;
            this.filter = filter;
        }

        @Override
        public Option<Integer> size() {
            return None();
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private Object[] next;
                private Iterator<T> source = iterable.iterator();
                {
                    readNext();
                }

                @Override
                public boolean hasNext() {
                    return next != null;
                }

                private void readNext() {
                    next = null;
                    while (source.hasNext() && next == null) {
                        T n = source.next();
                        if (filter.apply(n)) {
                            next = new Object[]{n};
                        }
                    }
                }

                @Override
                public T next() {
                    if (next == null) {
                        throw new NoSuchElementException();
                    }
                    @SuppressWarnings("unchecked")
                    T ret = (T) next[0];
                    readNext();
                    return ret;
                }

                @Override
                public void remove() {
                    if (next == null) {
                        throw new IllegalStateException();
                    }
                    source.remove();
                }
            };
        }
    }

    static class TransformingIterable<S,T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<S> iterable;
        private final Function1<? super S, ? extends T> transformer;

        public TransformingIterable(Iterable<S> iterable, Function1<? super S, ? extends T> transformer) {
            this.iterable = iterable;
            this.transformer = transformer;
        }

        @Override
        public Option<Integer> size() {
            return Iterables.resolveSize(iterable);
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private Iterator<S> source = iterable.iterator();

                @Override
                public boolean hasNext() {
                    return source.hasNext();
                }

                @Override
                public T next() {
                    return transformer.apply(source.next());
                }

                @Override
                public void remove() {
                    source.remove();
                }
            };
        }
    }
    
    static class ReversingIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<T> iterable;

        public ReversingIterable(Iterable<T> iterable) {
            this.iterable = iterable;
        }

        @Override
        public Option<Integer> size() {
            return Iterables.resolveSize(iterable);
        }

        @Override
        public Iterator<T> iterator() {
            final List<T> list = iterable instanceof List ? (List<T>)iterable : newList(iterable);
            return new Iterator<T>() {
                ListIterator<T> underlying = list.listIterator(list.size());
                @Override
                public boolean hasNext() {
                    return underlying.hasPrevious();
                }

                @Override
                public T next() {
                    return underlying.previous();
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public String toString() {
            return Collections.newList(this).toString();
        }
    }
    
    static final class CharSequenceIterable extends PossiblySizeAwareIterable<Character> {
        private final CharSequence charSeq;

        public CharSequenceIterable(CharSequence charSeq) {
            this.charSeq = charSeq;
        }

        @Override
        public Iterator<Character> iterator() {
            return new Iterator<Character>() {
                int i = 0;
                @Override
                public boolean hasNext() {
                    return i < charSeq.length();
                }

                @Override
                public Character next() {
                    i++;
                    if (i >= charSeq.length()) {
                        throw new NoSuchElementException();
                    }
                    return charSeq.charAt(i);
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public Option<Integer> size() {
            return Some(charSeq.length());
        }
    }

    static Option<Integer> resolveSize(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return Some(((Collection<?>)iterable).size());
        }
        if (iterable instanceof PossiblySizeAwareIterable) {
            return ((PossiblySizeAwareIterable<?>)iterable).size();
        }
        return None();
    }
}
