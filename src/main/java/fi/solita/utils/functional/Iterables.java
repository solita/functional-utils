package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMutableList;
import static fi.solita.utils.functional.Collections.newMutableListOfSize;
import static fi.solita.utils.functional.Collections.newMutableSet;
import static fi.solita.utils.functional.Functional.drop;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.forall;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.take;
import static fi.solita.utils.functional.Functional.takeWhile;
import static fi.solita.utils.functional.FunctionalA.max;
import static fi.solita.utils.functional.FunctionalA.min;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public abstract class Iterables {
    // cache most used Some-object to reduce object allocation
    @SuppressWarnings("unchecked")
    private static final Optional<Long>[] someCache = new Optional[4096];
    static {
        for (int i = 0; i < someCache.length; ++i) {
            someCache[i] = Optional.of((long)i);
        }
    }
    
    private static final Optional<Long> wrapSome(long size) {
        return size < someCache.length ? someCache[(int)size] : Optional.of(size);
    }
    
    public static final java.util.function.Function<Iterable<?>,Optional<Long>> resolveSize = new java.util.function.Function<Iterable<?>,Optional<Long>>() {
        public final Optional<Long> apply(Iterable<?> source) {
            if (source instanceof Collection) {
                return wrapSome((long)((Collection<?>)source).size());
            }
            if (source instanceof PossiblySizeAwareIterable) {
                return ((PossiblySizeAwareIterable<?>)source).size();
            }
            return Optional.empty();
        }
    };
    
    private static final java.util.function.Predicate<Iterator<?>> hasNext = new java.util.function.Predicate<Iterator<?>>() {
        public final boolean test(Iterator<?> source) {
            return source.hasNext();
        }
    };
    
    private static final java.util.function.Function<Iterator<Object>, Object> next = new java.util.function.Function<Iterator<Object>,Object>() {
        public final Object apply(Iterator<Object> source) {
            return source.next();
        }
    };
    
    private static final java.util.function.Function<Iterable<Object>,Iterator<Object>> toIterator = new java.util.function.Function<Iterable<Object>,Iterator<Object>>() {
        public final Iterator<Object> apply(Iterable<Object> source) {
            return source.iterator();
        }
    };
    
    private static final java.util.function.Function<Iterable<Object>,Iterator<Object>> toIteratorForced = new java.util.function.Function<Iterable<Object>,Iterator<Object>>() {
        public final Iterator<Object> apply(Iterable<Object> source) {
            if (source instanceof ForceableIterable) {
                ((ForceableIterable) source).completeIterationNeeded();
            }
            return source.iterator();
        }
    };
    
    static interface PossiblySizeAwareIterable<T> extends Iterable<T> {
        public abstract Optional<Long> size();
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
        private final Optional<T> from;
        private final Optional<T> toInclusive;
        private final Optional<Long> knownSize;
        
        public RangeIterable(Enumerable<T> enumeration, T from, Optional<T> toInclusive) {
            this(enumeration, from, toInclusive, Optional.empty());
        }
        
        public RangeIterable(Enumerable<T> enumeration, T from, T toInclusive, long knownSize) {
            this(enumeration, from, Optional.of(toInclusive), wrapSome(knownSize));
        }

        private RangeIterable(Enumerable<T> enumeration, T from, Optional<T> toInclusive, Optional<Long> knownSize) {
            this.enumeration = enumeration;
            this.from = Optional.of(from);
            this.toInclusive = toInclusive;
            this.knownSize = knownSize;
        }

        public final Iterator<T> iterator() {
            return new Iterator<T>() {
                private Optional<T> nextToReturn = from;
                
                public final boolean hasNext() {
                    return nextToReturn.isPresent();
                }

                public final T next() {
                    T ret;
                    try {
                        ret = nextToReturn.get();
                    } catch (RuntimeException e) {
                        throw new NoSuchElementException();
                    }
                    
                    if (toInclusive.isPresent() && ret.equals(toInclusive.get())) {
                        nextToReturn = Optional.empty();
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

        
        public final Optional<Long> size() {
            return knownSize.isPresent() ? wrapSome(knownSize.get()) : Optional.empty();
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

        
        public final Optional<Long> size() {
            return amount != null ? wrapSome(amount) : Optional.empty();
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
                private final List<Iterator<T>> iterators = newList(map((java.util.function.Function<Iterable<T>,Iterator<T>>)(Object)(force ? toIteratorForced : toIterator), elements));
                
                
                public final boolean hasNext() {
                    return !iterators.isEmpty() && forall(hasNext, iterators);
                }

                @SuppressWarnings("unchecked")
                
                public final Iterable<T> next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return newList(map((java.util.function.Function<Iterator<T>, T>)(Object)next, iterators));
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public final Optional<Long> size() {
            return Optional.empty();
        }
    }

    static final class ZippingIterable<A,B> extends MyIterable<Pair<A, B>> implements ForceableIterable {
        private final Iterable<? extends A> elements1;
        private final Iterable<? extends B> elements2;
        private boolean force = false;

        public ZippingIterable(Iterable<? extends A> elements1, Iterable<? extends B> elements2) {
            this.elements1 = elements1;
            this.elements2 = elements2;
        }

        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Iterator<Pair<A, B>> iterator() {
            if (force) {
                if (elements1 instanceof ForceableIterable) {
                    ((ForceableIterable)elements1).completeIterationNeeded();
                }
                if (elements2 instanceof ForceableIterable) {
                    ((ForceableIterable)elements2).completeIterationNeeded();
                }
            }
            return new Iterator<Pair<A, B>>() {
                private final Iterator<? extends A> it1 = elements1.iterator();
                private final Iterator<? extends B> it2 = elements2.iterator();
                
                
                public final boolean hasNext() {
                    return it1.hasNext() && it2.hasNext();
                }

                
                public final Pair<A, B> next() {
                    return Pair.of(it1.next(), it2.next());
                }

                
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        
        public final Optional<Long> size() {
            for (long a: it(resolveSize.apply(elements1))) {
                for (long b: it(resolveSize.apply(elements2))) {
                    return wrapSome(Math.min(a,b));
                }
            }
            return Optional.empty();
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
            List<Iterable<? extends T>> ret = newMutableList();
            for (Iterable<? extends T> es: elements) {
                if (es instanceof ConcatenatingIterable && ((ConcatenatingIterable<T>) es).elements instanceof Collection) {
                    ret.addAll(flattenNestedConcats((Collection<? extends Iterable<? extends T>>)((ConcatenatingIterable<T>)es).elements));
                } else {
                    ret.add(es);
                }
            }
            return ret;
        }

        public Optional<Long> size() {
            long s = 0;
            for (Optional<Long> size: map(resolveSize, elements)) {
                if (size.isPresent()) {
                    s += size.get();
                } else {
                    return Optional.empty();
                }
            }
            return wrapSome(s);
        }

        
        public final Iterator<T> iterator() {
            return new Iterator<T>() {
                @SuppressWarnings("unchecked")
                private final Iterator<Iterator<? extends T>> it = Functional.map((java.util.function.Function<Iterable<? extends T>,Iterator<? extends T>>)(Object)(force ? toIteratorForced : toIterator), elements).iterator();

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
            super(filter(Objects::nonNull, elements));
        }
        
        
        public Optional<Long> size() {
            return Optional.empty();
        }
    }
    
    static class DistinctIterable<T> extends MyIterable<T> implements ForceableIterable {
        private final Iterable<? extends T> iterable;
        private boolean force = false;

        public DistinctIterable(Iterable<? extends T> iterable) {
            this.iterable = iterable;
        }
        
        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Optional<Long> size() {
            return Optional.empty();
        }
        
        public Iterator<T> iterator() {
            final Set<T> visited = newMutableSet();
            if (force && iterable instanceof ForceableIterable) {
                ((ForceableIterable)iterable).completeIterationNeeded();
            }
            return new Iterator<T>() {
                private boolean hasNext;
                private T next;
                private final Iterator<? extends T> source = iterable.iterator();
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
                        if (visited.add(n)) {
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

    static final class FilteringIterable<T> extends MyIterable<T> implements ForceableIterable {
        private final Iterable<? extends T> iterable;
        private final java.util.function.Predicate<? super T> filter;
        private boolean force = false;

        public FilteringIterable(Iterable<? extends T> iterable, java.util.function.Predicate<? super T> filter) {
            this.iterable = iterable;
            this.filter = filter;
        }

        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Optional<Long> size() {
            return Optional.empty();
        }

        
        public final Iterator<T> iterator() {
            if (force && iterable instanceof ForceableIterable) {
                ((ForceableIterable)iterable).completeIterationNeeded();
            }
            return new Iterator<T>() {
                private boolean hasNext;
                private T next;
                private final Iterator<? extends T> source = iterable.iterator();
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
                        if (filter.test(n)) {
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
        private final Iterable<? extends S> iterable;
        private final java.util.function.Function<? super S, ? extends T> transformer;
        private boolean force = false;

        public TransformingIterable(Iterable<? extends S> iterable, java.util.function.Function<? super S, ? extends T> transformer) {
            if (iterable == null || transformer == null) {
                throw new IllegalArgumentException();
            }
            this.iterable = iterable;
            this.transformer = transformer;
        }

        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Optional<Long> size() {
            return resolveSize.apply(iterable);
        }

        
        public final Iterator<T> iterator() {
            if (force && iterable instanceof ForceableIterable) {
                ((ForceableIterable)iterable).completeIterationNeeded();
            }
            return new Iterator<T>() {
                private final Iterator<? extends S> source = iterable.iterator();

                
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
        private final Iterable<? extends T> iterable;
        private boolean force = false;

        public ReversingIterable(Iterable<? extends T> iterable) {
            this.iterable = iterable;
        }

        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Optional<Long> size() {
            return resolveSize.apply(iterable);
        }

        
        public final Iterator<T> iterator() {
            if (force && iterable instanceof ForceableIterable) {
                ((ForceableIterable)iterable).completeIterationNeeded();
            }
            return new Iterator<T>() {
                private final List<? extends T> list = iterable instanceof List ? (List<? extends T>)iterable : newList(iterable);
                private final ListIterator<? extends T> underlying = list.listIterator(list.size());
                
                
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
                    if (chars instanceof Iterable<?>) {
                        try {
                            chars.charAt(read);
                            return true;
                        } catch (IndexOutOfBoundsException e) {
                            return false;
                        }
                    } else {
                        return read < chars.length();
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

        
        public final Optional<Long> size() {
            if (chars instanceof String || chars instanceof StringBuilder || chars instanceof StringBuffer) {
                return wrapSome((long)chars.length());
            } else {
                return Optional.empty();
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

        
        public final Optional<Long> size() {
            if (it == null) {
                it = iterable.iterator();
            }
            if (!it.hasNext()) {
                return wrapSome((long)memo.length());
            } else {
                return Optional.empty();
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
        private Iterable<? extends T> iterable;
        private final Comparator<? super T> comparator;
        private boolean force = false;

        public SortingIterable(Iterable<? extends T> iterable, Comparator<? super T> comparator) {
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
            long initialSize = resolveSize.apply(iterable).orElse(11l);
            if (initialSize == 0) {
                return java.util.Collections.<T>emptyList().iterator();
            }
            
            if (force) {
                List<T> sorted = null;
                for (long size: it(Iterables.resolveSize.apply(iterable))) {
                    sorted = newMutableListOfSize(size);
                }
                if (sorted == null) {
                    sorted = newMutableList();
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
                queue.addAll((Collection<? extends T>)iterable);
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

        
        public final Optional<Long> size() {
            return resolveSize.apply(iterable);
        }
    }
    
    static final class TakingIterable<T> extends MyIterable<T> implements ForceableIterable {
        private final Iterable<? extends T> elements;
        private final long amount;
        private boolean force = false;

        public TakingIterable(Iterable<? extends T> elements, long amount) {
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
                private final Iterator<? extends T> it = elements.iterator();

                
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

        
        public final Optional<Long> size() {
            Optional<Long> s = resolveSize.apply(elements);
            if (s.isPresent()) {
                return wrapSome(min(s.get(), amount));
            } else {
                return Optional.empty();
            }
        }
    }
    
    static final class DroppingIterable<T> extends MyIterable<T> implements ForceableIterable {
        private final Iterable<? extends T> elements;
        private final long amount;
        private boolean force = false;

        public DroppingIterable(Iterable<? extends T> elements, long amount) {
            if (amount < 0) {
                amount = 0;
            }
            this.elements = elements;
            this.amount = amount;
        }
        
        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        @SuppressWarnings("unchecked")
        public final Iterator<T> iterator() {
            if (force && elements instanceof ForceableIterable) {
                ((ForceableIterable)elements).completeIterationNeeded();
            }
            Iterator<? extends T> it = elements.iterator();
            long left = amount;
            while (left > 0 && it.hasNext()) {
                it.next();
                left--;
            }
            return (Iterator<T>) it;
        }

        
        public final Optional<Long> size() {
            Optional<Long> s = resolveSize.apply(elements);
            if (s.isPresent()) {
                return wrapSome(max(s.get() - amount, 0l));
            } else {
                return Optional.empty();
            }
        }
    }
    
    static final class GroupingIterable<T> extends MyIterable<Iterable<T>> implements ForceableIterable {
        private final Iterable<? extends T> elements;
        private final BiPredicate<? super T,? super T> comparator;
        private boolean force = false;

        public GroupingIterable(Iterable<? extends T> elements, BiPredicate<? super T,? super T> comparator) {
            this.elements = elements;
            this.comparator = comparator;
        }
        
        public final void completeIterationNeeded() {
            this.force = true;
        }
        
        public final Optional<Long> size() {
            return Optional.empty();
        }
        
        
        public final Iterator<Iterable<T>> iterator() {
            if (force && elements instanceof ForceableIterable) {
                ((ForceableIterable)elements).completeIterationNeeded();
            }
            return new Iterator<Iterable<T>>() {
                private Iterable<? extends T> remaining = elements;
                
                
                public final boolean hasNext() {
                    return !isEmpty(remaining);
                }

                
                @SuppressWarnings("unchecked")
                public final Iterable<T> next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    final Object[] prev = { GroupingIterable.class };
                    List<T> s = newList(takeWhile(new java.util.function.Predicate<T>() {
                        public boolean test(T candidate) {
                            if (prev[0] == GroupingIterable.class) {
                                prev[0] = candidate;
                                return true;
                            }
                            boolean ret = comparator.test((T)prev[0], candidate);
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
