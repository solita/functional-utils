package fi.solita.utils.codegen;

import java.lang.reflect.Field;

public interface MetaField<T,R> extends MetaNamedMember<T,R> {
    @Override
    public Field getMember();
}
