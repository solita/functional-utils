package fi.solita.utils.functional;


public interface Functor<F, A, B, FA extends F, FB extends F> {
    FB fmap(Apply<? super A, ? extends B> f, FA as);
}