package com.backendnotebook.services.note.service;

import com.backendnotebook.common.models.Note;
import com.backendnotebook.common.models.UserInfo;
import com.backendnotebook.services.note.models.NoteQdo;
import com.backendnotebook.services.note.models.NoteRdo;
import com.backendnotebook.services.note.models.NoteListRdo;

import java.util.List;

public interface NoteService {

    public NoteRdo add(NoteQdo noteQdo, UserInfo userInfo, Integer timezoneOffset);
    public NoteRdo update(NoteQdo noteQdo, UserInfo userInfo, Integer timezoneOffset);
    public NoteListRdo getList(Integer pageNo, Integer pageSize,
                               UserInfo userInfo, Integer timezoneOffset, List<Integer> notebookIds);

    public Note findById(Integer id, UserInfo userInfo) ;

    }
