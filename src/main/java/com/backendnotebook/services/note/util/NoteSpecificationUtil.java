package com.backendnotebook.services.note.util;

import com.backendnotebook.common.models.Note;
import com.backendnotebook.common.models.UserInfo;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteSpecificationUtil {
    public static Specification<Note> filterNotes(List<Integer> ids, UserInfo userInfo,
                                                  List<Integer> notebookIds) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Integer userId = userInfo.getId();

            predicates.add(criteriaBuilder.equal(root.get("createdby").get("id"), userId));
            predicates.add(criteriaBuilder.equal(root.get("updatedby").get("id"), userId));

            if (!CollectionUtils.isEmpty(ids)) {
                predicates.add(root.get("ids").in(notebookIds));
            }

            if (!CollectionUtils.isEmpty(notebookIds)) {
                predicates.add(root.get("notebook").get("id").in(notebookIds));
            }

            query.orderBy(criteriaBuilder.desc(root.get("updatedat")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
