package lt.insoft.webgallery.domain.image;

import lt.insoft.webgallery.domain.image.exception.BadImageException;
import lt.insoft.webgallery.domain.image.exception.ImageNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ImageExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ImageNotFoundException.class})
    public ResponseEntity<Object> handleImageNotFound(RuntimeException ex, WebRequest request){
        return super.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND,request);
    }

    @ExceptionHandler(value = {BadImageException.class})
    public ResponseEntity<Object> handleBadImage(RuntimeException ex, WebRequest request){
        return super.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,request);
    }
}

