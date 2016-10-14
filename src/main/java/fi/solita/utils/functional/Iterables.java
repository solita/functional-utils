package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newListOfSize;
import static fi.solita.utils.functional.Functional.drop;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.forall;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.take;
import static fi.solita.utils.functional.Functional.takeWhile;
import static fi.solita.utils.functional.FunctionalA.max;
import static fi.solita.utils.functional.FunctionalA.min;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.isNull;
import static fi.solita.utils.functional.Predicates.not;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public abstract class Iterables {
    // cache most used Some-object to reduce object allocation
    @SuppressWarnings("unchecked")
    private static final Option<Long>[] someCache = new Option[4096];
    static {
        for (int i = 0; i < someCache.length; ++i) {
            someCache[i] = Some((long)i);
        }
    }
    
    private static final Option<Long> wrapSome(long size) {
        return size < someCache.length ? someCache[(int)size] : Some(size);
    }
    
    public static final Transformer<Iterable<?>,Option<Long>> resolveSize = new Transformer<Iterable<?>,Option<Long>>() {
        public final Option<Long> transform(Iterable<?> source) {
            if (source instanceof Collection) {
                return wrapSome((long)((Collection<?>)source).size());
            }
            if (source instanceof PossiblySizeAwareIterable) {
                return ((PossiblySizeAwareIterable<?>)source).size();
            }
            if (source instanceof Option) {
                return ((Option<?>)source).isDefined() ? wrapSome(1) : wrapSome(0);
            }
            return None();
        }
    };
    
    private static final Transformer<Iterator<?>,Boolean> hasNext = new Transformer<Iterator<?>,Boolean>() {
        public final Boolean transform(Iterator<?> source) {
            return source.hasNext();
        }
    };
    
    private static final Transformer<Iterator<Object>, Object> next = new Transformer<Iterator<Object>,Object>() {
        public final Object transform(Iterator<Object> source) {
            return source.next();
        }
    };
    
    private static final Transformer<Iterable<Object>,Iterator<Object>> toIterator = new Transformer<Iterable<Object>,Iterator<Object>>() {
        public final Iterator<Object> transform(Iterable<Object> source) {
            return source.iterator();
        }
    };
    
    private static final Transformer<Iterable<Object>,Iterator<Object>> toIteratorForced = new Transformer<Iterable<Object>,Iterator<Object>>() {
        public final Iterator<Object> transform(Iterable<Object> source) {
            if (source instanceof ForceableIterable) {
                ((ForceableIterable) source).completeIterationNeeded();
            }
            return source.iterator();
        }
    };
    
    static interface PossiblySizeAwareIterable<T> extends Iterable<T> {
        public abstract Option<Long> size();
    }
    
    static interface ForceableIterable {
        public abstract void completeIterationNeeded();
    }
    
    static abstract class MyIterable<T> implements Iterable<T>, PossiblySizeAwareIterable<T> {
        public String toString() {
            return getClass().getSimpleName() + Collections.newList(this).toString();
        }
    }
    
    static final class RangeIterable<T> extends MyIterable<T> {
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

        public final Iterator<T> iterator() {
            return new Iterator<T>() {
                private Option<T> nextToReturn = from;
                
                public final boolean hasNext() {
                    return nextToReturn.isDefined();
                }

                public final T next() {
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

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public final Option<Long> size() {
            return knownSize.isDefined() ? wrapSome(knownSize.get()) : Option.<Long>None();
        }
    }

    static final class RepeatingIterable<T> extends MyIterable<T> {
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

        
        public final Option<Long> size() {
            return amount != null ? wrapSome(amount) : Option.<Long>None();
        }

        
        public final Iterator<T> iterator() {
            return new Iterator<T>() {
                private int current = 0;
                
                public final boolean hasNext() {
                    return amount == null || current < amount;
                }

                
                public final T next() {
                    if (amount != null && current == amount) {
                        throw new NoSuchElementException();
                    }
                    current++;
                    return value;
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    static final class TransposingIterable<T> extends MyIterable<Iterable<T>> implements ForceableIterable {
        private final Iterable<? extends Iterable<T>> elements;
        private boolean force = false;

        public TransposingIterable(Iterable<? extends Iterable<T>> elements) {
            this.elements = elements;
        }
        
        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Iterator<Iterable<T>> iterator() {
            return new Iterator<Iterable<T>>() {
                @SuppressWarnings("unchecked")
                private final List<Iterator<T>> iterators = newList(map((Transformer<Iterable<T>,Iterator<T>>)(Object)(force ? toIteratorForced : toIterator), elements));
                
                
                public final boolean hasNext() {
                    return !iterators.isEmpty() && forall(hasNext, iterators);
                }

                @SuppressWarnings("unchecked")
                
                public final Iterable<T> next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return newList(map((Transformer<Iterator<T>, T>)(Object)next, iterators));
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public final Option<Long> size() {
            return None();
        }
    }

    static final class ZippingIterable<A,B> extends MyIterable<Tuple2<A, B>> implements ForceableIterable {
        private final Iterable<A> elements1;
        private final Iterable<B> elements2;
        private boolean force = false;

        public ZippingIterable(Iterable<A> elements1, Iterable<B> elements2) {
            this.elements1 = elements1;
            this.elements2 = elements2;
        }

        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Iterator<Tuple2<A, B>> iterator() {
            if (force) {
                if (elements1 instanceof ForceableIterable) {
                    ((ForceableIterable)elements1).completeIterationNeeded();
                }
                if (elements2 instanceof ForceableIterable) {
                    ((ForceableIterable)elements2).completeIterationNeeded();
                }
            }
            return new Iterator<Tuple2<A, B>>() {
                private final Iterator<A> it1 = elements1.iterator();
                private final Iterator<B> it2 = elements2.iterator();
                
                
                public final boolean hasNext() {
                    return it1.hasNext() && it2.hasNext();
                }

                
                public final Tuple2<A, B> next() {
                    return Tuple.of(it1.next(), it2.next());
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public final Option<Long> size() {
            for (long a: resolveSize.apply(elements1)) {
                for (long b: resolveSize.apply(elements2)) {
                    return wrapSome(Math.min(a,b));
                }
            }
            return None();
        }
    }

    static class ConcatenatingIterable<T> extends MyIterable<T> implements ForceableIterable {
        private final Iterable<? extends Iterable<? extends T>> elements;
        private boolean force = false;

        public ConcatenatingIterable(Iterable<? extends Iterable<? extends T>> elements) {
            if (elements == null) {
                throw new IllegalArgumentException();
            } else if (elements instanceof Collection) {
                this.elements = flattenNestedConcats((Collection<? extends Iterable<? extends T>>)elements);
            } else {
                this.elements = elements;
            }
        }
        
        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        @SuppressWarnings("unchecked")
        private final Collection<? extends Iterable<? extends T>> flattenNestedConcats(Collection<? extends Iterable<? extends T>> elements) {
            List<Iterable<? extends T>> ret = newList();
            for (Iterable<? extends T> es: elements) {
                if (es instanceof ConcatenatingIterable && ((ConcatenatingIterable<T>) es).elements instanceof Collection) {
                    ret.addAll(flattenNestedConcats((Collection<? extends Iterable<? extends T>>)((ConcatenatingIterable<T>)es).elements));
                } else {
                    ret.add(es);
                }
            }
            return ret;
        }

        public Option<Long> size() {
            long s = 0;
            for (Option<Long> size: map(resolveSize, elements)) {
                if (size.isDefined()) {
                    s += size.get();
                } else {
                    return Option.None();
                }
            }
            return wrapSome(s);
        }

        
        public final Iterator<T> iterator() {
            return new Iterator<T>() {
                @SuppressWarnings("unchecked")
                private final Iterator<Iterator<? extends T>> it = Functional.map((Transformer<Iterable<? extends T>,Iterator<? extends T>>)(Object)(force ? toIteratorForced : toIterator), elements).iterator();

                private Iterator<? extends T> lastUsed = it.hasNext() ? it.next() : java.util.Collections.<T>emptyList().iterator();

                
                public final boolean hasNext() {
                    while (!lastUsed.hasNext() && it.hasNext()) {
                        lastUsed = it.next();
                    }
                    return lastUsed.hasNext();
                }

                
                public final T next() {
                    hasNext();
                    return lastUsed.next();
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
    
    static final class FlatteningIterable<T> extends ConcatenatingIterable<T> {
        public FlatteningIterable(Iterable<? extends Iterable<? extends T>> elements) {
            super(filter(not(isNull()), elements));
        }
        
        
        public Option<Long> size() {
            return None();
        }
    }

    static final class FilteringIterable<T> extends MyIterable<T> implements ForceableIterable {
        private final Iterable<T> iterable;
        private final Apply<? super T, Boolean> filter;
        private boolean force = false;

        public FilteringIterable(Iterable<T> iterable, Apply<? super T, Boolean> filter) {
            this.iterable = iterable;
            this.filter = filter;
        }

        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Option<Long> size() {
            return None();
        }

        
        public final Iterator<T> iterator() {
            if (force && iterable instanceof ForceableIterable) {
                ((ForceableIterable)iterable).completeIterationNeeded();
            }
            return new Iterator<T>() {
                private boolean hasNext;
                private T next;
                private final Iterator<T> source = iterable.iterator();
                {
                    readNext();
                }

                
                public final boolean hasNext() {
                    return hasNext;
                }

                private final void readNext() {
                    hasNext = false;
                    while (!hasNext && source.hasNext()) {
                        T n = source.next();
                        if (filter.apply(n)) {
                            next = n;
                            hasNext = true;
                        }
                    }
                }

                
                public final T next() {
                    if (!hasNext) {
                        throw new NoSuchElementException();
                    }
                    T ret = (T) next;
                    readNext();
                    return ret;
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    static final class TransformingIterable<S,T> extends MyIterable<T> implements ForceableIterable {
        private final Iterable<S> iterable;
        private final Apply<? super S, ? extends T> transformer;
        private boolean force = false;

        public TransformingIterable(Iterable<S> iterable, Apply<? super S, ? extends T> transformer) {
            if (iterable == null || transformer == null) {
                throw new IllegalArgumentException();
            }
            this.iterable = iterable;
            this.transformer = transformer;
        }

        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Option<Long> size() {
            return resolveSize.apply(iterable);
        }

        
        public final Iterator<T> iterator() {
            if (force && iterable instanceof ForceableIterable) {
                ((ForceableIterable)iterable).completeIterationNeeded();
            }
            return new Iterator<T>() {
                private final Iterator<S> source = iterable.iterator();

                
                public final boolean hasNext() {
                    return source.hasNext();
                }

                
                public final T next() {
                    return transformer.apply(source.next());
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
    
    static final class ReversingIterable<T> extends MyIterable<T> implements ForceableIterable {
        private final Iterable<T> iterable;
        private boolean force = false;

        public ReversingIterable(Iterable<T> iterable) {
            this.iterable = iterable;
        }

        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Option<Long> size() {
            return resolveSize.apply(iterable);
        }

        
        public final Iterator<T> iterator() {
            if (force && iterable instanceof ForceableIterable) {
                ((ForceableIterable)iterable).completeIterationNeeded();
            }
            return new Iterator<T>() {
                private final List<T> list = iterable instanceof List ? (List<T>)iterable : newList(iterable);
                private final ListIterator<T> underlying = list.listIterator(list.size());
                
                
                public final boolean hasNext() {
                    return underlying.hasPrevious();
                }

                
                public final T next() {
                    return underlying.previous();
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
    
    static final class CharSequenceIterable extends MyIterable<Character> implements CharSequence {
        private final CharSequence chars;

        public CharSequenceIterable(CharSequence chars) {
            this.chars = chars;
        }
        
        
        public final char charAt(int index) {
            return chars.charAt(index);
        }

        
        public final int length() {
            return chars.length();
        }

        
        public final CharSequence subSequence(int start, int end) {
            return chars.subSequence(start, end);
        }
        
        
        public final Iterator<Character> iterator() {
            return new Iterator<Character>() {
                private int read = 0;
                
                public final boolean hasNext() {
                    try {
                        chars.charAt(read);
                        return true;
                    } catch (IndexOutOfBoundsException e) {
                        return false;
                    }
                }

                
                public final Character next() {
                    char ret = chars.charAt(read);
                    read++;
                    return ret;
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public final Option<Long> size() {
            if (chars instanceof String || chars instanceof StringBuilder || chars instanceof StringBuffer) {
                return wrapSome((long)chars.length());
            } else {
                return None();
            }
        }
        
        
        public final String toString() {
            return chars.toString();
        }
    }
    
    static final class MemoizingCharSequenceIterable extends MyIterable<Character> implements CharSequence, Iterable<Character> {
        private final StringBuilder memo = new StringBuilder();
        private final Iterable<Character> iterable;
        private Iterator<Character> it;
        
        public MemoizingCharSequenceIterable(Iterable<Character> chars) {
            iterable = chars;
        }
        
        private final int resolveLength() {
            while (it.hasNext()) {
                memo.append(it.next());
            }
            return memo.length();
        }
        
        /**
         * Does not neccessarily check if <i>end</i> is bigger than <i>length()<i>
         */
        
        public final CharSequence subSequence(int start, int end) {
            if (it == null) {
                it = iterable.iterator();
            }
            if (start < 0 || end < 0 || start > end || !it.hasNext() && end > length()) {
                throw new IndexOutOfBoundsException();
            }
            return new MemoizingCharSequenceIterable(take(end - start, FunctionalImpl.drop(start, this)));
        }
        
        
        public final int length() {
            if (it == null) {
                it = iterable.iterator();
            }
            return resolveLength();
        }
        
        
        public final char charAt(int index) {
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

        
        public final Iterator<Character> iterator() {
            return new Iterator<Character>() {
                private int read = 0;
                
                public final boolean hasNext() {
                    if (it == null) {
                        it = iterable.iterator();
                    }
                    if (read == memo.length() && it.hasNext()) {
                        memo.append(it.next());
                    }
                    return read < memo.length();
                }

                
                public final Character next() {
                    if (it == null) {
                        it = iterable.iterator();
                    }
                    if (read == memo.length()) {
                        memo.append(it.next());
                    }
                    char ret = memo.charAt(read);
                    read++;
                    return ret;
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public final Option<Long> size() {
            if (it == null) {
                it = iterable.iterator();
            }
            if (!it.hasNext()) {
                return wrapSome((long)memo.length());
            } else {
                return None();
            }
        }
        
        
        public final String toString() {
            if (it == null) {
                it = iterable.iterator();
            }
            resolveLength();
            return memo.toString();
        }
    }
    
    static final class SortingIterable<T> extends MyIterable<T> implements ForceableIterable {
        private Iterable<T> iterable;
        private final Comparator<? super T> comparator;
        private boolean force = false;

        public SortingIterable(Iterable<T> iterable, Comparator<? super T> comparator) {
            this.iterable = iterable;
            this.comparator = comparator;
        }
        
        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Iterator<T> iterator() {
            if (force && iterable instanceof ForceableIterable) {
                ((ForceableIterable)iterable).completeIterationNeeded();
            }
            long initialSize = resolveSize.apply(iterable).getOrElse(11l);
            if (initialSize == 0) {
                return java.util.Collections.<T>emptyList().iterator();
            }
            
            if (force) {
                List<T> sorted = null;
                for (long size: Iterables.resolveSize.apply(iterable)) {
                    sorted = newListOfSize(size);
                }
                if (sorted == null) {
                    sorted = newList();
                }

                if (iterable instanceof Collection) {
                    sorted.addAll((Collection<? extends T>) iterable);
                } else {
                    for (T t: iterable) {
                        sorted.add(t);
                    }
                }
                java.util.Collections.sort(sorted, comparator);
                return sorted.iterator();
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
                
                public final boolean hasNext() {
                    return !queue.isEmpty();
                }

                
                public final T next() {
                    return queue.remove();
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public final Option<Long> size() {
            return resolveSize.apply(iterable);
        }
    }
    
    static final class TakingIterable<T> extends MyIterable<T> implements ForceableIterable {
        private final Iterable<T> elements;
        private final long amount;
        private boolean force = false;

        public TakingIterable(Iterable<T> elements, long amount) {
            if (amount < 0) {
                amount = 0;
            }
            this.elements = elements;
            this.amount = amount;
        }
        
        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Iterator<T> iterator() {
            if (force && elements instanceof ForceableIterable) {
                ((ForceableIterable)elements).completeIterationNeeded();
            }
            return new Iterator<T>() {
                private long left = amount;
                private final Iterator<T> it = elements.iterator();

                
                public final boolean hasNext() {
                    return left > 0 && it.hasNext();
                }

                
                public final T next() {
                    if (left == 0) {
                        throw new NoSuchElementException();
                    }
                    left--;
                    return it.next();
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public final Option<Long> size() {
            Option<Long> s = resolveSize.apply(elements);
            if (s.isDefined()) {
                return wrapSome(min(s.get(), amount));
            } else {
                return None();
            }
        }
    }
    
    static final class DroppingIterable<T> extends MyIterable<T> implements ForceableIterable {
        private final Iterable<T> elements;
        private final long amount;
        private boolean force = false;

        public DroppingIterable(Iterable<T> elements, long amount) {
            if (amount < 0) {
                amount = 0;
            }
            this.elements = elements;
            this.amount = amount;
        }
        
        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Iterator<T> iterator() {
            if (force && elements instanceof ForceableIterable) {
                ((ForceableIterable)elements).completeIterationNeeded();
            }
            Iterator<T> it = elements.iterator();
            long left = amount;
            while (left > 0 && it.hasNext()) {
                it.next();
                left--;
            }
            return it;
        }

        
        public final Option<Long> size() {
            Option<Long> s = resolveSize.apply(elements);
            if (s.isDefined()) {
                return wrapSome(max(s.get() - amount, 0l));
            } else {
                return None();
            }
        }
    }
    
    static final class GroupingIterable<T> extends MyIterable<Iterable<T>> implements ForceableIterable {
        private final Iterable<T> elements;
        private final Apply<Map.Entry<T,T>, Boolean> comparator;
        private boolean force = false;

        public GroupingIterable(Iterable<T> elements, Apply<Map.Entry<T,T>, Boolean> comparator) {
            this.elements = elements;
            this.comparator = comparator;
        }
        
        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Option<Long> size() {
            return None();
        }
        
        
        public final Iterator<Iterable<T>> iterator() {
            if (force && elements instanceof ForceableIterable) {
                ((ForceableIterable)elements).completeIterationNeeded();
            }
            return new Iterator<Iterable<T>>() {
                private Iterable<T> remaining = elements;
                
                
                public final boolean hasNext() {
                    return !isEmpty(remaining);
                }

                
                @SuppressWarnings("unchecked")
                public final Iterable<T> next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    final Object[] prev = { GroupingIterable.class };
                    List<T> s = newList(takeWhile(new Predicate<T>() {
                        public boolean accept(T candidate) {
                            if (prev[0] == GroupingIterable.class) {
                                prev[0] = candidate;
                                return true;
                            }
                            boolean ret = comparator.apply(Tuple.of((T)prev[0], candidate));
                            prev[0] = candidate;
                            return ret;
                        }
                    }, remaining));
                    remaining = drop(s.size(), remaining);
                    return s;
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
}
