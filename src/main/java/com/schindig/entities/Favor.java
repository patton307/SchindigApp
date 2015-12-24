package com.schindig.entities;
import javax.persistence.*;

/**
 * Created by Agronis on 12/9/15.
 */
@Entity
public class Favor {

    @GeneratedValue
    @Id
    public Integer favorID;

//    @Column(nullable = false)
    public String favorName;

    public Boolean generic = false;

    public Integer useCount = 0;

    public String partyType;

    public String subType;


    public Favor(){}
    public Favor(Integer favorID, String favorName, Integer useCount) {

        this.favorID = favorID;
        this.favorName = favorName;
        this.useCount = useCount;
    }

    public Favor(String partyType, String subType, Integer useCount, Boolean generic, String favorName) {

        this.subType = subType;
        this.partyType = partyType;
        this.useCount = useCount;
        this.generic = generic;
        this.favorName = favorName;
    }
    public Boolean getGeneric() {

        return generic;
    }
    public String getPartyType() {

        return partyType;
    }
    public String getSubType() {

        return subType;
    }
    public Favor(String favorName) {

        this.favorName = favorName;
    }
    public String getFavorName() {

        return favorName;
    }
    public Integer getUseCount() {

        return useCount;
    }
    public Integer getFavorID() {

        return favorID;
    }
    public void setFavorID(Integer favorID) {

        this.favorID = favorID;
    }
    public void setFavorName(String favorName) {

        this.favorName = favorName;
    }
    public void setGeneric(Boolean generic) {

        this.generic = generic;
    }
    public void setUseCount(Integer useCount) {

        this.useCount = useCount;
    }
    public void setPartyType(String partyType) {

        this.partyType = partyType;
    }
    public void setSubType(String subType) {

        this.subType = subType;
    }
    // Database Connection

    /*
    @ManyToOne Party partyFavor;
    */
}
