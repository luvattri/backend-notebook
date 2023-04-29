package com.backendnotebook.services.notebook.models;

import com.backendnotebook.services.note.models.NoteRdo;

import java.util.List;

public class NotebookListRdo {
    public List<NotebookRdo> notebookList;
    public Long total;
    public Long pageNumber;
    public Long pageSize;


    public NotebookListRdo(List<NotebookRdo> notebookList, long totalElements, Integer pageNumber, Integer pageSize) {
        this.notebookList = notebookList;
        this.total = totalElements;
        this.pageNumber = Long.valueOf(pageNumber);
        this.pageSize = Long.valueOf(pageSize);
    }
}
