package lt.insoft.webgallery.domain.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.insoft.webgallery.domain.image.tag.TagDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    private Long id;

    private String name;

    private String type;

    private String description;

    private LocalDateTime DateTime;

    private List<TagDto> tags;

    private byte[] picBytes;

    private byte[] thumbnailBytes;

}
