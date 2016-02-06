package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;

import java.util.Comparator;
import java.util.Map;

import fi.solita.utils.functional.Iterables.RepeatingIterable;
import fi.solita.utils.functional.Iterables.ZippingIterable;

public abstract class FunctionalC extends FunctionalS {

    static final Transformer<CharSequence, Iterable<Character>> charSeq2iterable = new Transformer<CharSequence, Iterable<Character>>() {
        @Override
        public Iterable<Character> transform(CharSequence source) {
            return it(source);
        }
    };
    
    private static final Transformer<Iterable<Character>, CharSequence> iterable2charSeq = new Transformer<Iterable<Character>, CharSequence>() {
        @Override
        public CharSequence transform(Iterable<Character> source) {
            return it(source);
        }
    };

    
    
    public static final CharSequence subtract(CharSequence a, final CharSequence b) {
        return it(FunctionalImpl.subtract(it(a), newList(it(b))));
    }
    
    public static final String subtract(String a, final String b) {
        return subtract((CharSequence)a, (CharSequence)b).toString();
    }
    
    
    
    
    public static final CharSequence remove(char toRemove, CharSequence xs) {
        return it(FunctionalImpl.remove(toRemove, it(xs)));
    }
    
    public static final String remove(char toRemove, String xs) {
        return it(FunctionalImpl.remove(toRemove, it(xs))).toString();
    }
    
    
    
    public static final Option<Character> find(Apply<Character, Boolean> predicate, CharSequence xs) {
        return FunctionalImpl.find(predicate, it(xs));
    }
    
    
    
    public static final CharSequence filter(Apply<Character, Boolean> predicate, CharSequence xs) {
        return it(FunctionalImpl.filter(predicate, it(xs)));
    }
    
    public static final String filter(Apply<Character, Boolean> predicate, String xs) {
        return filter(predicate, (CharSequence)xs).toString();
    }
    
    
    
    public static final <T> Iterable<T> map(Apply<Character, ? extends T> f, CharSequence xs) {
        return FunctionalImpl.map(f, it(xs));
    }
    
    public static final <T1, T2> Iterable<Pair<T1,T2>> map(Apply<Character, T1> f1, Apply<Character, T2> f2, CharSequence xs) {
        return FunctionalImpl.map(f1, f2, it(xs));
    }
    
    public static final <T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(Apply<Character, T1> f1, Apply<Character, T2> f2, Apply<Character, T3> f3, CharSequence xs) {
        return FunctionalImpl.map(f1, f2, f3, it(xs));
    }
    
    public static final <T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(Apply<Character, T1> f1, Apply<Character, T2> f2, Apply<Character, T3> f3, Apply<Character, T4> f4, CharSequence xs) {
        return FunctionalImpl.map(f1, f2, f3, f4, it(xs));
    }
    
    public static final <T> Iterable<T> flatMap(Apply<Character, ? extends Iterable<? extends T>> f, CharSequence xs) {
        return FunctionalImpl.flatMap(f, it(xs));
    }
    
    
    
    public static final void foreach(Apply<Character, Void> procedure, CharSequence xs) {
        FunctionalImpl.foreach(procedure, it(xs));
    }
    
    public static final void foreach(ApplyVoid<Character> procedure, CharSequence xs) {
        FunctionalImpl.foreach(procedure, it(xs));
    }
    
    
    
    public static final Iterable<CharSequence> grouped(long groupSize, CharSequence xs) {
        return FunctionalImpl.map(iterable2charSeq, FunctionalImpl.grouped(groupSize, it(xs)));
    }
    
    public static final Iterable<String> grouped(long groupSize, String xs) {
        return FunctionalImpl.map(Transformers.toString, grouped(groupSize, (CharSequence)xs));
    }

    
    
    public static final Iterable<CharSequence> group(CharSequence xs) {
        return FunctionalImpl.map(iterable2charSeq, FunctionalImpl.group(it(xs)));
    }
    
