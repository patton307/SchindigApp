package com.schindig.utils;
import com.schindig.entities.Catalog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Agronis on 12/10/15.
 */
public class Params {


    /**In relation to Parties**/
    /**These are required**/
    public Integer partyId;
    public String partyName;
    public LocalDateTime partyDate;
    public String street1;
    public String street2;
    public Integer zip;
    public String usState;
    public String city;
    public Integer wizID;
    /**These can be modified later*/
    public ArrayList<String> inviteList;
    public HashMap<Integer, String> rsvp;
    public HashMap<Integer, Catalog> catalogList;
    public String stretchName;
    public Integer stretchGoal;
    public String invitePhone;

    /**In relation to Wizard items**/
    public String partyType;

    /**In relation to Catalog items**/
    public String partyFavor;

    /**In relation to User items**/
    public Integer userId;
    public String username;
    public String phone;
    public String email;
    public String firstName;
    public String lastName;
    public String rsvpStatus;


    
}
