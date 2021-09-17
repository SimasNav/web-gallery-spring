package lt.insoft.webgallery.domain.image.repository.impl;

import lt.insoft.webgallery.domain.image.dto.ImageDto;
import lt.insoft.webgallery.domain.image.model.Image;
import lt.insoft.webgallery.domain.image.model.Image_;
import lt.insoft.webgallery.domain.image.model.Tag;
import lt.insoft.webgallery.domain.image.model.Tag_;
import lt.insoft.webgallery.domain.image.repository.ImageRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Transactional
@Component("imageRepository")
public class ImageRepositoryImpl extends SimpleJpaRepository<Image, Long> implements ImageRepository {

    @PersistenceContext
    private EntityManager em;

    public ImageRepositoryImpl(EntityManager em) {
        super(Image.class, em);
    }

    @Override
    public List<ImageDto> findAllImagesByImageNameWithMultiSelect(String imageName) {
        imageName = imageName.toLowerCase();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ImageDto> cq = cb.createQuery(ImageDto.class);
        Root<Image> root = cq.from(Image.class);
        cq.multiselect(root.get(Image_.name), root.get(Image_.type));
        cq.where(cb.like(cb.lower(root.get(Image_.name)), "%" + imageName + "%"));
        return em.createQuery(cq).getResultList();
    }

}
