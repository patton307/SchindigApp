package com.schindig.entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Agronis on 12/9/15.
 */
@Entity
public class Party {

    @GeneratedValue
    @Id
    Integer id;

//    @OneToMany
//    User user;

    @Column(nullable = false)
    String street1;

    @Column(nullable = false)
    String street2;

    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    String state;

    @Column(nullable = false)
    Integer zip;

    @Column(nullable = false)
    Integer wizID;

    ArrayList<String> inviteList;

    HashMap<Integer, String> rsvp;

    HashMap<Integer, Catalog> catalogList;

    String stretchName;
    Integer stretchGoal;

    Party(){}

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
    public String getState() {

        return state;
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
}
