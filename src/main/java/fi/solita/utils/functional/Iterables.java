package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.forall;
import static fi.solita.utils.functional.Functional.head;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.span;
import static fi.solita.utils.functional.Functional.take;
import static fi.solita.utils.functional.FunctionalA.max;
import static fi.solita.utils.functional.FunctionalA.min;
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
    public static final Transformer<Iterable<?>,Option<Long>> resolveSize = new Transformer<Iterable<?>,Option<Long>>() {
        private final Option<Long> SOME_ZERO = Some(0l);
        private final Option<Long> SOME_ONE = Some(1l);
        
        public Option<Long> transform(Iterable<?> source) {
            if (source instanceof Collection) {
                return Some((long)((Collection<?>)source).size());
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
        
        public Boolean transform(Iterator<?> source) {
            return source.hasNext();
        }
    };
    
    private static final Transformer<Iterator<Object>, Object> next = new Transformer<Iterator<Object>,Object>() {
        
        public Object transform(Iterator<Object> source) {
            return source.next();
        }
    };
    
    private static final Transformer<Iterable<Object>,Iterator<Object>> toIterator = new Transformer<Iterable<Object>,Iterator<Object>>() {
        
        public Iterator<Object> transform(Iterable<Object> source) {
            return source.iterator();
        }
    };
    
    static abstract class PossiblySizeAwareIterable<T> implements Iterable<T> {
        public abstract Option<Long> sizeEstimate();
        
        
        public String toString() {
            return getClass().getSimpleName() + Collections.newList(this).toString();
        }
    }
    
    static final class RangeIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Enumerable<T> enumeration;
        private final Option<T> from;
        private final Option<T> toInclusive;
        private final Option<Long> knownSize;
        
        public RangeIterable(Enumerable<T> enumeration, T from, Option<T> toInclusive) {
            this(enumeration, from, toInclusive, Option.<Long>None());
        }
        
        public RangeIterable(Enumerable<T> enumeration, T from, T toInclusive, long knownSize) {
            this(enumeration, from, Some(toInclusive), Some(knownSize));
        }

        private RangeIterable(Enumerable<T> enumeration, T from, Option<T> toInclusive, Option<Long> knownSize) {
            this.enumeration = enumeration;
            this.from = Some(from);
            this.toInclusive = toInclusive;
            this.knownSize = knownSize;
        }

        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private Option<T> nextToReturn = from;
                
                public boolean hasNext() {
                    return nextToReturn.isDefined();
                }

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

                
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public Option<Long> sizeEstimate() {
            return knownSize.isDefined() ? Some(knownSize.get()) : Option.<Long>None();
        }
    }

    static final class RepeatingIterable<T> extends PossiblySizeAwareIterable<T> {
        private final T value;
        private final Long amount;

        public RepeatingIterable(T value) {
            this.value = value;
            this.amount = null;
        }

        public RepeatingIterable(T value, long amount) {
            this.value = value;
            this.amount = amount;
        }

        
        public Option<Long> sizeEstimate() {
            return amount != null ? Some(amount) : Option.<Long>None();
        }

        
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int current = 0;
                
                public boolean hasNext() {
                    return amount == null || current < amount;
                }

                
                public T next() {
                    if (amount != null && current == amount) {
                        throw new NoSuchElementException();
                    }
                    current++;
                    return value;
                }

                
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
        
        
        public Iterator<Iterable<T>> iterator() {
            return new Iterator<Iterable<T>>() {
                @SuppressWarnings("unchecked")
                private final List<Iterator<T>> iterators = newList(map((Transformer<Iterable<T>,Iterator<T>>)(Object)toIterator, elements));
                
                
                public boolean hasNext() {
                    return !iterators.isEmpty() && forall(hasNext, iterators);
                }

                @SuppressWarnings("unchecked")
                
                public Iterable<T> next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return newList(map((Transformer<Iterator<T>, T>)(Object)next, iterators));
                }

                
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public Option<Long> sizeEstimate() {
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

        
        public Iterator<Tuple2<A, B>> iterator() {
            return new Iterator<Tuple2<A, B>>() {
                private final Iterator<A> it1 = elements1.iterator();
                private final Iterator<B> it2 = elements2.iterator();
                
                
                public boolean hasNext() {
                    return it1.hasNext() && it2.hasNext();
                }

                
                public Tuple2<A, B> next() {
                    return Tuple.of(it1.next(), it2.next());
                }

                
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public Option<Long> sizeEstimate() {
            for (long a: resolveSize.apply(elements1)) {
                for (long b: resolveSize.apply(elements2)) {
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

        
        public Option<Long> sizeEstimate() {
            long s = 0;
            for (Option<Long> size: map(resolveSize, elements)) {
                if (size.isDefined()) {
                    s += size.get();
                } else {
                    return Option.None();
                }
            }
            return Some(s);
        }

        
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                @SuppressWarnings("unchecked")
                private final Iterator<Iterator<? extends T>> it = Functional.map((Transformer<Iterable<? extends T>,Iterator<? extends T>>)(Object)toIterator, elements).iterator();

                private Iterator<? extends T> lastUsed = it.hasNext() ? it.next() : java.util.Collections.<T>emptyList().iterator();

                
                public boolean hasNext() {
                    while (!lastUsed.hasNext() && it.hasNext()) {
                        lastUsed = it.next();
                    }
                    return lastUsed.hasNext();
                }

                
                public T next() {
                    hasNext();
                    return lastUsed.next();
                }

                
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
        
        
        public Option<Long> sizeEstimate() {
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

        
        public Option<Long> sizeEstimate() {
            return None();
        }

        
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private boolean hasNext;
                private T next;
                private final Iterator<T> source = iterable.iterator();
                {
                    readNext();
                }

                
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

                
                public T next() {
                    if (!hasNext) {
                        throw new NoSuchElementException();
                    }
                    T ret = (T) next;
                    readNext();
                    return ret;
                }

                
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

        
        public Option<Long> sizeEstimate() {
            return resolveSize.apply(iterable);
        }

        
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private final Iterator<S> source = iterable.iterator();

                
                public boolean hasNext() {
                    return source.hasNext();
                }

                
                public T next() {
                    return transformer.apply(source.next());
                }

                
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

        
        public Option<Long> sizeEstimate() {
            return resolveSize.apply(iterable);
        }

        
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private final List<T> list = iterable instanceof List ? (List<T>)iterable : newList(iterable);
                private final ListIterator<T> underlying = list.listIterator(list.size());
                
                
                public boolean hasNext() {
                    return underlying.hasPrevious();
                }

                
                public T next() {
                    return underlying.previous();
                }

                
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
    
    static final class CharSequenceIterable extends PossiblySizeAwareIterable<Character> implements CharSequence {
        private final CharSequence chars;

        public CharSequenceIterable(CharSequence chars) {
            this.chars = chars;
        }
        
        
        public char charAt(int index) {
            return chars.charAt(index);
        }

        
        public int length() {
            return chars.length();
        }

        
        public CharSequence subSequence(int start, int end) {
            return chars.subSequence(start, end);
        }
        
        
        public Iterator<Character> iterator() {
            return new Iterator<Character>() {
                private int read = 0;
                
                public boolean hasNext() {
                    try {
                        chars.charAt(read);
                        return true;
                    } catch (IndexOutOfBoundsException e) {
                        return false;
                    }
                }

                
                public Character next() {
                    char ret = chars.charAt(read);
                    read++;
                    return ret;
                }

                
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public Option<Long> sizeEstimate() {
            if (chars instanceof String || chars instanceof StringBuilder || chars instanceof StringBuffer) {
                return Some((long)chars.length());
            } else {
                return None();
            }
        }
        
        
        public String toString() {
            return chars.toString();
        }
    }
    
    static class MemoizingCharSequenceIterable extends PossiblySizeAwareIterable<Character> implements CharSequence, Iterable<Character> {
        private final StringBuilder memo = new StringBuilder();
        private final Iterator<Character> it;
        
        public MemoizingCharSequenceIterable(Iterable<Character> chars) {
            it = chars.iterator();
        }
        
        private int resolveLength() {
            while (it.hasNext()) {
                memo.append(it.next());
            }
            return memo.length();
        }
        
        /**
         * Does not neccessarily check if <i>end</i> is bigger than <i>length()<i>
         */
        
        public CharSequence subSequence(int start, int end) {
            if (start < 0 || end < 0 || start > end || !it.hasNext() && end > length()) {
                throw new IndexOutOfBoundsException();
            }
            return new MemoizingCharSequenceIterable(take(end - start, FunctionalImpl.drop(start, this)));
        }
        
        
        public int length() {
            return resolveLength();
        }
        
        
        public char charAt(int index) {
            if (index < 0)
                throw new IndexOutOfBoundsException();
            Iterator<Character> it = iterator();
            try {
                for (int i = 0; i < index; ++i) {
                    it.next();
                }
                return it.next();
            } catch (NoSuchElementException e) {
                throw new IndexOutOfBoundsException(index + " was greater than " + length());
            }
        }

        
        public Iterator<Character> iterator() {
            return new Iterator<Character>() {
                private int read = 0;
                
                public boolean hasNext() {
                    if (read == memo.length() && it.hasNext()) {
                        memo.append(it.next());
                    }
                    return read < memo.length();
                }

                
                public Character next() {
                    if (read == memo.length()) {
                        memo.append(it.next());
                    }
                    char ret = memo.charAt(read);
                    read++;
                    return ret;
                }

                
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public Option<Long> sizeEstimate() {
            if (!it.hasNext()) {
                return Some((long)memo.length());
            } else {
                return None();
            }
        }
        
        
        public String toString() {
            resolveLength();
            return memo.toString();
        }
    }
    
    static final class SortingIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<T> iterable;
        private final Comparator<? super T> comparator;

        public SortingIterable(Iterable<T> iterable, Comparator<? super T> comparator) {
            this.iterable = iterable;
            this.comparator = comparator;
        }
        
        public Iterator<T> iterator() {
            long initialSize = resolveSize.apply(iterable).getOrElse(11l);
            if (initialSize == 0) {
                return java.util.Collections.<T>emptyList().iterator();
            }
            final PriorityQueue<T> queue = new PriorityQueue<T>((int)initialSize, comparator);
            if (iterable instanceof Collection) {
                queue.addAll((Collection<T>)iterable);
            } else {
                for (T t: iterable) {
                    queue.add(t);
                }
            }
            return new Iterator<T>() {
                
                public boolean hasNext() {
                    return !queue.isEmpty();
                }

                
                public T next() {
                    return queue.remove();
                }

                
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public Option<Long> sizeEstimate() {
            return resolveSize.apply(iterable);
        }
    }
    
    static final class TakingIterable<T> extends PossiblySizeAwareIterable<T> {
        private final Iterable<T> elements;
        private final long amount;

        public TakingIterable(Iterable<T> elements, long amount) {
            if (amount < 0) {
                amount = 0;
            }
            this.elements = elements;
            this.amount = amount;
        }
        
        
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private long left = amount;
                private final Iterator<T> it = elements.iterator();

                
                public boolean hasNext() {
                    return left > 0 && it.hasNext();
                }

                
                public T next() {
                    if (left == 0) {
                        throw new NoSuchElementException();
                    }
                    left--;
                    return it.next();
                }

                
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public Option<Long> sizeEstimate() {
            Option<Long> s = resolveSize.apply(elements);
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
        private final long amount;

        public DroppingIterable(Iterable<T> elements, long amount) {
            if (amount < 0) {
                amount = 0;
            }
            this.elements = elements;
            this.amount = amount;
        }
        
        
        public Iterator<T> iterator() {
            Iterator<T> it = elements.iterator();
            long left = amount;
            while (left > 0 && it.hasNext()) {
                it.next();
                left--;
            }
            return it;
        }

        
        public Option<Long> sizeEstimate() {
            Option<Long> s = resolveSize.apply(elements);
            if (s.isDefined()) {
                return Some(max(s.get() - amount, 0l));
            } else {
                return None();
            }
        }
    }
    
    static final class GroupingIterable<T> extends PossiblySizeAwareIterable<Iterable<T>> {
        private final Iterable<T> elements;
        private final Apply<Tuple2<T,T>, Boolean> comparator;

        public GroupingIterable(Iterable<T> elements, Apply<Tuple2<T,T>, Boolean> comparator) {
            this.elements = elements;
            this.comparator = comparator;
        }
        
        
        public Option<Long> sizeEstimate() {
            return None();
        }
        
        
        public Iterator<Iterable<T>> iterator() {
            return new Iterator<Iterable<T>>() {
                private Iterable<T> remaining = elements;
                
                
                public boolean hasNext() {
                    return !isEmpty(remaining);
                }

                
                public Iterable<T> next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    final T first = head(remaining);
                    Pair<Iterable<T>, Iterable<T>> s = span(new Predicate<T>() {
                        
                        public boolean accept(T candidate) {
                            return comparator.apply(Tuple.of(candidate, first));
                        }
                    }, remaining);
                    remaining = s.right;
                    return s.left;
                }

                
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
}
