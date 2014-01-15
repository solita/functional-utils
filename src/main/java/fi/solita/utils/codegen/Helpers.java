package fi.solita.utils.codegen;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.*;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Transformers.prepend;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Generated;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.AbstractTypeVisitor6;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleElementVisitor6;
import javax.lang.model.util.SimpleTypeVisitor6;
import javax.lang.model.util.Types;

import fi.solita.utils.functional.Collections;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Functional;
import fi.solita.utils.functional.Iterables;
import fi.solita.utils.functional.Monoids;
import fi.solita.utils.functional.Ordering;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Tuple2;

public abstract class Helpers {

    private static final Pattern qNameMatcher = Pattern.compile("([a-zA-Z0-9_$]+\\.)+[a-zA-Z0-9_$]+");
    private static final String qNameReplacementString = Matcher.quoteReplacement("{${") + "$0" + Matcher.quoteReplacement("}$}");
    
    public static final String importTypes(CharSequence typesStr) {
        return qNameMatcher.matcher(typesStr).replaceAll(qNameReplacementString);
    }
    
    public static final String importType(Class<?> clazz) {
        return "{${" + clazz.getName().replace('$', '.') + "}$}";
    }
    
    public static final String containedType(Element e) {
        // FIXME: fancier way to do this?
        String type = qualifiedName.apply(e);
        return containedType(type);
    }
    
    public static final String containedType(String type) {
        // FIXME: fancier way to do this?
        return type.substring(type.indexOf('<')+1, type.lastIndexOf('>'));
    }
    
    public static final String resolveBoxedGenericType(TypeMirror type) {
        // FIXME: any correct ways to do this?
        return boxed.apply(type.toString());
    }
    
    public static final Transformer<String,String> replaceTypeVarWithObject = new Transformer<String,String>() {
        @Override
        public String transform(String source) {
            if (source.indexOf('.') == -1) {
                return source.endsWith("[]") ? "Object[]" : "Object";
            }
            return source;
        }
    };
    
    public static final Transformer<TypeParameterElement,String> typeParameter2String = new Transformer<TypeParameterElement,String>() {
        @Override
        public String transform(TypeParameterElement source) {
            String bound = Functional.mkString(" & ", map(source.getBounds(), typeMirror2GenericQualifiedName)).toString();
            return source.getSimpleName().toString() + (bound.equals("java.lang.Object") ? "" : " extends " + bound);
        }
    };
    
    public static final Function1<TypeMirror, String> typeMirror2GenericQualifiedName = new Transformer<TypeMirror,String>() {
        private final SimpleTypeVisitor6<List<? extends TypeMirror>, Object> visitor = new SimpleTypeVisitor6<List<? extends TypeMirror>, Object>() {
            @Override
            public List<? extends TypeMirror> visitDeclared(DeclaredType t, Object p) {
                return t.getTypeArguments();
            }
            
            @Override
            public List<? extends TypeMirror> visitArray(ArrayType t, Object p) {
                return t.getComponentType().accept(this, p);
            }
        };
        
        private final Predicate<String> notEmpty = new Predicate<String>() {
            @Override
            public boolean accept(String candidate) {
                return !candidate.isEmpty();
            }
        };
        
        @Override
        public String transform(TypeMirror source) {
            String type = typeMirror2QualifiedName.apply(source);
            List<? extends TypeMirror> params = source.accept(visitor, null);
            
            if (params == null) {
                return type;
            } else {
                List<String> p = newList(filter(map(params, this), notEmpty));
                if (p.isEmpty()) {
                    return type;
                } else {
                    return type + "<" + Functional.mkString(", ", map(p, boxed)) + ">";
                }
            }
        }
    };
    
