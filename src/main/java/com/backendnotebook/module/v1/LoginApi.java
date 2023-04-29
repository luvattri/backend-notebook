package com.backendnotebook.module.v1;


import com.backendnotebook.common.constants.ApirUri;
import com.backendnotebook.services.user.model.AuthRequestQdo;
import com.backendnotebook.services.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApirUri.USER)
public class LoginApi {


    @Autowired
    private LoginService loginService;

    @PostMapping("/authenticate")
    public String authenticateAndGenrateToken(@RequestBody AuthRequestQdo authRequestQdo) {

        return loginService.authenticateAndGenrateToken(authRequestQdo);
    }
}
