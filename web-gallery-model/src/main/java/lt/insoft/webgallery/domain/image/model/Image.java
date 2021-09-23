package lt.insoft.webgallery.domain.image.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lt.insoft.webgallery.domain.image.model.Tag;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class Image {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

    @Lob
    @Column(name = "PIC_BYTES")
    private byte[] picBytes;

    @Lob
    @Column(name = "THUMBNAIL_BYTES")
    private byte[] thumbnailBytes;

    @ManyToMany
    @JoinTable(name = "IMAGE_TAG",
    joinColumns = @JoinColumn(name = "IMAGE_ID"),
    inverseJoinColumns = @JoinColumn(name ="TAG_ID"))
    private List<Tag> tags = new ArrayList<>();

}