    public static final Function1<TypeMirror, String> typeMirror2QualifiedName = new Transformer<TypeMirror, String>() {
        private final SimpleTypeVisitor6<String, Object> visitor = new SimpleTypeVisitor6<String, Object>() {
            @Override
            public String visitDeclared(DeclaredType t, Object p) {
                return qualifiedName.apply(t.asElement());
            }
            @Override
            public String visitTypeVariable(TypeVariable t, Object p) {
                return qualifiedName.apply(t.asElement());
            }
            public String visitArray(ArrayType t, Object p) {
                return t.getComponentType().accept(this, p) + "[]";
            }
            @Override
            public String visitPrimitive(PrimitiveType t, Object p) {
                return t.toString();
            }
            @Override
            public String visitWildcard(WildcardType t, Object p) {
                String objectClass = Object.class.getName();
                String ext = t.getExtendsBound() != null ? typeMirror2GenericQualifiedName.apply(t.getExtendsBound()) : objectClass;
                String sup = t.getSuperBound() != null ? typeMirror2GenericQualifiedName.apply(t.getSuperBound()) : objectClass;
                return !ext.equals(objectClass) ? "? extends " + ext : !sup.equals(objectClass) ? "? super " + sup : "?";
            }
        };
        
        @Override
        public String transform(TypeMirror source) {
            return source.accept(visitor, null);
        }
    };
    
    public static final Function1<Element, String> simpleName = new Transformer<Element, String>() {
        @Override
        public String transform(Element source) {
            return source.getSimpleName().toString();
        }
    };
    
    public static final Function1<Element, ElementKind> kind = new Transformer<Element, ElementKind>() {
        @Override
        public ElementKind transform(Element source) {
            return source.getKind();
        }
    };
    
    public static final Function1<Element, TypeMirror> type = new Transformer<Element, TypeMirror>() {
        @Override
        public TypeMirror transform(Element source) {
            return source.asType();
        }
    };
    
    public static final Function1<ExecutableElement, List<? extends VariableElement>> parameters = new Transformer<ExecutableElement, List<? extends VariableElement>>() {
        @Override
        public List<? extends VariableElement> transform(ExecutableElement source) {
            return source.getParameters();
        }
    };
    
    public static final Function1<Element, Element> enclosingElement = new Transformer<Element, Element>() {
        @Override
        public Element transform(Element source) {
            return source.getEnclosingElement();
        }
    };
    
    public static final <T extends TypeParameterElement> Function1<String, String> handleTypeVariables(final List<T> typeParameters) {
        // FIXME: How to "convert" a typeVariable to a concrete class?
        final Map<String, List<T>> bySimpleName = groupBy(typeParameters, simpleName);
        return new Transformer<String, String>() {
            @Override
            public String transform(String source) {
                if (!isPrimitive(source)) {
                    List<T> t = bySimpleName.get(source.endsWith("[]") ? source.substring(0, source.length()-2) : source);
                    if (t != null) {
                        TypeMirror tm = head(head(t).getBounds());
                        if (tm.getKind() == TypeKind.TYPEVAR)
                            return Object.class.getName();
                        else
                            return tm.toString();
                    }
                }
                return source;
            }
        };
    }
    
    private static final Set<String> primitiveTypeNames = newSet(int.class.getName(), short.class.getName(), char.class.getName(), byte.class.getName(), long.class.getName(), double.class.getName(), float.class.getName(), boolean.class.getName(), void.class.getName());
    
    public static final boolean isPrimitive(String typename) {
        return primitiveTypeNames.contains(typename);
    }
    
    public static final Function1<Element, String> qualifiedName = new Transformer<Element, String>() {
        private final SimpleElementVisitor6<String, Object> visitor = new SimpleElementVisitor6<String, Object>() {
            @Override
            public String visitType(TypeElement e, Object p) {
                return e.getQualifiedName().toString();
            }
            @Override
            public String visitVariable(VariableElement e, Object p) {
                return e.asType().toString();
            }
            @Override
            public String visitTypeParameter(TypeParameterElement e, Object p) {
                return e.getSimpleName().toString();
            }
        };
        
        @Override
        public String transform(Element source) {
            return source.accept(visitor, null);
        }
    };
    
    public static final String getPackageName(Element element) {
        while (!element.getKind().equals(ElementKind.PACKAGE)) {
            element = element.getEnclosingElement();
        }
        return element.accept(packageResolver, null);
    }
    
