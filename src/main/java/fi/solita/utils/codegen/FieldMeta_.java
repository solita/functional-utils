package fi.solita.utils.codegen;

import java.io.Serializable;
import java.lang.reflect.Field;

public interface FieldMeta_<T,R> extends Meta_<T,R>, Serializable {
    @Override
    public Field getMember();
    
    @Override
    public String getName();
}
