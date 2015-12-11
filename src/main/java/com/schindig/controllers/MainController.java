package com.schindig.controllers;
import com.schindig.entities.Catalog;
import com.schindig.entities.Party;
import com.schindig.entities.User;
import com.schindig.entities.Wizard;
import com.schindig.services.CatalogRepo;
import com.schindig.services.PartyRepo;
import com.schindig.services.UserRepo;
import com.schindig.services.WizardRepo;
import com.schindig.utils.Methods;
import com.schindig.utils.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agronis on 12/9/15.
 */
@CrossOrigin
@RestController
public class MainController {

    @Autowired
    WizardRepo wizard;

    @Autowired
    CatalogRepo catalog;

    @Autowired
    PartyRepo parties;

    @Autowired
    UserRepo users;

    @PostConstruct
    public void init() {

        Integer wizCheck = wizard.wizardSize();
        if (wizCheck==0) {
            String fileContent = Methods.readFile("wizard.csv");

            String[] lines = fileContent.split("\n");

            for (String line : lines) {
                String[] columns = line.split(",");
                Wizard wiz= new Wizard(columns[0], columns[1]);
                wizard.save(wiz);
            }
        }

        Integer catCheck = catalog.catalogSize();
        if (catCheck==0) {
            String fileContent = Methods.readFile("catalog.csv");

            String[] lines = fileContent.split("\n");

            for (String line : lines) {
                Catalog cat = new Catalog(line);
                catalog.save(cat);
            }
        }



    }

    /**1**/
    @RequestMapping("/get-wizard")
    public ArrayList<Wizard> getPartyList() {
        return (ArrayList<Wizard>) wizard.findAll();
    }

    /**2**/
    @RequestMapping("/get-catalog")
    public ArrayList<Catalog> getCatalogList() {
        return (ArrayList<Catalog>) catalog.findAll();
    }

//    /**5**/
//    @RequestMapping(path = "/create-party", method = RequestMethod.POST)
//    public void createParty( @RequestBody Params params ){
//
//        Party p = new Party(
//                params.partyName, params.partyDate, params.street1, params.street2, params.city,
//                params.usState, params.zip, params.wizID, params.inviteList,
//                params.catalogList, params.stretchName, params.stretchGoal);
//
//        parties.save(p);
//
//    }
//
//    /**6**/
//    @RequestMapping(path = "/add-favor", method = RequestMethod.POST)
//    public void addFavor( @RequestBody Params params ){
//        Catalog c = new Catalog(params.partyFavor);
//        catalog.save(c);
//
//    }
//
//    /**7**/
//    @RequestMapping(path = "/add-invite", method = RequestMethod.POST)
//    public void addInvite( @RequestBody Params params ){
//        Party p = parties.findOne(params.partyId);
//        p.inviteList.add(params.invitePhone);
//        parties.save(p);
//    }
//
//    /**8**/
//    @RequestMapping(path = "/rsvp", method = RequestMethod.POST)
//    public void rsvp( @RequestBody Params params ){
//        Party p = parties.findOne(params.partyId);
//        User u = users.findOne(params.userId);
//        p.rsvp.put(u.id, params.rsvpStatus);
//    }
//
//    /**9**/
//    @RequestMapping(path = "/update-user", method = RequestMethod.POST)
//    public void updateUser( @RequestBody User user ){
//        users.save(user);
//    }


}
