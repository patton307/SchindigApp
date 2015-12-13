package com.schindig.services;
import com.schindig.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Agronis on 12/9/15.
 */
public interface UserRepo extends CrudRepository<User, Integer> {
    User findOneByName(String name);
    
}
