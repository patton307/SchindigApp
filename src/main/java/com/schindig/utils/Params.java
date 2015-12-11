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
    public ArrayList<Catalog> catalogList;
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

    public Integer getPartyId() {

        return partyId;
    }
    public String getPartyName() {

        return partyName;
    }
    public LocalDateTime getPartyDate() {

        return partyDate;
    }
    public String getStreet1() {

        return street1;
    }
    public String getStreet2() {

        return street2;
    }
    public Integer getZip() {

        return zip;
    }
    public String getUsState() {

        return usState;
    }
    public String getCity() {

        return city;
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
    public ArrayList<Catalog> getCatalogList() {

        return catalogList;
    }
    public String getStretchName() {

        return stretchName;
    }
    public Integer getStretchGoal() {

        return stretchGoal;
    }
    public String getInvitePhone() {

        return invitePhone;
    }
    public String getPartyType() {

        return partyType;
    }
    public String getPartyFavor() {

        return partyFavor;
    }
    public Integer getUserId() {

        return userId;
    }
    public String getUsername() {

        return username;
    }
    public String getPhone() {

        return phone;
    }
    public String getEmail() {

        return email;
    }
    public String getFirstName() {

        return firstName;
    }
    public String getLastName() {

        return lastName;
    }
    public String getRsvpStatus() {

        return rsvpStatus;
    }
}
