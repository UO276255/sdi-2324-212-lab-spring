package com.uniovi.sdi2324212spring.controllers;

import com.uniovi.sdi2324212spring.entities.Mark;
import com.uniovi.sdi2324212spring.entities.Professor;
import com.uniovi.sdi2324212spring.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorController {
    @Autowired //Inyectar el servicio
    private ProfessorService profesorService;


    @RequestMapping("/professor/list")
    public String getList(Model model) {
        model.addAttribute("professorList", profesorService.getProfesores());
        return "professor/list";
    }

    @RequestMapping(value = "/professor/add")
    public String getProfessor() {
        return "professor/add";
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor p) {
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
        p.setId(id);
        profesorService.addProfessor(p);
        return "redirect:/professor/details/"+id;
    }
}