    public static final Iterable<String> group(String xs) {
        return FunctionalImpl.map(Transformers.toString, group((CharSequence)xs));
    }
    
    public static final <T> Iterable<CharSequence> group(Apply<Tuple2<Character,Character>, Boolean> comparator, CharSequence xs) {
        return FunctionalImpl.map(iterable2charSeq, FunctionalImpl.group(comparator, it(xs)));
    }
    
    public static final <T> Iterable<String> group(Apply<Tuple2<Character,Character>, Boolean> comparator, String xs) {
        return FunctionalImpl.map(Transformers.toString, group(comparator, (CharSequence)xs));
    }
    
    
    
    public static final char head(CharSequence xs) {
        return xs.charAt(0);
    }
    
    public static final Option<Character> headOption(CharSequence xs) {
        try {
            return Some(xs.charAt(0));
        } catch (IndexOutOfBoundsException e) {
            return None();
        }
    }
    
    
    
    public static final CharSequence tail(CharSequence xs) {
        return xs.subSequence(1, xs.length());
    }
    
    public static final String tail(String xs) {
        return xs.substring(1, xs.length());
    }
    
    
    
    public static final char last(CharSequence xs) {
        return xs.charAt(xs.length() - 1);
    }
    
    public static final Option<Character> lastOption(CharSequence xs) {
        try {
            return Some(xs.charAt(xs.length() - 1));
        } catch (IndexOutOfBoundsException e) {
            return None();
        }
    }
    
    
    
    public static final CharSequence init(CharSequence xs) {
        return it(FunctionalImpl.init(it(xs)));
    }
    
    public static final String init(String xs) {
        return xs.substring(0, xs.length()-1);
    }
    
    
    
    public static final CharSequence take(long amount, CharSequence xs) {
        return it(FunctionalImpl.take(amount, it(xs)));
    }
    
    public static final String take(long amount, String xs) {
        return xs.substring(0, min((int)amount, xs.length()));
    }
    
    
    
    public static final CharSequence drop(long amount, CharSequence xs) {
        return it(FunctionalImpl.drop(amount, it(xs)));
    }
    
    public static final String drop(long amount, String xs) {
        return xs.substring(min((int)amount, xs.length()), xs.length());
    }
    
    
    
    public static final CharSequence takeWhile(Apply<Character, Boolean> predicate, CharSequence xs) {
        return it(FunctionalImpl.takeWhile(predicate, it(xs)));
    }
    
    public static final String takeWhile(Apply<Character, Boolean> predicate, String xs) {
        return takeWhile(predicate, (CharSequence)xs).toString();
    }
    
    
    
    public static final CharSequence dropWhile(Apply<Character, Boolean> predicate, CharSequence xs) {
        return it(FunctionalImpl.dropWhile(predicate, it(xs)));
    }
    
    public static final String dropWhile(Apply<Character, Boolean> predicate, String xs) {
        return dropWhile(predicate, (CharSequence)xs).toString();
    }
    
    
    
    public static final <T> Pair<CharSequence, CharSequence> span(Apply<Character, Boolean> predicate, CharSequence xs) {
        return Pair.of(takeWhile(predicate, xs), dropWhile(predicate, xs));
    }
    
    public static final <T> Pair<String, String> span(Apply<Character, Boolean> predicate, String xs) {
        Pair<CharSequence,CharSequence> span = span(predicate, (CharSequence)xs);
        return Pair.of(span.left.toString(), span.right.toString());
    }
    
    
    
    public static final boolean isEmpty(String xs) {
        return xs.isEmpty();
    }
    
