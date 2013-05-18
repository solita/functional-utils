package fi.solita.utils.codegen;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.contains;
import static fi.solita.utils.functional.Functional.exists;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.find;
import static fi.solita.utils.functional.Functional.head;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.reduce;
import static fi.solita.utils.functional.Functional.sort;
import static fi.solita.utils.functional.Functional.zip;
import static fi.solita.utils.functional.Predicates.empty;
import static fi.solita.utils.functional.Predicates.equalTo;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Transformers.append;
import static fi.solita.utils.functional.Transformers.mkString;
import static fi.solita.utils.functional.Transformers.replaceAll;

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
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleElementVisitor6;
import javax.lang.model.util.SimpleTypeVisitor6;
import javax.lang.model.util.Types;

import fi.solita.utils.functional.Collections;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Iterables;
import fi.solita.utils.functional.Ordering;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;

public abstract class Helpers {

    public static Predicate<String> hasTypeParameters = new Predicate<String>() {
        @Override
        public boolean accept(String candidate) {
            // FIXME: How to determine if a type contains type parameters?
            return typeArgsWithTypeArgs.matcher(candidate).find();
        }
    };
    
    public static String containedType(Element e, Elements elements) {
        // FIXME: fancier way to do this?
        String type = qualifiedName.apply(e);
        return type.substring(type.indexOf('<')+1, type.lastIndexOf('>'));
    }
    
    public static String resolveBoxedGenericType(TypeMirror type) {
        // FIXME: any correct ways to do this?
        return boxed.apply(type.toString());
    }
    
    public static Transformer<TypeParameterElement,String> typeParameter2String = new Transformer<TypeParameterElement,String>() {
        @Override
        public String transform(TypeParameterElement source) {
            String bound = mkString(" & ", map(source.getBounds(), typeMirror2GenericQualifiedName));
            return source.getSimpleName().toString() + (bound.equals(Object.class.getName()) ? "" : " extends " + bound);
        }
    };
    
    public static Function1<TypeMirror, String> typeMirror2GenericQualifiedName = new Transformer<TypeMirror,String>() {
        @Override
        public String transform(TypeMirror source) {
            String type = typeMirror2QualifiedName.apply(source);
            List<? extends TypeMirror> params = source.accept(new SimpleTypeVisitor6<List<? extends TypeMirror>, Object>() {
                @Override
                public List<? extends TypeMirror> visitDeclared(DeclaredType t, Object p) {
                    return t.getTypeArguments();
                }
            }, null);
            
            if (params == null) {
                return type;
            } else {
                List<String> p = newList(filter(map(params, this), not(empty)));
                if (p.isEmpty()) {
                    return type;
                } else {
                    return type + "<" + mkString(", ", p) + ">";
                }
            }
        }
    };
    
