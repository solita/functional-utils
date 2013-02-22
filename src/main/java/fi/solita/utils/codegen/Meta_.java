package fi.solita.utils.codegen;

import java.io.Serializable;
import java.lang.reflect.Field;

import fi.solita.utils.functional.Apply;

public interface Meta_<T, R> extends Apply<T, R>, Serializable {
    String getName();
    Field getMember();
}
