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
    public Integer partyID;

//    @OneToMany
//    public User host;

//    @Column(nullable = false)
    public String partyName;

    public String partyType;

    public String subType;

//    @Column(nullable = false)
    public LocalDateTime createDate = LocalDateTime.now();

//    @Column(nullable = false)
    public String partyDate;

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

    public Integer position = 1;

    public Boolean byob = false;

    public Boolean theme = false;

    public String parking = " ";

    public Party(){}
    public Party(String partyName, String partyType, String subType, LocalDateTime createDate, String partyDate, String street1, String street2, String city, String usState, Integer zip) {

        this.partyName = partyName;
        this.partyType = partyType;
        this.subType = subType;
        this.createDate = createDate;
        this.partyDate = partyDate;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.usState = usState;
        this.zip = zip;
    }
    public Party(String partyName, String partyType, String subType, LocalDateTime createDate, String partyDate, String street1, String street2, String city, String usState, Integer zip, ArrayList<String> inviteList, HashMap<Integer, String> rsvp, ArrayList<Catalog> catalogList, String stretchName, Integer stretchGoal, Integer position, Boolean byob, Boolean theme, String parking) {

        this.partyName = partyName;
        this.partyType = partyType;
        this.subType = subType;
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
        this.position = position;
        this.byob = byob;
        this.theme = theme;
        this.parking = parking;
    }
    public String getStreet1() {

        return street1;
    }
    public Integer getPartyID() {

        return partyID;
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
    public String getSubType() {

        return subType;
    }
    public LocalDateTime getCreateDate() {

        return createDate;
    }
    public String getPartyDate() {

        return partyDate;
    }
    public Integer getPosition() {

        return position;
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
