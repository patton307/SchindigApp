package com.schindig.utils;
import com.schindig.entities.Catalog;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Agronis on 12/14/15.
 */
public class PartyParams {

    /**In relation to Parties**/
    /**
     * These are required
     **/
    public Integer hostID;
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
}
