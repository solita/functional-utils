package fi.solita.utils.codegen;

import java.lang.reflect.Method;

public abstract class MetaMethodPredicate<T> extends fi.solita.utils.functional.Predicate<T> implements MethodMeta_<T, Boolean> {
    private transient Method $r;
    private final Class<?> clazz;
    private final String name;
    private final Class<?>[] argClasses;
    
    public MetaMethodPredicate(Class<?> clazz, String name, Class<?>... argClasses) {
        this.clazz = clazz;
        this.name = name;
        this.argClasses = argClasses;
    }
    
    @Override
    public final Method getMember() {
        if ($r == null) {
            $r = MetaMethod.doGetMember(clazz, name, argClasses);
        }
        return $r;
    }
    
    public final String getName() {
        return name;
    }
    
    @Override
    public final String toString() {
        return MetaMethod.doToString(clazz, name);
    }
}