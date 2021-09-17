package lt.insoft.webgallery.domain.image.exception;

public class ImageNotFoundException extends  RuntimeException{
    public ImageNotFoundException(Long id) {
        super("Image with ID " + id + " was not found");
    }
}
