package fi.solita.utils.functional;

import java.io.Serializable;



public interface Identifiable<ID extends Serializable> {
    ID getId();
}
