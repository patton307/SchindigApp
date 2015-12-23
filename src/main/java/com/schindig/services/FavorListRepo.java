package com.schindig.services;

import com.schindig.entities.Favor;
import com.schindig.entities.FavorList;
import com.schindig.entities.Party;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by landonkail on 12/16/15.
 */
public interface FavorListRepo extends CrudRepository<FavorList, Integer> {

    ArrayList<FavorList> findByParty(Party party);

    @Query("SELECT (f) FROM FavorList f WHERE favor = ? AND party = ?")
    FavorList findOneByFavorAndParty(Favor favor, Party party);

    Favor findByFavor(Favor id);


}