    public static final boolean isEmpty(CharSequence xs) {
        try {
            xs.charAt(0);
            return false;
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
    }
    
    public static final long size(CharSequence xs) {
        return xs.length();
    }
    
    public static final boolean contains(char candidate, CharSequence xs) {
        return FunctionalImpl.exists(Predicates.equalTo(candidate), it(xs));
    }
    
    public static final boolean contains(char candidate, String xs) {
        return xs.indexOf(candidate) != -1;
    }
    
    public static final boolean exists(Apply<Character, Boolean> predicate, CharSequence xs) {
        return FunctionalImpl.exists(predicate, it(xs));
    }
    
    public static final boolean forall(Apply<Character, Boolean> predicate, CharSequence xs) {
        return FunctionalImpl.forall(predicate, it(xs));
    }
    
    
    
    public static final CharSequence cons(char x, CharSequence xs) {
        return it(FunctionalImpl.cons(x, it(xs)));
    }
    
    public static final String cons(char x, String xs) {
        return x + xs;
    }
    
    
    
    public static final CharSequence concat(CharSequence a, CharSequence b) {
        return it(FunctionalImpl.concat(it(a), it(b)));
    }
    
    public static final String concat(String a, String b) {
        return a + b;
    }
    
    
    
    public static final CharSequence sort(CharSequence xs) {
        return it(FunctionalImpl.sort(it(xs)));
    }
    
    public static final String sort(String xs) {
        return sort((CharSequence)xs).toString();
    }
    
    public static final CharSequence sort(Comparator<Character> comparator, CharSequence xs) {
        return it(FunctionalImpl.sort(comparator, it(xs)));
    }
    
    public static final String sort(Comparator<Character> comparator, String xs) {
        return sort(comparator, (CharSequence)xs).toString();
    }
    
    
    
    public static final <Z> Z fold(Z zero, Apply<Map.Entry<? extends Z,? extends Character>, Z> f, CharSequence xs) {
        return FunctionalImpl.fold(zero, f, it(xs));
    }
    
    public static final Option<Character> fold(Apply<Map.Entry<? extends Character,? extends Character>, Character> f, CharSequence xs) {
        return FunctionalImpl.fold(f, it(xs));
    }
    
    public static Option<Character> min(CharSequence xs) {
        return FunctionalImpl.min(it(xs));
    }
    
    public static final Option<Character> max(CharSequence xs) {
        return FunctionalImpl.max(it(xs));
    }
    
    public static final Iterable<Tuple2<Character, Character>> zip(CharSequence a, CharSequence b) {
        return FunctionalImpl.zip(it(a), it(b));
    }
    
    public static final Iterable<Tuple3<Character, Character, Character>> zip(CharSequence a, CharSequence b, CharSequence c) {
        return FunctionalImpl.zip(it(a), it(b), it(c)); 
    }
    
    public static final Iterable<Tuple2<Integer, Character>> zipWithIndex(CharSequence a) {
        return new ZippingIterable<Integer,Character>(range(0), it(a));
    }
    
    
    
    public static final CharSequence repeat(final char value) {
        return it(new RepeatingIterable<Character>(value));
    }
    
    public static final CharSequence repeat(char value, long amount) {
        if (amount <= 0) {
            return "";
        }
        return it(new RepeatingIterable<Character>(value, amount));
    }
    
    
    
    public static final String mkString(CharSequence xs) {
        return xs.toString();
    }
    
    public static final String mkString(CharSequence delimiter, CharSequence xs) {
        return FunctionalImpl.mkString(delimiter, FunctionalImpl.map(Transformers.toString, it(xs)));
    }
    
    
    
    public static final CharSequence reverse(CharSequence xs) {
        return it(FunctionalImpl.reverse(it(xs)));
    }
    
    public static final String reverse(String xs) {
        return reverse((CharSequence)xs).toString();
    }
    
    
    
    public static CharSequence distinct(CharSequence xs) {
        return it(FunctionalImpl.distinct(it(xs)));
    }
    
    
    
    public static CharSequence unlines(Iterable<? extends CharSequence> xs) {
        return FunctionalImpl.unlines(xs);
    }
}
