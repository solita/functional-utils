package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.not;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import fi.solita.utils.functional.Iterables.RepeatingIterable;

/**
 * Variants of {@link Functional} for Strings and other CharSequences.
 */
public abstract class FunctionalC extends FunctionalS {

    static final Transformer<CharSequence, Iterable<Character>> charSeq2iterable = new Transformer<CharSequence, Iterable<Character>>() {
        @Override
        public final Iterable<Character> transform(CharSequence source) {
            return it(source);
        }
    };
    
    private static final Transformer<Iterable<Character>, CharSequence> iterable2charSeq = new Transformer<Iterable<Character>, CharSequence>() {
        @Override
        public final CharSequence transform(Iterable<Character> source) {
            return it(source);
        }
    };

    
    /**
     * @see Functional#subtract
     */
    public static final CharSequence subtract(CharSequence xs, CharSequence toSubtract) {
        return it(FunctionalImpl.subtract(it(xs), newList(it(toSubtract))));
    }
    
    /**
     * @see Functional#subtract
     */
    public static final String subtract(String xs, String toSubtract) {
        return xs == null || toSubtract == null ? null : subtract((CharSequence)xs, (CharSequence)toSubtract).toString();
    }
    
    
    
    /**
     * @see Functional#remove
     */
    public static final CharSequence remove(char toRemove, CharSequence xs) {
        return it(FunctionalImpl.remove(toRemove, it(xs)));
    }
    
    
    /**
     * @see Functional#remove
     */
    public static final String remove(char toRemove, String xs) {
        return xs == null ? null : it(FunctionalImpl.remove(toRemove, it(xs))).toString();
    }
    
    
    
    /**
     * @see Functional#find
     */
    public static final Option<Character> find(Apply<Character, Boolean> predicate, CharSequence xs) {
        return FunctionalImpl.find(predicate, it(xs));
    }
    
    
    
    /**
     * @see Functional#filter
     */
    public static final CharSequence filter(Apply<Character, Boolean> predicate, CharSequence xs) {
        return it(FunctionalImpl.filter(predicate, it(xs)));
    }
    
    /**
     * @see Functional#filter
     */
    public static final String filter(Apply<Character, Boolean> predicate, String xs) {
        return xs == null ? null : filter(predicate, (CharSequence)xs).toString();
    }
    
    
    
    /**
     * @see Functional#map(Apply, Iterable)
     */
    public static final <T> Iterable<T> map(Apply<Character, ? extends T> f, CharSequence xs) {
        return FunctionalImpl.map(f, it(xs));
    }
    
    /**
     * @see Functional#map(Apply, Apply, Iterable)
     */
    public static final <T1, T2> Iterable<Pair<T1,T2>> map(Apply<Character, T1> f1, Apply<Character, T2> f2, CharSequence xs) {
        return FunctionalImpl.map(f1, f2, it(xs));
    }
    
    /**
     * @see Functional#map(Apply, Apply, Apply, Iterable)
     */
    public static final <T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(Apply<Character, T1> f1, Apply<Character, T2> f2, Apply<Character, T3> f3, CharSequence xs) {
        return FunctionalImpl.map(f1, f2, f3, it(xs));
    }
    
    /**
     * @see Functional#map(Apply, Apply, Apply, Apply, Iterable)
     */
    public static final <T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(Apply<Character, T1> f1, Apply<Character, T2> f2, Apply<Character, T3> f3, Apply<Character, T4> f4, CharSequence xs) {
        return FunctionalImpl.map(f1, f2, f3, f4, it(xs));
    }
    
    /**
     * @see Functional#flatMap
     */
    public static final <T> Iterable<T> flatMap(Apply<Character, ? extends Iterable<? extends T>> f, CharSequence xs) {
        return FunctionalImpl.flatMap(f, it(xs));
    }
    
    
    
    /**
     * @see Functional#foreach
     */
    public static final void foreach(Apply<Character, Void> procedure, CharSequence xs) {
        FunctionalImpl.foreach(procedure, it(xs));
    }
    
    /**
     * @see Functional#foreach
     */
    public static final void foreach(ApplyVoid<Character> procedure, CharSequence xs) {
        FunctionalImpl.foreach(procedure, it(xs));
    }
    
    
    
    /**
     * @see Functional#grouped
     */
    public static final Iterable<CharSequence> grouped(long groupSize, CharSequence xs) {
        return FunctionalImpl.map(iterable2charSeq, FunctionalImpl.grouped(groupSize, it(xs)));
    }
    
    /**
     * @see Functional#grouped
     */
    public static final Iterable<String> grouped(long groupSize, String xs) {
        return FunctionalImpl.map(Transformers.toString, grouped(groupSize, (CharSequence)xs));
    }

    
    
