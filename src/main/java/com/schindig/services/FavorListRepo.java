package com.schindig.services;

import com.schindig.entities.Favor;
import com.schindig.entities.FavorList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by landonkail on 12/16/15.
 */
public interface FavorListRepo extends CrudRepository<FavorList, Integer> {

    List<FavorList> findByParty(Integer id);

}