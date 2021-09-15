package lt.insoft.webgallery.domain.image.tag;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagDto {

    private Long id;
    private String tagName;
}
