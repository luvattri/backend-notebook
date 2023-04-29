package com.backendnotebook.common.repository;

import com.backendnotebook.common.models.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteBookRepository extends JpaRepository<Notebook, Integer>, JpaSpecificationExecutor {
}
