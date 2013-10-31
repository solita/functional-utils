package fi.solita.utils.functional;

import java.io.Serializable;

import fi.solita.utils.codegen.NoMetadataGeneration;

@NoMetadataGeneration
public abstract class MultiParamFunction<T extends Tuple, R> implements Apply<T,R>, Serializable {
    public abstract Function1<T, R> tuppled();
    
    public final R apply(T t) {
        return tuppled().apply(t);
    }
}
