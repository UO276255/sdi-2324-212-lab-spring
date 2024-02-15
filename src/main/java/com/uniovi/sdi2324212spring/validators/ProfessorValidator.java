package com.uniovi.sdi2324212spring.validators;

import com.uniovi.sdi2324212spring.entities.Professor;
import com.uniovi.sdi2324212spring.entities.User;
import com.uniovi.sdi2324212spring.services.ProfessorService;
import com.uniovi.sdi2324212spring.services.UsersService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProfessorValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
