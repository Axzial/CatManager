package fr.axzial.catmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link fr.axzial.catmanager.model.CatBreed} not found exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CatBreedNotFoundException extends RuntimeException {

    public CatBreedNotFoundException() {
        super();
    }

    public CatBreedNotFoundException(String message) {
        super(message);
    }

    public CatBreedNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatBreedNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CatBreedNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
