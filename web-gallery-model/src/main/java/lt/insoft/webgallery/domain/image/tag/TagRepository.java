package lt.insoft.webgallery.domain.image.tag;

import lt.insoft.webgallery.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {

}
