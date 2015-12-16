package com.schindig.entities;

import javax.persistence.*;

/**
 * Created by landonkail on 12/16/15.
 */
@Entity
public class FavorList {

    @GeneratedValue
    @Id
    public Integer listID;

    @OneToOne
    public Favor favor;

    @OneToOne
    public Party party;

    public FavorList(Favor favor, Party party) {
        this.favor = favor;
        this.party = party;
    }


    public FavorList() {
    }

    public Integer getListID() {
        return listID;
    }

    public Favor getFavor() {
        return favor;
    }
}
