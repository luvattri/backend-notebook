package com.backendnotebook.services.note.models;

import com.backendnotebook.common.constants.CommonConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteQdo {
    public Integer id;
    public String title;
    public String content;
    public Integer notebookId;
}
