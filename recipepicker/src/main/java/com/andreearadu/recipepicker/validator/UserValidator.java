package com.andreearadu.recipepicker.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.andreearadu.recipepicker.dto.UserDto;

@Component
public class UserValidator implements Validator {
   
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto user = (UserDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "notEmpty");
        if (user.getName().length() < 6) {
            errors.rejectValue("name", "size.userForm.name");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "notEmpty");
        if (!user.getEmail().matches("^(.+)@(.+)$")) {
            errors.rejectValue("email", "size.userForm.email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "notEmpty");
        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "size.userForm.password");
        }
    }
}