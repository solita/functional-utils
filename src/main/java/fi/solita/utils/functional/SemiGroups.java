package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.filter;

import java.util.*;
import java.util.Map.Entry;

public abstract class SemiGroups {
    
    public static final SemiGroup<Integer> intSum = Monoids.intSum;

    public static final SemiGroup<Integer> intProduct = Monoids.intProduct;
    
    public static final SemiGroup<Long> longSum = Monoids.longSum;

    public static final SemiGroup<Long> longProduct = Monoids.longProduct;

    public static final SemiGroup<Double> doubleSum = Monoids.doubleSum;

    public static final SemiGroup<Double> doubleProduct = Monoids.doubleProduct;
    
    public static final SemiGroup<String> stringConcat = Monoids.stringConcat;

    public static final SemiGroup<Boolean> booleanConjunction = Monoids.booleanConjunction;

    public static final SemiGroup<Boolean> booleanDisjunction = Monoids.booleanDisjunction;
    
    public static final SemiGroup<?> setIntersection = new SemiGroups.SetIntersection<Object>();
    
    /**
     * @return SemiGroup that always keeps the first element and discards the second.
     */
    @SuppressWarnings("unchecked")
    public static final <T> SemiGroup<T> first() {
        return (SemiGroup<T>) first;
    }
    
    /**
     * @return SemiGroup that always keeps the second element and discards the first.
     */
    @SuppressWarnings("unchecked")
    public static final <T> SemiGroup<T> last() {
        return (SemiGroup<T>) last;
    }
    
    /**
     * @return SemiGroup that always fails with an error.
     */
    public static final <T> SemiGroup<T> fail() {
        return new SemiGroup<T>() {
            public T apply(Entry<? extends T, ? extends T> t) {
                throw new RuntimeException(SemiGroup.class.getName() + ".fail() was applied with values " + t.getKey() + " and " + t.getValue() + "! Does a map of yours have duplicate keys, or what?");
            }
        };
    }
    
