package fr.axzial.catmanager.exception;

/**
 * The {@link fr.axzial.catmanager.model.CatOwner} not found exception.
 */
public class CatOwnerNotFoundException extends RuntimeException {

    public CatOwnerNotFoundException() {
        super();
    }

    public CatOwnerNotFoundException(String message) {
        super(message);
    }

    public CatOwnerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatOwnerNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CatOwnerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
