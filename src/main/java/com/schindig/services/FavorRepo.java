package com.schindig.services;
import com.schindig.entities.Favor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Agronis on 12/9/15.
 */
public interface FavorRepo extends CrudRepository<Favor, Integer> {


    
}
