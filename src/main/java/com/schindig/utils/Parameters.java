package com.schindig.utils;
import com.schindig.entities.Catalog;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Agronis on 12/10/15.
 */
public class Parameters {


    /**In relation to Parties**/
    /**
     * These are required
     **/
    public Integer partyID;
    public String partyType;
    public String subType;
    public String partyName;
    public String partyDate;
    public String street1;
    public String street2;
    public Integer zip;
    public String usState;
    public String city;
    /**
     * These can be modified later
     */
    public ArrayList<String> inviteList;
    public HashMap<Integer, String> rsvp;
    public ArrayList<Catalog> catalogList;
    public String stretchName;
    public Integer stretchGoal;
    public String invitePhone;
    public Boolean byob;
    public Boolean theme;
    public String[] parking;
    public Integer wizPosition;



    /**
     * In relation to Wizard items
     **/


    /**
     * In relation to Catalog items
     **/
    public String partyFavor;
    public String favorName;

    /**
     * In relation to User items
     **/
    public Integer userId;
    public String username;
    public String phone;
    public String email;
    public String firstName;
    public String lastName;
    public String rsvpStatus;


    public Integer getPartyID() {

        return partyID;
    }
    public String getPartyType() {

        return partyType;
    }
    public String getSubType() {

        return subType;
    }
    public String getPartyName() {

        return partyName;
    }
    public String getPartyDate() {

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
    public Boolean getByob() {

        return byob;
    }
    public Boolean getTheme() {

        return theme;
    }
    public String[] getParking() {

        return parking;
    }
    public Integer getWizPosition() {

        return wizPosition;
    }
    public String getPartyFavor() {

        return partyFavor;
    }
    public String getFavorName() {

        return favorName;
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