package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.it;

import java.util.Iterator;
import java.util.Optional;

/**
 * Not sure if this is good, but lets "Try" ;)
 * So, a Try is Either a typed error or a succesful value.
 * A Try is in addition an Iterable, which throws an exception if the Try was a failure.
 * @see Match
 */
public class Try<FAILURE,SUCCESS> extends Either<FAILURE,SUCCESS> implements Iterable<SUCCESS> {

    public static final <FAILURE,SUCCESS> Try<FAILURE,SUCCESS> failure(FAILURE left) {
        return new Try<FAILURE, SUCCESS>(Optional.of(left), Optional.empty());
    }

    public static final <FAILURE,SUCCESS> Try<FAILURE,SUCCESS> success(SUCCESS right) {
        return new Try<FAILURE, SUCCESS>(Optional.empty(), Optional.of(right));
    }

    public final Optional<FAILURE> failure;
    public final Optional<SUCCESS> success;
    
    private Try(Optional<FAILURE> failure, Optional<SUCCESS> success) {
        super(failure, success);
        this.failure = failure;
        this.success = success;
    }
    
    public final boolean isSuccess() {
        return success.isPresent();
    }
    
    public final boolean isFailure() {
        return failure.isPresent();
    }
    
    public Iterator<SUCCESS> iterator() {
        for (FAILURE f: it(left)) {
            throw new UnsupportedOperationException("Try was a Failure: " + f);
        }
        return it(right).iterator();
    }
    
    @SuppressWarnings("unchecked")
    public <R> Try<FAILURE,R> map(Apply<? super SUCCESS, ? extends R> f) {
        return isLeft() ? (Try<FAILURE, R>)this : Try.<FAILURE,R>success(f.apply(success.get()));
    }

    @Override
    public String toString() {
        return isLeft() ? "Try.failure(" + left + ")" : "Try.success(" + right + ")";
    }
}
