package com.backendnotebook.services.note.models;


import com.backendnotebook.common.constants.CommonConstants;
import com.backendnotebook.common.models.Note;
import com.backendnotebook.common.models.Notebook;
import com.backendnotebook.services.user.model.UserInfoRdo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteRdo {

    @JsonProperty(CommonConstants.ID)
    public Integer id;
    @JsonProperty(CommonConstants.TITLE)
    public String  title;
    @JsonProperty(CommonConstants.CONTENT)
    public String content;

    @JsonProperty(CommonConstants.CREATED_BY)
    public UserInfoRdo createdBy;

    @JsonProperty(CommonConstants.UPDATED_BY)
    public UserInfoRdo updatedby;

    @JsonProperty(CommonConstants.CREATED_AT)
    public Long createdat;

    @JsonProperty(CommonConstants.UPDATED_AT)
    public Long updatedat;

    public NoteRdo() {
    }

    public NoteRdo(Note note) {
        this.id = note.getId();
        this.content = note.getContent();
        this.title = note.getTitle();
        this.createdat = note.getCreatedat().getTime();
        this.createdBy = note.getCreatedby() != null ? new UserInfoRdo(note.getCreatedby()) : null;
        this.updatedat = note.getUpdatedat().getTime();
        this.updatedby = note.getUpdatedby() != null ? new UserInfoRdo(note.getUpdatedby()) : null;
    }
}
