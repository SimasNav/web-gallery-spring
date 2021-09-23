package lt.insoft.webgallery.domain.image;


import lombok.RequiredArgsConstructor;
import lt.insoft.webgallery.domain.image.dto.DescriptionDto;
import lt.insoft.webgallery.domain.image.dto.ImageDto;
import lt.insoft.webgallery.domain.image.exception.BadImageException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public List<ImageDto> getAllImages() {
        return this.imageService.getAllImagesDto();
    }

    @GetMapping("/{id}")
    public ImageDto getImageById(@PathVariable Long id) {
        return this.imageService.getImageDtoById(id);
    }

    @PostMapping()
    public Long uploadImage(@RequestPart("imageFile") MultipartFile file) throws IOException {
       return this.imageService.uploadImage(file);
    }

    @PutMapping("/update-info/{id}")
    public void updateImageInfo(@PathVariable Long id, @RequestBody ImageDto imageDto) {
        imageService.updateImageInfo(id, imageDto);
    }

    @PutMapping("/update-photo/{id}")
    public Long updateImagePhoto(@PathVariable Long id, @RequestPart("imageFile") MultipartFile file) throws BadImageException {
        return this.imageService.updateImagePhoto(id, file);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        this.imageService.deleteImage(id);
    }

    /**
     * Search methods using Specifications API, multiselect
     */

    @GetMapping("/search/{searchField}")
    public List<ImageDto> findAllImagesBySearchField(@PathVariable String searchField) {
        return this.imageService.findAllImagesBySearchField(searchField);
    }

    @GetMapping("/search/multiselect/{searchField}")
    public List<ImageDto> findAllImagesByImageNameWithMultiSelect(@PathVariable String searchField) {
        return this.imageService.findAllImagesByImageNameWithMultiSelect(searchField);
    }
}
