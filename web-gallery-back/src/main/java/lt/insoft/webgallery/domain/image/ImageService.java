package lt.insoft.webgallery.domain.image;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.zip.Deflater;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void uploadImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setDescription(file.getContentType());
        image.setDateTime(LocalDateTime.now());
        image.setPicBytes(file.getBytes());
        imageRepository.save(image);
    }

    public void deleteImage(Long id) {
        Image image = findImageById(id);
        this.imageRepository.delete(image);
    }

    private Image findImageById(Long id) {
        return this.imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
    }

}
