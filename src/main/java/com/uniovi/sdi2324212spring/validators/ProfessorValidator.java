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
    private final ProfessorService professorService;

    public ProfessorValidator(ProfessorService professorService) {
        this.professorService = professorService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Professor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Professor prof = (Professor) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
        if (prof.getDni().length() != 9 ) {
            errors.rejectValue("dni", "Error.profAdd.dni.length");
        }
        if(!Character.isLetter(prof.getDni().charAt(prof.getDni().length()-1))){
            errors.rejectValue("dni", "Error.profAdd.dni.letter");
        }
        if(professorService.getProfessorByDni(prof.getDni()) != null){
            errors.rejectValue("dni", "Error.signup.dni.duplicate");
        }
    }
}
