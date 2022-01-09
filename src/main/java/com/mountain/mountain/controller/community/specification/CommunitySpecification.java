package com.mountain.mountain.controller.community.specification;

import com.mountain.mountain.domain.community.model.Community;
import org.springframework.data.jpa.domain.Specification;

public class CommunitySpecification {


    public static Specification<Community> equalCateId(long cateId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"),cateId));
    }

    public static Specification<Community> equalTitle(String title) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("Title"), title)));
    }
}
