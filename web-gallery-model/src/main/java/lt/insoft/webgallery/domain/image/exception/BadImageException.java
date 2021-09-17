package lt.insoft.webgallery.domain.image.exception;

public class BadImageException extends Exception {
    public BadImageException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
