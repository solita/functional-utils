package fi.solita.utils.functional;


public interface BiFunctor<F, A, B, C, D, FAC extends F, FBD extends F, FBC extends F, FAB extends F> {
    FBD bimap(Apply<A, B> f, Apply<C, D> f2, FAC e);

    FBC first(Apply<A, B> f, FAC e);

    FAC second(Apply<B, C> f, FAB e);
}