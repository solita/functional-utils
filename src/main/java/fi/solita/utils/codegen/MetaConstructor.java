package fi.solita.utils.codegen;

import java.lang.reflect.Constructor;
import java.util.List;

public interface MetaConstructor<T, R> extends MetaMember<T, R> {
    Constructor<R> getMember();
    List<Class<?>> getConstructorParameterTypes();
}
