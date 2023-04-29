package com.backendnotebook.services.user.filer.annotation;

import com.backendnotebook.common.constants.ValidationConstants;
import com.backendnotebook.services.user.filer.services.EmailValidator;
import com.nimbusds.jose.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.*;


@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailValidate {
    String message() default ValidationConstants.EMAIL_VALIDATION_ERROR;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
