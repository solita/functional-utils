package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyMap;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class Monoids {
    private Monoids() {
    }
    
    public static final <T> Monoid<T> of(final SemiGroup<T> sg, final ApplyZero<T> zero) {
        return new Monoid<T>() {
            public T apply(Map.Entry<? extends T, ? extends T> t) {
                return sg.apply(t);
            }

            public T zero() {
                return zero.get();
            }
        };
    }
    
    public static final class BooleanDisjunction extends SemiGroups.BooleanDisjunction implements Monoid<Boolean> {
      public Boolean zero() {
          return false;
      }
  }

  public static final class BooleanConjunction extends SemiGroups.BooleanConjunction implements Monoid<Boolean> {
      public Boolean zero() {
          return true;
      }
  }

  public static final class StringConcat extends SemiGroups.StringConcat implements Monoid<String> {
      public String zero() {
          return "";
      }
  }
  
  public static final class IntProduct extends SemiGroups.IntProduct implements Monoid<Integer> {
      public Integer zero() {
          return 1;
      }
  }

  public static final class IntSum extends SemiGroups.IntSum implements Monoid<Integer> {
      public Integer zero() {
          return 0;
      }
  }

  public static final class LongProduct extends SemiGroups.LongProduct implements Monoid<Long> {
      public Long zero() {
          return 1l;
      }
  }

  public static final class LongSum extends SemiGroups.LongSum implements Monoid<Long> {
      public Long zero() {
          return 0l;
      }
  }
  
  public static final class DoubleProduct extends SemiGroups.DoubleProduct implements Monoid<Double> {
      public Double zero() {
          return 1.0;
      }
  }

  public static final class DoubleSum extends SemiGroups.DoubleSum implements Monoid<Double> {
      public Double zero() {
          return 0.0;
      }
  }
  
  public static final class Endo<T> extends SemiGroups.Endo<T> implements Monoid<Apply<T,T>> {
      public Apply<T, T> zero() {
          return Function.id();
      }
  }
  
  public static final class SetUnion<T> extends SemiGroups.SetUnion<T> implements Monoid<Set<T>> {
      public Set<T> zero() {
          return java.util.Collections.emptySet();
      }
  }
  
  public static final class SetIntersection<T> extends SemiGroups.SetIntersection<T> implements Monoid<Set<T>> {
      private static final Set<?> AllContainingSet = new AllContainingSet<Object>();
      static final class AllContainingSet<T> implements Set<T> {
          public int size() {
              return Integer.MAX_VALUE;
          }

          public boolean isEmpty() {
              return false;
          }

          public boolean contains(Object o) {
              return true;
          }

          public boolean containsAll(Collection<?> c) {
              return true;
          }

          public Iterator<T> iterator() {
              throw new UnsupportedOperationException();
          }

          public Object[] toArray() {
              throw new UnsupportedOperationException();
          }

          public <S> S[] toArray(S[] a) {
              throw new UnsupportedOperationException();
          }

          public boolean add(T e) {
              throw new UnsupportedOperationException();
          }

          public boolean remove(Object o) {
              throw new UnsupportedOperationException();
          }

          public boolean addAll(Collection<? extends T> c) {
              throw new UnsupportedOperationException();
          }

          public boolean retainAll(Collection<?> c) {
              throw new UnsupportedOperationException();
          }

          public boolean removeAll(Collection<?> c) {
              throw new UnsupportedOperationException();
          }

          public void clear() {
              throw new UnsupportedOperationException();
          }
      }

      @SuppressWarnings("unchecked")
      public Set<T> zero() {
          return (Set<T>) AllContainingSet;
      }
  }
  
  public static final class ComparatorConcat<T> extends SemiGroups.ComparatorConcat<T> implements Monoid<Comparator<T>> {
      public Comparator<T> zero() {
          return new Comparator<T>() {
              public int compare(T o1, T o2) {
                  return 0;
              }
          };
      }
  }
  
  public static final class MapCombine<K,V> extends SemiGroups.MapCombine<K, V> implements Monoid<Map<K,V>> {
      public MapCombine(SemiGroup<V> sg) {
          super(sg);
      }

      public Map<K, V> zero() {
          return emptyMap();
      }
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
  
  private static final Monoid<Set<Object>> setUnion = new SetUnion<Object>();
  private static final Monoid<Set<Object>> setIntersection = new SetIntersection<Object>();
  private static final ComparatorConcat<Object> comparatorConcat = new ComparatorConcat<Object>();
  
  @SuppressWarnings("unchecked")
  public static final <T> Monoid<Set<T>> setUnion() {
      return (Monoid<Set<T>>)(Object)setUnion;
  }
  
  @SuppressWarnings("unchecked")
  public static final <T> Monoid<Set<T>> setIntersection() {
      return (Monoid<Set<T>>)(Object)setIntersection;
  }
  
  @SuppressWarnings("unchecked")
  public static final <T> ComparatorConcat<T> comparatorConcat() {
      return (ComparatorConcat<T>)comparatorConcat;
  }
  
  public static final <K,V> MapCombine<K,V> mapCombine(SemiGroup<V> sg) {
    return new MapCombine<K,V>(sg);
  }
  
  public static final <T1,T2> Monoid<Tuple2<T1,T2>> product(Monoid<T1> m1, Monoid<T2> m2) {
      return Monoids.of(SemiGroups.product(m1, m2), Function.of(Tuple.of(m1.zero(), m2.zero())));
  }
  
  public static final <T1,T2,T3> Monoid<Tuple3<T1,T2,T3>> product(Monoid<T1> m1, Monoid<T2> m2, Monoid<T3> m3) {
      return Monoids.of(SemiGroups.product(m1, m2, m3), Function.of(Tuple.of(m1.zero(), m2.zero(), m3.zero())));
  }
  
  public static final <T1,T2,T3,T4> Monoid<Tuple4<T1,T2,T3,T4>> product(Monoid<T1> m1, Monoid<T2> m2, Monoid<T3> m3, Monoid<T4> m4) {
    return Monoids.of(SemiGroups.product(m1, m2, m3, m4), Function.of(Tuple.of(m1.zero(), m2.zero(), m3.zero(), m4.zero())));
  }
}
