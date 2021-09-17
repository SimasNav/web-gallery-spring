package lt.insoft.webgallery.domain.image;

import lt.insoft.webgallery.domain.image.model.Image;
import lt.insoft.webgallery.domain.image.model.Image_;
import lt.insoft.webgallery.domain.image.model.Tag;
import lt.insoft.webgallery.domain.image.model.Tag_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

@Service
public final class ImageSpecifications {

    public static Specification<Image> search(String searchField) {
        searchField = searchField.toLowerCase();
        Specification<Image> specification = Specification.where(null);
        if (StringUtils.isNotBlank(searchField)) {
            specification = specification.or(nameLike(searchField))
                    .or(typeLike(searchField))
                    .or(descriptionLike(searchField))
                    .or(tagNameLike(searchField));
        }
        return specification;
    }

    private static Specification<Image> nameLike(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get(Image_.name)), "%" + name + "%"));
    }

    private static Specification<Image> typeLike(String type) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get(Image_.type)), "%" + type + "%"));
    }

    private static Specification<Image> descriptionLike(String description) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get(Image_.description)), "%" + description + "%"));
    }

    private static Specification<Image> tagNameLike(String tagName) {
        return (root, criteriaQuery, cb) -> {
            Subquery<Image> subQuery = criteriaQuery.subquery(Image.class);
            Root<Image> subRoot = subQuery.correlate(root);
            Join<Image, Tag> tagListJoin = subRoot.join(Image_.tags);
            subQuery.select(subRoot);
            subQuery.where(cb.like(cb.lower(tagListJoin.get(Tag_.tagName)), "%" + tagName + "%"));
            return cb.exists(subQuery);
        };
    }
}
