package lt.insoft.webgallery.domain.image;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageDTO {

    private String name;

    private String description;

    private LocalDateTime localDateTime;
}
