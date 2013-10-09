package fi.solita.utils.codegen;

import java.lang.reflect.Field;

import fi.solita.utils.codegen.generators.Content;

public abstract class MetaFieldPredicate<T> extends fi.solita.utils.functional.Predicate<T> implements FieldMeta_<T, Boolean> {
    private transient Field $r;
    private final Class<?> clazz;
    private final String name;
    
    public MetaFieldPredicate(Class<?> clazz, String name) {
        this.clazz = clazz;
        this.name = name;
    }
    
    @Override
    public final Field getMember() {
        if ($r == null) {
            try {
                Field f = (Field)(Object)clazz.getDeclaredField(name);
                f.setAccessible(true);
                $r = f;
            } catch (Throwable $e) {
                throw Content.wrap($e);
            }
        }
        return $r;
    }
    
    public final String getName() {
        return name;
    }
    
    @Override
    public final String toString() {
        return clazz.getSimpleName() + "." + name;
    }
}