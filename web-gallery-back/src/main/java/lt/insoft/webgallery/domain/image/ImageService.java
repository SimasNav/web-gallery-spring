package lt.insoft.webgallery.domain.image;

import lombok.RequiredArgsConstructor;
import lt.insoft.webgallery.domain.image.dto.DescriptionDto;
import lt.insoft.webgallery.domain.image.dto.ImageDto;
import lt.insoft.webgallery.domain.image.exception.BadImageException;
import lt.insoft.webgallery.domain.image.exception.ImageNotFoundException;
import lt.insoft.webgallery.domain.image.model.Image;
import lt.insoft.webgallery.domain.image.model.Tag;
import lt.insoft.webgallery.domain.image.dto.TagDto;
import lt.insoft.webgallery.domain.image.repository.ImageRepository;
import lt.insoft.webgallery.domain.image.repository.TagRepository;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;

    public List<ImageDto> getAllImagesDto() {
        List<Image> images = imageRepository.findAll();
        List<ImageDto> imageDtoList = images.stream()
                .map(this::fillImageDto)
                .collect(Collectors.toList());
        return imageDtoList;
    }


    public ImageDto getImageDtoById(Long id) {
        Image image = findImageById(id);
        return fillImageDto(image);
    }

    public void uploadImage(MultipartFile file, DescriptionDto description) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setDescription(description.getDescription());
        image.setDateTime(LocalDateTime.now());
        image.setPicBytes(file.getBytes());
        image.setThumbnailBytes(makeThumbnail(file.getBytes(), FilenameUtils.getExtension(file.getOriginalFilename())));
        for (String tagName : description.getTagsNames()) {
            Tag tag = new Tag(tagName);
            image.getTags().add(tag);
            tagRepository.save(tag);
        }
        imageRepository.save(image);
    }

    public void updateImageInfo(Long id, ImageDto imageDto) {
        Image image = findImageById(id);
        image.setName(imageDto.getName());
        image.setDescription(imageDto.getDescription());
        image.setType(imageDto.getType());
        this.imageRepository.save(image);
    }

    public void updateImagePhoto(Long id, MultipartFile file) throws BadImageException {
        Image image = findImageById(id);
        try {
            byte[] picBytes = file.getBytes();
            image.setPicBytes(picBytes);
            image.setThumbnailBytes(makeThumbnail(picBytes, FilenameUtils.getExtension(file.getOriginalFilename())));
        } catch (IOException e) {
            throw new BadImageException("Cannot save binary data", e);
        }

        this.imageRepository.save(image);
    }

    public void deleteImage(Long id) {
        Image image = findImageById(id);
        this.imageRepository.delete(image);
    }

    public List<ImageDto> findAllImagesBySearchField(String searchField) {
        List<Image> images = imageRepository.findAll(ImageSpecifications.search(searchField));
        return getAllImagesDto(images);
    }

    public List<ImageDto> findAllImagesByImageNameWithMultiSelect(String imageName) {
        return imageRepository.findAllImagesByImageNameWithMultiSelect(imageName);
    }

    private ImageDto fillImageDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setDescription(image.getDescription());
        imageDto.setName(image.getName());
        imageDto.setType(image.getType());
        imageDto.setDateTime(image.getDateTime());
        //imageDto.setPicBytes(image.getPicBytes());
        //imageDto.setThumbnailBytes(image.getThumbnailBytes());
        imageDto.setTags(getImageTagsDto(image));
        return imageDto;
    }

    private List<TagDto> getImageTagsDto(Image image) {
        List<TagDto> tagDtoList = image.getTags().stream()
                .map(tag -> new TagDto(tag.getId(), tag.getTagName()))
                .collect(Collectors.toList());
        return tagDtoList;
    }

    private Image findImageById(Long id) {
        return this.imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
    }

    private byte[] makeThumbnail(byte[] picBytes, String format) throws IOException {
        InputStream is = new ByteArrayInputStream(picBytes);
        BufferedImage srcImage = ImageIO.read(is);
        BufferedImage scaledImage = Scalr.resize(srcImage, 100);
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        ImageIO.write(scaledImage, format, boas);
        return boas.toByteArray();
    }

    private List<ImageDto> getAllImagesDto(List<Image> images) {
        List<ImageDto> imageDtoList = images.stream()
                .map(this::fillImageDto)
                .collect(Collectors.toList());
        return imageDtoList;
    }
}
