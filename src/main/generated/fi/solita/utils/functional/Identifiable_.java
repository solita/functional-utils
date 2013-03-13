package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Identifiable_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <ID extends java.io.Serializable> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Identifiable<ID>, ID> getId() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Identifiable<ID>, ID>() {
        public ID apply(fi.solita.utils.functional.Identifiable<ID> $self) {
            return $self.getId();
        }
    };
    }
    

}
