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

    public Integer userID;

//    @Column(nullable = false)
    public String partyName;

    public String partyType;

    public String description;

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

    public String stretchName;

    public Integer stretchGoal;

    public Integer stretchStatus = 0;

    public Integer wizPosition = 1;

    public Boolean byob = false;

    public Boolean themeCheck = false;

    public String theme;

    public String parking;

    public Party(){}

    public Party(Integer userID, String partyName, String partyType, String description, String subType, LocalDateTime createDate, String partyDate, String street1, String street2, String city, String usState, Integer zip) {

        this.userID = userID;
        this.partyName = partyName;
        this.partyType = partyType;
        this.description = description;
        this.subType = subType;
        this.createDate = createDate;
        this.partyDate = partyDate;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.usState = usState;
        this.zip = zip;
    }
    public Party(Integer userID, String partyName, String partyType, String description, String subType, LocalDateTime createDate, String partyDate, String street1, String street2, String city, String usState, Integer zip, String stretchName, Integer stretchGoal, Integer stretchStatus, Boolean byob, Boolean themeCheck, String theme, String parking) {

        this.userID = userID;
        this.partyName = partyName;
        this.partyType = partyType;
        this.description = description;
        this.subType = subType;
        this.createDate = createDate;
        this.partyDate = partyDate;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.usState = usState;
        this.zip = zip;
        this.stretchName = stretchName;
        this.stretchGoal = stretchGoal;
        this.stretchStatus = stretchStatus;
        this.byob = byob;
        this.themeCheck = themeCheck;
        this.theme = theme;
        this.parking = parking;
    }




    public String getDescription() {

        return description;
    }
    public Integer getPartyID() {

        return partyID;
    }
    public String getPartyName() {

        return partyName;
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
    public String getStreet1() {

        return street1;
    }
    public String getStreet2() {

        return street2;
    }
    public String getCity() {

        return city;
    }
    public String getUsState() {

        return usState;
    }
    public Integer getZip() {

        return zip;
    }

    public String getStretchName() {

        return stretchName;
    }
    public Integer getStretchGoal() {

        return stretchGoal;
    }
    public Integer getStretchStatus() {

        return stretchStatus;
    }
    public Integer getWizPosition() {

        return wizPosition;
    }
    public Boolean getByob() {

        return byob;
    }
    public String getTheme() {

        return theme;
    }
    public String getParking() {

        return parking;
    }
    public Integer getUserID() {

        return userID;
    }
    public Boolean getThemeCheck() {

        return themeCheck;
    }
    public void setPartyID(Integer partyID) {

        this.partyID = partyID;
    }
    public void setUserID(Integer userID) {

        this.userID = userID;
    }
    public void setPartyName(String partyName) {

        this.partyName = partyName;
    }
    public void setPartyType(String partyType) {

        this.partyType = partyType;
    }
    public void setDescription(String description) {

        this.description = description;
    }
    public void setSubType(String subType) {

        this.subType = subType;
    }
    public void setCreateDate(LocalDateTime createDate) {

        this.createDate = createDate;
    }
    public void setPartyDate(String partyDate) {

        this.partyDate = partyDate;
    }
    public void setStreet1(String street1) {

        this.street1 = street1;
    }
    public void setStreet2(String street2) {

        this.street2 = street2;
    }
    public void setCity(String city) {

        this.city = city;
    }
    public void setUsState(String usState) {

        this.usState = usState;
    }
    public void setZip(Integer zip) {

        this.zip = zip;
    }
    public void setStretchName(String stretchName) {

        this.stretchName = stretchName;
    }
    public void setStretchGoal(Integer stretchGoal) {

        this.stretchGoal = stretchGoal;
    }
    public void setStretchStatus(Integer stretchStatus) {

        this.stretchStatus = stretchStatus;
    }
    public void setWizPosition(Integer wizPosition) {

        this.wizPosition = wizPosition;
    }
    public void setByob(Boolean byob) {

        this.byob = byob;
    }
    public void setThemeCheck(Boolean themeCheck) {

        this.themeCheck = themeCheck;
    }
    public void setTheme(String theme) {

        this.theme = theme;
    }
    public void setParking(String parking) {

        this.parking = parking;
    }
}
