package com.schindig.services;

import com.schindig.entities.InviteList;
import com.schindig.entities.Party;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by landonkail on 12/16/15.
 */
public interface InviteListRepo extends CrudRepository<InviteList, Integer> {
//    List<InviteList> findAllByParty(Integer party);

}
