package lt.insoft.webgallery.domain.image;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getAllImages() {

        return imageRepository.findAll();

    }

    public Image addNewImage(ImageDTO imageDTO) {

        Image image = new Image();
        image.setName(imageDTO.getName());
        image.setDescription(imageDTO.getDescription());
        image.setDateTime(LocalDateTime.now());
        return imageRepository.save(image);

    }

    public void deleteImage(Long id) {
        Image image = findImageById(id);
        this.imageRepository.delete(image);

    }

    private Image findImageById(Long id) {
        return this.imageRepository.findById(id)
                                   .orElseThrow(() -> new ImageNotFoundException(id));
    }
}
