package fi.solita.utils.codegen;

import java.io.Serializable;
import java.lang.reflect.Constructor;

import fi.solita.utils.functional.Apply;

public interface ConstructorMeta_<T, R> extends Apply<T, R>, Serializable {
    Constructor<R> getMember();
}
