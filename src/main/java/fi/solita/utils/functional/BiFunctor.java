package fi.solita.utils.functional;


public interface BiFunctor<F, A, B, C, D, FAC extends F, FBD extends F, FBC extends F, FAD extends F> {
    FBD bimap(java.util.function.Function<? super A, ? extends B> f, java.util.function.Function<? super C, ? extends D> f2, FAC e);

    FBC first(java.util.function.Function<? super A, ? extends B> f, FAC e);

    FAD second(java.util.function.Function<? super C, ? extends D> f, FAC e);
}