package com.uniovi.sdi2324212spring.controllers;

import com.uniovi.sdi2324212spring.entities.Professor;
import com.uniovi.sdi2324212spring.services.ProfessorService;
import com.uniovi.sdi2324212spring.validators.ProfessorValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorController {
    private final ProfessorService profesorService;
    private final ProfessorValidator professorValidator;

    public ProfessorController(ProfessorService profesorService, ProfessorValidator professorValidator) {
        this.profesorService = profesorService;
        this.professorValidator = professorValidator;
    }

    @RequestMapping("/professor/list")
    public String getList(Model model) {
        model.addAttribute("professorList", profesorService.getProfesores());
        return "professor/list";
    }

    @RequestMapping(value = "/professor/add")
    public String setProfessor(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/add";
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String setProfessor(@Validated Professor p, BindingResult result) {
        professorValidator.validate(p,result);
        if(result.hasErrors()){
            return "/professor/add";
        }
        profesorService.addProfessor(p);
        return "redirect:/professor/list";
    }
    @RequestMapping("/professor/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("p", profesorService.getProfessor(id));
        return "professor/details";
    }

    @RequestMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        profesorService.deleteProfessor(id);
        return "redirect:/professor/list";
    }
    @RequestMapping(value = "/professor/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("p", profesorService.getProfessor(id));
        return "professor/edit";
    }
    @RequestMapping(value="/professor/edit/{id}", method=RequestMethod.POST)
    public String setEdit(@ModelAttribute Professor p, @PathVariable Long id){
        Professor original = profesorService.getProfessor(id);
        // modificar solo score y description
        original.setName(p.getName());
        original.setDni(p.getDni());
        original.setSurname(p.getSurname());
        original.setCategory(p.getCategory());
        profesorService.addProfessor(original);
        return "redirect:/professor/details/"+id;
    }
}

