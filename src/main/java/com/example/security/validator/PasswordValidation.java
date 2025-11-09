package com.example.security.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD,ElementType.METHOD})
@Constraint(validatedBy = StrongPasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)

public @interface PasswordValidation {
    String message() default "Password must be strong";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
