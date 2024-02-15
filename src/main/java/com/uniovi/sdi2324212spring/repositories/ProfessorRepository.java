package com.uniovi.sdi2324212spring.repositories;

import com.uniovi.sdi2324212spring.entities.Professor;
import com.uniovi.sdi2324212spring.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {
    Professor findByDni(String dni);
}
