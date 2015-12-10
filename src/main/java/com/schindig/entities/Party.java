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

    @Column(nullable = false)
    public String partyName;

    @Column(nullable = false)
    public LocalDateTime createDate;

    @Column(nullable = false)
    public LocalDateTime partyDate;

    @Column(nullable = false)
    public String street1;

    public String street2;

    @Column(nullable = false)
    public String city;

    @Column(nullable = false)
    public String usState;

    @Column(nullable = false)
    public Integer zip;

    @Column(nullable = false)
    public Integer wizID;

    public ArrayList<String> inviteList;

    public HashMap<Integer, String> rsvp;

    public HashMap<Integer, Catalog> catalogList;

    public String stretchName;

    public Integer stretchGoal;

    public Party(){}
    public Party(String partyName, LocalDateTime partyDate, String street1, String street2, String city, String usState, Integer zip, Integer wizID, ArrayList<String> inviteList, HashMap<Integer, String> rsvp, HashMap<Integer, Catalog> catalogList, String stretchName, Integer stretchGoal) {

        this.partyName = partyName;
        this.partyDate = partyDate;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.usState = usState;
        this.zip = zip;
        this.wizID = wizID;
        this.inviteList = inviteList;
        this.rsvp = rsvp;
        this.catalogList = catalogList;
        this.stretchName = stretchName;
        this.stretchGoal = stretchGoal;
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
    public Integer getWizID() {

        return wizID;
    }
    public ArrayList<String> getInviteList() {

        return inviteList;
    }
    public HashMap<Integer, String> getRsvp() {

        return rsvp;
    }
    public HashMap<Integer, Catalog> getCatalogList() {

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
