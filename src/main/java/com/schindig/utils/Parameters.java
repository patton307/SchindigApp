package com.schindig.utils;

import com.schindig.entities.Favor;
import com.schindig.entities.Party;
import com.schindig.entities.User;
import org.springframework.data.annotation.Transient;

/**
 * Created by landonkail on 12/14/15.
 */
public class Parameters {

    public Favor favor;

    @Transient
    public Party party;

    public User user;

    public String rsvpStatus;

    public Integer partyID;
    public String favorName;


}
