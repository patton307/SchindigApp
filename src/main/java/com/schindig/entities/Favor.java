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

    public Integer partyTypeKey = 0;

    public Integer subTypeKey = 0;

    public Favor(){}
    public Favor(Integer favorID, String favorName, Integer useCount) {

        this.favorID = favorID;
        this.favorName = favorName;
        this.useCount = useCount;
    }

    public Favor(Integer subTypeKey, Integer partyTypeKey, Integer useCount, Boolean generic, String favorName) {

        this.subTypeKey = subTypeKey;
        this.partyTypeKey = partyTypeKey;
        this.useCount = useCount;
        this.generic = generic;
        this.favorName = favorName;
    }
    public Boolean getGeneric() {

        return generic;
    }
    public Integer getPartyTypeKey() {

        return partyTypeKey;
    }
    public Integer getSubTypeKey() {

        return subTypeKey;
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
}
