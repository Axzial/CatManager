package fr.axzial.catmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link fr.axzial.catmanager.model.CatBreed} not found exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
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
