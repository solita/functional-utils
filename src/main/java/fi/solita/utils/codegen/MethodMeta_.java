package fi.solita.utils.codegen;

import java.io.Serializable;
import java.lang.reflect.Method;

import fi.solita.utils.functional.Apply;

public interface MethodMeta_<T, R> extends Apply<T, R>, Serializable {
    String getName();
    Method getMember();
}
