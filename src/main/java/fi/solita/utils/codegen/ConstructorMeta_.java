package fi.solita.utils.codegen;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.List;

import fi.solita.utils.functional.Apply;

public interface ConstructorMeta_<T, R> extends Apply<T, R>, Serializable {
    Constructor<R> getMember();
    List<Class<?>> getConstructorParameterTypes();
}
