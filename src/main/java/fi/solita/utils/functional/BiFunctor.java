package fi.solita.utils.functional;


public interface BiFunctor<F, A, B, C, D, FAC extends F, FBD extends F, FBC extends F, FAD extends F> {
    FBD bimap(Apply<? super A, ? extends B> f, Apply<? super C, ? extends D> f2, FAC e);

    FBC first(Apply<? super A, ? extends B> f, FAC e);

    FAD second(Apply<? super C, ? extends D> f, FAC e);
}