package lt.insoft.webgallery.domain.image.tag;

import lombok.Data;
import lombok.NoArgsConstructor;
import lt.insoft.webgallery.domain.image.Image;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    @ManyToMany(mappedBy = "tags")
    private List<Image> images = new ArrayList<>();


}