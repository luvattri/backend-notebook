package com.backendnotebook.module.v1;

import com.backendnotebook.common.constants.ApirUri;
import com.backendnotebook.common.models.UserInfo;
import com.backendnotebook.services.user.model.UserInfoQdo;
import com.backendnotebook.services.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(ApirUri.USER)
public class SignUpApi {

    private  final UserInfoService userInfoService;

    @Autowired
    public SignUpApi(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    @PostMapping("/signup")
    public String addNewUser(@RequestBody UserInfoQdo userInfoQdo) {
        return userInfoService.addUser(userInfoQdo);
    }
}
