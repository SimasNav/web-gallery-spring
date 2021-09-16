package lt.insoft.webgallery.domain.image;

import lt.insoft.webgallery.domain.image.tag.Tag;
import lt.insoft.webgallery.domain.image.tag.Tag_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class ImageSpecifications {

    ImageService imageService;

    public ImageSpecifications(ImageService imageService) {
        this.imageService = imageService;
    }

    private static Specification<Image> nameLike(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(Image_.name)), "%" + name + "%"));
    }

    private static Specification<Image> typeLike(String type) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(Image_.type)), "%" + type + "%"));
    }

    private static Specification<Image> descriptionLike(String description) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(Image_.description)), "%" + description + "%"));
    }

    private static Specification<Image> tagNameLike(String tagName) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<Image, Tag> joinTags = root.join(Image_.tags);
            return criteriaBuilder.like(criteriaBuilder.lower(joinTags.get(Tag_.tagName)), "%" + tagName + "%");
        });
    }

    public static Specification<Image> search(String searchField) {
        searchField = searchField.toLowerCase();
        Specification<Image> specification = Specification.where(null);
        if (StringUtils.isNotBlank(searchField)) {
            specification = specification.or(ImageSpecifications.nameLike(searchField))
                    .or(ImageSpecifications.typeLike(searchField))
                    .or(ImageSpecifications.descriptionLike(searchField))
                   /*.or(ImageSpecifications.tagNameLike(searchField))*/;
        }
        return specification;
    }


}
