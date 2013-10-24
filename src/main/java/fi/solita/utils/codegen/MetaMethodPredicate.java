package fi.solita.utils.codegen;

import java.lang.reflect.Method;

import fi.solita.utils.functional.Predicate;

public abstract class MetaMethodPredicate<T> extends Predicate<T> implements MetaMethod<T, Boolean> {
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
            $r = MetaMethods.doGetMember(clazz, name, argClasses);
        }
        return $r;
    }
    
    public final String getName() {
        return name;
    }
    
    @Override
    public final String toString() {
        return MetaMethods.doToString(clazz, name);
    }
}