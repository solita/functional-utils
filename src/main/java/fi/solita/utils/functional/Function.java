package fi.solita.utils.functional;

import java.util.Arrays;

public abstract class Function {
    private Function() {
    }

    public static enum GivenLater {
        _
    }
    public static enum GivenEvenLater {
        __
    }
    public static final GivenLater _ = GivenLater._;
    public static final GivenEvenLater __ = GivenEvenLater.__;
    
    public static final <R> Function0<R> of(final R r) {
        return new Function0<R>() {
            @Override
            public final R apply() {
                return r;
            }
        };
    }

    public static final <T, R> Function1<T, R> of(final Apply<T, R> apply) {
        return new Function1<T, R>() {
            @Override
            public final R apply(T t) {
                return apply.apply(t);
            }
        };
    }
    
    public static final <R> Function0<R> memoize(final Function0<R> apply) {
        return new Function0<R>() {
            private R r;
            @Override
            public final R apply() {
                if (r == null) {
                    r = apply.apply();
                }
                return r;
            }
        };
    }

    private static final Function1<?, ?> ID = new Function1<Object, Object>() {
        @Override
        public final Object apply(Object t) {
            return t;
        }
    };

    @SuppressWarnings("unchecked")
    public static final <T> Function1<T, T> id() {
        return (Function1<T, T>) ID;
    }
    
    public static final <T,R> Function1<T,R> constant(final R r) {
        return new Function1<T, R>() {
            @Override
            public final R apply(T ignored) {
                return r;
            }
        };
    }

    public static final <R, E extends Throwable> Function0.Ex1<R,E> throwing(final E e) {
        return new Function0.Ex1<R,E>() {
            @Override
            public final R apply() throws E {
                throw e;
            }
        };
    }

    public static final <A, B, C> Function1<B, Function1<A, C>> flip(final Apply<A, ? extends Apply<B, C>> f) {
        return new Function1<B, Function1<A, C>>() {
            @Override
            public final Function1<A, C> apply(final B g) {
                return new Function1<A, C>() {
                    @Override
                    public final C apply(A t) {
                        return f.apply(t).apply(g);
                    }
                };
            }
        };
    }

    public static final <A, B, C> Function2<A, B, C> uncurried(final Apply<A, ? extends Apply<B, C>> f) {
        return new Function2<A, B, C>() {
            @Override
            public final C apply(A t1, B t2) {
                return f.apply(t1).apply(t2);
            }
        };
    }

    public static final <A, B, C, D> Function3<A, B, C, D> uncurried2(final Apply<A, ? extends Apply<B, ? extends Apply<C, D>>> f) {
        return new Function3<A, B, C, D>() {
            @Override
            public final D apply(A t1, B t2, C t3) {
                return f.apply(t1).apply(t2).apply(t3);
            }
        };
    }

    public static final <A, B, C, D, E> Function4<A, B, C, D, E> uncurried3(final Apply<A, ? extends Apply<B, ? extends Apply<C, ? extends Apply<D, E>>>> f) {
        return new Function4<A, B, C, D, E>() {
            @Override
            public final E apply(A t1, B t2, C t3, D t4) {
                return f.apply(t1).apply(t2).apply(t3).apply(t4);
            }
        };
    }
}

class PartialApplicationHelper {
    static final Object[] replacePlaceholdersWithParams(Object[] paramsAndPlaceholders, Object... actualParamsForPlaceholders) {
        Object[] ret = Arrays.copyOf(paramsAndPlaceholders, paramsAndPlaceholders.length);
        int t = 0;
        for (int i = 0; i < ret.length; ++i) {
            if (ret[i] == Function._) {
                ret[i] = actualParamsForPlaceholders[t];
                t++;
                if (t == actualParamsForPlaceholders.length) {
                    break;
                }
            }
        }
        return ret;
    }
    
    static final <R> R doApply(Apply<Tuple,R> f, Object[] paramsAndPlaceholders, Object... params) {
        return f.apply(Tuple.of(replacePlaceholdersWithParams(paramsAndPlaceholders, params)));
    }
    
    private static final Tuple replacePlaceholders(Object[] args, Object[] t, Object... p) {
        Object[] ret = new Object[args.length];
        int ti = 0;
        int pi = 0;
        for (int i = 0; i < ret.length; ++i) {
            if (args[i] == Function._) {
                ret[i] = t[ti];
                ti++;
            } else {
                ret[i] = p[pi];
                pi++;
            }
        }
        return Tuple.of(ret);
    }
    
    @SuppressWarnings("unchecked")
    static final <R,FR extends Apply<?,R>> FR makeSecondFunc(final Apply<? extends Tuple,R> f, final Object[] args, final Object... t) {
        final Apply<Tuple,R> ff = ((Apply<Tuple,R>)f);
        switch (args.length - t.length) {
            case 1: return (FR) new Function1<Object,R>() {
                @Override
                public final R apply(Object p1) {
                    return ff.apply(replacePlaceholders(args, t, p1));
                }
            };
            case 2: return (FR) new Function2<Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2));
                }
            };
            case 3: return (FR) new Function3<Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3));
                }
            };
            case 4: return (FR) new Function4<Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4));
                }
            };
            case 5: return (FR) new Function5<Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5));
                }
            };
            case 6: return (FR) new Function6<Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6));
                }
            };
            case 7: return (FR) new Function7<Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7));
                }
            };
            case 8: return (FR) new Function8<Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8));
                }
            };
            case 9: return (FR) new Function9<Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9));
                }
            };
            case 10: return (FR) new Function10<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
                }
            };
            case 11: return (FR) new Function11<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
                }
            };
            case 12: return (FR) new Function12<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
                }
            };
            case 13: return (FR) new Function13<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13));
                }
            };
            case 14: return (FR) new Function14<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14));
                }
            };
            case 15: return (FR) new Function15<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15));
                }
            };
            case 16: return (FR) new Function16<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16));
                }
            };
            case 17: return (FR) new Function17<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17));
                }
            };
            case 18: return (FR) new Function18<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18));
                }
            };
            case 19: return (FR) new Function19<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19));
                }
            };
            case 20: return (FR) new Function20<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20));
                }
            };
            case 21: return (FR) new Function21<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21));
                }
            };
            case 22: return (FR) new Function22<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22));
                }
            };
            case 23: return (FR) new Function23<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23));
                }
            };
            case 24: return (FR) new Function24<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24));
                }
            };
            case 25: return (FR) new Function25<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25));
                }
            };
            case 26: return (FR) new Function26<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26));
                }
            };
        }
        throw new UnsupportedOperationException("Not implemented");
    }
}
