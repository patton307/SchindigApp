package com.schindig.services;

import com.schindig.entities.FavorList;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by landonkail on 12/16/15.
 */
public interface FavorListRepo extends CrudRepository<FavorList, Integer> {
//    List<FavorList> findAllByParty(Integer id);

}
