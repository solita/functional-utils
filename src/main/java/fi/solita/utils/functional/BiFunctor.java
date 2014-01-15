package fi.solita.utils.functional;


public interface BiFunctor<F, A, B, C, D, FAC extends F, FBD extends F, FBC extends F, FAD extends F> {
    FBD bimap(Apply<A, B> f, Apply<C, D> f2, FAC e);

    FBC first(Apply<A, B> f, FAC e);

    FAD second(Apply<C, D> f, FAC e);
}