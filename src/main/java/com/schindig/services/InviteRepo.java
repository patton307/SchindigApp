package com.schindig.services;

import com.schindig.entities.Invite;
import com.schindig.entities.Party;
import com.schindig.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by landonkail on 12/16/15.
 */
public interface InviteRepo extends CrudRepository<Invite, Integer> {
//    List<InviteList> findAllByParty(Integer party);

//    List<Invite> findAllByUserId(Integer id);

    Invite findByUser(Integer id);

//    @Query("SELECT i FROM Invite i WHERE party =?, user =?")
//    Integer findInvite(Party party, User user);

    @Query("SELECT party FROM Invite i WHERE user = ?")
    List<Party> findInvite(User user);

    @Query("SELECT COUNT(i) FROM Invite i WHERE user = ?")
    Integer findInviteCount(Party party);
}
