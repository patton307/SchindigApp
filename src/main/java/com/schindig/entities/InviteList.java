package com.schindig.entities;

import javax.persistence.*;

/**
 * Created by landonkail on 12/16/15.
 */
@Entity
public class InviteList {

    @GeneratedValue
    @Id
    public Integer invitelistID;

    public Integer userID;

    @OneToOne
    public Party party;

    String phone;

    String email;


    public InviteList(Integer invitelistID, Integer userID, Party party, String phone, String email) {
        this.invitelistID = invitelistID;
        this.userID = userID;
        this.party = party;
        this.phone = phone;
        this.email = email;
    }

    public InviteList() {
    }

    public Integer getInvitelistID() {
        return invitelistID;
    }

    public Integer getUserID() {
        return userID;
    }

    public Party getParty() {
        return party;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
