package com.agenciatorus.api.validation;

import com.agenciatorus.api.Services.UserServices;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

    @Autowired
    private UserServices userServices;



    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (userServices == null) {
            return true;
        }
        return !userServices.existsByUsername(username);
    }
}
