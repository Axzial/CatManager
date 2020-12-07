package fr.axzial.catmanager.exception;

/**
 * The {@link fr.axzial.catmanager.model.CatBreed} not found exception.
 */
public class BreedNotFoundException extends RuntimeException {

    public BreedNotFoundException() {
        super();
    }

    public BreedNotFoundException(String message) {
        super(message);
    }

    public BreedNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BreedNotFoundException(Throwable cause) {
        super(cause);
    }

    protected BreedNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
