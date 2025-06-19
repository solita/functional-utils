package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.emptyMap;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Monoids {
    
    /**
     * @return a monoid constructed from {@code semigroup} and {@code zeroElement}.
     */
    public static final <T> Monoid<T> of(final SemiGroup<T> semigroup, final ApplyZero<T> zeroElement) {
        return new Monoid<T>() {
            public T apply(Map.Entry<? extends T, ? extends T> t) {
                return semigroup.apply(t);
            }

            public T zero() {
                return zeroElement.get();
            }
        };
    }
    
    
    
    
    public static final Monoid<Integer> intSum = new IntSum();

    public static final Monoid<Integer> intProduct = new IntProduct();
    
    public static final Monoid<Long> longSum = new LongSum();

    public static final Monoid<Long> longProduct = new LongProduct();
    
    public static final Monoid<Double> doubleSum = new DoubleSum();

    public static final Monoid<Double> doubleProduct = new DoubleProduct();

    public static final Monoid<String> stringConcat = new StringConcat();

    public static final Monoid<Boolean> booleanConjunction = new BooleanConjunction();

    public static final Monoid<Boolean> booleanDisjunction = new BooleanDisjunction();
    
    @SuppressWarnings("unchecked")
    public static final <T> Monoid<Apply<T,T>> endo() {
        return (Monoid<Apply<T,T>>)(Object)endo;
    }

    /**
     * Preserves iteration order
     */
    @SuppressWarnings("unchecked")
    public static final <T> Monoid<Set<T>> setUnion() {
        return (Monoid<Set<T>>)(Object)setUnion;
    }
    
    /**
     * Tries all comparators sequentially as long as the comparison results in equality.
     */
    @SuppressWarnings("unchecked")
    public static final <T> Monoid<Comparator<T>> comparatorConcat() {
        return (ComparatorConcat<T>)comparatorConcat;
    }
    
    public static final <T> Monoid<Iterable<T>> iterableConcat() {
        return new Monoid<Iterable<T>>() {
            public Iterable<T> apply(Map.Entry<? extends Iterable<T>, ? extends Iterable<T>> t) {
                return concat(t.getKey(), t.getValue());
            }

            public Iterable<T> zero() {
                return emptyList();
            }
        };
    }
    
    public static final <T> Monoid<Collection<T>> collectionConcat() {
        return new Monoid<Collection<T>>() {
            public Collection<T> apply(Map.Entry<? extends Collection<T>, ? extends Collection<T>> t) {
                if (t.getKey() == null || t.getKey().isEmpty()) {
                    return t.getValue();
                } else if (t.getValue() == null || t.getValue().isEmpty()) {
                    return t.getKey();
                }
                return newList(concat(t.getKey(), t.getValue()));
            }

            public Collection<T> zero() {
                return emptyList();
            }
        };
    }
    
    public static final <T> Monoid<List<T>> listConcat() {
        return new Monoid<List<T>>() {
            public List<T> apply(Map.Entry<? extends List<T>, ? extends List<T>> t) {
                if (t.getKey() == null || t.getKey().isEmpty()) {
                    return t.getValue();
                } else if (t.getValue() == null || t.getValue().isEmpty()) {
                    return t.getKey();
                }
                return newList(concat(t.getKey(), t.getValue()));
            }

            public List<T> zero() {
                return emptyList();
            }
        };
    }
    
    /**
     * Combines values of the same key with the semigroup binary operation.
     */
    public static final <K,V> Monoid<Map<K,V>> mapCombine(SemiGroup<V> sg) {
      return new MapCombine<K,V>(sg);
    }
    
    /**
     * Tuple of monoids is a monoid.
     */
    public static final <T1,T2> Monoid<Tuple2<T1,T2>> product(Monoid<T1> m1, Monoid<T2> m2) {
        return Monoids.of(SemiGroups.product(m1, m2), Function.of(Tuple.of(m1.zero(), m2.zero())));
    }
    
    /**
     * Tuple of monoids is a monoid.
     */
    public static final <T1,T2,T3> Monoid<Tuple3<T1,T2,T3>> product(Monoid<T1> m1, Monoid<T2> m2, Monoid<T3> m3) {
        return Monoids.of(SemiGroups.product(m1, m2, m3), Function.of(Tuple.of(m1.zero(), m2.zero(), m3.zero())));
    }
    
    /**
     * Tuple of monoids is a monoid.
     */
    public static final <T1,T2,T3,T4> Monoid<Tuple4<T1,T2,T3,T4>> product(Monoid<T1> m1, Monoid<T2> m2, Monoid<T3> m3, Monoid<T4> m4) {
        return Monoids.of(SemiGroups.product(m1, m2, m3, m4), Function.of(Tuple.of(m1.zero(), m2.zero(), m3.zero(), m4.zero())));
    }
    
    
    
    
    
  static final class BooleanDisjunction extends SemiGroups.BooleanDisjunction implements Monoid<Boolean> {
      public Boolean zero() {
          return false;
      }
  }

  static final class BooleanConjunction extends SemiGroups.BooleanConjunction implements Monoid<Boolean> {
      public Boolean zero() {
          return true;
      }
  }

  static final class StringConcat extends SemiGroups.StringConcat implements Monoid<String> {
      public String zero() {
          return "";
      }
  }
  
  static final class IntProduct extends SemiGroups.IntProduct implements Monoid<Integer> {
      public Integer zero() {
          return 1;
      }
  }

  static final class IntSum extends SemiGroups.IntSum implements Monoid<Integer> {
      public Integer zero() {
          return 0;
      }
  }

  static final class LongProduct extends SemiGroups.LongProduct implements Monoid<Long> {
      public Long zero() {
          return 1l;
      }
  }

  static final class LongSum extends SemiGroups.LongSum implements Monoid<Long> {
      public Long zero() {
          return 0l;
      }
  }
  
  static final class DoubleProduct extends SemiGroups.DoubleProduct implements Monoid<Double> {
      public Double zero() {
          return 1.0;
      }
  }

  static final class DoubleSum extends SemiGroups.DoubleSum implements Monoid<Double> {
      public Double zero() {
          return 0.0;
      }
  }
  
  static final class Endo<T> extends SemiGroups.Endo<T> implements Monoid<Apply<T,T>> {
      public Apply<T, T> zero() {
          return Function.id();
      }
  }
  
  static final class SetUnion<T> extends SemiGroups.SetUnion<T> implements Monoid<Set<T>> {
      public Set<T> zero() {
          return java.util.Collections.emptySet();
      }
  }
  
  static final class ComparatorConcat<T> extends SemiGroups.ComparatorConcat<T> implements Monoid<Comparator<T>> {
      public Comparator<T> zero() {
          return new Comparator<T>() {
              public int compare(T o1, T o2) {
                  return 0;
              }
          };
      }
  }
  
  static final class MapCombine<K,V> extends SemiGroups.MapCombine<K, V> implements Monoid<Map<K,V>> {
      public MapCombine(SemiGroup<V> sg) {
          super(sg);
      }

      public Map<K, V> zero() {
          return emptyMap();
      }
  }
  
  private static final Monoid<Set<Object>> setUnion = new SetUnion<Object>();
  private static final ComparatorConcat<Object> comparatorConcat = new ComparatorConcat<Object>();
  private static final Monoid<Apply<Object,Object>> endo = new Endo<Object>();
  
}
