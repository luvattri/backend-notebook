package com.backendnotebook.services.notebook.services;

import com.backendnotebook.common.models.Notebook;
import com.backendnotebook.common.models.UserInfo;
import com.backendnotebook.services.notebook.models.NotebookListRdo;
import com.backendnotebook.services.notebook.models.NotebookQdo;
import com.backendnotebook.services.notebook.models.NotebookRdo;

public interface NotebookService {
    public NotebookRdo add(NotebookQdo notebookQdo, UserInfo userInfo, Integer timezoneOffset) ;
    public NotebookRdo update(NotebookQdo notebookQdo, UserInfo userInfo, Integer timezoneOffset);
    public NotebookListRdo getList(Integer pageNo, Integer pageSize,
                                   UserInfo userInfo, Integer timezoneOffset);
    public Notebook findById(Integer id, UserInfo userInfo) ;
    }
