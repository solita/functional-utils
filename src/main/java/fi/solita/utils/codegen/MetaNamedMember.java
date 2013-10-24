package fi.solita.utils.codegen;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;

public interface MetaNamedMember<T, R> extends MetaMember<T, R>, Serializable {
    AccessibleObject getMember();
    String getName();
}
