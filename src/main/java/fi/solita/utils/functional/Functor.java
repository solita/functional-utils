package fi.solita.utils.functional;


public interface Functor<F, A, B, FA extends F, FB extends F> {
    FB fmap(Apply<A,B> f, FA as);
}