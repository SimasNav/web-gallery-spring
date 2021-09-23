package lt.insoft.webgallery.domain.image.repository;

import lt.insoft.webgallery.domain.image.dto.ImageDto;
import lt.insoft.webgallery.domain.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;


@NoRepositoryBean
public interface ImageRepository extends JpaRepository<Image,Long>, JpaSpecificationExecutor<Image> {

    List<ImageDto> findAllImagesByImageNameWithMultiSelect(String imageName);

}
