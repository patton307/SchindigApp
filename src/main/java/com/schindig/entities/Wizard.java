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
    Integer id;

    @Column(nullable = false)
    String partyType;

    String partyMod;

    public Wizard(String partyType, String partyMod) {

        this.partyType = partyType;
        this.partyMod = partyMod;
    }
}
