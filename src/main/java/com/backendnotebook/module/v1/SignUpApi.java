package com.backendnotebook.module.v1;

import com.backendnotebook.common.constants.ApirUri;
import com.backendnotebook.common.constants.CommonConstants;
import com.backendnotebook.common.rdo.BasicRdo;
import com.backendnotebook.services.user.model.UserInfoQdo;
import com.backendnotebook.services.user.model.UserInfoRdo;
import com.backendnotebook.services.user.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApirUri.USER)
public class SignUpApi {

    private  final UserInfoService userInfoService;

    @Autowired
    public SignUpApi(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    @PostMapping("/signup")
    public ResponseEntity<BasicRdo> addNewUser(@RequestBody @Valid UserInfoQdo userInfoQdo) {

        BasicRdo<UserInfoRdo> basicRdo = new BasicRdo<>();
        UserInfoRdo userInfoRdo = userInfoService.addUser(userInfoQdo);
        basicRdo.data = userInfoRdo;
        return basicRdo.getResponse(CommonConstants.SUCESS, HttpStatus.CREATED, userInfoRdo);
    }
}
