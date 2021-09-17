package lt.insoft.webgallery.domain.image.repository;

import lt.insoft.webgallery.domain.image.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {

}
