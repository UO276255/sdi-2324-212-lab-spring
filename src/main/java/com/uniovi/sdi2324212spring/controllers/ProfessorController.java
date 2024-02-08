package com.uniovi.sdi2324212spring.controllers;

import com.uniovi.sdi2324212spring.entities.Mark;
import com.uniovi.sdi2324212spring.entities.Professor;
import com.uniovi.sdi2324212spring.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfessorController {
    @Autowired //Inyectar el servicio
    private ProfessorService profesorService;


    @RequestMapping("/professor/list")
    public String getList() {
        return profesorService.getProfesores().toString();
    }

    @RequestMapping( value = "/professor/add", method = RequestMethod.POST)
    public String setProfesor(@ModelAttribute Professor p) {
        profesorService.addProfessor(p);
        return "Added";
    }
    @RequestMapping( "/professor/details/{id}")
    public String getDetail(@PathVariable Long id){
        return profesorService.getProfessor(id).toString();
    }

    @RequestMapping(  "/professor/delete/{id}")
    public String deleteProfesor(@PathVariable Long id) {
        profesorService.deleteProfessor(id);
        return "Deleted";

    }
    @RequestMapping(value = "/professor/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("mark", profesorService.getProfessor(id));
        return "formulario edit";
    }
    @RequestMapping(value="/professor/edit/{id}", method=RequestMethod.POST)
    public String setEdit(@ModelAttribute Professor p, @PathVariable Long id){
        p.setId(id);
        profesorService.addProfessor(p);
        return "formulario edit";
    }
}

