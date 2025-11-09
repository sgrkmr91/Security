package com.example.security.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class StrongPasswordValidator implements ConstraintValidator<PasswordValidation,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean flag = false;
        if(s!=null && s.length()>8 && s.matches(".*[A-Z].*]") && s.matches(".*[0-9].*")){
         flag = true;
        }
        return flag;
    }
}
