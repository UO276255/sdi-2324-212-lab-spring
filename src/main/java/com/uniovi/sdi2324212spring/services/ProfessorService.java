package com.uniovi.sdi2324212spring.services;

import com.uniovi.sdi2324212spring.entities.Professor;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {



    public List<Professor> profesores = new ArrayList<Professor>();

    @PostConstruct
    public void init() {
        profesores.add(new Professor(1L,"114568675D","Juan","Gonzalez Campos","Matematicas"));
        profesores.add(new Professor(2L,"256482169K","Susana","Lopez Hidalgo","Historia"));
    }

    public List<Professor> getProfesores(){
        return this.profesores;
    }

    public Professor getProfessor(Long id) {
        return profesores.stream()
                .filter(p -> p.getId().equals(id)).findFirst().get();
    }
    public void addProfessor(Professor p) {
        if (p.getId().equals(null)) {
            p.setId(profesores.get(profesores.size() - 1).getId() + 1);
        }
        profesores.add(p);
    }

    public void deleteProfessor(Long id){
        profesores.removeIf(p -> p.getId().equals(id));
    }


}
