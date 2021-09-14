package lt.insoft.webgallery.domain.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {

    private String name;

    private String description;

    private LocalDateTime localDateTime;

    private byte[] picBytes;
}
