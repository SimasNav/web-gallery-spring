package lt.insoft.webgallery.domain.image;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<ImageDto> getAllImages() {
        return this.imageService.getAllImagesDto();
    }

    @GetMapping("/{id}")
    public ImageDto getImageById(@PathVariable Long id){
        return this.imageService.getImageDtoById(id);
    }

    @PostMapping()
    public void uploadImage(@RequestPart("imageFile") MultipartFile file, @RequestPart("tags") DescriptionDto description) throws IOException {
        this.imageService.uploadImage(file, description);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        this.imageService.deleteImage(id);
    }

}
