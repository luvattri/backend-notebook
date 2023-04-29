package com.backendnotebook.services.notebook.services;

import com.backendnotebook.common.models.Notebook;
import com.backendnotebook.common.models.UserInfo;
import com.backendnotebook.common.repository.NoteBookRepository;
import com.backendnotebook.services.notebook.models.NotebookListRdo;
import com.backendnotebook.services.notebook.models.NotebookQdo;
import com.backendnotebook.services.notebook.models.NotebookRdo;
import com.backendnotebook.services.notebook.util.NotebookSpecifiation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotebookServiceImpl implements NotebookService{
    private NoteBookRepository noteBookRepository;


    @Override
    public NotebookRdo add(NotebookQdo notebookQdo, UserInfo userInfo, Integer timezoneOffset) {

        Notebook notebook = save(notebookQdo, userInfo, timezoneOffset);

        return new NotebookRdo(notebook);
    }

    @Override
    public NotebookRdo update(NotebookQdo notebookQdo, UserInfo userInfo, Integer timezoneOffset) {
        Notebook notebook = save(notebookQdo, userInfo, timezoneOffset);
        return new NotebookRdo(notebook);
    }

    private Notebook save(NotebookQdo notebookQdo, UserInfo userInfo, Integer timezoneOffset) {
        Notebook notebook = null;
        if (notebookQdo.id == null) {
            notebook = new Notebook();
            notebook.setCreatedby(userInfo);
            notebook.setCreatedat(new Date(System.currentTimeMillis()));
        } else {
            notebook = noteBookRepository.findById(notebookQdo.id).orElse(null);
        }
        notebook.setTitle(notebookQdo.title);

        notebook.setUpdatedby(userInfo);
        notebook.setUpdatedat(new Date(System.currentTimeMillis()));

        noteBookRepository.save(notebook);
        return notebook ;
    }


    @Override
    public NotebookListRdo getList(Integer pageNo, Integer pageSize,
                                   UserInfo userInfo, Integer timezoneOffset) {

        Specification<Notebook> notebookSpecification = NotebookSpecifiation.filterNotebook(null, userInfo);
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Notebook> books = noteBookRepository.findAll(notebookSpecification, pageable);

        List<NotebookRdo> noteList = books.getContent().stream().map(notebook -> new NotebookRdo(notebook)).collect(Collectors.toList());

        return new NotebookListRdo(noteList, books.getTotalElements(), pageNo, pageSize);
    }

    public Notebook findById(Integer id, UserInfo userInfo) {
        Specification<Notebook> notebookSpecification = NotebookSpecifiation.filterNotebook(
                Collections.singletonList(id),userInfo);

        List<Notebook> notebookList = noteBookRepository.findAll(notebookSpecification);

        if (!CollectionUtils.isEmpty(notebookList)) {
            return notebookList.get(0);
        }
        return null;
    }
    @Autowired
    public void setNoteBookRepository(NoteBookRepository noteBookRepository) {
        this.noteBookRepository = noteBookRepository;
    }
}
