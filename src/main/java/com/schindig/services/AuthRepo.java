package com.schindig.services;
import com.schindig.entities.Auth;
import com.schindig.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by Agronis on 12/19/15.
 */
public interface AuthRepo extends CrudRepository<Auth, Integer> {

    Auth findByDevice(String device);

//    ArrayList<Auth> findByUser(User user);

}