    public static Function1<TypeMirror, String> typeMirror2QualifiedName = new Transformer<TypeMirror, String>() {
        @Override
        public String transform(TypeMirror source) {
            return source.accept(new SimpleTypeVisitor6<String, Object>() {
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
                };
                @Override
                public String visitWildcard(WildcardType t, Object p) {
                    String ext = t.getExtendsBound() != null ? typeMirror2GenericQualifiedName.apply(t.getExtendsBound()) : Object.class.getName();
                    String sup = t.getSuperBound() != null ? typeMirror2GenericQualifiedName.apply(t.getSuperBound()) : Object.class.getName();
                    return !ext.equals(Object.class.getName()) ? "? extends " + ext : !sup.equals(Object.class.getName()) ? "? super " + sup : "?";
                }
            }, null);
        }
    };
    
    public static Function1<Element, String> simpleName = new Transformer<Element, String>() {
        @Override
        public String transform(Element source) {
            return source.getSimpleName().toString();
        }
    };
    
    public static Function1<Element, ElementKind> kind = new Transformer<Element, ElementKind>() {
        @Override
        public ElementKind transform(Element source) {
            return source.getKind();
        }
    };
    
    public static Function1<Element, TypeMirror> type = new Transformer<Element, TypeMirror>() {
        @Override
        public TypeMirror transform(Element source) {
            return source.asType();
        }
    };
    
    public static Function1<ExecutableElement, List<? extends VariableElement>> parameters = new Transformer<ExecutableElement, List<? extends VariableElement>>() {
        @Override
        public List<? extends VariableElement> transform(ExecutableElement source) {
            return source.getParameters();
        }
    };
    
    public static Function1<Element, Element> enclosingElement = new Transformer<Element, Element>() {
        @Override
        public Element transform(Element source) {
            return source.getEnclosingElement();
        }
    };
    
    private static final Pattern typeArgPat = Pattern.compile("([^.\\[\\]]+)(\\[\\])?");
    
    public static Function1<String, String> handleTypeVariables(final List<? extends TypeParameterElement> typeParameters) {
        // FIXME: How to "convert" a typeVariable to a concrete class?
        return new Transformer<String, String>() {
            @Override
            public String transform(String source) {
                Matcher m = typeArgPat.matcher(source);
                if (boxed.apply(source).equals(source) && m.matches()) {
                    for (TypeParameterElement e: find(typeParameters, simpleName.andThen(equalTo(m.group(1))))) {
                        return head(e.getBounds()).toString();
                    }
                }
                return source;
            }
        };
    }
    
    public static Function1<Element, String> qualifiedName = new Transformer<Element, String>() {
        @Override
        public String transform(Element source) {
            return source.accept(new SimpleElementVisitor6<String, Object>() {
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
            }, null);
        }
    };
    
    public static String getPackageName(Element element) {
        while (!element.getKind().equals(ElementKind.PACKAGE)) {
            element = element.getEnclosingElement();
        }
        return element.accept(new SimpleElementVisitor6<String, Object>() {
            @Override
            public String visitPackage(PackageElement e, Object p) {
                return e.getQualifiedName().toString();
            }
        }, null);
    }
    
    public static Function1<Element, Iterable<VariableElement>> element2Fields = new Transformer<Element, Iterable<VariableElement>>() {
        @SuppressWarnings("unchecked")
        @Override
        public Iterable<VariableElement> transform(Element source) {
            return sort((Iterable<VariableElement>) filter(source.getEnclosedElements(), fields), variableElementComparator);
        }
    };
    
    public static Function1<Element, Iterable<ExecutableElement>> element2Methods = new Transformer<Element, Iterable<ExecutableElement>>() {
        @SuppressWarnings("unchecked")
        @Override
        public Iterable<ExecutableElement> transform(Element source) {
            return sort((Iterable<ExecutableElement>) filter(source.getEnclosedElements(), methods), executableElementComparator);
        }
    };
    
    public static Function1<Element, Iterable<ExecutableElement>> element2Constructors = new Transformer<Element, Iterable<ExecutableElement>>() {
        @SuppressWarnings("unchecked")
        @Override
        public Iterable<ExecutableElement> transform(Element source) {
            return sort((Iterable<ExecutableElement>)filter(source.getEnclosedElements(), constructors), executableElementComparator);
        }
    };

    public static Comparator<Element> variableElementComparator = new Ordering<Element>() {
        @Override
        public int compare(Element o1, Element o2) {
            return simpleName.apply(o1).compareTo(simpleName.apply(o2));
        }
    }.then(new Ordering<Element>() {
        @Override
        public int compare(Element o1, Element o2) {
            return type.apply(o1).toString().compareTo(type.apply(o2).toString());
        }
    });
    
    public static Comparator<ExecutableElement> executableElementComparator =  reduce(
        new Ordering<ExecutableElement>() {
            @Override
            public int compare(ExecutableElement o1, ExecutableElement o2) {
                return enclosingElement.andThen(kind).apply(o1).compareTo(enclosingElement.andThen(kind).apply(o2));
            }
        },
        new Ordering<ExecutableElement>() {
            @Override
            public int compare(ExecutableElement o1, ExecutableElement o2) {
                return enclosingElement.andThen(qualifiedName).apply(o1).compareTo(enclosingElement.andThen(qualifiedName).apply(o2));
            }
        },
        new Ordering<ExecutableElement>() {
            @Override
            public int compare(ExecutableElement o1, ExecutableElement o2) {
                return byIterable(variableElementComparator).compare(o1.getParameters(), o2.getParameters());
            }
        }
    );
    
    private static final <S, T extends Iterable<? extends S>> Ordering<T> byIterable(final Comparator<S> c) {
        return new Ordering<T>() {
            @Override
            public int compare(T o1, T o2) {
                for (Integer s1: Iterables.resolveSize.apply(o1)) {
                    for (Integer s2: Iterables.resolveSize.apply(o2)) {
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
    
    public static Function1<Element, Iterable<TypeElement>> element2NestedClasses = new Transformer<Element, Iterable<TypeElement>>() {
        @SuppressWarnings("unchecked")
        @Override
        public Iterable<TypeElement> transform(Element source) {
            return (Iterable<TypeElement>) filter(source.getEnclosedElements(), (classes.or(interfaces)).and(staticElements));
        }
    };
    
    public static Function1<String, String> boxed = new Transformer<String, String>() {
        @Override
        public String transform(String source) {
            if (source.equals(int.class.getName())) {
                return Integer.class.getName();
            } else if (source.equals(short.class.getName())) {
                return Short.class.getName();
            } else if (source.equals(char.class.getName())) {
                return Character.class.getName();
            } else if (source.equals(byte.class.getName())) {
                return Byte.class.getName();
            } else if (source.equals(long.class.getName())) {
                return Long.class.getName();
            } else if (source.equals(double.class.getName())) {
                return Double.class.getName();
            } else if (source.equals(float.class.getName())) {
                return Float.class.getName();
            } else if (source.equals(boolean.class.getName())) {
                return Boolean.class.getName();
            } else if (source.equals(void.class.getName())) {
                return Void.class.getName();
            }
            return source;
        }
    };
    
    public static Predicate<Element> fields = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getKind() == ElementKind.FIELD;
        }
    };
    
    public static Predicate<Element> classes = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getKind() == ElementKind.CLASS;
        }
    };
    
    public static Predicate<Element> interfaces = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getKind() == ElementKind.INTERFACE;
        }
    };
    
    public static Predicate<ExecutableElement> objectMethod = new Predicate<ExecutableElement>() {
        @Override
        public boolean accept(ExecutableElement candidate) {
            return candidate.getSimpleName().toString().equals("hashCode") ||
                   candidate.getSimpleName().toString().equals("equals") ||
                   candidate.getSimpleName().toString().equals("toString") ||
                   candidate.getSimpleName().toString().equals("finalize") ||
                   candidate.getSimpleName().toString().equals("clone");
        }
    };
    
    public static Predicate<Element> methods = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getKind() == ElementKind.METHOD;
        }
    };
    
    public static Predicate<Element> constructors = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getKind() == ElementKind.CONSTRUCTOR;
        }
    };
    
    public static Predicate<Element> staticElements = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getModifiers().contains(Modifier.STATIC);
        }
    };
    
    public static Predicate<Element> nonGeneratedElements = new Predicate<Element>() {
        @Override
        public boolean accept(Element candidate) {
            return candidate.getAnnotation(Generated.class) == null;
        }
    };

    public static boolean hasNonQmarkGenerics(String str) {
        return typeArgs.matcher(str.replaceAll("<\\s*[?]\\s*>", "")).find();
    }
    
    public static boolean isInstanceMethod(Element e) {
        return !staticElements.accept(e) && e.getKind() == ElementKind.METHOD;
    }

    public static boolean isPrivate(Element e) {
        return contains(e.getModifiers(), Modifier.PRIVATE);
    }

    public static boolean returnsVoid(ExecutableElement method) {
        return method.getReturnType().getKind() == TypeKind.VOID;
    }
    
    private static final Pattern typeArgsWithTypeArgs = Pattern.compile("[<,]\\s*([?]\\s*(super|extends))?[^.?]+[>,]");

    public static final Pattern typeArgs = Pattern.compile("<.*>");

    public static boolean hasRawTypes(Element e) {
        return e.getAnnotation(SuppressWarnings.class) != null && contains(e.getAnnotation(SuppressWarnings.class).value(), "rawtypes") ||
               e.getEnclosingElement() != null && hasRawTypes(e.getEnclosingElement());
    }

    public static boolean throwsCheckedExceptions(ExecutableElement e, ProcessingEnvironment processingEnv) {
        return exists(e.getThrownTypes(), not(Helpers.uncheckedExceptions(processingEnv)));
    }

    public static Predicate<TypeMirror> uncheckedExceptions(final ProcessingEnvironment processingEnv) {
        return new Predicate<TypeMirror>() {
            @Override
            public boolean accept(TypeMirror candidate) {
                return processingEnv.getTypeUtils().isSubtype(candidate, processingEnv.getElementUtils().getTypeElement(RuntimeException.class.getName()).asType()) ||
                       processingEnv.getTypeUtils().isSubtype(candidate, processingEnv.getElementUtils().getTypeElement(Error.class.getName()).asType());
            }
        };
    }

    public static Class<? extends Member> elementClass(Element e) {
        switch (e.getKind()) {
            case METHOD: return Method.class;
            case FIELD: return Field.class;
            case CONSTRUCTOR: return Constructor.class;
            default: throw new RuntimeException("only suitable for members");
        }
    }
    
    public static Iterable<? extends TypeParameterElement> allTypeParams(ExecutableElement e) {
        return staticElements.accept(e)
                ? e.getTypeParameters()
                : (Set<TypeParameterElement>)newSet(concat(((TypeElement)e.getEnclosingElement()).getTypeParameters(), e.getTypeParameters()));
    }

    /**
     * Relevant type parameters are (those present on the element itself) and (those present on enclosing type if the element is not a nested static class)
     */
    public static Iterable<? extends TypeParameterElement> relevantTypeParams(ExecutableElement e) {
        List<? extends TypeParameterElement> enclosingTypeVars = ((TypeElement)e.getEnclosingElement()).getTypeParameters();
        return staticElements.accept(e)               ? e.getTypeParameters() :
               e.getKind() == ElementKind.CONSTRUCTOR ? concat(enclosingTypeVars, e.getTypeParameters()) :
                                                        concat(filter(enclosingTypeVars, usedIn(e)), e.getTypeParameters());
    }

    private static Predicate<TypeParameterElement> usedIn(final ExecutableElement e) {
        Iterable<TypeMirror> allTypeMirrors = cons(e.getReturnType(), map(e.getParameters(), new Transformer<VariableElement,TypeMirror>(){
            @Override
            public TypeMirror transform(VariableElement source) {
                return source.asType();
            }
        }));
        Iterable<String> typeVars = map(e.getTypeParameters(), new Transformer<TypeParameterElement,String>() {
            @Override
            public String transform(TypeParameterElement source) {
                return source.getSimpleName() + "<" + mkString("-", map(source.getBounds(), typeMirror2GenericQualifiedName)) + ">";
            }
        });
        final String s = "." + mkString(".", concat(typeVars, map(allTypeMirrors, typeMirror2GenericQualifiedName))) + ".";
        return new Predicate<TypeParameterElement>() {
            @Override
            public boolean accept(TypeParameterElement candidate) {
                return s.matches(".*[^a-zA-Z0-9_]" + Pattern.quote(simpleName.apply(candidate)) + "[^a-zA-Z0-9_].*");
            }
        };
    }

    public static String resolveVisibility(Element e) {
        return e.getModifiers().contains(Modifier.PUBLIC) ? "public" : 
               e.getModifiers().contains(Modifier.PROTECTED) ? "protected" :
               // change privates into package-visibility so that the actual owning class can use them
               "";
    }
    
    public static boolean isSubtype(Element elem, Class<?> parent, ProcessingEnvironment processingEnv) {
        return isSubtype(elem, parent.getName(), processingEnv);
    }
    
    public static boolean isSubtype(Element elem, String parentClassName, ProcessingEnvironment processingEnv) {
        Types typeUtils = processingEnv.getTypeUtils();
        return typeUtils.isSubtype(typeUtils.erasure(elem.asType()), typeUtils.erasure(processingEnv.getElementUtils().getTypeElement(parentClassName).asType()));
    }
    
    public static Iterable<String> parameterTypesAsClasses(ExecutableElement element) {
        return map(element.getParameters(), qualifiedName.andThen(handleTypeVariables(newList(relevantTypeParams(element))).andThen(replaceAll(typeArgs, "")).andThen(append(".class"))));
    }
    
    public static String elementGenericQualifiedName(TypeElement enclosingElement) {
        return qualifiedName.apply(enclosingElement) + (enclosingElement.getTypeParameters().isEmpty() ? "" : "<" + mkString(", ", map(enclosingElement.getTypeParameters(), qualifiedName)) + ">");
    }
    
    public static Iterable<String> paramsWithCast(ExecutableElement e, final boolean isPrivate) {
        Iterable<String> argCasts = map(e.getParameters(), new Transformer<VariableElement, String>() {
            @Override
            public String transform(VariableElement source) {
                return isPrivate ? "(Object)" :
                       source.asType().getKind().isPrimitive() ? "(" + qualifiedName.apply(source) + ")" :
                       "";
            }
        });
        return map(zip(argCasts, map(e.getParameters(), simpleName)), mkString(""));
    }
    
    public static Predicate<Element> withAnnotation(final String className) {
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
}
