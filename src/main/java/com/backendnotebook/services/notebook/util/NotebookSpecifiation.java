package com.backendnotebook.services.notebook.util;

import com.backendnotebook.common.models.Note;
import com.backendnotebook.common.models.Notebook;
import com.backendnotebook.common.models.UserInfo;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotebookSpecifiation {
    public static Specification<Notebook> filterNotebook(List<Integer> ids, UserInfo userInfo) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Integer userId = userInfo.getId();

            if (!CollectionUtils.isEmpty(ids)) {
                predicates.add(root.get("id").in(ids));
            }
            predicates.add(criteriaBuilder.equal(root.get("createdby").get("id"), userId));
            predicates.add(criteriaBuilder.equal(root.get("updatedby").get("id"), userId));

            query.orderBy(criteriaBuilder.desc(root.get("updatedat")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
