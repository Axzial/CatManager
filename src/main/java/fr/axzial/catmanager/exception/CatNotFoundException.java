package fr.axzial.catmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link fr.axzial.catmanager.model.Cat} not found exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CatNotFoundException extends RuntimeException {

    public CatNotFoundException() {
        super();
    }

    public CatNotFoundException(String message) {
        super(message);
    }

    public CatNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CatNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
