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
    
    public static class BooleanDisjunction extends SemiGroups.BooleanDisjunction implements Monoid<Boolean> {
      @Override
      public Boolean zero() {
          return false;
      }
  }

  public static class BooleanConjunction extends SemiGroups.BooleanConjunction implements Monoid<Boolean> {
      @Override
      public Boolean zero() {
          return true;
      }
  }

  public static class StringConcat extends SemiGroups.StringConcat implements Monoid<String> {
      @Override
      public String zero() {
          return "";
      }
  }

  public static class LongProduct extends SemiGroups.LongProduct implements Monoid<Long> {
      @Override
      public Long zero() {
          return 1l;
      }
  }

  public static class LongSum extends SemiGroups.LongSum implements Monoid<Long> {
      @Override
      public Long zero() {
          return 0l;
      }
  }

  public static class IntProduct extends SemiGroups.IntProduct implements Monoid<Integer> {
      @Override
      public Integer zero() {
          return 1;
      }
  }

  public static class IntSum extends SemiGroups.IntSum implements Monoid<Integer> {
      @Override
      public Integer zero() {
          return 0;
      }
  }
  
  public static class Endo<T> extends SemiGroups.Endo<T> implements Monoid<Apply<T,T>> {
      @Override
      public Apply<T, T> zero() {
          return Function.id();
      }
  }
  
  public static class SetUnion<T> extends SemiGroups.SetUnion<T> implements Monoid<Set<T>> {
      @Override
      public Set<T> zero() {
          return java.util.Collections.emptySet();
      }
  }
  
  public static class SetIntersection<T> extends SemiGroups.SetIntersection<T> implements Monoid<Set<T>> {
      private static final Set<?> AllContainingSet = new AllContainingSet<Object>();
      static final class AllContainingSet<T> implements Set<T> {
          @Override
          public int size() {
              return Integer.MAX_VALUE;
          }

          @Override
          public boolean isEmpty() {
              return false;
          }

          @Override
          public boolean contains(Object o) {
              return true;
          }

          @Override
          public boolean containsAll(Collection<?> c) {
              return true;
          }

          @Override
          public Iterator<T> iterator() {
              throw new UnsupportedOperationException();
          }

          @Override
          public Object[] toArray() {
              throw new UnsupportedOperationException();
          }

          @Override
          public <S> S[] toArray(S[] a) {
              throw new UnsupportedOperationException();
          }

          @Override
          public boolean add(T e) {
              throw new UnsupportedOperationException();
          }

          @Override
          public boolean remove(Object o) {
              throw new UnsupportedOperationException();
          }

          @Override
          public boolean addAll(Collection<? extends T> c) {
              throw new UnsupportedOperationException();
          }

          @Override
          public boolean retainAll(Collection<?> c) {
              throw new UnsupportedOperationException();
          }

          @Override
          public boolean removeAll(Collection<?> c) {
              throw new UnsupportedOperationException();
          }

          @Override
          public void clear() {
              throw new UnsupportedOperationException();
          }
      }

      @SuppressWarnings("unchecked")
      @Override
      public Set<T> zero() {
          return (Set<T>) AllContainingSet;
      }
  }
  
  public static class ComparatorConcat<T> extends SemiGroups.ComparatorConcat<T> implements Monoid<Comparator<T>> {
      @Override
      public Comparator<T> zero() {
          return new Comparator<T>() {
              @Override
              public int compare(T o1, T o2) {
                  return 0;
              }
          };
      }
  }
  
  public static class MapCombine<K,V> extends SemiGroups.MapCombine<K, V> implements Monoid<Map<K,V>> {
      public MapCombine(SemiGroup<V> sg) {
          super(sg);
      }

      @Override
      public Map<K, V> zero() {
          return emptyMap();
      }
  }

  public static final Monoid<Integer> intSum = new IntSum();

  public static final Monoid<Integer> intProduct = new IntProduct();

  public static final Monoid<Long> longSum = new LongSum();

  public static final Monoid<Long> longProduct = new LongProduct();

  public static final Monoid<String> stringConcat = new StringConcat();

  public static final Monoid<Boolean> booleanConjunction = new BooleanConjunction();

  public static final Monoid<Boolean> booleanDisjunction = new BooleanDisjunction();
  
  public static final <T> Monoid<Set<T>> setUnion() {
      return new SetUnion<T>();
  }
  
  public static final <T> Monoid<Set<T>> setIntersection() {
      return new SetIntersection<T>();
  }
  
  public static final <T> ComparatorConcat<T> comparatorConcat() {
      return new ComparatorConcat<T>();
  }
  
  public static final <K,V> MapCombine<K,V> mapCombine(SemiGroup<V> sg) {
    return new MapCombine<K,V>(sg);
  }
}
