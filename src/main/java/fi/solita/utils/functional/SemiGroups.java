package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newMapOfSize;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.filter;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public abstract class SemiGroups {
    private SemiGroups() {
		}
    
    public static class BooleanDisjunction extends Function2<Boolean,Boolean,Boolean> implements SemiGroup<Boolean> {
      @Override
      public Boolean apply(Boolean first, Boolean second) {
          return first || second;
      }
  }

  public static class BooleanConjunction extends Function2<Boolean,Boolean,Boolean> implements SemiGroup<Boolean> {
      @Override
      public Boolean apply(Boolean first, Boolean second) {
          return first && second;
      }
  }

  public static class StringConcat extends Function2<String,String,String> implements SemiGroup<String> {
      @Override
      public String apply(String first, String second) {
          return first + second;
      }
  }

  public static class LongProduct extends Function2<Long,Long,Long> implements SemiGroup<Long> {
      @Override
      public Long apply(Long first, Long second) {
          return first * second;
      }
  }

  public static class LongSum extends Function2<Long,Long,Long> implements SemiGroup<Long> {
      @Override
      public Long apply(Long first, Long second) {
          return first + second;
      }
  }

  public static class IntProduct extends Function2<Integer,Integer,Integer> implements SemiGroup<Integer> {
      @Override
      public Integer apply(Integer first, Integer second) {
          return first * second;
      }
  }

  public static class IntSum extends Function2<Integer,Integer,Integer> implements SemiGroup<Integer> {
      @Override
      public Integer apply(Integer first, Integer second) {
          return first + second;
      }
  }
  
  public static class SetUnion<T> extends Function2<Set<T>,Set<T>,Set<T>> implements SemiGroup<Set<T>> {
      @Override
      public Set<T> apply(Set<T> first, Set<T> second) {
          if (first instanceof Monoids.SetIntersection.AllContainingSet) {
              return second;
          }
          if (second instanceof Monoids.SetIntersection.AllContainingSet) {
              return first;
          }
          return newSet(concat(first, second));
      }
  }
  
  public static class SetIntersection<T> extends Function2<Set<T>,Set<T>,Set<T>> implements SemiGroup<Set<T>> {
      @Override
      public Set<T> apply(Set<T> first, final Set<T> second) {
          if (first instanceof Monoids.SetIntersection.AllContainingSet) {
              return second;
          }
          if (second instanceof Monoids.SetIntersection.AllContainingSet) {
              return first;
          }
          return newSet(filter(first, new Predicate<T>() {
              @Override
              public boolean accept(T candidate) {
                  return second.contains(candidate);
              }
          }));
      }
  }
  
  public static class ComparatorConcat<T> extends Function2<Comparator<T>,Comparator<T>,Comparator<T>> implements SemiGroup<Comparator<T>> {
      @Override
      public Comparator<T> apply(final Comparator<T> first, final Comparator<T> second) {
          return new Comparator<T>() {
              @Override
              public int compare(T o1, T o2) {
                  int c = first.compare(o1, o2);
                  return c != 0 ? c : second.compare(o1, o2);
              }
          };
      }
  }
  
  public static class MapCombine<K, V> extends Function2<Map<K,V>,Map<K,V>,Map<K,V>> implements SemiGroup<Map<K,V>> {
      private final SemiGroup<V> sg;

			public MapCombine(SemiGroup<V> sg) {
          this.sg = sg;
			}
  	
      @Override
      public Map<K, V> apply(Map<K, V> t1, Map<K, V> t2) {
          Map<K, V> ret = newMapOfSize(t1.size() + t2.size());
          ret.putAll(t1);
          for (Map.Entry<K, V> entry: t2.entrySet()) {
          	  V valOrNull = ret.get(entry.getKey());
              if (valOrNull != null) {
                  ret.put(entry.getKey(), sg.apply(Tuple.of(valOrNull, entry.getValue())));
              } else {
                  ret.put(entry.getKey(), entry.getValue());
              }
          }
          return ret;
      }
  }
    
    public static final SemiGroup<Integer> intSum = Monoids.intSum;

    public static final SemiGroup<Integer> intProduct = Monoids.intProduct;

    public static final SemiGroup<Long> longSum = Monoids.longSum;

    public static final SemiGroup<Long> longProduct = Monoids.longProduct;

    public static final SemiGroup<String> stringConcat = Monoids.stringConcat;

    public static final SemiGroup<Boolean> booleanConjunction = Monoids.booleanConjunction;

    public static final SemiGroup<Boolean> booleanDisjunction = Monoids.booleanDisjunction;
    
    public static final <T> SemiGroup<Set<T>> setUnion() {
        return Monoids.setUnion();
    }
    
    public static final <T> SemiGroup<Set<T>> setIntersection() {
        return Monoids.setIntersection();
    }
    
    public static final <T> SemiGroup<Comparator<T>> comparatorConcat() {
        return Monoids.comparatorConcat();
    }
    
    public static final <K,V> SemiGroup<Map<K,V>> mapCombine(SemiGroup<V> sg) {
        return new MapCombine<K, V>(sg);
    }
}