    /**
     * @return SemiGroup that fails with an error on unequal (by Java equality) values.
     */
    public static final <T> SemiGroup<T> failUnequal() {
        return new SemiGroup<T>() {
            public T apply(Entry<? extends T, ? extends T> t) {
                if (t.getKey().equals(t.getValue())) {
                    return t.getKey();
                } else {
                    throw new RuntimeException(SemiGroup.class.getName() + ".failUnequal() was applied with values " + t.getKey() + " and " + t.getValue() + "! Does a map of yours have duplicate keys, or what?");
                }
            }
        };
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> SemiGroup<Apply<T,T>> endo() {
        return (SemiGroup<Apply<T,T>>)(Object)endo;
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends Comparable<T>> SemiGroup<T> max() {
        return (SemiGroup<T>) max;
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends Comparable<T>> SemiGroup<T> min() {
        return (SemiGroup<T>) min;
    }
    
    public static final <T> SemiGroup<T> maxBy(Comparator<? super T> comparator) {
        return new MaxBy<T>(comparator);
    }
    
    public static final <T> SemiGroup<T> minBy(Comparator<? super T> comparator) {
        return new MinBy<T>(comparator);
    }

    /**
     * Preserves iteration order
     */
    public static final <T> SemiGroup<Set<T>> setUnion() {
        return Monoids.setUnion();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> SemiGroup<Set<T>> setIntersection() {
        return (SemiGroup<Set<T>>)setIntersection;
    }
    
    public static final <T> SemiGroup<List<T>> listConcat() {
        return Monoids.listConcat();
    }
    
    public static final <T> SemiGroup<Collection<T>> collectionConcat() {
        return Monoids.collectionConcat();
    }
    
    public static final <T> SemiGroup<Iterable<T>> iterableConcat() {
        return Monoids.iterableConcat();
    }
    
    /**
     * Tries all comparators sequentially as long as the comparison results in equality.
     */
    public static final <T> SemiGroup<Comparator<T>> comparatorConcat() {
        return Monoids.comparatorConcat();
    }
    
    /**
     * Combines values of the same key with the semigroup binary operation.
     */
    public static final <K,V> SemiGroup<Map<K,V>> mapCombine(SemiGroup<V> sg) {
        return new MapCombine<K, V>(sg);
    }
    
    /**
     * Tuple of semigroups is a semigroup.
     */
    public static final <T1,T2> SemiGroup<Tuple2<T1,T2>> product(SemiGroup<T1> s1, SemiGroup<T2> s2) {
        return new Product2<T1,T2>(s1, s2);
    }
    
    /**
     * Tuple of semigroups is a semigroup.
     */
    public static final <T1,T2,T3> SemiGroup<Tuple3<T1,T2,T3>> product(SemiGroup<T1> s1, SemiGroup<T2> s2, SemiGroup<T3> s3) {
        return new Product3<T1,T2,T3>(s1, s2, s3);
    }
    
    /**
     * Tuple of semigroups is a semigroup.
     */
    public static final <T1,T2,T3,T4> SemiGroup<Tuple4<T1,T2,T3,T4>> product(SemiGroup<T1> s1, SemiGroup<T2> s2, SemiGroup<T3> s3, SemiGroup<T4> s4) {
        return new Product4<T1,T2,T3,T4>(s1, s2, s3, s4);
    }
    
    
    
  static class BooleanDisjunction extends Function2<Boolean,Boolean,Boolean> implements SemiGroup<Boolean> {
      @Override
      public final Boolean apply(Boolean first, Boolean second) {
          return first || second;
      }
  }

  static class BooleanConjunction extends Function2<Boolean,Boolean,Boolean> implements SemiGroup<Boolean> {
      @Override
      public final Boolean apply(Boolean first, Boolean second) {
          return first && second;
      }
  }

  static class StringConcat extends Function2<String,String,String> implements SemiGroup<String> {
      @Override
      public final String apply(String first, String second) {
          return first + second;
      }
  }

  static class LongProduct extends Function2<Long,Long,Long> implements SemiGroup<Long> {
      @Override
      public final Long apply(Long first, Long second) {
          return first * second;
      }
  }

  static class LongSum extends Function2<Long,Long,Long> implements SemiGroup<Long> {
      @Override
      public final Long apply(Long first, Long second) {
          return first + second;
      }
  }

  static class IntProduct extends Function2<Integer,Integer,Integer> implements SemiGroup<Integer> {
      @Override
      public final Integer apply(Integer first, Integer second) {
          return first * second;
      }
  }

  static class IntSum extends Function2<Integer,Integer,Integer> implements SemiGroup<Integer> {
      @Override
      public final Integer apply(Integer first, Integer second) {
          return first + second;
      }
  }
  
  static class DoubleProduct extends Function2<Double,Double,Double> implements SemiGroup<Double> {
      @Override
      public final Double apply(Double first, Double second) {
          return first * second;
      }
  }
  
  static class DoubleSum extends Function2<Double,Double,Double> implements SemiGroup<Double> {
      @Override
      public final Double apply(Double first, Double second) {
          return first + second;
      }
  }
  
  static final class First<T> extends Function2<T,T,T> implements SemiGroup<T> {
      @Override
      public final T apply(T t1, T t2) {
          return t1;
      }
  }
  
  static final class Last<T> extends Function2<T,T,T> implements SemiGroup<T> {
      @Override
      public final T apply(T t1, T t2) {
          return t2;
      }
  }
  
  static final class Max<T extends Comparable<T>> extends Function2<T,T,T> implements SemiGroup<T> {
    @Override
    public final T apply(T t1, T t2) {
        return t1.compareTo(t2) < 0 ? t2 : t1;
    }
  }
  
  static final class Min<T extends Comparable<T>> extends Function2<T,T,T> implements SemiGroup<T> {
      @Override
      public final T apply(T t1, T t2) {
          return t1.compareTo(t2) > 0 ? t2 : t1;
      }
  }
  
  static final class MaxBy<T> extends Function2<T,T,T> implements SemiGroup<T> {
      private final Comparator<? super T> comparator;
      public MaxBy(Comparator<? super T> comparator) {
          this.comparator = comparator;
      }
      
      @Override
      public final T apply(T t1, T t2) {
          return comparator.compare(t1, t2) < 0 ? t2 : t1;
      }
  }
  
  static final class MinBy<T> extends Function2<T,T,T> implements SemiGroup<T> {
      private final Comparator<? super T> comparator;
      public MinBy(Comparator<? super T> comparator) {
          this.comparator = comparator;
      }
      
      @Override
      public final T apply(T t1, T t2) {
          return comparator.compare(t1, t2) > 0 ? t2 : t1;
      }
  }
  
  static class Endo<T> extends Function2<Apply<T,T>,Apply<T,T>,Apply<T,T>> implements SemiGroup<Apply<T, T>> {
      @Override
      public final Apply<T, T> apply(Apply<T, T> t1, Apply<T, T> t2) {
          return Function.of(t1).andThen(t2);
      }
  }
  
  static class SetUnion<T> extends Function2<Set<T>,Set<T>,Set<T>> implements SemiGroup<Set<T>> {
      @Override
      public final Set<T> apply(Set<T> first, Set<T> second) {
          if (first == null || first.isEmpty()) {
              return second;
          }
          if (second == null || second.isEmpty()) {
              return first;
          }
          return new LinkedHashSet<T>(newList(concat(first, second)));
      }
  }
  
  static class SetIntersection<T> extends Function2<Set<T>,Set<T>,Set<T>> implements SemiGroup<Set<T>> {
      @Override
      public final Set<T> apply(Set<T> first, final Set<T> second) {
          if (first == null || first.isEmpty()) {
              return first;
          }
          if (second == null || second.isEmpty()) {
              return second;
          }
          
          final Set<T> smaller;
          final Set<T> larger;
          if (first.size() <= second.size()) {
              smaller = first;
              larger = second;
          } else {
              smaller = second;
              larger = first;
          }
          
          return newSet(filter(new Predicate<T>() {
              @Override
              public boolean accept(T candidate) {
                  return larger.contains(candidate);
              }
          }, smaller));
      }
  }
  
  static class ComparatorConcat<T> extends Function2<Comparator<T>,Comparator<T>,Comparator<T>> implements SemiGroup<Comparator<T>> {
      @Override
      public final Comparator<T> apply(final Comparator<T> first, final Comparator<T> second) {
          return new Comparator<T>() {
              public int compare(T o1, T o2) {
                  if (o1 == null && o2 == null) {
                      return 0;
                  }
                  int c = first.compare(o1, o2);
                  return c != 0 ? c : second.compare(o1, o2);
              }
          };
      }
  }
  
  static class MapCombine<K, V> extends Function2<Map<K,V>,Map<K,V>,Map<K,V>> implements SemiGroup<Map<K,V>> {
      private final Function2<V,V,V> sg;

      @SuppressWarnings("unchecked")
      public MapCombine(final SemiGroup<V> sg) {
          this.sg = sg instanceof Function2 ? (Function2<V,V,V>)sg : new Function2<V,V,V>() {
            @Override
            public V apply(V t1, V t2) {
                return sg.apply(Pair.of(t1, t2));
            }
        };
      }

      @Override
      public final Map<K, V> apply(Map<K, V> t1, Map<K, V> t2) {
          if (t1 == null || t1.isEmpty()) {
              return t2;
          } else if (t2 == null || t2.isEmpty()) {
              return t1;
          }
          
          Map<K,V> smaller;
          Map<K,V> larger;
          if (t1.size() <= t2.size()) {
              smaller = t1;
              larger = t2;
          } else {
              smaller = t2;
              larger = t1;
          }
          
          Map<K, V> ret = new HashMap<K, V>(larger);
          for (Map.Entry<K, V> entry: smaller.entrySet()) {
              V valOrNull = ret.get(entry.getKey());
              if (valOrNull != null) {
                  ret.put(entry.getKey(), sg.apply(valOrNull, entry.getValue()));
              } else {
                  ret.put(entry.getKey(), entry.getValue());
              }
          }
          return ret;
      }
  }
  
  static final class Product2<T1,T2> extends Function2<Tuple2<T1,T2>,Tuple2<T1,T2>,Tuple2<T1,T2>> implements SemiGroup<Tuple2<T1,T2>> {
    private final SemiGroup<T1> s1;
    private final SemiGroup<T2> s2;
    
    protected Product2(SemiGroup<T1> s1, SemiGroup<T2> s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public final Tuple2<T1, T2> apply(Tuple2<T1, T2> t1, Tuple2<T1, T2> t2) {
        return Tuple.of(s1.apply(Tuple.of(t1._1, t2._1)), s2.apply(Tuple.of(t1._2, t2._2)));
    }
  }
  
  static final class Product3<T1,T2,T3> extends Function2<Tuple3<T1,T2,T3>,Tuple3<T1,T2,T3>,Tuple3<T1,T2,T3>> implements SemiGroup<Tuple3<T1,T2,T3>> {
      private final SemiGroup<T1> s1;
      private final SemiGroup<T2> s2;
      private final SemiGroup<T3> s3;
      
      protected Product3(SemiGroup<T1> s1, SemiGroup<T2> s2, SemiGroup<T3> s3) {
          this.s1 = s1;
          this.s2 = s2;
          this.s3 = s3;
      }

      @Override
      public final Tuple3<T1, T2, T3> apply(Tuple3<T1, T2, T3> t1, Tuple3<T1, T2, T3> t2) {
          return Tuple.of(s1.apply(Tuple.of(t1._1, t2._1)), s2.apply(Tuple.of(t1._2, t2._2)), s3.apply(Tuple.of(t1._3, t2._3)));
      }
    }
  
  static final class Product4<T1,T2,T3,T4> extends Function2<Tuple4<T1,T2,T3,T4>,Tuple4<T1,T2,T3,T4>,Tuple4<T1,T2,T3,T4>> implements SemiGroup<Tuple4<T1,T2,T3,T4>> {
      private final SemiGroup<T1> s1;
      private final SemiGroup<T2> s2;
      private final SemiGroup<T3> s3;
      private final SemiGroup<T4> s4;
      
      protected Product4(SemiGroup<T1> s1, SemiGroup<T2> s2, SemiGroup<T3> s3, SemiGroup<T4> s4) {
          this.s1 = s1;
          this.s2 = s2;
          this.s3 = s3;
          this.s4 = s4;
      }

      @Override
      public final Tuple4<T1, T2, T3, T4> apply(Tuple4<T1, T2, T3, T4> t1, Tuple4<T1, T2, T3, T4> t2) {
          return Tuple.of(s1.apply(Tuple.of(t1._1, t2._1)), s2.apply(Tuple.of(t1._2, t2._2)), s3.apply(Tuple.of(t1._3, t2._3)), s4.apply(Tuple.of(t1._4, t2._4)));
      }
    }
  
    private static final SemiGroup<?> max = new Max<Integer>();
    private static final SemiGroup<?> min = new Min<Integer>();
    
    private static final SemiGroup<?> first = new First<Object>();
    private static final SemiGroup<?> last = new Last<Object>();
    
    private static final SemiGroup<Apply<Object,Object>> endo = new Endo<Object>();
}
