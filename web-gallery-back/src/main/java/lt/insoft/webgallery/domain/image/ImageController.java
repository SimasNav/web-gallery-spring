package lt.insoft.webgallery.domain.image;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageController {

    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<Image> getAllImages() {
        return this.imageService.getAllImages();
    }

    @PostMapping
    public Image addNewImage(@RequestBody ImageDTO imageDTO) {
        return this.imageService.addNewImage(imageDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        this.imageService.deleteImage(id);
    }

}
