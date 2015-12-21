package com.schindig.services;
import com.schindig.entities.Party;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by Agronis on 12/9/15.
 */
public interface PartyRepo extends CrudRepository<Party, Integer> {

    @Query("SELECT COUNT(p) FROM Party p")
    Integer totalPartyCount();

    @Query("SELECT DISTINCT partyType FROM Party")
    ArrayList<String> partyTypes();

    @Query("SELECT DISTINCT subType FROM Party")
    ArrayList<String> subTypes();

    @Query("SELECT DISTINCT subType FROM Party WHERE partyType = ?")
    ArrayList<String> partySubType(String subType);
}

