package com.uniovi.sdi2324212spring.services;

import com.uniovi.sdi2324212spring.entities.Mark;
import com.uniovi.sdi2324212spring.entities.Professor;
import javax.annotation.PostConstruct;

import com.uniovi.sdi2324212spring.repositories.MarksRepository;
import com.uniovi.sdi2324212spring.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getProfesores(){
        List<Professor> prof = new ArrayList<Professor>();
        professorRepository.findAll().forEach(prof::add);
        return prof;
    }

    public Professor getProfessor(Long id) {
        return professorRepository.findById(id).get();
    }
    public void addProfessor(Professor p) {
        professorRepository.save(p);
    }

    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }
}
