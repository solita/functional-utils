package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.forAll;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.max;
import static fi.solita.utils.functional.Functional.min;
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
    public static final Transformer<Iterable<?>,Option<Integer>> resolveSize = new Transformer<Iterable<?>,Option<Integer>>() {
        private final Option<Integer> SOME_ZERO = Some(0);
        private final Option<Integer> SOME_ONE = Some(1);
        @Override
        public Option<Integer> transform(Iterable<?> source) {
            if (source instanceof Collection) {
                return Some(((Collection<?>)source).size());
            }
            if (source instanceof PossiblySizeAwareIterable) {
                return ((PossiblySizeAwareIterable<?>)source).sizeEstimate();
            }
            if (source instanceof Option) {
                return ((Option<?>)source).isDefined() ? SOME_ONE : SOME_ZERO;
            }
            return None();
        }
    };
    
    private static final Transformer<Iterator<?>,Boolean> hasNext = new Transformer<Iterator<?>,Boolean>() {
        @Override
        public Boolean transform(Iterator<?> source) {
            return source.hasNext();
        }
    };
    
    private static final Transformer<Iterator<Object>, Object> next = new Transformer<Iterator<Object>,Object>() {
        @Override
        public Object transform(Iterator<Object> source) {
            return source.next();
        }
    };
    
    private static final Transformer<Iterable<Object>,Iterator<Object>> toIterator = new Transformer<Iterable<Object>,Iterator<Object>>() {
        @Override
        public Iterator<Object> transform(Iterable<Object> source) {
            return source.iterator();
        }
    };
    
    static abstract class PossiblySizeAwareIterable<T> implements Iterable<T> {
        public abstract Option<Integer> sizeEstimate();
        
        @Override
        public String toString() {
            return getClass().getSimpleName() + Collections.newList(this).toString();
        }
    }
    
    static final class RangeIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Enumerable<T> enumeration;
        private final Option<T> from;
        private final Option<T> toInclusive;
        private final Option<Integer> knownSize;
        
        public RangeIterable(Enumerable<T> enumeration, T from, Option<T> toInclusive) {
            this(enumeration, from, toInclusive, Option.<Integer>None());
        }
        
        public RangeIterable(Enumerable<T> enumeration, T from, T toInclusive, int knownSize) {
            this(enumeration, from, Some(toInclusive), Some(knownSize));
        }

        private RangeIterable(Enumerable<T> enumeration, T from, Option<T> toInclusive, Option<Integer> knownSize) {
            this.enumeration = enumeration;
            this.from = Some(from);
            this.toInclusive = toInclusive;
            this.knownSize = knownSize;
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private Option<T> nextToReturn = from;
                
                @Override
                public boolean hasNext() {
                    return nextToReturn.isDefined();
                }

                @Override
                public T next() {
                    T ret;
                    try {
                        ret = nextToReturn.get();
                    } catch (RuntimeException e) {
                        throw new NoSuchElementException();
                    }
                    
                    if (toInclusive.isDefined() && ret.equals(toInclusive.get())) {
                        nextToReturn = None();
                    } else {
                        nextToReturn = enumeration.succ(ret);
                    }
                    
                    return ret;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public Option<Integer> sizeEstimate() {
            return knownSize;
        }
    }

    static final class RepeatingIterable<T> extends PossiblySizeAwareIterable<T> {
        private final T value;
        private final Integer amount;

        public RepeatingIterable(T value) {
            this.value = value;
            this.amount = null;
        }

        public RepeatingIterable(T value, int amount) {
            this.value = value;
            this.amount = amount;
        }

        @Override
        public Option<Integer> sizeEstimate() {
            return amount != null ? Some(amount) : Option.<Integer>None();
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int current = 0;
                @Override
                public boolean hasNext() {
                    return current < amount;
                }

                @Override
                public T next() {
                    if (current == amount) {
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

    static final class TransposingIterable<T> extends PossiblySizeAwareIterable<Iterable<T>> {
        private final Iterable<? extends Iterable<T>> elements;

        public TransposingIterable(Iterable<? extends Iterable<T>> elements) {
            this.elements = elements;
        }
        
        @Override
        public Iterator<Iterable<T>> iterator() {
            return new Iterator<Iterable<T>>() {
                @SuppressWarnings("unchecked")
                private final List<Iterator<T>> iterators = newList(map(elements, (Transformer<Iterable<T>,Iterator<T>>)(Object)toIterator));
                
                @Override
                public boolean hasNext() {
                    return !iterators.isEmpty() && forAll(iterators, hasNext);
                }

                @SuppressWarnings("unchecked")
                @Override
                public Iterable<T> next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return newList(map(iterators, (Transformer<Iterator<T>, T>)(Object)next));
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public Option<Integer> sizeEstimate() {
            return None();
        }
    }

    static final class ZippingIterable<A,B> extends PossiblySizeAwareIterable<Tuple2<A, B>> {
        private final Iterable<A> elements1;
        private final Iterable<B> elements2;

        public ZippingIterable(Iterable<A> elements1, Iterable<B> elements2) {
            this.elements1 = elements1;
            this.elements2 = elements2;
        }

        @Override
        public Iterator<Tuple2<A, B>> iterator() {
            return new Iterator<Tuple2<A, B>>() {
                private final Iterator<A> it1 = elements1.iterator();
                private final Iterator<B> it2 = elements2.iterator();
                
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
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public Option<Integer> sizeEstimate() {
            for (int a: resolveSize.apply(elements1)) {
                for (int b: resolveSize.apply(elements2)) {
                    return Some(Functional.min(a,b));
                }
            }
            return None();
        }
    }

    static class ConcatenatingIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<? extends Iterable<? extends T>> elements;

        public ConcatenatingIterable(Iterable<? extends Iterable<? extends T>> elements) {
            this.elements = elements;
        }

        @Override
        public Option<Integer> sizeEstimate() {
            int s = 0;
            for (Option<Integer> size: map(elements, resolveSize)) {
                if (size.isDefined()) {
                    s += size.get();
                } else {
                    return Option.None();
                }
            }
            return Some(s);
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                @SuppressWarnings("unchecked")
                private final Iterator<Iterator<? extends T>> it = newList(Functional.map(elements, (Transformer<Iterable<? extends T>,Iterator<? extends T>>)(Object)toIterator)).iterator();

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
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
    
    static final class FlatteningIterable<T> extends ConcatenatingIterable<T> {
        public FlatteningIterable(Iterable<? extends Iterable<? extends T>> elements) {
            super(elements);
        }
        
        @Override
        public Option<Integer> sizeEstimate() {
            return None();
        }
    }

    static final class FilteringIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<T> iterable;
        private final Apply<? super T, Boolean> filter;

        public FilteringIterable(Iterable<T> iterable, Apply<? super T, Boolean> filter) {
            this.iterable = iterable;
            this.filter = filter;
        }

        @Override
        public Option<Integer> sizeEstimate() {
            return None();
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private boolean hasNext;
                private T next;
                private final Iterator<T> source = iterable.iterator();
                {
                    readNext();
                }

                @Override
                public boolean hasNext() {
                    return hasNext;
                }

                private void readNext() {
                    hasNext = false;
                    while (!hasNext && source.hasNext()) {
                        T n = source.next();
                        if (filter.apply(n)) {
                            next = n;
                            hasNext = true;
                        }
                    }
                }

                @Override
                public T next() {
                    if (!hasNext) {
                        throw new NoSuchElementException();
                    }
                    T ret = (T) next;
                    readNext();
                    return ret;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    static final class TransformingIterable<S,T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<S> iterable;
        private final Apply<? super S, ? extends T> transformer;

        public TransformingIterable(Iterable<S> iterable, Apply<? super S, ? extends T> transformer) {
            this.iterable = iterable;
            this.transformer = transformer;
        }

        @Override
        public Option<Integer> sizeEstimate() {
            return resolveSize.apply(iterable);
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private final Iterator<S> source = iterable.iterator();

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
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
    
    static final class ReversingIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<T> iterable;

        public ReversingIterable(Iterable<T> iterable) {
            this.iterable = iterable;
        }

        @Override
        public Option<Integer> sizeEstimate() {
            return resolveSize.apply(iterable);
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private final List<T> list = iterable instanceof List ? (List<T>)iterable : newList(iterable);
                private final ListIterator<T> underlying = list.listIterator(list.size());
                
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
    }
    
    static final class CharSequenceIterable extends PossiblySizeAwareIterable<Character> {
        private final CharSequence charSeq;

        public CharSequenceIterable(CharSequence charSeq) {
            this.charSeq = charSeq;
        }

        @Override
        public Iterator<Character> iterator() {
            return new Iterator<Character>() {
                private int i = 0;
                @Override
                public boolean hasNext() {
                    return i < charSeq.length();
                }

                @Override
                public Character next() {
                    i++;
                    try {
                        return charSeq.charAt(i-1);
                    } catch (IndexOutOfBoundsException e) {
                        throw new NoSuchElementException();
                    }
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public Option<Integer> sizeEstimate() {
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
            if (iterable instanceof Collection) {
                queue.addAll((Collection<T>)iterable);
            } else {
                for (T t: iterable) {
                    queue.add(t);
                }
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
        public Option<Integer> sizeEstimate() {
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
                private int left = amount;
                private final Iterator<T> it = elements.iterator();

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
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public Option<Integer> sizeEstimate() {
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
        public Option<Integer> sizeEstimate() {
            Option<Integer> s = resolveSize.apply(elements);
            if (s.isDefined()) {
                return Some(max(s.get() - amount, 0));
            } else {
                return None();
            }
        }
    }
}