    /**
     * @see Functional#group(Iterable)
     */
    public static final Iterable<CharSequence> group(CharSequence xs) {
        return FunctionalImpl.map(iterable2charSeq, FunctionalImpl.group(it(xs)));
    }
    
    /**
     * @see Functional#group(Iterable)
     */
    public static final Iterable<String> group(String xs) {
        return FunctionalImpl.map(Transformers.toString, group((CharSequence)xs));
    }
    
    /**
     * @see Functional#group(Apply, Iterable)
     */
    public static final Iterable<CharSequence> group(Apply<Map.Entry<Character,Character>, Boolean> comparator, CharSequence xs) {
        return FunctionalImpl.map(iterable2charSeq, FunctionalImpl.group(comparator, it(xs)));
    }
    
    /**
     * @see Functional#group(Apply, Iterable)
     */
    public static final Iterable<String> group(Apply<Map.Entry<Character,Character>, Boolean> comparator, String xs) {
        return FunctionalImpl.map(Transformers.toString, group(comparator, (CharSequence)xs));
    }
    
    
    
    /**
     * @see Functional#head
     */
    public static final Character head(CharSequence xs) {
        return xs == null ? null : xs.charAt(0);
    }
    
    /**
     * @see Functional#headOption
     */
    public static final Option<Character> headOption(CharSequence xs) {
        if (xs == null) {
            return null;
        }
        try {
            return Some(xs.charAt(0));
        } catch (IndexOutOfBoundsException e) {
            return None();
        }
    }
    
    
    
    /**
     * @see Functional#tail
     */
    public static final CharSequence tail(CharSequence xs) {
        return xs == null ? null : xs.subSequence(1, xs.length());
    }
    
    /**
     * @see Functional#tail
     */
    public static final String tail(String xs) {
        return xs == null ? null : xs.substring(1, xs.length());
    }
    
    
    
    /**
     * @see Functional#last
     */
    public static final Character last(CharSequence xs) {
        return xs == null ? null : xs.charAt(xs.length() - 1);
    }
    
    /**
     * @see Functional#lastOption
     */
    public static final Option<Character> lastOption(CharSequence xs) {
        if (xs == null) {
            return null;
        }
        try {
            return Some(xs.charAt(xs.length() - 1));
        } catch (IndexOutOfBoundsException e) {
            return None();
        }
    }
    
    
    
    /**
     * @see Functional#init
     */
    public static final CharSequence init(CharSequence xs) {
        return it(FunctionalImpl.init(it(xs)));
    }
    
    /**
     * @see Functional#init
     */
    public static final String init(String xs) {
        return xs == null ? null : xs.substring(0, xs.length()-1);
    }
    
    
    
    /**
     * @see Functional#take
     */
    public static final CharSequence take(long amount, CharSequence xs) {
        return it(FunctionalImpl.take(amount, it(xs)));
    }
    
    /**
     * @see Functional#take
     */
    public static final String take(long amount, String xs) {
        return xs == null ? null : xs.substring(0, Functional.min((int)amount, xs.length()));
    }
    
    
    
    /**
     * @see Functional#drop
     */
    public static final CharSequence drop(long amount, CharSequence xs) {
        return it(FunctionalImpl.drop(amount, it(xs)));
    }
    
    /**
     * @see Functional#drop
     */
    public static final String drop(long amount, String xs) {
        return xs == null ? null : xs.substring(Functional.min((int)amount, xs.length()), xs.length());
    }
    
    
    
    /**
     * @see Functional#takeWhile
     */
    public static final CharSequence takeWhile(Apply<Character, Boolean> predicate, CharSequence xs) {
        return it(FunctionalImpl.takeWhile(predicate, it(xs)));
    }
    
    /**
     * @see Functional#takeWhile
     */
    public static final String takeWhile(Apply<Character, Boolean> predicate, String xs) {
        return xs == null ? null : takeWhile(predicate, (CharSequence)xs).toString();
    }
    
    
    
    /**
     * @see Functional#dropWhile
     */
    public static final CharSequence dropWhile(Apply<Character, Boolean> predicate, CharSequence xs) {
        return it(FunctionalImpl.dropWhile(predicate, it(xs)));
    }
    
    /**
     * @see Functional#dropWhile
     */
    public static final String dropWhile(Apply<Character, Boolean> predicate, String xs) {
        return xs == null ? null : dropWhile(predicate, (CharSequence)xs).toString();
    }
    
    
    
    /**
     * @see Functional#span
     */
    public static final Pair<CharSequence, CharSequence> span(Apply<Character, Boolean> predicate, CharSequence xs) {
        return Pair.of(takeWhile(predicate, xs), dropWhile(predicate, xs));
    }
    
