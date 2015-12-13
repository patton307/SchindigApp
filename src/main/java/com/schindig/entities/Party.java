package com.schindig.entities;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Agronis on 12/9/15.
 */
@Entity
public class Party {

    @GeneratedValue
    @Id
    public Integer id;

//    @OneToMany
//    public User host;

//    @Column(nullable = false)
    public String partyName;

    public String partyType;

    public Integer partyMod;

//    @Column(nullable = false)
    public LocalDateTime createDate;

//    @Column(nullable = false)
    public LocalDateTime partyDate;

//    @Column(nullable = false)
    public String street1;

    public String street2;

//    @Column(nullable = false)
    public String city;

//    @Column(nullable = false)
    public String usState;

//    @Column(nullable = false)
    public Integer zip;

    public ArrayList<String> inviteList;

    public HashMap<Integer, String> rsvp;

    public ArrayList<Catalog> catalogList;

    public String stretchName;

    public Integer stretchGoal;

    public Integer pos;

    public Boolean byob = false;

    public Boolean theme = false;

    public String parking = " ";

    public Party(){}
    public Party(String partyName, String partyType, Integer partyMod, LocalDateTime createDate, LocalDateTime partyDate, String street1, String street2, String city, String usState, Integer zip) {

        this.partyName = partyName;
        this.partyType = partyType;
        this.partyMod = partyMod;
        this.createDate = createDate;
        this.partyDate = partyDate;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.usState = usState;
        this.zip = zip;
    }
    public Party(String partyName, String partyType, Integer partyMod, LocalDateTime createDate, LocalDateTime partyDate, String street1, String street2, String city, String usState, Integer zip, ArrayList<String> inviteList, HashMap<Integer, String> rsvp, ArrayList<Catalog> catalogList, String stretchName, Integer stretchGoal, Integer pos, Boolean byob, Boolean theme, String parking) {

        this.partyName = partyName;
        this.partyType = partyType;
        this.partyMod = partyMod;
        this.createDate = createDate;
        this.partyDate = partyDate;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.usState = usState;
        this.zip = zip;
        this.inviteList = inviteList;
        this.rsvp = rsvp;
        this.catalogList = catalogList;
        this.stretchName = stretchName;
        this.stretchGoal = stretchGoal;
        this.pos = pos;
        this.byob = byob;
        this.theme = theme;
        this.parking = parking;
    }
    public Integer getId() {

        return id;
    }
    public String getStreet1() {

        return street1;
    }
    public String getStreet2() {

        return street2;
    }
    public String getCity() {

        return city;
    }
    public Integer getZip() {

        return zip;
    }
    public String getPartyType() {

        return partyType;
    }
    public Integer getPartyMod() {

        return partyMod;
    }
    public LocalDateTime getCreateDate() {

        return createDate;
    }
    public LocalDateTime getPartyDate() {

        return partyDate;
    }
    public Integer getPos() {

        return pos;
    }
    public Boolean getByob() {

        return byob;
    }
    public Boolean getTheme() {

        return theme;
    }
    public String getParking() {

        return parking;
    }
    public ArrayList<String> getInviteList() {

        return inviteList;
    }
    public HashMap<Integer, String> getRsvp() {

        return rsvp;
    }
    public ArrayList<Catalog> getCatalogList() {

        return catalogList;
    }
    public String getStretchName() {

        return stretchName;
    }
    public Integer getStretchGoal() {

        return stretchGoal;
    }
    public String getUsState() {

        return usState;
    }
    public String getPartyName() {


        return partyName;
    }
}