    private static final SimpleElementVisitor6<String, Object> packageResolver = new SimpleElementVisitor6<String, Object>() {
        @Override
        public String visitPackage(PackageElement e, Object p) {
            return e.getQualifiedName().toString();
        }
    };
    
    public static final Function1<Element, Iterable<VariableElement>> element2Fields = new Transformer<Element, Iterable<VariableElement>>() {
        @SuppressWarnings("unchecked")
        @Override
        public Iterable<VariableElement> transform(Element source) {
            return sort((Iterable<VariableElement>) filter(source.getEnclosedElements(), fields), variableElementComparator);
        }
    };
    
    public static final Function1<Element, Iterable<ExecutableElement>> element2Methods = new Transformer<Element, Iterable<ExecutableElement>>() {
        @SuppressWarnings("unchecked")
        @Override
        public Iterable<ExecutableElement> transform(Element source) {
            return sort((Iterable<ExecutableElement>) filter(source.getEnclosedElements(), methods), executableElementComparator);
        }
    };
    
    public static final Function1<Element, Iterable<ExecutableElement>> element2Constructors = new Transformer<Element, Iterable<ExecutableElement>>() {
        @SuppressWarnings("unchecked")
        @Override
        public Iterable<ExecutableElement> transform(Element source) {
            return sort((Iterable<ExecutableElement>)filter(source.getEnclosedElements(), constructors), executableElementComparator);
        }
    };

    public static final Comparator<Element> variableElementComparator = new Ordering<Element>() {
        @Override
        public int compare(Element o1, Element o2) {
            return o1.getSimpleName().toString().compareTo(o2.getSimpleName().toString());
        }
    }.then(new Ordering<Element>() {
        @Override
        public int compare(Element o1, Element o2) {
            return type.apply(o1).toString().toLowerCase().compareTo(type.apply(o2).toString().toLowerCase());
        }
    });
    
    public static final Comparator<ExecutableElement> executableElementComparator =  reduce(
        new Ordering<ExecutableElement>() {
            @Override
            public int compare(ExecutableElement o1, ExecutableElement o2) {
                return kind.apply(o1).compareTo(kind.apply(o2));
            }
        },
        new Ordering<ExecutableElement>() {
            @Override
            public int compare(ExecutableElement o1, ExecutableElement o2) {
                return byIterable(variableElementComparator).compare(o1.getParameters(), o2.getParameters());
            }
        },
        new Ordering<ExecutableElement>() {
            @Override
            public int compare(ExecutableElement o1, ExecutableElement o2) {
                return o1.getReturnType().toString().compareTo(o2.getReturnType().toString());
            }
        }
    );
    
