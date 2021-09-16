package lt.insoft.webgallery.domain.image;

import lt.insoft.webgallery.domain.image.tag.Tag;
import lt.insoft.webgallery.domain.image.tag.Tag_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class ImageSpecifications {

    public static Specification<Image> search(String searchField) {
        searchField = searchField.toLowerCase();
        Specification<Image> specification = Specification.where(null);
        if (StringUtils.isNotBlank(searchField)) {
            specification = specification.or(nameLike(searchField))
                    .or(typeLike(searchField))
                    .or(descriptionLike(searchField))
                    .or(tagNameLike2(searchField));
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
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<Image, Tag> joinTags = root.join(Image_.tags);
            criteriaQuery.distinct(true);
            return criteriaBuilder.like(criteriaBuilder.lower(joinTags.get(Tag_.tagName)), "%" + tagName + "%");
        });
    }

    private static Specification<Image> tagNameLike2(String tagName) {
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
// select distinct image0_.id as id1_0_, image0_.date_time as date_tim2_0_, image0_.description as descript3_0_, image0_.name as name4_0_, image0_.pic_bytes as pic_byte5_0_, image0_.thumbnail_bytes as thumbnai6_0_, image0_.type as type7_0_ from image image0_ inner join image_tag tags1_ on image0_.id=tags1_.image_id inner join tag tag2_ on tags1_.tag_id=tag2_.id where lower(image0_.name) like ? or lower(image0_.type) like ? or lower(image0_.description) like ? or lower(tag2_.tag_name) like ?
// select          image0_.id as id1_0_, image0_.date_time as date_tim2_0_, image0_.description as descript3_0_, image0_.name as name4_0_, image0_.pic_bytes as pic_byte5_0_, image0_.thumbnail_bytes as thumbnai6_0_, image0_.type as type7_0_ from image image0_ where lower(image0_.name) like ? or lower(image0_.type) like ? or lower(image0_.description) like ? or exists (select image0_.id as id1_0_ from image_tag tags1_, tag tag2_ where image0_.id=tags1_.image_id and tags1_.tag_id=tag2_.id and (lower(tag2_.tag_name) like ?))