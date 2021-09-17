package lt.insoft.webgallery.domain.image.dto;

import lombok.Data;

import java.util.List;

@Data
public class DescriptionDto {

    private String description;

    private List<String> tagsNames;
}
