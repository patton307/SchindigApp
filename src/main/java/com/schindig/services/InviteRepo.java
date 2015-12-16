package com.schindig.services;

import com.schindig.entities.Invite;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by landonkail on 12/16/15.
 */
public interface InviteRepo extends CrudRepository<Invite, Integer> {
//    List<InviteList> findAllByParty(Integer party);

}
