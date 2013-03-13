package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Functional_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <S, T> fi.solita.utils.functional.Function2<S[], fi.solita.utils.functional.Apply<? super S,? extends java.lang.Iterable<T>>, java.lang.Iterable<T>> flatMap() { return new fi.solita.utils.functional.Function2<S[], fi.solita.utils.functional.Apply<? super S,? extends java.lang.Iterable<T>>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(S[] elements, fi.solita.utils.functional.Apply<? super S,? extends java.lang.Iterable<T>> transformer) {
            return fi.solita.utils.functional.Functional.<S, T>flatMap(elements, transformer);
        }
    };
    }
    
    public static final <S, T> fi.solita.utils.functional.Function2<java.lang.Iterable<S>, fi.solita.utils.functional.Apply<? super S,? extends java.lang.Iterable<T>>, java.lang.Iterable<T>> flatMap1() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<S>, fi.solita.utils.functional.Apply<? super S,? extends java.lang.Iterable<T>>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<S> elements, fi.solita.utils.functional.Apply<? super S,? extends java.lang.Iterable<T>> transformer) {
            return fi.solita.utils.functional.Functional.<S, T>flatMap(elements, transformer);
        }
    };
    }
    
    public static final <T extends java.lang.Comparable<T>> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.lang.Iterable<T>> sort() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Functional.<T>sort(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.util.Comparator<? super T>, java.lang.Iterable<T>> sort1() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.util.Comparator<? super T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<T> elements, java.util.Comparator<? super T> comparator) {
            return fi.solita.utils.functional.Functional.<T>sort(elements, comparator);
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.Iterable<java.lang.Character>, java.lang.String> mkString = new fi.solita.utils.functional.Function1<java.lang.Iterable<java.lang.Character>, java.lang.String>() {
        public java.lang.String apply(java.lang.Iterable<java.lang.Character> elements) {
            return fi.solita.utils.functional.Functional.mkString(elements);
        }
    };
    
    public static final  fi.solita.utils.functional.Function2<java.lang.String, java.lang.Iterable<java.lang.String>, java.lang.String> mkString1 = new fi.solita.utils.functional.Function2<java.lang.String, java.lang.Iterable<java.lang.String>, java.lang.String>() {
        public java.lang.String apply(java.lang.String delim, java.lang.Iterable<java.lang.String> elements) {
            return fi.solita.utils.functional.Functional.mkString(delim, elements);
        }
    };
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, T> last() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, T>() {
        public T apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Functional.<T>last(elements);
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.Integer, java.lang.Iterable<java.lang.Integer>> range = new fi.solita.utils.functional.Function1<java.lang.Integer, java.lang.Iterable<java.lang.Integer>>() {
        public java.lang.Iterable<java.lang.Integer> apply(java.lang.Integer from) {
            return fi.solita.utils.functional.Functional.range(from);
        }
    };
    
    public static final  fi.solita.utils.functional.Function2<java.lang.Integer, java.lang.Integer, java.lang.Iterable<java.lang.Integer>> range1 = new fi.solita.utils.functional.Function2<java.lang.Integer, java.lang.Integer, java.lang.Iterable<java.lang.Integer>>() {
        public java.lang.Iterable<java.lang.Integer> apply(java.lang.Integer from, java.lang.Integer toInclusive) {
            return fi.solita.utils.functional.Functional.range(from, toInclusive);
        }
    };
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.lang.Integer, java.lang.Iterable<T>> drop() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.lang.Integer, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<T> elements, java.lang.Integer amount) {
            return fi.solita.utils.functional.Functional.<T>drop(elements, amount);
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.Integer, java.lang.Long> sum = new fi.solita.utils.functional.Function1<java.lang.Integer, java.lang.Long>() {
        public java.lang.Long apply(java.lang.Integer e1) {
            return fi.solita.utils.functional.Functional.sum(e1);
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<java.lang.Iterable<java.lang.Integer>, java.lang.Long> sum1 = new fi.solita.utils.functional.Function1<java.lang.Iterable<java.lang.Integer>, java.lang.Long>() {
        public java.lang.Long apply(java.lang.Iterable<java.lang.Integer> elements) {
            return fi.solita.utils.functional.Functional.sum(elements);
        }
    };
    
    public static final  fi.solita.utils.functional.Function2<java.lang.Integer, java.lang.Integer, java.lang.Long> sum2 = new fi.solita.utils.functional.Function2<java.lang.Integer, java.lang.Integer, java.lang.Long>() {
        public java.lang.Long apply(java.lang.Integer e1, java.lang.Integer e2) {
            return fi.solita.utils.functional.Functional.sum(e1, e2);
        }
    };
    
    public static final  fi.solita.utils.functional.Function3<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Long> sum3 = new fi.solita.utils.functional.Function3<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Long>() {
        public java.lang.Long apply(java.lang.Integer e1, java.lang.Integer e2, java.lang.Integer e3) {
            return fi.solita.utils.functional.Functional.sum(e1, e2, e3);
        }
    };
    
    public static final  fi.solita.utils.functional.Function4<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer[], java.lang.Long> sum4 = new fi.solita.utils.functional.Function4<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer[], java.lang.Long>() {
        public java.lang.Long apply(java.lang.Integer e1, java.lang.Integer e2, java.lang.Integer e3, java.lang.Integer[] rest) {
            return fi.solita.utils.functional.Functional.sum(e1, e2, e3, rest);
        }
    };
    
    public static final <T> fi.solita.utils.functional.Function2<T[], T, java.lang.Boolean> contains() { return new fi.solita.utils.functional.Function2<T[], T, java.lang.Boolean>() {
        public java.lang.Boolean apply(T[] elements, T element) {
            return fi.solita.utils.functional.Functional.<T>contains(elements, element);
        }
    };
    }
    
    public static final <T, E> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, T, java.lang.Boolean> contains1() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, T, java.lang.Boolean>() {
        public java.lang.Boolean apply(java.lang.Iterable<T> elements, T element) {
            return fi.solita.utils.functional.Functional.<T, E>contains(elements, element);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<T, java.lang.Iterable<T>> repeat() { return new fi.solita.utils.functional.Function1<T, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(T value) {
            return fi.solita.utils.functional.Functional.<T>repeat(value);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T, java.lang.Integer, java.lang.Iterable<T>> repeat1() { return new fi.solita.utils.functional.Function2<T, java.lang.Integer, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(T value, java.lang.Integer amount) {
            return fi.solita.utils.functional.Functional.<T>repeat(value, amount);
        }
    };
    }
    
    public static final <A> fi.solita.utils.functional.Function1<A[], java.lang.Iterable<java.util.Map.Entry<java.lang.Integer,A>>> zipWithIndex() { return new fi.solita.utils.functional.Function1<A[], java.lang.Iterable<java.util.Map.Entry<java.lang.Integer,A>>>() {
        public java.lang.Iterable<java.util.Map.Entry<java.lang.Integer,A>> apply(A[] a) {
            return fi.solita.utils.functional.Functional.<A>zipWithIndex(a);
        }
    };
    }
    
    public static final <A> fi.solita.utils.functional.Function1<java.lang.Iterable<A>, java.lang.Iterable<java.util.Map.Entry<java.lang.Integer,A>>> zipWithIndex1() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<A>, java.lang.Iterable<java.util.Map.Entry<java.lang.Integer,A>>>() {
        public java.lang.Iterable<java.util.Map.Entry<java.lang.Integer,A>> apply(java.lang.Iterable<A> a) {
            return fi.solita.utils.functional.Functional.<A>zipWithIndex(a);
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.Iterable<?>, java.lang.Boolean> isEmpty = new fi.solita.utils.functional.Function1<java.lang.Iterable<?>, java.lang.Boolean>() {
        public java.lang.Boolean apply(java.lang.Iterable<?> elements) {
            return fi.solita.utils.functional.Functional.isEmpty(elements);
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<java.lang.Iterable<java.lang.String>, java.lang.String> unlines = new fi.solita.utils.functional.Function1<java.lang.Iterable<java.lang.String>, java.lang.String>() {
        public java.lang.String apply(java.lang.Iterable<java.lang.String> elements) {
            return fi.solita.utils.functional.Functional.unlines(elements);
        }
    };
    
    public static final <T> fi.solita.utils.functional.Function1<T[][], java.lang.Iterable<T>> flatten() { return new fi.solita.utils.functional.Function1<T[][], java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(T[][] elements) {
            return fi.solita.utils.functional.Functional.<T>flatten(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Iterable<? extends T>[], java.lang.Iterable<T>> flatten1() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<? extends T>[], java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<? extends T>[] elements) {
            return fi.solita.utils.functional.Functional.<T>flatten(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Iterable<? extends java.lang.Iterable<? extends T>>, java.lang.Iterable<T>> flatten2() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<? extends java.lang.Iterable<? extends T>>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<? extends java.lang.Iterable<? extends T>> elements) {
            return fi.solita.utils.functional.Functional.<T>flatten(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, fi.solita.utils.functional.Option<T>> headOption() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Functional.<T>headOption(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Iterable<T>> takeWhile() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<T> elements, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> predicate) {
            return fi.solita.utils.functional.Functional.<T>takeWhile(elements, predicate);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T[], java.lang.Integer, java.lang.Iterable<java.util.List<T>>> grouped() { return new fi.solita.utils.functional.Function2<T[], java.lang.Integer, java.lang.Iterable<java.util.List<T>>>() {
        public java.lang.Iterable<java.util.List<T>> apply(T[] elements, java.lang.Integer size) {
            return fi.solita.utils.functional.Functional.<T>grouped(elements, size);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.lang.Integer, java.lang.Iterable<java.util.List<T>>> grouped1() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.lang.Integer, java.lang.Iterable<java.util.List<T>>>() {
        public java.lang.Iterable<java.util.List<T>> apply(java.lang.Iterable<T> elements, java.lang.Integer size) {
            return fi.solita.utils.functional.Functional.<T>grouped(elements, size);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.util.Set<T>, java.util.Set<T>, java.util.Set<T>> union() { return new fi.solita.utils.functional.Function2<java.util.Set<T>, java.util.Set<T>, java.util.Set<T>>() {
        public java.util.Set<T> apply(java.util.Set<T> e1, java.util.Set<T> e2) {
            return fi.solita.utils.functional.Functional.<T>union(e1, e2);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function3<java.util.Set<T>, java.util.Set<T>, java.util.Set<T>[], java.util.Set<T>> union1() { return new fi.solita.utils.functional.Function3<java.util.Set<T>, java.util.Set<T>, java.util.Set<T>[], java.util.Set<T>>() {
        public java.util.Set<T> apply(java.util.Set<T> e1, java.util.Set<T> e2, java.util.Set<T>[] e) {
            return fi.solita.utils.functional.Functional.<T>union(e1, e2, e);
        }
    };
    }
    
    public static final <S, T> fi.solita.utils.functional.Function2<S[], fi.solita.utils.functional.Apply<? super S,? extends T>, java.lang.Iterable<T>> map() { return new fi.solita.utils.functional.Function2<S[], fi.solita.utils.functional.Apply<? super S,? extends T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(S[] elements, fi.solita.utils.functional.Apply<? super S,? extends T> transformer) {
            return fi.solita.utils.functional.Functional.<S, T>map(elements, transformer);
        }
    };
    }
    
    public static final <S, T> fi.solita.utils.functional.Function2<java.lang.Iterable<S>, fi.solita.utils.functional.Apply<? super S,? extends T>, java.lang.Iterable<T>> map1() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<S>, fi.solita.utils.functional.Apply<? super S,? extends T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<S> elements, fi.solita.utils.functional.Apply<? super S,? extends T> transformer) {
            return fi.solita.utils.functional.Functional.<S, T>map(elements, transformer);
        }
    };
    }
    
    public static final <K1, V1, K2, V2> fi.solita.utils.functional.Function2<java.util.Map<K1,V1>, fi.solita.utils.functional.Apply<java.util.Map.Entry<K1,V1>,java.util.Map.Entry<K2,V2>>, java.util.Map<K2,V2>> map2() { return new fi.solita.utils.functional.Function2<java.util.Map<K1,V1>, fi.solita.utils.functional.Apply<java.util.Map.Entry<K1,V1>,java.util.Map.Entry<K2,V2>>, java.util.Map<K2,V2>>() {
        public java.util.Map<K2,V2> apply(java.util.Map<K1,V1> source, fi.solita.utils.functional.Apply<java.util.Map.Entry<K1,V1>,java.util.Map.Entry<K2,V2>> transformer) {
            return fi.solita.utils.functional.Functional.<K1, V1, K2, V2>map(source, transformer);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, T> head() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, T>() {
        public T apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Functional.<T>head(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Option<T>> find() { return new fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(T[] elements, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> filter) {
            return fi.solita.utils.functional.Functional.<T>find(elements, filter);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Option<T>> find1() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(java.lang.Iterable<T> elements, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> filter) {
            return fi.solita.utils.functional.Functional.<T>find(elements, filter);
        }
    };
    }
    
    public static final <K, V> fi.solita.utils.functional.Function2<java.util.Map<? extends K,V>, K, fi.solita.utils.functional.Option<V>> find2() { return new fi.solita.utils.functional.Function2<java.util.Map<? extends K,V>, K, fi.solita.utils.functional.Option<V>>() {
        public fi.solita.utils.functional.Option<V> apply(java.util.Map<? extends K,V> map, K key) {
            return fi.solita.utils.functional.Functional.<K, V>find(map, key);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T, java.lang.Iterable<? extends T>, java.lang.Iterable<T>> cons() { return new fi.solita.utils.functional.Function2<T, java.lang.Iterable<? extends T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(T element, java.lang.Iterable<? extends T> elements) {
            return fi.solita.utils.functional.Functional.<T>cons(element, elements);
        }
    };
    }
    
    public static final <T extends java.lang.Comparable<T>> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, fi.solita.utils.functional.Option<T>> min() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Functional.<T>min(elements);
        }
    };
    }
    
    public static final <T extends java.lang.Comparable<T>> fi.solita.utils.functional.Function2<T, T[], T> min1() { return new fi.solita.utils.functional.Function2<T, T[], T>() {
        public T apply(T e1, T[] elements) {
            return fi.solita.utils.functional.Functional.<T>min(e1, elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.util.Set<T>, java.util.Set<T>, java.util.Set<T>> intersection() { return new fi.solita.utils.functional.Function2<java.util.Set<T>, java.util.Set<T>, java.util.Set<T>>() {
        public java.util.Set<T> apply(java.util.Set<T> e1, java.util.Set<T> e2) {
            return fi.solita.utils.functional.Functional.<T>intersection(e1, e2);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function3<java.util.Set<T>, java.util.Set<T>, java.util.Set<T>[], java.util.Set<T>> intersection1() { return new fi.solita.utils.functional.Function3<java.util.Set<T>, java.util.Set<T>, java.util.Set<T>[], java.util.Set<T>>() {
        public java.util.Set<T> apply(java.util.Set<T> e1, java.util.Set<T> e2, java.util.Set<T>[] e) {
            return fi.solita.utils.functional.Functional.<T>intersection(e1, e2, e);
        }
    };
    }
    
    public static final <A, B> fi.solita.utils.functional.Function2<A[], B[], java.lang.Iterable<java.util.Map.Entry<A,B>>> zip() { return new fi.solita.utils.functional.Function2<A[], B[], java.lang.Iterable<java.util.Map.Entry<A,B>>>() {
        public java.lang.Iterable<java.util.Map.Entry<A,B>> apply(A[] a, B[] b) {
            return fi.solita.utils.functional.Functional.<A, B>zip(a, b);
        }
    };
    }
    
    public static final <A, B> fi.solita.utils.functional.Function2<A[], java.lang.Iterable<B>, java.lang.Iterable<java.util.Map.Entry<A,B>>> zip1() { return new fi.solita.utils.functional.Function2<A[], java.lang.Iterable<B>, java.lang.Iterable<java.util.Map.Entry<A,B>>>() {
        public java.lang.Iterable<java.util.Map.Entry<A,B>> apply(A[] a, java.lang.Iterable<B> b) {
            return fi.solita.utils.functional.Functional.<A, B>zip(a, b);
        }
    };
    }
    
    public static final <A, B> fi.solita.utils.functional.Function2<java.lang.Iterable<A>, B[], java.lang.Iterable<java.util.Map.Entry<A,B>>> zip2() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<A>, B[], java.lang.Iterable<java.util.Map.Entry<A,B>>>() {
        public java.lang.Iterable<java.util.Map.Entry<A,B>> apply(java.lang.Iterable<A> a, B[] b) {
            return fi.solita.utils.functional.Functional.<A, B>zip(a, b);
        }
    };
    }
    
    public static final <A, B> fi.solita.utils.functional.Function2<java.lang.Iterable<A>, java.lang.Iterable<B>, java.lang.Iterable<java.util.Map.Entry<A,B>>> zip3() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<A>, java.lang.Iterable<B>, java.lang.Iterable<java.util.Map.Entry<A,B>>>() {
        public java.lang.Iterable<java.util.Map.Entry<A,B>> apply(java.lang.Iterable<A> a, java.lang.Iterable<B> b) {
            return fi.solita.utils.functional.Functional.<A, B>zip(a, b);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Iterable<T>> dropWhile() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<T> elements, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> predicate) {
            return fi.solita.utils.functional.Functional.<T>dropWhile(elements, predicate);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.lang.Iterable<T>> reverse() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Functional.<T>reverse(elements);
        }
    };
    }
    
    public static final <T extends java.lang.Comparable<T>> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, fi.solita.utils.functional.Option<T>> max() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Functional.<T>max(elements);
        }
    };
    }
    
    public static final <T extends java.lang.Comparable<T>> fi.solita.utils.functional.Function2<T, T[], T> max1() { return new fi.solita.utils.functional.Function2<T, T[], T>() {
        public T apply(T e1, T[] elements) {
            return fi.solita.utils.functional.Functional.<T>max(e1, elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, fi.solita.utils.functional.Option<T>> lastOption() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Functional.<T>lastOption(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.lang.Iterable<T>> init() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Functional.<T>init(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Apply<fi.solita.utils.functional.Tuple2<T,T>,T>, fi.solita.utils.functional.Option<T>> fold() { return new fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Apply<fi.solita.utils.functional.Tuple2<T,T>,T>, fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(T[] elements, fi.solita.utils.functional.Apply<fi.solita.utils.functional.Tuple2<T,T>,T> f) {
            return fi.solita.utils.functional.Functional.<T>fold(elements, f);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<? extends T>, fi.solita.utils.functional.Apply<fi.solita.utils.functional.Tuple2<T,T>,T>, fi.solita.utils.functional.Option<T>> fold1() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<? extends T>, fi.solita.utils.functional.Apply<fi.solita.utils.functional.Tuple2<T,T>,T>, fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(java.lang.Iterable<? extends T> elements, fi.solita.utils.functional.Apply<fi.solita.utils.functional.Tuple2<T,T>,T> f) {
            return fi.solita.utils.functional.Functional.<T>fold(elements, f);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T[], T[], java.lang.Iterable<T>> concat() { return new fi.solita.utils.functional.Function2<T[], T[], java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(T[] elements1, T[] elements2) {
            return fi.solita.utils.functional.Functional.<T>concat(elements1, elements2);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T[], java.lang.Iterable<? extends T>, java.lang.Iterable<T>> concat1() { return new fi.solita.utils.functional.Function2<T[], java.lang.Iterable<? extends T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(T[] elements1, java.lang.Iterable<? extends T> elements2) {
            return fi.solita.utils.functional.Functional.<T>concat(elements1, elements2);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<? extends T>, T[], java.lang.Iterable<T>> concat2() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<? extends T>, T[], java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<? extends T> elements1, T[] elements2) {
            return fi.solita.utils.functional.Functional.<T>concat(elements1, elements2);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<T>> concat3() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<? extends T> elements1, java.lang.Iterable<? extends T> elements2) {
            return fi.solita.utils.functional.Functional.<T>concat(elements1, elements2);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function3<java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<T>> concat4() { return new fi.solita.utils.functional.Function3<java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<? extends T> elements1, java.lang.Iterable<? extends T> elements2, java.lang.Iterable<? extends T> elements3) {
            return fi.solita.utils.functional.Functional.<T>concat(elements1, elements2, elements3);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function4<java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<T>> concat5() { return new fi.solita.utils.functional.Function4<java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<? extends T> elements1, java.lang.Iterable<? extends T> elements2, java.lang.Iterable<? extends T> elements3, java.lang.Iterable<? extends T> elements4) {
            return fi.solita.utils.functional.Functional.<T>concat(elements1, elements2, elements3, elements4);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function6<java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>[], java.lang.Iterable<T>> concat6() { return new fi.solita.utils.functional.Function6<java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>, java.lang.Iterable<? extends T>[], java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<? extends T> elements1, java.lang.Iterable<? extends T> elements2, java.lang.Iterable<? extends T> elements3, java.lang.Iterable<? extends T> elements4, java.lang.Iterable<? extends T> elements5, java.lang.Iterable<? extends T>[] rest) {
            return fi.solita.utils.functional.Functional.<T>concat(elements1, elements2, elements3, elements4, elements5, rest);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.lang.Iterable<T>> tail() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Functional.<T>tail(elements);
        }
    };
    }
    
    public static final <T extends fi.solita.utils.functional.SemiGroup<T>> fi.solita.utils.functional.Function1<T, T> reduce() { return new fi.solita.utils.functional.Function1<T, T>() {
        public T apply(T e1) {
            return fi.solita.utils.functional.Functional.<T>reduce(e1);
        }
    };
    }
    
    public static final <T extends fi.solita.utils.functional.SemiGroup<T>> fi.solita.utils.functional.Function1<T[], fi.solita.utils.functional.Option<T>> reduce1() { return new fi.solita.utils.functional.Function1<T[], fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(T[] elements) {
            return fi.solita.utils.functional.Functional.<T>reduce(elements);
        }
    };
    }
    
    public static final <T extends fi.solita.utils.functional.SemiGroup<T>> fi.solita.utils.functional.Function1<java.lang.Iterable<? extends T>, fi.solita.utils.functional.Option<T>> reduce2() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<? extends T>, fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(java.lang.Iterable<? extends T> elements) {
            return fi.solita.utils.functional.Functional.<T>reduce(elements);
        }
    };
    }
    
    public static final <T extends fi.solita.utils.functional.SemiGroup<T>> fi.solita.utils.functional.Function2<T, T, T> reduce3() { return new fi.solita.utils.functional.Function2<T, T, T>() {
        public T apply(T e1, T e2) {
            return fi.solita.utils.functional.Functional.<T>reduce(e1, e2);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Monoid<T>, T> reduce4() { return new fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Monoid<T>, T>() {
        public T apply(T[] elements, fi.solita.utils.functional.Monoid<T> m) {
            return fi.solita.utils.functional.Functional.<T>reduce(elements, m);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<? extends T>, fi.solita.utils.functional.Monoid<T>, T> reduce5() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<? extends T>, fi.solita.utils.functional.Monoid<T>, T>() {
        public T apply(java.lang.Iterable<? extends T> elements, fi.solita.utils.functional.Monoid<T> m) {
            return fi.solita.utils.functional.Functional.<T>reduce(elements, m);
        }
    };
    }
    
    public static final <T extends fi.solita.utils.functional.SemiGroup<T>> fi.solita.utils.functional.Function3<T, T, T, T> reduce6() { return new fi.solita.utils.functional.Function3<T, T, T, T>() {
        public T apply(T e1, T e2, T e3) {
            return fi.solita.utils.functional.Functional.<T>reduce(e1, e2, e3);
        }
    };
    }
    
    public static final <T extends fi.solita.utils.functional.SemiGroup<T>> fi.solita.utils.functional.Function4<T, T, T, T, T> reduce7() { return new fi.solita.utils.functional.Function4<T, T, T, T, T>() {
        public T apply(T e1, T e2, T e3, T e4) {
            return fi.solita.utils.functional.Functional.<T>reduce(e1, e2, e3, e4);
        }
    };
    }
    
    public static final <T extends fi.solita.utils.functional.SemiGroup<T>> fi.solita.utils.functional.Function5<T, T, T, T, T[], T> reduce8() { return new fi.solita.utils.functional.Function5<T, T, T, T, T[], T>() {
        public T apply(T e1, T e2, T e3, T e4, T[] elements) {
            return fi.solita.utils.functional.Functional.<T>reduce(e1, e2, e3, e4, elements);
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.Iterable<?>, java.lang.Integer> size = new fi.solita.utils.functional.Function1<java.lang.Iterable<?>, java.lang.Integer>() {
        public java.lang.Integer apply(java.lang.Iterable<?> elements) {
            return fi.solita.utils.functional.Functional.size(elements);
        }
    };
    
    public static final <G, T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,G>, java.util.Map<G,java.util.List<T>>> groupBy() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,G>, java.util.Map<G,java.util.List<T>>>() {
        public java.util.Map<G,java.util.List<T>> apply(java.lang.Iterable<T> elements, fi.solita.utils.functional.Apply<? super T,G> f) {
            return fi.solita.utils.functional.Functional.<G, T>groupBy(elements, f);
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.Integer, java.lang.Long> product = new fi.solita.utils.functional.Function1<java.lang.Integer, java.lang.Long>() {
        public java.lang.Long apply(java.lang.Integer e1) {
            return fi.solita.utils.functional.Functional.product(e1);
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<java.lang.Iterable<java.lang.Integer>, java.lang.Long> product1 = new fi.solita.utils.functional.Function1<java.lang.Iterable<java.lang.Integer>, java.lang.Long>() {
        public java.lang.Long apply(java.lang.Iterable<java.lang.Integer> elements) {
            return fi.solita.utils.functional.Functional.product(elements);
        }
    };
    
    public static final  fi.solita.utils.functional.Function2<java.lang.Integer, java.lang.Integer, java.lang.Long> product2 = new fi.solita.utils.functional.Function2<java.lang.Integer, java.lang.Integer, java.lang.Long>() {
        public java.lang.Long apply(java.lang.Integer e1, java.lang.Integer e2) {
            return fi.solita.utils.functional.Functional.product(e1, e2);
        }
    };
    
    public static final  fi.solita.utils.functional.Function3<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Long> product3 = new fi.solita.utils.functional.Function3<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Long>() {
        public java.lang.Long apply(java.lang.Integer e1, java.lang.Integer e2, java.lang.Integer e3) {
            return fi.solita.utils.functional.Functional.product(e1, e2, e3);
        }
    };
    
    public static final  fi.solita.utils.functional.Function4<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer[], java.lang.Long> product4 = new fi.solita.utils.functional.Function4<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer[], java.lang.Long>() {
        public java.lang.Long apply(java.lang.Integer e1, java.lang.Integer e2, java.lang.Integer e3, java.lang.Integer[] rest) {
            return fi.solita.utils.functional.Functional.product(e1, e2, e3, rest);
        }
    };
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.util.Collection<T>, java.lang.Iterable<T>> subtract() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.util.Collection<T>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<T> a, java.util.Collection<T> b) {
            return fi.solita.utils.functional.Functional.<T>subtract(a, b);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.lang.Integer, java.lang.Iterable<T>> take() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.lang.Integer, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<T> elements, java.lang.Integer amount) {
            return fi.solita.utils.functional.Functional.<T>take(elements, amount);
        }
    };
    }
    
    public static final <T, R> fi.solita.utils.functional.Function2<java.lang.Iterable<? extends fi.solita.utils.functional.Apply<? super T,? extends R>>, T, java.lang.Iterable<R>> sequence() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<? extends fi.solita.utils.functional.Apply<? super T,? extends R>>, T, java.lang.Iterable<R>>() {
        public java.lang.Iterable<R> apply(java.lang.Iterable<? extends fi.solita.utils.functional.Apply<? super T,? extends R>> elements, T t) {
            return fi.solita.utils.functional.Functional.<T, R>sequence(elements, t);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Boolean> forAll() { return new fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Boolean>() {
        public java.lang.Boolean apply(T[] elements, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> filter) {
            return fi.solita.utils.functional.Functional.<T>forAll(elements, filter);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Boolean> forAll1() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Boolean>() {
        public java.lang.Boolean apply(java.lang.Iterable<T> elements, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> filter) {
            return fi.solita.utils.functional.Functional.<T>forAll(elements, filter);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Apply<T,java.lang.Boolean>, java.lang.Boolean> exists() { return new fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Apply<T,java.lang.Boolean>, java.lang.Boolean>() {
        public java.lang.Boolean apply(T[] elements, fi.solita.utils.functional.Apply<T,java.lang.Boolean> filter) {
            return fi.solita.utils.functional.Functional.<T>exists(elements, filter);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Boolean> exists1() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Boolean>() {
        public java.lang.Boolean apply(java.lang.Iterable<T> elements, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> filter) {
            return fi.solita.utils.functional.Functional.<T>exists(elements, filter);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Iterable<T>> filter() { return new fi.solita.utils.functional.Function2<T[], fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(T[] elements, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> filter) {
            return fi.solita.utils.functional.Functional.<T>filter(elements, filter);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Iterable<T>> filter1() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, java.lang.Iterable<T>>() {
        public java.lang.Iterable<T> apply(java.lang.Iterable<T> elements, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> filter) {
            return fi.solita.utils.functional.Functional.<T>filter(elements, filter);
        }
    };
    }
    
    public static final <T, E> fi.solita.utils.functional.Function2<java.util.Map<T,E>, fi.solita.utils.functional.Apply<java.util.Map.Entry<? super T,? super E>,java.lang.Boolean>, java.util.Map<T,E>> filter2() { return new fi.solita.utils.functional.Function2<java.util.Map<T,E>, fi.solita.utils.functional.Apply<java.util.Map.Entry<? super T,? super E>,java.lang.Boolean>, java.util.Map<T,E>>() {
        public java.util.Map<T,E> apply(java.util.Map<T,E> map, fi.solita.utils.functional.Apply<java.util.Map.Entry<? super T,? super E>,java.lang.Boolean> filter) {
            return fi.solita.utils.functional.Functional.<T, E>filter(map, filter);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Pair<java.lang.Iterable<T>,java.lang.Iterable<T>>> span() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Pair<java.lang.Iterable<T>,java.lang.Iterable<T>>>() {
        public fi.solita.utils.functional.Pair<java.lang.Iterable<T>,java.lang.Iterable<T>> apply(java.lang.Iterable<T> elements, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> predicate) {
            return fi.solita.utils.functional.Functional.<T>span(elements, predicate);
        }
    };
    }
    

}
