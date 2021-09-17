package lt.insoft.webgallery.domain.image.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.insoft.webgallery.domain.image.dto.TagDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    public ImageDto(String name, String type) {
        this.name = name;
        this.type = type;
    }

    private Long id;

    private String name;

    private String type;

    private String description;

    private LocalDateTime DateTime;

    private List<TagDto> tags;

    private byte[] picBytes;

    private byte[] thumbnailBytes;

}
