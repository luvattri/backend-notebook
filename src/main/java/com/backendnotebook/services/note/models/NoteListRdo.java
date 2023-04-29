package com.backendnotebook.services.note.models;

import com.backendnotebook.services.note.models.NoteRdo;

import java.util.List;

public class NoteListRdo {
    public List<NoteRdo> noteList;
    public Long total;
    public Long pageNumber;
    public Long pageSize;


    public NoteListRdo(List<NoteRdo> noteList, long totalElements, Integer pageNumber, Integer pageSize) {
        this.noteList = noteList;
        this.total = totalElements;
        this.pageNumber = Long.valueOf(pageNumber);
        this.pageSize = Long.valueOf(pageSize);
    }
}
