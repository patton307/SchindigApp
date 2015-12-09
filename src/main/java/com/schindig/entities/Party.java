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



    
}
