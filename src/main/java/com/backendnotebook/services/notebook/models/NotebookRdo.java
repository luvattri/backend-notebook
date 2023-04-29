package com.backendnotebook.services.notebook.models;

import com.backendnotebook.common.constants.CommonConstants;
import com.backendnotebook.common.models.Note;
import com.backendnotebook.common.models.Notebook;
import com.backendnotebook.services.user.model.UserInfoRdo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotebookRdo {
    @JsonProperty(CommonConstants.ID)
    public Integer id;
    @JsonProperty(CommonConstants.TITLE)
    public String  title;
    @JsonProperty(CommonConstants.CREATED_BY)
    public UserInfoRdo createdBy;

    @JsonProperty(CommonConstants.UPDATED_BY)
    public UserInfoRdo updatedby;

    @JsonProperty(CommonConstants.CREATED_AT)
    public Long createdat;

    @JsonProperty(CommonConstants.UPDATED_AT)
    public Long updatedat;

    public NotebookRdo() {
    }

    public NotebookRdo(Notebook notebook) {
        this.id = notebook.getId();
        this.title = notebook.getTitle();
        this.createdat = notebook.getCreatedat().getTime();
        this.createdBy = notebook.getCreatedby() != null ? new UserInfoRdo(notebook.getCreatedby()) : null;
        this.updatedat = notebook.getUpdatedat().getTime();
        this.updatedby = notebook.getUpdatedby() != null ? new UserInfoRdo(notebook.getUpdatedby()) : null;
    }
}
