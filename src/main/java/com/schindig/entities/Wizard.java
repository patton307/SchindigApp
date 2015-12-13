package com.schindig.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Agronis on 12/9/15.
 */
@Entity
public class Wizard {

    @GeneratedValue
    @Id
    public Integer id;

//    @Column(nullable = false)
    public String partyType;

    public String partyMod = " ";

    public Wizard(){}

    public Wizard(String partyType, String partyMod) {

        this.partyType = partyType;
        this.partyMod = partyMod;
    }

    public Integer getId() {

        return id;
    }
    public String getPartyType() {

        return partyType;
    }
    public String getPartyMod() {

        return partyMod;
    }
}
