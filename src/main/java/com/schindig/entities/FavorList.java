package com.schindig.entities;

import javax.persistence.*;

/**
 * Created by landonkail on 12/16/15.
 */
@Entity
public class FavorList {

    @GeneratedValue
    @Id
    public Integer favorlistID;

    @ManyToOne
    public Favor favlist;

    public FavorList(Integer favorlistID, Favor favlist) {
        this.favorlistID = favorlistID;
        this.favlist = favlist;
    }

    public FavorList() {
    }

    public Integer getFavorlistID() {
        return favorlistID;
    }

    public Favor getFavlist() {
        return favlist;
    }
}
