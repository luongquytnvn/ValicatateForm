package com.codegym.validation;

import com.codegym.models.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PhoneNumberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"phoneNumber","phoneNumber.empty","phone number not empty");
        User user = (User) target;
        String regex = "[0-9]{10}";
        if (!user.getPhoneNumber().matches(regex)) {
            errors.rejectValue("phoneNumber", "phoneNumber.error","Phone number has 10 number");
        }
    }
}
