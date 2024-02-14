package com.uniovi.sdi2324212spring.repositories;

import com.uniovi.sdi2324212spring.entities.*;
import org.springframework.data.repository.CrudRepository;
public interface UsersRepository extends CrudRepository<User, Long>{
}