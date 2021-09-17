package lt.insoft.webgallery.domain.image.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagDto {

    private Long id;
    private String tagName;
}
