package com.uniovi.sdi2324212spring.controllers;

import com.uniovi.sdi2324212spring.entities.Professor;
import com.uniovi.sdi2324212spring.services.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }


}