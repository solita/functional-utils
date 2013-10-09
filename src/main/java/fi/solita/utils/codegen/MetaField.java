package fi.solita.utils.codegen;

import java.lang.reflect.Field;

import fi.solita.utils.codegen.generators.Content;
import fi.solita.utils.functional.Function1;

public abstract class MetaField<T, R> extends Function1<T, R> implements FieldMeta_<T, R> {
    private transient Field $r;
    private final Class<?> clazz;
    private final String name;
    
    public MetaField(Class<?> clazz, String name) {
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