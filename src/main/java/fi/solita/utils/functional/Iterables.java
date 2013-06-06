package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.forAll;
import static fi.solita.utils.functional.Functional.headOption;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.max;
import static fi.solita.utils.functional.Functional.min;
import static fi.solita.utils.functional.Functional.sort;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public abstract class Iterables {
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
            return getClass().getSimpleName() + Collections.newList(this).toString();
        }
    }
    
    static class TransposingIterable<T> extends PossiblySizeAwareIterable<Iterable<T>> {
        private final Iterable<? extends Iterable<T>> elements;

        public TransposingIterable(Iterable<? extends Iterable<T>> elements) {
            this.elements = elements;
        }
        
        private static Transformer<Iterator<?>,Boolean> hasNext = new Transformer<Iterator<?>,Boolean>() {
            @Override
            public Boolean transform(Iterator<?> source) {
                return source.hasNext();
            }
        };
        
        private Transformer<Iterator<T>,T> next = new Transformer<Iterator<T>,T>() {
            @Override
            public T transform(Iterator<T> source) {
                return source.next();
            }
        };
        
        @Override
        public Iterator<Iterable<T>> iterator() {
            return new Iterator<Iterable<T>>() {
                List<Iterator<T>> iterators = newList(map(elements, new Transformer<Iterable<T>,Iterator<T>>() {
                    @Override
                    public Iterator<T> transform(Iterable<T> source) {
                        return source.iterator();
                    }
                }));
                @Override
                public boolean hasNext() {
                    return !iterators.isEmpty() && forAll(iterators, hasNext);
                }

                @Override
                public Iterable<T> next() {
                    return newList(map(iterators, next));
                }

                @Override
                public void remove() {
                    for (Iterator<?> it: iterators) {
                        it.remove();
                    }
                }
            };
        }

        @Override
        public Option<Integer> size() {
            Iterable<Option<Integer>> resolvedSizes = filter(map(elements, resolveSize), new Predicate<Option<?>>() {
                @Override
                public boolean accept(Option<?> candidate) {
                    return candidate.isDefined();
                }
            });
            return headOption(sort(map(resolvedSizes, new Transformer<Option<Integer>,Integer>() {
                @Override
                public Integer transform(Option<Integer> source) {
                    return source.get();
                }
            })));
        }
    }

    static class ZippingIterable<A,B> extends PossiblySizeAwareIterable<Tuple2<A, B>> {
        private final Iterable<A> elements1;
        private final Iterable<B> elements2;

        public ZippingIterable(Iterable<A> elements1, Iterable<B> elements2) {
            this.elements1 = elements1;
            this.elements2 = elements2;
        }

        @Override
        public Iterator<Tuple2<A, B>> iterator() {
            return new Iterator<Tuple2<A, B>>() {
                Iterator<A> it1 = elements1.iterator();
                Iterator<B> it2 = elements2.iterator();
                @Override
                public boolean hasNext() {
                    return it1.hasNext() && it2.hasNext();
                }

                @Override
                public Tuple2<A, B> next() {
                    return Tuple.of(it1.next(), it2.next());
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
            for (int a: resolveSize.apply(elements1)) {
                for (int b: resolveSize.apply(elements2)) {
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
                Option<Integer> resolvedSize = resolveSize.apply(it);
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
        private final Apply<? super T, Boolean> filter;

        public FilteringIterable(Iterable<T> iterable, Apply<? super T, Boolean> filter) {
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
        private final Apply<? super S, ? extends T> transformer;

        public TransformingIterable(Iterable<S> iterable, Apply<? super S, ? extends T> transformer) {
            this.iterable = iterable;
            this.transformer = transformer;
        }

        @Override
        public Option<Integer> size() {
            return resolveSize.apply(iterable);
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
            return resolveSize.apply(iterable);
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
                    if (i >= charSeq.length()) {
                        throw new NoSuchElementException();
                    }
                    i++;
                    return charSeq.charAt(i-1);
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
    
    static final class SortingIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<T> iterable;
        private final Comparator<? super T> comparator;

        public SortingIterable(Iterable<T> iterable, Comparator<? super T> comparator) {
            this.iterable = iterable;
            this.comparator = comparator;
        }
        @Override
        public Iterator<T> iterator() {
            int initialSize = resolveSize.apply(iterable).getOrElse(11);
            if (initialSize == 0) {
                return java.util.Collections.<T>emptyList().iterator();
            }
            final PriorityQueue<T> queue = new PriorityQueue<T>(initialSize, comparator);
            for (T t: iterable) {
                queue.add(t);
            }
            return new Iterator<T>() {
                @Override
                public boolean hasNext() {
                    return !queue.isEmpty();
                }

                @Override
                public T next() {
                    return queue.remove();
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public Option<Integer> size() {
            return resolveSize.apply(iterable);
        }
    }
    
    static final class TakingIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<T> elements;
        private final int amount;

        public TakingIterable(Iterable<T> elements, int amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("amount must be >= 0");
            }
            this.elements = elements;
            this.amount = amount;
        }
        
        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                int left = amount;
                Iterator<T> it = elements.iterator();

                @Override
                public boolean hasNext() {
                    return left > 0 && it.hasNext();
                }

                @Override
                public T next() {
                    if (left == 0) {
                        throw new NoSuchElementException();
                    }
                    left--;
                    return it.next();
                }

                @Override
                public void remove() {
                    it.remove();
                }
            };
        }

        @Override
        public Option<Integer> size() {
            Option<Integer> s = resolveSize.apply(elements);
            if (s.isDefined()) {
                return Some(min(s.get(), amount));
            } else {
                // a good guess, since it's probably rare that 'take' is
                // called with an amount of significantly larger than the size
                // of the iterable. Right?
                return Some(amount);
            }
        }
    }
    
    static final class DroppingIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<T> elements;
        private final int amount;

        public DroppingIterable(Iterable<T> elements, int amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("amount must be >= 0");
            }
            this.elements = elements;
            this.amount = amount;
        }
        
        @Override
        public Iterator<T> iterator() {
            Iterator<T> it = elements.iterator();
            int left = amount;
            while (left > 0 && it.hasNext()) {
                it.next();
                left--;
            }
            return it;
        }

        @Override
        public Option<Integer> size() {
            Option<Integer> s = resolveSize.apply(elements);
            if (s.isDefined()) {
                return Some(max(s.get() - amount, 0));
            } else {
                return None();
            }
        }
    }
    
    public static Transformer<Iterable<?>,Option<Integer>> resolveSize = new Transformer<Iterable<?>,Option<Integer>>() {
        @Override
        public Option<Integer> transform(Iterable<?> source) {
            if (source instanceof Collection) {
                return Some(((Collection<?>)source).size());
            }
            if (source instanceof PossiblySizeAwareIterable) {
                return ((PossiblySizeAwareIterable<?>)source).size();
            }
            return None();
        }
    };
}
