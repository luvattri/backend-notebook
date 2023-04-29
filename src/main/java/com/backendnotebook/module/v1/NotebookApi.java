package com.backendnotebook.module.v1;

import com.backendnotebook.common.constants.ApirUri;
import com.backendnotebook.common.constants.CommonConstants;
import com.backendnotebook.common.models.Note;
import com.backendnotebook.common.models.Notebook;
import com.backendnotebook.common.models.UserInfo;
import com.backendnotebook.common.rdo.BasicRdo;
import com.backendnotebook.services.notebook.models.NotebookListRdo;
import com.backendnotebook.services.notebook.models.NotebookQdo;
import com.backendnotebook.services.notebook.models.NotebookRdo;
import com.backendnotebook.services.notebook.services.NotebookService;
import com.backendnotebook.services.user.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApirUri.NOTEBOOK)
public class NotebookApi {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private NotebookService notebookService;

    @PostMapping
    public ResponseEntity<BasicRdo> add(
            @RequestBody @Valid NotebookQdo noteQdo,
            @RequestParam(required = false, name = CommonConstants.TIMEZONE_OFFSET) Integer timezoneOffset,
            Authentication authentication) {

        String username = authentication.getName();
        UserInfo userInfo = userInfoService.findByName(username).orElse(null);
        noteQdo.id = 0;
        NotebookRdo noteRdo = notebookService.add(noteQdo, userInfo, timezoneOffset);
        BasicRdo<NotebookRdo> basicRdo = new BasicRdo<>();
        basicRdo.data = noteRdo;
        return basicRdo.getResponse(CommonConstants.SUCESS, HttpStatus.CREATED, noteRdo);
    }

    @PutMapping
    public ResponseEntity<BasicRdo> update(
            @RequestBody @Valid NotebookQdo notebookQdo,
            @RequestParam(required = false, name = CommonConstants.TIMEZONE_OFFSET) Integer timezoneOffset,
            Authentication authentication) {

        BasicRdo<NotebookRdo> basicRdo = new BasicRdo<>();

        String username = authentication.getName();
        UserInfo userInfo = userInfoService.findByName(username).orElse(null);
        Boolean isPresent = true;
        if (notebookQdo.id == null) {
            isPresent = false;
        } else {
            Notebook note = notebookService.findById(notebookQdo.id, userInfo);
            if (note == null) {
                isPresent = false;
            }
        }
        if (!isPresent) {
            return basicRdo.getResponse(CommonConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST, null);
        }
        NotebookRdo noteRdo = notebookService.update(notebookQdo, userInfo, timezoneOffset);
        basicRdo.data = noteRdo;
        return basicRdo.getResponse(CommonConstants.SUCESS, HttpStatus.NO_CONTENT, noteRdo);
    }

    @GetMapping
    public ResponseEntity<BasicRdo> list(
            @RequestParam (defaultValue = "0", name = CommonConstants.PAGE_NO) Integer pageNo,
            @RequestParam (defaultValue = "50", name = CommonConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam (required = false, name = CommonConstants.NOTEBOOK_IDS) List<Integer> notebookIds,
            @RequestParam(required = false, name = CommonConstants.TIMEZONE_OFFSET) Integer timezoneOffset,
            Authentication authentication) {

        String username = authentication.getName();
        UserInfo userInfo = userInfoService.findByName(username).orElse(null);
        NotebookListRdo noteListRdo = notebookService.getList(pageNo, pageSize, userInfo, timezoneOffset);
        BasicRdo<List<NotebookRdo>> basicRdo = new BasicRdo<>();
        return basicRdo.getResponse(CommonConstants.SUCESS, HttpStatus.OK, noteListRdo.notebookList,
                noteListRdo.pageNumber, noteListRdo.pageSize, noteListRdo.total);
    }

}