    private static final <S, T extends Iterable<? extends S>> Ordering<T> byIterable(final Comparator<S> c) {
        return new Ordering<T>() {
            @Override
            public int compare(T o1, T o2) {
                for (Long s1: Iterables.resolveSize.apply(o1)) {
                    for (Long s2: Iterables.resolveSize.apply(o2)) {
                        int s = s1.compareTo(s2);
                        if (s != 0) {
                            return s;
                        }
                    }
                }
                Iterator<? extends S> it1 = o1.iterator();
                Iterator<? extends S> it2 = o2.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    int i = c.compare(it1.next(), it2.next());
                    if (i != 0) {
                        return i;
                    }
                }
                return it1.hasNext() ? 1 : it2.hasNext() ? -1 : 0;
            }
        };
    }
    
    public static final Function1<Element, Iterable<TypeElement>> element2NestedClasses = new Transformer<Element, Iterable<TypeElement>>() {
        @SuppressWarnings("unchecked")
        @Override
        public Iterable<TypeElement> transform(Element source) {
            return (Iterable<TypeElement>) filter(source.getEnclosedElements(), (classes.or(interfaces)).and(staticElements));
        }
    };
    
    public static final Function1<String, String> boxed = new Transformer<String, String>() {
        @Override
        public String transform(String source) {
            if (source.equals("int")) {
                return "java.lang.Integer";
            } else if (source.equals("short")) {
                return "java.lang.Short";
            } else if (source.equals("char")) {
                return "java.lang.Character";
            } else if (source.equals("byte")) {
                return "java.lang.Byte";
            } else if (source.equals("long")) {
                return "java.lang.Long";
            } else if (source.equals("double")) {
                return "java.lang.Double";
            } else if (source.equals("float")) {
                return "java.lang.Float";
            } else if (source.equals("boolean")) {
                return "java.lang.Boolean";
            } else if (source.equals("void")) {
                return "java.lang.Void";
            }
            return source;
        }
    };
    
    public static final Function1<Element,String> boxedQualifiedName = qualifiedName.andThen(boxed);
    
    public static final Predicate<Element> fields = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getKind() == ElementKind.FIELD;
        }
    };
    
    public static final Predicate<Element> classes = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getKind() == ElementKind.CLASS;
        }
    };
    
    public static final Predicate<Element> interfaces = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getKind() == ElementKind.INTERFACE;
        }
    };
    
    public static final Predicate<ExecutableElement> objectMethod = new Predicate<ExecutableElement>() {
        @Override
        public boolean accept(ExecutableElement candidate) {
            return objectMethods.contains(candidate.getSimpleName().toString());
        }
    };
    
    private static final Set<String> objectMethods = newSet("hashCode", "equals", "toString", "finalize", "clone");
    
    public static final Predicate<Element> methods = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getKind() == ElementKind.METHOD;
        }
    };
    
    public static final Predicate<Element> constructors = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getKind() == ElementKind.CONSTRUCTOR;
        }
    };
    
    public static final Predicate<Element> staticElements = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getModifiers().contains(Modifier.STATIC);
        }
    };
    
    public static final Predicate<Element> nonGeneratedElements = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getAnnotation(Generated.class) == null;
        }
    };
    
    public static final Predicate<Object> equalTo(final Object other) {
        return new Predicate<Object>() {
            @Override
            public boolean accept(Object candidate) {
                return candidate.equals(other);
            }   
        };
    }
    
    public static final boolean hasNonQmarkGenerics(String str) {
        return str.indexOf('<') != -1;
    }
    
    public static final boolean isInstanceMethod(Element e) {
        return e.getKind() == ElementKind.METHOD && !staticElements.accept(e);
    }
    
    public static final Predicate<Element> publicElement = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getModifiers().contains(Modifier.PUBLIC);
        }
    };

    public static final boolean isPrivate(Element e) {
        return e.getModifiers().contains(Modifier.PRIVATE);
    }
    
    public static final boolean isFinal(Element e) {
        return e.getModifiers().contains(Modifier.FINAL);
    }

    public static final boolean returnsVoid(ExecutableElement method) {
        return method.getReturnType().getKind() == TypeKind.VOID;
    }
    
    public static final boolean hasRawTypes(Element e) {
        SuppressWarnings suppressWarnings = e.getAnnotation(SuppressWarnings.class);
        return suppressWarnings != null && Functional.contains("rawtypes", suppressWarnings.value()) ||
               e.getEnclosingElement() != null && hasRawTypes(e.getEnclosingElement());
    }

    
    
    public static final class EnvDependent {
        public final Types typeUtils;
        public final Elements elementUtils;
        public final Messager messager;
        private final Predicate<TypeMirror> uncheckedExceptions;

        public EnvDependent(final ProcessingEnvironment env) {
            typeUtils = env.getTypeUtils();
            elementUtils = env.getElementUtils();
            messager = env.getMessager();
            
            this.uncheckedExceptions = new Predicate<TypeMirror>() {
                final TypeMirror runtimeException = elementUtils.getTypeElement(RuntimeException.class.getName()).asType();
                final TypeMirror error = elementUtils.getTypeElement(Error.class.getName()).asType();
            
                @Override
                public boolean accept(TypeMirror candidate) {
                    return typeUtils.isSubtype(candidate, runtimeException) ||
                           typeUtils.isSubtype(candidate, error);
                }
            };
        }
        
        public final boolean isSubtype(TypeMirror type, Class<?> parent) {
            return typeUtils.isSubtype(typeUtils.erasure(type), typeUtils.erasure(elementUtils.getTypeElement(parent.getName()).asType()));
        }
        
        public final boolean isSameType(TypeMirror type, Class<?> other) {
            return typeUtils.isSameType(typeUtils.erasure(type), typeUtils.erasure(elementUtils.getTypeElement(other.getName()).asType()));
        }
        
        public final boolean throwsCheckedExceptions(ExecutableElement e) {
            return exists(e.getThrownTypes(), not(uncheckedExceptions));
        }
    }
    
    public static final Transformer<Long,Long> nanosToMillis = new Transformer<Long,Long>() {
        @Override
        public Long transform(Long source) {
            return source/1000/1000;
        }
    };

    public static final Class<? extends Member> elementClass(Element e) {
        switch (e.getKind()) {
            case METHOD: return Method.class;
            case FIELD: return Field.class;
            case CONSTRUCTOR: return Constructor.class;
            default: throw new RuntimeException("only suitable for members");
        }
    }
    
    public static final Iterable<? extends TypeParameterElement> allTypeParams(ExecutableElement e) {
        List<? extends TypeParameterElement> typeParameters = e.getTypeParameters();
        if (staticElements.accept(e)) {
            return typeParameters;
        }
        final Set<String> typeParameterNames = newSet(map(typeParameters, simpleName));
        Iterable<? extends TypeParameterElement> enclosingTypeVars = filter(((TypeElement)e.getEnclosingElement()).getTypeParameters(), simpleName.andThen(new Predicate<String>() {
            @Override
            public boolean accept(String candidate) {
                return !typeParameterNames.contains(candidate);
            }
        }));
        return concat(enclosingTypeVars, typeParameters);
    }

    /**
     * Relevant type parameters are (those present on the element itself) and (those present on enclosing type if the element is not a nested static class)
     */
    public static final Iterable<? extends TypeParameterElement> relevantTypeParams(ExecutableElement e) {
        List<? extends TypeParameterElement> typeParameters = e.getTypeParameters();
        if (staticElements.accept(e)) {
            return typeParameters;
        }
        
        final Set<String> typeParameterNames = newSet(map(typeParameters, simpleName));
        Iterable<? extends TypeParameterElement> enclosingTypeVars = filter(((TypeElement)e.getEnclosingElement()).getTypeParameters(), simpleName.andThen(new Predicate<String>() {
            @Override
            public boolean accept(String candidate) {
                return !typeParameterNames.contains(candidate);
            }
        }));
        if (e.getKind() == ElementKind.CONSTRUCTOR) {
            return concat(enclosingTypeVars, typeParameters);
        }
        final Set<Name> allUsedTypeParameters = allUsedTypeParameters(e);
        return concat(filter(enclosingTypeVars, new Predicate<TypeParameterElement>() {
                @Override
                public boolean accept(TypeParameterElement candidate) {
                    return allUsedTypeParameters.contains(candidate.getSimpleName());
                }
            }), typeParameters);
    }
    
    //private static final Map<String,Pattern> patternCacheForUsedIn = newMap();

    private static final class TV extends AbstractTypeVisitor6<Set<Name>,Set<Name>> {
        private Set<TypeMirror> visited = newSet();
        @Override
        public Set<Name> visitPrimitive(PrimitiveType t, Set<Name> p) {
            return p;
        }
        @Override
        public Set<Name> visitNull(NullType t, Set<Name> p) {
            return p;
        }
        @Override
        public Set<Name> visitArray(ArrayType t, Set<Name> p) {
            return visit(t.getComponentType(), p);
        }
        @Override
        public Set<Name> visitDeclared(DeclaredType t, Set<Name> p) {
            if (visited.contains(t)) {
                return p;
            }
            visited.add(t);
            if (t.getKind() == TypeKind.TYPEVAR) {
                p.add(t.asElement().getSimpleName());
            }
            for (TypeMirror tm: t.getTypeArguments()) {
                visit(tm, p);
            }
            return p;
        }
        @Override
        public Set<Name> visitError(ErrorType t, Set<Name> p) {
            return p;
        }
        @Override
        public Set<Name> visitTypeVariable(TypeVariable t, Set<Name> p) {
            if (visited.contains(t)) {
                return p;
            }
            visited.add(t);
            p.add(t.asElement().getSimpleName());
            visit(t.getLowerBound(), p);
            visit(t.getUpperBound(), p);
            return p;
        }
        @Override
        public Set<Name> visitWildcard(WildcardType t, Set<Name> p) {
            if (t.getExtendsBound() != null) visit(t.getExtendsBound(), p);
            if (t.getSuperBound() != null) visit(t.getSuperBound(), p);
            return p;
        }
        @Override
        public Set<Name> visitExecutable(ExecutableType t, Set<Name> p) {
            visit(t.getReturnType(), p);
            for (TypeMirror tm: t.getParameterTypes()) {
                visit(tm, p);
            }
            for (TypeMirror tm: t.getThrownTypes()) {
                visit(tm, p);
            }
            for (TypeMirror tm: t.getTypeVariables()) {
                visit(tm, p);
            }
            return p;
        }
        @Override
        public Set<Name> visitNoType(NoType t, Set<Name> p) {
            return p;
        }
    };
    
    public static final <T> Set<Name> allUsedTypeParameters(ExecutableElement e) {
        return new TV().visit(e.asType(), Collections.<Name>newSet());
    }
    
    /*public static final Predicate<TypeParameterElement> usedIn(final ExecutableElement e) {
        Iterable<TypeMirror> allTypeMirrors = cons(e.getReturnType(), map(e.getParameters(), type));
        Iterable<String> typeVars = map(e.getTypeParameters(), typeParameterElement2string);
        final String s = "." + Functional.mkString(".", concat(typeVars, map(allTypeMirrors, typeMirror2GenericQualifiedName))) + ".";
        return new Predicate<TypeParameterElement>() {
            @Override
            public boolean accept(TypeParameterElement candidate) {
                String name = simpleName.apply(candidate);
                Pattern pat = patternCacheForUsedIn.get(name);
                if (pat == null) {
                    pat = Pattern.compile(".*[^a-zA-Z0-9_$]" + Pattern.quote(name) + "[^a-zA-Z0-9_$].*");
                    patternCacheForUsedIn.put(name, pat);
                }
                return pat.matcher(s).matches();
            }
        };
    }
    
    private static final Transformer<TypeParameterElement,String> typeParameterElement2string = new Transformer<TypeParameterElement,String>() {
        @Override
        public String transform(TypeParameterElement source) {
            return source.getSimpleName() + "<" + Functional.mkString("-", map(source.getBounds(), typeMirror2GenericQualifiedName)) + ">";
        }
    };*/

    public static final String resolveVisibility(Element e) {
        Set<Modifier> modifiers = e.getModifiers();
        return modifiers.contains(Modifier.PUBLIC) ? "public " : 
               modifiers.contains(Modifier.PROTECTED) ? "protected " :
               // change privates into package-visibility so that the actual owning class can use them
               "";
    }
    
    public static final Iterable<String> parameterTypesAsClasses(ExecutableElement element, List<? extends TypeParameterElement> relevantTypeParams) {
        return map(element.getParameters(), qualifiedName.andThen(handleTypeVariables(relevantTypeParams).andThen(removeGenericPart)));
    }
    
    public static final Transformer<String,String> removeGenericPart = new Transformer<String,String>() {
        @Override
        public String transform(String source) {
            int i = source.indexOf('<');
            return i == -1 ? source : source.substring(0, i);
        }
    };
    
    public static final String elementGenericQualifiedName(TypeElement enclosingElement) {
        return qualifiedName.apply(enclosingElement) + (enclosingElement.getTypeParameters().isEmpty() ? "" : "<" + Functional.mkString(", ", map(enclosingElement.getTypeParameters(), qualifiedName)) + ">");
    }
    
    public static final Iterable<String> paramsWithCast(List<? extends VariableElement> parameters, final boolean isPrivate) {
        Iterable<String> argCasts = map(parameters, new Transformer<VariableElement, String>() {
            @Override
            public String transform(VariableElement source) {
                return isPrivate ? "(Object)" :
                       source.asType().getKind().isPrimitive() ? "(" + qualifiedName.apply(source) + ")" :
                       "";
            }
        });
        return map(zip(argCasts, map(parameters, simpleName)), join);
    }
    
    public static final Predicate<Element> withAnnotation(final String className) {
        return new Predicate<Element>() {
            @Override
            public boolean accept(final Element e) {
                return acc(e, Collections.<Element>newSet());
            }
            
            private boolean acc(final Element e, final Set<Element> visited) {
                if (visited.contains(e)) {
                    return false;
                }
                visited.add(e);
                return exists(e.getAnnotationMirrors(), new Predicate<AnnotationMirror>() {
                    @Override
                    public boolean accept(AnnotationMirror m) {
                        Element elem = m.getAnnotationType().asElement();
                        return qualifiedName.apply(elem).equals(className) || !elem.equals(e) && acc(elem, visited);
                    }
                });
            }
        };
    }
    
    public static final <T> Predicate<T> instanceOf(final Class<? extends T> clazz) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return clazz.isInstance(candidate);
            }
        };
    }
    
    public static final <LEFT> Transformer<Map.Entry<? extends LEFT, ?>, LEFT> left() {
        return new Transformer<Map.Entry<? extends LEFT, ?>, LEFT>() {
            @Override
            public LEFT transform(Map.Entry<? extends LEFT, ?> source) {
                return source.getKey();
            }
        };
    }

    public static final <RIGHT> Transformer<Map.Entry<?, ? extends RIGHT>, RIGHT> right() {
        return new Transformer<Map.Entry<?, ? extends RIGHT>, RIGHT>() {
            @Override
            public RIGHT transform(Map.Entry<?, ? extends RIGHT> source) {
                return source.getValue();
            }
        };
    }
    
    public static final Transformer<Tuple2<?,?>, String> join = new Transformer<Tuple2<?,?>,String>() {
        @Override
        public String transform(Tuple2<?,?> source) {
            return source._1.toString() + source._2.toString();
        }
    };
    
    public static final Transformer<Tuple2<?,?>, String> joinWithSpace = new Transformer<Tuple2<?,?>,String>() {
        @Override
        public String transform(Tuple2<?,?> source) {
            return source._1.toString() + " " + source._2.toString();
        }
    };
    
    public static final Transformer<Object,String> toString = new Transformer<Object,String>() {
        @Override
        public String transform(Object source) {
            return source.toString();
        }
    };
    
    public static final Predicate<String> endsWith(final String suffix) {
        return new Predicate<String>() {
            @Override
            public boolean accept(String candidate) {
                return candidate.endsWith(suffix);
            }
        };
    }
    
    public static final Predicate<String> contains(final char c) {
        return new Predicate<String>() {
            @Override
            public boolean accept(String candidate) {
                return candidate.indexOf(c) != -1;
            }
        };
    }
    
    private static final Map<String,Pattern> patternCacheForTypeVariableReplacer = newMap();

    public static final Transformer<String, String> typeVariableReplacer(final List<String> toReplace) {
        return new Transformer<String,String>() {
            @Override
            public String transform(String candidate) {
                for (String r: toReplace) {
                    Pattern pat = patternCacheForTypeVariableReplacer.get(r);
                    if (pat == null) {
                        pat = Pattern.compile("([^a-zA-Z0-9_])([?]\\s*(?:extends|super)\\s+)?" + Pattern.quote(r) + "([^a-zA-Z0-9_])");
                        patternCacheForTypeVariableReplacer.put(r, pat);
                    }
                    candidate = pat.matcher(candidate).replaceAll("$1?$3");
                }
                return candidate;
            }
        };
    }

    public static final Transformer<Iterable<Long>,Long> iterableSum = new Transformer<Iterable<Long>,Long>() {
        @Override
        public Long transform(Iterable<Long> source) {
            return reduce(source, Monoids.longSum);
        }
    };

    public static final Transformer<CharSequence, String> padding = prepend("  ");
}
