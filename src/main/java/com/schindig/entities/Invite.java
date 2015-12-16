package com.schindig.entities;

import javax.persistence.*;

/**
 * Created by landonkail on 12/16/15.
 */
@Entity
public class Invite {

    @GeneratedValue
    @Id
    public Integer inviteID;

    @OneToOne
    public User user;

    @OneToOne
    public Party party;

    public String phone;

    public String email;


    public Invite(Integer inviteID, User user, Party party, String phone, String email) {
        this.inviteID = inviteID;
        this.user = user;
        this.party = party;
        this.phone = phone;
        this.email = email;
    }

    public Invite() {
    }

    public Invite(User user, Party party, String phone, String email) {

        this.user = user;
        this.party = party;
        this.phone = phone;
        this.email = email;
    }
    public Integer getInviteID() {
        return inviteID;
    }

    public User getUser() {
        return user;
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
