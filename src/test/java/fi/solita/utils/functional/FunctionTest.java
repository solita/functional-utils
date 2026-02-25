package fi.solita.utils.functional;

import static fi.solita.utils.functional.Function.__;
import static fi.solita.utils.functional.Function.___;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FunctionTest {

    @Test
    public void testOfValue() {
        Function0<Integer> constant = Function.of(42);
        assertEquals(Integer.valueOf(42), constant.apply());
        assertEquals(Integer.valueOf(42), constant.apply());
    }
    
    @Test
    public void testOfValueWithNull() {
        Function0<String> constant = Function.of((String) null);
        assertNull(constant.apply());
    }
    
    @Test
    public void testConsumer1() {
        final StringBuilder result = new StringBuilder();
        Function1<String, Void> consumer = Function.consumer(new ApplyVoid<String>() {
            @Override
            public void accept(String t) {
                result.append(t);
            }
        });
        
        assertNull(consumer.apply("hello"));
        assertEquals("hello", result.toString());
    }
    
    @Test
    public void testConsumer2() {
        final StringBuilder result = new StringBuilder();
        Function2<String, String, Void> consumer = Function.consumer(new ApplyVoid2<String, String>() {
            @Override
            public void accept(String t1, String t2) {
                result.append(t1).append(t2);
            }
        });
        
        assertNull(consumer.apply("hello", "world"));
        assertEquals("helloworld", result.toString());
    }
    
    @Test
    public void testConsumer3() {
        final StringBuilder result = new StringBuilder();
        Function3<String, String, String, Void> consumer = Function.consumer(new ApplyVoid3<String, String, String>() {
            @Override
            public void accept(String t1, String t2, String t3) {
                result.append(t1).append(t2).append(t3);
            }
        });
        
        assertNull(consumer.apply("a", "b", "c"));
        assertEquals("abc", result.toString());
    }
    
    @Test
    public void testConsumer4() {
        final StringBuilder result = new StringBuilder();
        Function4<String, String, String, String, Void> consumer = Function.consumer(new ApplyVoid4<String, String, String, String>() {
            @Override
            public void accept(String t1, String t2, String t3, String t4) {
                result.append(t1).append(t2).append(t3).append(t4);
            }
        });
        
        assertNull(consumer.apply("a", "b", "c", "d"));
        assertEquals("abcd", result.toString());
    }
    
    @Test
    public void testConsumer5() {
        final StringBuilder result = new StringBuilder();
        Function5<String, String, String, String, String, Void> consumer = Function.consumer(new ApplyVoid5<String, String, String, String, String>() {
            @Override
            public void accept(String t1, String t2, String t3, String t4, String t5) {
                result.append(t1).append(t2).append(t3).append(t4).append(t5);
            }
        });
        
        assertNull(consumer.apply("a", "b", "c", "d", "e"));
        assertEquals("abcde", result.toString());
    }
    
    @Test
    public void testConsumer6() {
        final StringBuilder result = new StringBuilder();
        Function6<String, String, String, String, String, String, Void> consumer = Function.consumer(new ApplyVoid6<String, String, String, String, String, String>() {
            @Override
            public void accept(String t1, String t2, String t3, String t4, String t5, String t6) {
                result.append(t1).append(t2).append(t3).append(t4).append(t5).append(t6);
            }
        });
        
        assertNull(consumer.apply("a", "b", "c", "d", "e", "f"));
        assertEquals("abcdef", result.toString());
    }
    
    @Test
    public void testOfApply() {
        Function1<String, Integer> function = Function.of(new Apply<String, Integer>() {
            @Override
            public Integer apply(String t) {
                return t.length();
            }
        });
        
        assertEquals(Integer.valueOf(5), function.apply("hello"));
    }
    
    @Test
    public void testOfApply2() {
        Function2<Integer, Integer, Integer> function = Function.of(new Apply2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) {
                return t1 + t2;
            }
        });
        
        assertEquals(Integer.valueOf(7), function.apply(3, 4));
    }
    
    @Test
    public void testOfApply3() {
        Function3<Integer, Integer, Integer, Integer> function = Function.of(new Apply3<Integer, Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2, Integer t3) {
                return t1 + t2 + t3;
            }
        });
        
        assertEquals(Integer.valueOf(15), function.apply(5, 6, 4));
    }
    
    @Test
    public void testOfApply4() {
        Function4<Integer, Integer, Integer, Integer, Integer> function = Function.of(new Apply4<Integer, Integer, Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2, Integer t3, Integer t4) {
                return t1 + t2 + t3 + t4;
            }
        });
        
        assertEquals(Integer.valueOf(22), function.apply(5, 6, 4, 7));
    }
    
    @Test
    public void testOfApply5() {
        Function5<Integer, Integer, Integer, Integer, Integer, Integer> function = Function.of(new Apply5<Integer, Integer, Integer, Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5) {
                return t1 + t2 + t3 + t4 + t5;
            }
        });
        
        assertEquals(Integer.valueOf(25), function.apply(5, 6, 4, 7, 3));
    }
    
    @Test
    public void testOfApply6() {
        Function6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> function = Function.of(new Apply6<Integer, Integer, Integer, Integer, Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6) {
                return t1 + t2 + t3 + t4 + t5 + t6;
            }
        });
        
        assertEquals(Integer.valueOf(27), function.apply(5, 6, 4, 7, 3, 2));
    }
    
    @Test
    public void testOfApply7() {
        Function7<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function = Function.of(new Apply7<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7) {
                return t1 + t2 + t3 + t4 + t5 + t6 + t7;
            }
        });
        
        assertEquals(Integer.valueOf(35), function.apply(5, 6, 4, 7, 3, 2, 8));
    }
    
    @Test
    public void testOfApply8() {
        Function8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function = Function.of(new Apply8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8) {
                return t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8;
            }
        });
        
        assertEquals(Integer.valueOf(44), function.apply(5, 6, 4, 7, 3, 2, 8, 9));
    }
    
    @Test
    public void testOfApply9() {
        Function9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function = Function.of(new Apply9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8, Integer t9) {
                return t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9;
            }
        });
        
        assertEquals(Integer.valueOf(45), function.apply(5, 6, 4, 7, 3, 2, 8, 9, 1));
    }
    
    @Test
    public void testOfApply10() {
        Function10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function = Function.of(new Apply10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8, Integer t9, Integer t10) {
                return t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10;
            }
        });
        
        assertEquals(Integer.valueOf(55), function.apply(5, 6, 4, 7, 3, 2, 8, 9, 1, 10));
    }
    
    @Test
    public void testMemoize() {
        final int[] callCount = {0};
        Function0<String> memoized = Function.memoize(new ApplyZero<String>() {
            @Override
            public String get() {
                callCount[0]++;
                return "computed";
            }
        });
        
        assertEquals("computed", memoized.apply());
        assertEquals(1, callCount[0]);
        
        // Second call should return memoized value
        assertEquals("computed", memoized.apply());
        assertEquals(1, callCount[0]); // Still 1, not 2
    }
    
    @Test
    public void testMemoizeWithNull() {
        final int[] callCount = {0};
        Function0<String> memoized = Function.memoize(new ApplyZero<String>() {
            @Override
            public String get() {
                callCount[0]++;
                return null;
            }
        });
        
        assertNull(memoized.apply());
        assertEquals(1, callCount[0]);
        
        assertNull(memoized.apply());
        // Due to the null check, this will call again
        assertEquals(2, callCount[0]);
    }
    
    @Test
    public void testId() {
        Function1<String, String> id = Function.id();
        
        assertEquals("hello", id.apply("hello"));
        assertEquals("world", id.apply("world"));
        
        Integer num = 42;
        Function1<Integer, Integer> idInt = Function.id();
        assertSame(num, idInt.apply(num));
    }
    
    @Test
    public void testIdReturnsSameInstance() {
        Function1<String, String> id1 = Function.id();
        Function1<String, String> id2 = Function.id();
        assertSame(id1, id2);
    }
    
    @Test
    public void testConstant() {
        Function1<String, Integer> constant = Function.constant(42);
        
        assertEquals(Integer.valueOf(42), constant.apply("ignored"));
        assertEquals(Integer.valueOf(42), constant.apply("anything"));
        assertEquals(Integer.valueOf(42), constant.apply(null));
    }
    
    @Test
    public void testConstantWithNull() {
        Function1<String, String> constant = Function.constant(null);
        
        assertNull(constant.apply("anything"));
    }
    
    @Test
    public void testThrowing() {
        final RuntimeException exception = new RuntimeException("test exception");
        Function0.Ex1<String, RuntimeException> throwing = Function.throwing(exception);
        
        try {
            throwing.apply();
            fail("Should have thrown exception");
        } catch (RuntimeException e) {
            assertSame(exception, e);
        }
    }
    
    @Test
    public void testFlip() {
        Apply<Integer, Apply<String, String>> original = new Apply<Integer, Apply<String, String>>() {
            @Override
            public Apply<String, String> apply(final Integer i) {
                return new Apply<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s + i;
                    }
                };
            }
        };
        
        Function1<String, Function1<Integer, String>> flipped = Function.flip(original);
        
        assertEquals("hello5", flipped.apply("hello").apply(5));
    }
    
    @Test
    public void testUncurried() {
        Apply<Integer, Apply<String, String>> curried = new Apply<Integer, Apply<String, String>>() {
            @Override
            public Apply<String, String> apply(final Integer i) {
                return new Apply<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s + i;
                    }
                };
            }
        };
        
        Function2<Integer, String, String> uncurried = Function.uncurried(curried);
        
        assertEquals("hello5", uncurried.apply(5, "hello"));
    }
    
    @Test
    public void testUncurried2() {
        Apply<Integer, Apply<String, Apply<Double, String>>> curried = 
            new Apply<Integer, Apply<String, Apply<Double, String>>>() {
                @Override
                public Apply<String, Apply<Double, String>> apply(final Integer i) {
                    return new Apply<String, Apply<Double, String>>() {
                        @Override
                        public Apply<Double, String> apply(final String s) {
                            return new Apply<Double, String>() {
                                @Override
                                public String apply(Double d) {
                                    return s + i + d;
                                }
                            };
                        }
                    };
                }
            };
        
        Function3<Integer, String, Double, String> uncurried = Function.uncurried2(curried);
        
        assertEquals("hello53.14", uncurried.apply(5, "hello", 3.14));
    }
    
    @Test
    public void testUncurried3() {
        Apply<Integer, Apply<String, Apply<Double, Apply<Boolean, String>>>> curried = 
            new Apply<Integer, Apply<String, Apply<Double, Apply<Boolean, String>>>>() {
                @Override
                public Apply<String, Apply<Double, Apply<Boolean, String>>> apply(final Integer i) {
                    return new Apply<String, Apply<Double, Apply<Boolean, String>>>() {
                        @Override
                        public Apply<Double, Apply<Boolean, String>> apply(final String s) {
                            return new Apply<Double, Apply<Boolean, String>>() {
                                @Override
                                public Apply<Boolean, String> apply(final Double d) {
                                    return new Apply<Boolean, String>() {
                                        @Override
                                        public String apply(Boolean b) {
                                            return s + i + d + b;
                                        }
                                    };
                                }
                            };
                        }
                    };
                }
            };
        
        Function4<Integer, String, Double, Boolean, String> uncurried = Function.uncurried3(curried);
        
        assertEquals("hello53.14true", uncurried.apply(5, "hello", 3.14, true));
    }
    
    @Test
    public void testAp2() {
        Apply2<Integer, Integer, Integer> add = new Apply2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };
        
        Function1<Integer, Integer> add5 = Function.ap(add, 5);
        
        assertEquals(Integer.valueOf(8), add5.apply(3));
        assertEquals(Integer.valueOf(15), add5.apply(10));
    }
    
    @Test
    public void testAp3_1arg() {
        Apply3<Integer, Integer, Integer, Integer> add3 = new Apply3<Integer, Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b, Integer c) {
                return a + b + c;
            }
        };
        
        Function2<Integer, Integer, Integer> add5 = Function.ap(add3, 5);
        
        assertEquals(Integer.valueOf(11), add5.apply(3, 3));
    }
    
    @Test
    public void testAp3_2args() {
        Apply3<Integer, Integer, Integer, Integer> add3 = new Apply3<Integer, Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b, Integer c) {
                return a + b + c;
            }
        };
        
        Function1<Integer, Integer> add8 = Function.ap(add3, 5, 3);
        
        assertEquals(Integer.valueOf(11), add8.apply(3));
    }
    
    @Test
    public void testAp4_1arg() {
        Apply4<Integer, Integer, Integer, Integer, Integer> add4 = 
            new Apply4<Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d) {
                    return a + b + c + d;
                }
            };
        
        Function3<Integer, Integer, Integer, Integer> add5 = Function.ap(add4, 5);
        
        assertEquals(Integer.valueOf(12), add5.apply(2, 3, 2));
    }
    
    @Test
    public void testAp4_2args() {
        Apply4<Integer, Integer, Integer, Integer, Integer> add4 = 
            new Apply4<Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d) {
                    return a + b + c + d;
                }
            };
        
        Function2<Integer, Integer, Integer> add8 = Function.ap(add4, 5, 3);
        
        assertEquals(Integer.valueOf(12), add8.apply(2, 2));
    }
    
    @Test
    public void testAp4_3args() {
        Apply4<Integer, Integer, Integer, Integer, Integer> add4 = 
            new Apply4<Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d) {
                    return a + b + c + d;
                }
            };
        
        Function1<Integer, Integer> add10 = Function.ap(add4, 5, 3, 2);
        
        assertEquals(Integer.valueOf(12), add10.apply(2));
    }
    
    @Test
    public void testAp5_1arg() {
        Apply5<Integer, Integer, Integer, Integer, Integer, Integer> add5 = 
            new Apply5<Integer, Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d, Integer e) {
                    return a + b + c + d + e;
                }
            };
        
        Function4<Integer, Integer, Integer, Integer, Integer> add1 = Function.ap(add5, 1);
        
        assertEquals(Integer.valueOf(15), add1.apply(2, 3, 4, 5));
    }
    
    @Test
    public void testAp5_2args() {
        Apply5<Integer, Integer, Integer, Integer, Integer, Integer> add5 = 
            new Apply5<Integer, Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d, Integer e) {
                    return a + b + c + d + e;
                }
            };
        
        Function3<Integer, Integer, Integer, Integer> add3 = Function.ap(add5, 1, 2);
        
        assertEquals(Integer.valueOf(15), add3.apply(3, 4, 5));
    }
    
    @Test
    public void testAp5_3args() {
        Apply5<Integer, Integer, Integer, Integer, Integer, Integer> add5 = 
            new Apply5<Integer, Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d, Integer e) {
                    return a + b + c + d + e;
                }
            };
        
        Function2<Integer, Integer, Integer> add6 = Function.ap(add5, 1, 2, 3);
        
        assertEquals(Integer.valueOf(15), add6.apply(4, 5));
    }
    
    @Test
    public void testAp5_4args() {
        Apply5<Integer, Integer, Integer, Integer, Integer, Integer> add5 = 
            new Apply5<Integer, Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d, Integer e) {
                    return a + b + c + d + e;
                }
            };
        
        Function1<Integer, Integer> add10 = Function.ap(add5, 1, 2, 3, 4);
        
        assertEquals(Integer.valueOf(15), add10.apply(5));
    }
    
    @Test
    public void testAp6_1arg() {
        Apply6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> add6 = 
            new Apply6<Integer, Integer, Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d, Integer e, Integer f) {
                    return a + b + c + d + e + f;
                }
            };
        
        Function5<Integer, Integer, Integer, Integer, Integer, Integer> add1 = Function.ap(add6, 1);
        
        assertEquals(Integer.valueOf(21), add1.apply(2, 3, 4, 5, 6));
    }
    
    @Test
    public void testAp6_2args() {
        Apply6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> add6 = 
            new Apply6<Integer, Integer, Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d, Integer e, Integer f) {
                    return a + b + c + d + e + f;
                }
            };
        
        Function4<Integer, Integer, Integer, Integer, Integer> add3 = Function.ap(add6, 1, 2);
        
        assertEquals(Integer.valueOf(21), add3.apply(3, 4, 5, 6));
    }
    
    @Test
    public void testAp6_3args() {
        Apply6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> add6 = 
            new Apply6<Integer, Integer, Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d, Integer e, Integer f) {
                    return a + b + c + d + e + f;
                }
            };
        
        Function3<Integer, Integer, Integer, Integer> add6partial = Function.ap(add6, 1, 2, 3);
        
        assertEquals(Integer.valueOf(21), add6partial.apply(4, 5, 6));
    }
    
    @Test
    public void testAp6_4args() {
        Apply6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> add6 = 
            new Apply6<Integer, Integer, Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d, Integer e, Integer f) {
                    return a + b + c + d + e + f;
                }
            };
        
        Function2<Integer, Integer, Integer> add10 = Function.ap(add6, 1, 2, 3, 4);
        
        assertEquals(Integer.valueOf(21), add10.apply(5, 6));
    }
    
    @Test
    public void testAp6_5args() {
        Apply6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> add6 = 
            new Apply6<Integer, Integer, Integer, Integer, Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b, Integer c, Integer d, Integer e, Integer f) {
                    return a + b + c + d + e + f;
                }
            };
        
        Function1<Integer, Integer> add15 = Function.ap(add6, 1, 2, 3, 4, 5);
        
        assertEquals(Integer.valueOf(21), add15.apply(6));
    }
    
    @Test
    public void testPlaceholderConstants() {
        assertNotNull(__);
        assertNotNull(___);
        assertSame(__, Function.__);
        assertSame(___, Function.___);
    }
}
