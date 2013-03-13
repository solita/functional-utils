package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Collections_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.CharSequence, java.lang.Iterable<java.lang.Character>> it = new fi.solita.utils.functional.Function1<java.lang.CharSequence, java.lang.Iterable<java.lang.Character>>() {
        public java.lang.Iterable<java.lang.Character> apply(java.lang.CharSequence charSeq) {
            return fi.solita.utils.functional.Collections.it(charSeq);
        }
    };
    
    public static final <T> fi.solita.utils.functional.Function0<java.util.Set<T>> newSet() { return new fi.solita.utils.functional.Function0<java.util.Set<T>>() {
        public java.util.Set<T> apply() {
            return fi.solita.utils.functional.Collections.<T>newSet();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<T[], java.util.Set<T>> newSet1() { return new fi.solita.utils.functional.Function1<T[], java.util.Set<T>>() {
        public java.util.Set<T> apply(T[] elements) {
            return fi.solita.utils.functional.Collections.<T>newSet(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.util.Set<T>> newSet2() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.util.Set<T>>() {
        public java.util.Set<T> apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Collections.<T>newSet(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Integer, java.util.List<T>> newListOfSize() { return new fi.solita.utils.functional.Function1<java.lang.Integer, java.util.List<T>>() {
        public java.util.List<T> apply(java.lang.Integer initialSize) {
            return fi.solita.utils.functional.Collections.<T>newListOfSize(initialSize);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.lang.Class<T>, T[]> newArray() { return new fi.solita.utils.functional.Function2<java.lang.Iterable<T>, java.lang.Class<T>, T[]>() {
        public T[] apply(java.lang.Iterable<T> elements, java.lang.Class<T> clazz) {
            return fi.solita.utils.functional.Collections.<T>newArray(elements, clazz);
        }
    };
    }
    
    public static final <K, V> fi.solita.utils.functional.Function0<java.util.Map<K,V>> newMap() { return new fi.solita.utils.functional.Function0<java.util.Map<K,V>>() {
        public java.util.Map<K,V> apply() {
            return fi.solita.utils.functional.Collections.<K, V>newMap();
        }
    };
    }
    
    public static final <K, V> fi.solita.utils.functional.Function1<java.util.Map.Entry<? extends K,? extends V>, java.util.Map<K,V>> newMap1() { return new fi.solita.utils.functional.Function1<java.util.Map.Entry<? extends K,? extends V>, java.util.Map<K,V>>() {
        public java.util.Map<K,V> apply(java.util.Map.Entry<? extends K,? extends V> e1) {
            return fi.solita.utils.functional.Collections.<K, V>newMap(e1);
        }
    };
    }
    
    public static final <K, V> fi.solita.utils.functional.Function1<java.lang.Iterable<? extends java.util.Map.Entry<? extends K,? extends V>>, java.util.Map<K,V>> newMap2() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<? extends java.util.Map.Entry<? extends K,? extends V>>, java.util.Map<K,V>>() {
        public java.util.Map<K,V> apply(java.lang.Iterable<? extends java.util.Map.Entry<? extends K,? extends V>> elements) {
            return fi.solita.utils.functional.Collections.<K, V>newMap(elements);
        }
    };
    }
    
    public static final <K, V> fi.solita.utils.functional.Function1<java.util.Map.Entry<? extends K,? extends V>[], java.util.Map<K,V>> newMap3() { return new fi.solita.utils.functional.Function1<java.util.Map.Entry<? extends K,? extends V>[], java.util.Map<K,V>>() {
        public java.util.Map<K,V> apply(java.util.Map.Entry<? extends K,? extends V>[] elements) {
            return fi.solita.utils.functional.Collections.<K, V>newMap(elements);
        }
    };
    }
    
    public static final <K, V> fi.solita.utils.functional.Function2<java.util.Map.Entry<? extends K,? extends V>, java.util.Map.Entry<? extends K,? extends V>, java.util.Map<K,V>> newMap4() { return new fi.solita.utils.functional.Function2<java.util.Map.Entry<? extends K,? extends V>, java.util.Map.Entry<? extends K,? extends V>, java.util.Map<K,V>>() {
        public java.util.Map<K,V> apply(java.util.Map.Entry<? extends K,? extends V> e1, java.util.Map.Entry<? extends K,? extends V> e2) {
            return fi.solita.utils.functional.Collections.<K, V>newMap(e1, e2);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Integer, java.util.Set<T>> newSetOfSize() { return new fi.solita.utils.functional.Function1<java.lang.Integer, java.util.Set<T>>() {
        public java.util.Set<T> apply(java.lang.Integer initialSize) {
            return fi.solita.utils.functional.Collections.<T>newSetOfSize(initialSize);
        }
    };
    }
    
    public static final <K, V> fi.solita.utils.functional.Function1<java.lang.Integer, java.util.Map<K,V>> newMapOfSize() { return new fi.solita.utils.functional.Function1<java.lang.Integer, java.util.Map<K,V>>() {
        public java.util.Map<K,V> apply(java.lang.Integer initialSize) {
            return fi.solita.utils.functional.Collections.<K, V>newMapOfSize(initialSize);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<java.util.List<T>> newList() { return new fi.solita.utils.functional.Function0<java.util.List<T>>() {
        public java.util.List<T> apply() {
            return fi.solita.utils.functional.Collections.<T>newList();
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<boolean[], java.util.List<java.lang.Boolean>> newList1 = new fi.solita.utils.functional.Function1<boolean[], java.util.List<java.lang.Boolean>>() {
        public java.util.List<java.lang.Boolean> apply(boolean[] array) {
            return fi.solita.utils.functional.Collections.newList(array);
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<byte[], java.util.List<java.lang.Byte>> newList2 = new fi.solita.utils.functional.Function1<byte[], java.util.List<java.lang.Byte>>() {
        public java.util.List<java.lang.Byte> apply(byte[] array) {
            return fi.solita.utils.functional.Collections.newList(array);
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<char[], java.util.List<java.lang.Character>> newList3 = new fi.solita.utils.functional.Function1<char[], java.util.List<java.lang.Character>>() {
        public java.util.List<java.lang.Character> apply(char[] array) {
            return fi.solita.utils.functional.Collections.newList(array);
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<double[], java.util.List<java.lang.Double>> newList4 = new fi.solita.utils.functional.Function1<double[], java.util.List<java.lang.Double>>() {
        public java.util.List<java.lang.Double> apply(double[] array) {
            return fi.solita.utils.functional.Collections.newList(array);
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<float[], java.util.List<java.lang.Float>> newList5 = new fi.solita.utils.functional.Function1<float[], java.util.List<java.lang.Float>>() {
        public java.util.List<java.lang.Float> apply(float[] array) {
            return fi.solita.utils.functional.Collections.newList(array);
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<int[], java.util.List<java.lang.Integer>> newList6 = new fi.solita.utils.functional.Function1<int[], java.util.List<java.lang.Integer>>() {
        public java.util.List<java.lang.Integer> apply(int[] array) {
            return fi.solita.utils.functional.Collections.newList(array);
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<long[], java.util.List<java.lang.Long>> newList7 = new fi.solita.utils.functional.Function1<long[], java.util.List<java.lang.Long>>() {
        public java.util.List<java.lang.Long> apply(long[] array) {
            return fi.solita.utils.functional.Collections.newList(array);
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<short[], java.util.List<java.lang.Short>> newList8 = new fi.solita.utils.functional.Function1<short[], java.util.List<java.lang.Short>>() {
        public java.util.List<java.lang.Short> apply(short[] array) {
            return fi.solita.utils.functional.Collections.newList(array);
        }
    };
    
    public static final <T> fi.solita.utils.functional.Function1<T, java.util.List<T>> newList9() { return new fi.solita.utils.functional.Function1<T, java.util.List<T>>() {
        public java.util.List<T> apply(T e1) {
            return fi.solita.utils.functional.Collections.<T>newList(e1);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<T[], java.util.List<T>> newList10() { return new fi.solita.utils.functional.Function1<T[], java.util.List<T>>() {
        public java.util.List<T> apply(T[] elements) {
            return fi.solita.utils.functional.Collections.<T>newList(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.util.List<T>> newList11() { return new fi.solita.utils.functional.Function1<java.lang.Iterable<T>, java.util.List<T>>() {
        public java.util.List<T> apply(java.lang.Iterable<T> elements) {
            return fi.solita.utils.functional.Collections.<T>newList(elements);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function2<T, T, java.util.List<T>> newList12() { return new fi.solita.utils.functional.Function2<T, T, java.util.List<T>>() {
        public java.util.List<T> apply(T e1, T e2) {
            return fi.solita.utils.functional.Collections.<T>newList(e1, e2);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function3<T, T, T, java.util.List<T>> newList13() { return new fi.solita.utils.functional.Function3<T, T, T, java.util.List<T>>() {
        public java.util.List<T> apply(T e1, T e2, T e3) {
            return fi.solita.utils.functional.Collections.<T>newList(e1, e2, e3);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function4<T, T, T, T, java.util.List<T>> newList14() { return new fi.solita.utils.functional.Function4<T, T, T, T, java.util.List<T>>() {
        public java.util.List<T> apply(T e1, T e2, T e3, T e4) {
            return fi.solita.utils.functional.Collections.<T>newList(e1, e2, e3, e4);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function5<T, T, T, T, T, java.util.List<T>> newList15() { return new fi.solita.utils.functional.Function5<T, T, T, T, T, java.util.List<T>>() {
        public java.util.List<T> apply(T e1, T e2, T e3, T e4, T e5) {
            return fi.solita.utils.functional.Collections.<T>newList(e1, e2, e3, e4, e5);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function6<T, T, T, T, T, T, java.util.List<T>> newList16() { return new fi.solita.utils.functional.Function6<T, T, T, T, T, T, java.util.List<T>>() {
        public java.util.List<T> apply(T e1, T e2, T e3, T e4, T e5, T e6) {
            return fi.solita.utils.functional.Collections.<T>newList(e1, e2, e3, e4, e5, e6);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function7<T, T, T, T, T, T, T[], java.util.List<T>> newList17() { return new fi.solita.utils.functional.Function7<T, T, T, T, T, T, T[], java.util.List<T>>() {
        public java.util.List<T> apply(T e1, T e2, T e3, T e4, T e5, T e6, T[] elements) {
            return fi.solita.utils.functional.Collections.<T>newList(e1, e2, e3, e4, e5, e6, elements);
        }
    };
    }
    

}
