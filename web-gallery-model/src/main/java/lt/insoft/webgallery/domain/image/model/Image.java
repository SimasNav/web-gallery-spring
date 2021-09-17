package lt.insoft.webgallery.domain.image.model;


import lombok.Data;
import lt.insoft.webgallery.domain.image.model.Tag;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Image {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String name;

    private String type;

    private String description;

    private LocalDateTime dateTime;

    @Lob
    private byte[] picBytes;

    @Lob
    private byte[] thumbnailBytes;

    @ManyToMany
    @JoinTable(name = "IMAGE_TAG",
    joinColumns = @JoinColumn(name = "IMAGE_ID"),
    inverseJoinColumns = @JoinColumn(name ="TAG_ID"))
    private List<Tag> tags = new ArrayList<>();

}
