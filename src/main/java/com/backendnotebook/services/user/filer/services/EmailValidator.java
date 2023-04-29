package com.backendnotebook.services.user.filer.services;

import com.backendnotebook.common.repository.UserInfoRepository;
import com.backendnotebook.services.user.filer.annotation.EmailValidate;
import com.backendnotebook.services.user.service.UserInfoService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<EmailValidate, String> {

    private UserInfoService userInfoService;
    private final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    @Override
    public void initialize(EmailValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        Boolean isEmailValid = patternMatches(email, regexPattern);
        if (!isEmailValid) {
            return false;
        }
        Boolean isPresent = userInfoService.emailExsistes(email);
        if (isPresent) {
            return false;
        }

        return true;
    }

    private boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
}