    /**
     * @see Functional#span
     */
    public static final Pair<String, String> span(Apply<Character, Boolean> predicate, String xs) {
        if (xs == null) {
            return null;
        }
        for (int i = 0; i < xs.length(); ++i) {
            if (!predicate.apply(xs.charAt(i))) {
                return Pair.of(xs.substring(0, i), xs.substring(i));
            }
        }
        return Pair.of(xs, "");
    }
    
    /**
     * @see Functional#partition
     */
    public static final Pair<CharSequence, CharSequence> partition(Apply<Character, Boolean> predicate, CharSequence xs) {
        return Pair.of(filter(predicate, xs), filter(not(predicate), xs));
    }
    
    /**
     * @see Functional#partition
     */
    public static final Pair<String, String> partition(Apply<Character, Boolean> predicate, String xs) {
        return partition(predicate, (CharSequence)xs).bimap(Transformers.toString, Transformers.toString);
    }
    
    
    
    /**
     * @see Functional#split(int, Iterable)
     */
    public static final Pair<CharSequence,CharSequence> split(int i, CharSequence xs) {
        return xs == null ? null : Pair.of(xs.subSequence(0, i), xs.subSequence(i, xs.length()));
    }
    
    /**
     * @see Functional#split(Iterable)
     */
    public static <T> Pair<Character, CharSequence> split(CharSequence xs) {
        return xs == null ? null : Pair.of(xs.charAt(0), xs.subSequence(1, xs.length()));
    }
    
    /**
     * @see Functional#split(int, Iterable)
     */
    public static final Pair<String,String> split(int i, String xs) {
        return xs == null ? null : Pair.of(xs.substring(0, i), xs.substring(i));
    }
    
    /**
     * @see Functional#split(Iterable)
     */
    public static <T> Pair<Character, String> split(String xs) {
        return xs == null ? null : Pair.of(xs.charAt(0), xs.substring(1));
    }
    
    
    
    /**
     * @see Functional#every
     */
    public static final <T> CharSequence every(int nth, CharSequence xs) {
        return it(FunctionalImpl.every(nth, it(xs)));
    }
    
    /**
     * @see Functional#every
     */
    public static final <T> String every(int nth, String xs) {
        return xs == null ? null : every(nth, (CharSequence)xs).toString();
    }
    
    
    
    /**
     * @see Functional#isEmpty
     */
    public static final boolean isEmpty(String xs) {
        return xs.isEmpty();
    }
    
