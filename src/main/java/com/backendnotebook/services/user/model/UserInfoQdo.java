package com.backendnotebook.services.user.model;

import com.backendnotebook.services.user.filer.annotation.EmailValidate;
import com.backendnotebook.services.user.filer.annotation.UserNameValidate;

public class UserInfoQdo {

    @EmailValidate
    public String email;
    public String password;
}
