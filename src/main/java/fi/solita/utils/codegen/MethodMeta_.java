package fi.solita.utils.codegen;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface MethodMeta_<T,R> extends Meta_<T,R>, Serializable{
    @Override
    public Method getMember();
    
    @Override
    public String getName();
}
