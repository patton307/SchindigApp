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

    public Integer wizPosition = 1;

    public Boolean byob = false;

    public Boolean theme = false;

    public String[] parking = new String[] {"Valet", "On-Site", "Off-Site"};

    public Party(){}




}
