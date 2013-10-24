package fi.solita.utils.codegen;

import java.lang.reflect.Method;

public interface MetaMethod<T,R> extends MetaNamedMember<T,R> {
    @Override
    public Method getMember();
}
