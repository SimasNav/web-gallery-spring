package lt.insoft.webgallery.domain.image;


import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.zip.Deflater;

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

    @PostMapping("/upload")
    public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        this.imageService.uploadImage(file);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        this.imageService.deleteImage(id);
    }




}
