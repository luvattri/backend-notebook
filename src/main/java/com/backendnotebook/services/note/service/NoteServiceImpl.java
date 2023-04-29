package com.backendnotebook.services.note.service;

import com.backendnotebook.common.models.Note;
import com.backendnotebook.common.models.Notebook;
import com.backendnotebook.common.models.UserInfo;
import com.backendnotebook.common.repository.NoteRepository;
import com.backendnotebook.services.note.models.NoteQdo;
import com.backendnotebook.services.note.models.NoteRdo;
import com.backendnotebook.services.note.util.NoteSpecificationUtil;
import com.backendnotebook.services.note.models.NoteListRdo;
import com.backendnotebook.services.notebook.services.NotebookService;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    private NoteRepository noteRepository;
    private NotebookService notebookService;

    @Override
    public NoteRdo add(NoteQdo noteQdo, UserInfo userInfo, Integer timezoneOffset) {

        Note note = save(noteQdo, userInfo, timezoneOffset);

        return new NoteRdo(note);
    }

    @Override
    public NoteRdo update(NoteQdo noteQdo, UserInfo userInfo, Integer timezoneOffset) {
        Note note = save(noteQdo, userInfo, timezoneOffset);
        return new NoteRdo(note);
    }

    private Note save(NoteQdo noteQdo, UserInfo userInfo, Integer timezoneOffset) {
        Note note = null;
        if (noteQdo.id == null) {
            note = new Note();
            note.setCreatedby(userInfo);
            note.setCreatedat(new Date(System.currentTimeMillis()));
        } else {
            note = noteRepository.findById(noteQdo.id).orElse(null);
        }
        if (noteQdo.notebookId != null) {
            Notebook notebook = notebookService.findById(noteQdo.notebookId, userInfo);
            if (notebook != null) {
                note.setNotebook(notebook);
            }
        }

        note.setTitle(noteQdo.title);
        note.setContent(noteQdo.content);

        note.setUpdatedby(userInfo);
        note.setUpdatedat(new Date(System.currentTimeMillis()));

        noteRepository.save(note);
        return note;
    }


    @Override
    public NoteListRdo getList(Integer pageNo, Integer pageSize,
                               UserInfo userInfo, Integer timezoneOffset, List<Integer> notebookIds) {

        Specification<Note> noteSpecification = NoteSpecificationUtil.filterNotes(null, userInfo, notebookIds);
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Note> notes = noteRepository.findAll(noteSpecification, pageable);

        List<NoteRdo> noteList = notes.getContent().stream().map(note -> new NoteRdo(note)).collect(Collectors.toList());

        return new NoteListRdo(noteList, notes.getTotalElements(), pageNo, pageSize);
    }

    @Override
    public Note findById(Integer id, UserInfo userInfo) {
        Specification<Note> noteSpecification = NoteSpecificationUtil.filterNotes(Collections.singletonList(id), userInfo, null);

        List<Note> noteList = noteRepository.findAll(noteSpecification);

        if (!CollectionUtils.isEmpty(noteList)) {
            return noteList.get(0);
        }
        return null;
    }
        @Autowired
    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Autowired
    public void setNotebookService(NotebookService notebookService) {
        this.notebookService = notebookService;
    }
}