    /**
     * @see Functional#isEmpty
     */
    public static final boolean isEmpty(CharSequence xs) {
        try {
            xs.charAt(0);
            return false;
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
    }
    
    /**
     * @see Functional#size
     */
    public static final long size(CharSequence xs) {
        return xs.length();
    }
    
    /**
     * @see Functional#contains
     */
    public static final boolean contains(char candidate, CharSequence xs) {
        return FunctionalImpl.exists(Predicates.equalTo(candidate), it(xs));
    }
    
    /**
     * @see Functional#contains
     */
    public static final boolean contains(char candidate, String xs) {
        return xs.indexOf(candidate) != -1;
    }
    
    /**
     * @see Functional#exists
     */
    public static final boolean exists(Apply<Character, Boolean> predicate, CharSequence xs) {
        return FunctionalImpl.exists(predicate, it(xs));
    }
    
    /**
     * @see Functional#forall
     */
    public static final boolean forall(Apply<Character, Boolean> predicate, CharSequence xs) {
        return FunctionalImpl.forall(predicate, it(xs));
    }
    
    
    
    /**
     * @see Functional#cons
     */
    public static final CharSequence cons(char x, CharSequence xs) {
        return it(FunctionalImpl.cons(x, it(xs)));
    }
    
    /**
     * @see Functional#cons
     */
    public static final String cons(char x, String xs) {
        return xs == null ? null : x + xs;
    }
    
    
    
    /**
     * @see Functional#concat
     */
    public static final CharSequence concat(CharSequence a, CharSequence b) {
        return a == null && b == null ? null : a == null ? b : b == null ? a : it(FunctionalImpl.concat(it(a), it(b)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final String concat(String a, String b) {
        return a == null && b == null ? null : a == null ? b : b == null ? a : a + b;
    }
    
    
    
    /**
     * @see Functional#sort(Iterable)
     */
    public static final CharSequence sort(CharSequence xs) {
        return it(FunctionalImpl.sort(it(xs)));
    }
    
    /**
     * @see Functional#sort(Iterable)
     */
    public static final String sort(String xs) {
        return xs == null ? null : sort((CharSequence)xs).toString();
    }
    
    /**
     * @see Functional#sort(Comparator, Iterable)
     */
    public static final CharSequence sort(Comparator<Character> comparator, CharSequence xs) {
        return it(FunctionalImpl.sort(comparator, it(xs)));
    }
    
    /**
     * @see Functional#sort(Comparator, Iterable)
     */
    public static final String sort(Comparator<Character> comparator, String xs) {
        return xs == null ? null : sort(comparator, (CharSequence)xs).toString();
    }
    
    
    
    /**
     * @see Functional#fold(Object, Apply, Iterable)
     */
    public static final <Z> Z fold(Z zero, Apply<Map.Entry<? extends Z,? extends Character>, Z> f, CharSequence xs) {
        return FunctionalImpl.fold(zero, f, it(xs));
    }
    
    /**
     * @see Functional#fold(Apply, Iterable)
     */
    public static final Option<Character> fold(Apply<Map.Entry<? extends Character,? extends Character>, Character> f, CharSequence xs) {
        return FunctionalImpl.fold(f, it(xs));
    }
    
    /**
     * @see Functional#min
     */
    public static final Option<Character> min(CharSequence xs) {
        return FunctionalImpl.min(it(xs));
    }
    
    /**
     * @see Functional#max
     */
    public static final Option<Character> max(CharSequence xs) {
        return FunctionalImpl.max(it(xs));
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable)
     */
    public static final Iterable<Pair<Character, Character>> zip(CharSequence a, CharSequence b) {
        return FunctionalImpl.zip(it(a), it(b));
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable)
     */
    public static final Iterable<Tuple3<Character, Character, Character>> zip(CharSequence a, CharSequence b, CharSequence c) {
        return FunctionalImpl.zip(it(a), it(b), it(c)); 
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable, Iterable)
     */
    public static final Iterable<Tuple4<Character, Character, Character, Character>> zip(CharSequence a, CharSequence b, CharSequence c, CharSequence d) {
        return FunctionalImpl.zip(it(a), it(b), it(c), it(d)); 
    }
    
    /**
     * @see Functional#zipWithIndex
     */
    public static final Iterable<Pair<Integer, Character>> zipWithIndex(CharSequence a) {
        return FunctionalImpl.zipWithIndex(it(a));
    }
    
    /**
     * @see Functional#unzip
     */
    public static final <A, B> Pair<CharSequence, CharSequence> unzipC(Iterable<Pair<Character,Character>> xs) {
        if (xs == null) {
            return null;
        }
        Pair<Iterable<Character>, Iterable<Character>> ret = FunctionalImpl.unzip(newList(xs));
        return Pair.of(it(ret.left()), it(ret.right()));
    }
    
    /**
     * @see Functional#unzip3
     */
    public static final <A, B, C> Tuple3<CharSequence, CharSequence, CharSequence> unzip3C(Iterable<Tuple3<Character,Character,Character>> xs) {
        if (xs == null) {
            return null;
        }
        Tuple3<Iterable<Character>,Iterable<Character>,Iterable<Character>> ret = FunctionalImpl.unzip3(newList(xs));
        return Tuple.of(it(ret._1), it(ret._2), it(ret._3));
    }
    
    
    
    /**
     * @see Functional#repeat(Object)
     */
    public static final CharSequence repeat(final char value) {
        return it(new RepeatingIterable<Character>(value));
    }
    
    /**
     * @see Functional#repeat(Object, long)
     */
    public static final CharSequence repeat(char value, long amount) {
        if (amount <= 0) {
            return "";
        }
        return it(new RepeatingIterable<Character>(value, amount));
    }
    
    /**
     * @see Functional#rangify
     */
    public static final <T> Iterable<List<Character>> rangify(Enumerable<Character> enumeration, CharSequence xs) {
        return FunctionalImpl.rangify(enumeration, it(xs));
    }
    
    
    /**
     * @see Functional#mkString(Iterable)
     */
    public static final String mkString(CharSequence xs) {
        return xs == null ? null : xs.toString();
    }
    
    /**
     * @see Functional#mkString(CharSequence, Iterable)
     */
    public static final String mkString(CharSequence delimiter, CharSequence xs) {
        return FunctionalImpl.mkString(delimiter, FunctionalImpl.map(Transformers.toString, it(xs)));
    }
    
    
    
    /**
     * @see Functional#reverse
     */
    public static final CharSequence reverse(CharSequence xs) {
        return it(FunctionalImpl.reverse(it(xs)));
    }
    
    /**
     * @see Functional#reverse
     */
    public static final String reverse(String xs) {
        return xs == null ? null : reverse((CharSequence)xs).toString();
    }
    
    
    
    /**
     * @see Functional#distinct
     */
    public static final CharSequence distinct(CharSequence xs) {
        return it(FunctionalImpl.distinct(it(xs)));
    }
    
    
    
    /**
     * @see Functional#unlines
     */
    public static final CharSequence unlines(Iterable<? extends CharSequence> xs) {
        return FunctionalImpl.unlines(xs);
    }
}
