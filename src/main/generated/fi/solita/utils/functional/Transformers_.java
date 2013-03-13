package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Transformers_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.String, fi.solita.utils.functional.Transformer<java.util.Map.Entry<?,?>,java.lang.String>> mkString = new fi.solita.utils.functional.Function1<java.lang.String, fi.solita.utils.functional.Transformer<java.util.Map.Entry<?,?>,java.lang.String>>() {
        public fi.solita.utils.functional.Transformer<java.util.Map.Entry<?,?>,java.lang.String> apply(java.lang.String delim) {
            return fi.solita.utils.functional.Transformers.mkString(delim);
        }
    };
    
    public static final <ID> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Identifiable<? extends ID>,ID>> id() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Identifiable<? extends ID>,ID>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Identifiable<? extends ID>,ID> apply() {
            return fi.solita.utils.functional.Transformers.<ID>id();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Option<T>,T>> optionGetOrElse() { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Option<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Option<T>,T> apply(T orElse) {
            return fi.solita.utils.functional.Transformers.<T>optionGetOrElse(orElse);
        }
    };
    }
    
    public static final <V> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<java.util.Map.Entry<?,V>,V>> values() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<java.util.Map.Entry<?,V>,V>>() {
        public fi.solita.utils.functional.Transformer<java.util.Map.Entry<?,V>,V> apply() {
            return fi.solita.utils.functional.Transformers.<V>values();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._7<T>,T>> _7() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._7<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._7<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_7();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._8<T>,T>> _8() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._8<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._8<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_8();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._11<T>,T>> _11() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._11<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._11<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_11();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._9<T>,T>> _9() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._9<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._9<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_9();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._10<T>,T>> _10() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._10<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._10<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_10();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._3<T>,T>> _3() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._3<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._3<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_3();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._4<T>,T>> _4() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._4<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._4<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_4();
        }
    };
    }
    
    public static final <LEFT> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<java.util.Map.Entry<? extends LEFT,?>,LEFT>> left() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<java.util.Map.Entry<? extends LEFT,?>,LEFT>>() {
        public fi.solita.utils.functional.Transformer<java.util.Map.Entry<? extends LEFT,?>,LEFT> apply() {
            return fi.solita.utils.functional.Transformers.<LEFT>left();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._5<T>,T>> _5() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._5<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._5<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_5();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._6<T>,T>> _6() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._6<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._6<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_6();
        }
    };
    }
    
    public static final <RIGHT> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<java.util.Map.Entry<?,? extends RIGHT>,RIGHT>> right() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<java.util.Map.Entry<?,? extends RIGHT>,RIGHT>>() {
        public fi.solita.utils.functional.Transformer<java.util.Map.Entry<?,? extends RIGHT>,RIGHT> apply() {
            return fi.solita.utils.functional.Transformers.<RIGHT>right();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._1<T>,T>> _1() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._1<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._1<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_1();
        }
    };
    }
    
    public static final <K, V> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<java.util.Map.Entry<K,V>,java.util.Map.Entry<V,K>>> flip() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<java.util.Map.Entry<K,V>,java.util.Map.Entry<V,K>>>() {
        public fi.solita.utils.functional.Transformer<java.util.Map.Entry<K,V>,java.util.Map.Entry<V,K>> apply() {
            return fi.solita.utils.functional.Transformers.<K, V>flip();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._2<T>,T>> _2() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._2<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._2<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_2();
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.String, fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String>> append = new fi.solita.utils.functional.Function1<java.lang.String, fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String>>() {
        public fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String> apply(java.lang.String suffix) {
            return fi.solita.utils.functional.Transformers.append(suffix);
        }
    };
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Option<T>,T>> optionGet() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Option<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Option<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>optionGet();
        }
    };
    }
    
    public static final <K> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<java.util.Map.Entry<K,?>,K>> keys() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<java.util.Map.Entry<K,?>,K>>() {
        public fi.solita.utils.functional.Transformer<java.util.Map.Entry<K,?>,K> apply() {
            return fi.solita.utils.functional.Transformers.<K>keys();
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.String, fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String>> prepend = new fi.solita.utils.functional.Function1<java.lang.String, fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String>>() {
        public fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String> apply(java.lang.String prefix) {
            return fi.solita.utils.functional.Transformers.prepend(prefix);
        }
    };
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._18<T>,T>> _18() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._18<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._18<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_18();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._19<T>,T>> _19() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._19<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._19<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_19();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._16<T>,T>> _16() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._16<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._16<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_16();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._17<T>,T>> _17() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._17<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._17<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_17();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._14<T>,T>> _14() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._14<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._14<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_14();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._15<T>,T>> _15() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._15<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._15<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_15();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._12<T>,T>> _12() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._12<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._12<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_12();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._13<T>,T>> _13() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._13<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._13<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_13();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._22<T>,T>> _22() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._22<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._22<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_22();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._21<T>,T>> _21() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._21<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._21<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_21();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._20<T>,T>> _20() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._20<T>,T>>() {
        public fi.solita.utils.functional.Transformer<fi.solita.utils.functional.Tuple._20<T>,T> apply() {
            return fi.solita.utils.functional.Transformers.<T>_20();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Integer, fi.solita.utils.functional.Transformer<java.lang.Iterable<T>,java.lang.Iterable<T>>> take() { return new fi.solita.utils.functional.Function1<java.lang.Integer, fi.solita.utils.functional.Transformer<java.lang.Iterable<T>,java.lang.Iterable<T>>>() {
        public fi.solita.utils.functional.Transformer<java.lang.Iterable<T>,java.lang.Iterable<T>> apply(java.lang.Integer amount) {
            return fi.solita.utils.functional.Transformers.<T>take(amount);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Integer, fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String>> prefix() { return new fi.solita.utils.functional.Function1<java.lang.Integer, fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String>>() {
        public fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String> apply(java.lang.Integer chars) {
            return fi.solita.utils.functional.Transformers.<T>prefix(chars);
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function2<java.util.regex.Pattern, java.lang.String, fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String>> replaceAll = new fi.solita.utils.functional.Function2<java.util.regex.Pattern, java.lang.String, fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String>>() {
        public fi.solita.utils.functional.Transformer<java.lang.String,java.lang.String> apply(java.util.regex.Pattern pattern, java.lang.String replacement) {
            return fi.solita.utils.functional.Transformers.replaceAll(pattern, replacement);
        }
    };
    

}
