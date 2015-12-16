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

    public FavorList(Integer listID, Favor favor) {
        this.listID = listID;
        this.favor = favor;
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
