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
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
    @RequestMapping(path = "/wizard", method = RequestMethod.GET)
    public ArrayList<Wizard> getPartyList() {
        return (ArrayList<Wizard>) wizard.findAll();
    }

    /**2**/
    @RequestMapping(path = "/catalog", method = RequestMethod.GET)
    public ArrayList<Catalog> getCatalogList() {
        return (ArrayList<Catalog>) catalog.findAll();
    }

    @RequestMapping(path = "/parties", method = RequestMethod.GET)
    public ArrayList<Party> getAllParties(@RequestBody User user){
        user = users.findOne(user.id);
        ArrayList<Party> partyList = (ArrayList<Party>) parties.findAll();
        final User finalUser = user;
        partyList.stream()
                .filter(party -> {
                    return (party.inviteList.contains(finalUser.phone));
                })
                .collect(Collectors.toCollection(ArrayList<Party>::new));
        return partyList;
    }

    /**5**/
    @RequestMapping(path = "/party/create", method = RequestMethod.POST)
    public Party createParty( @RequestBody Party party ){
        /**User u = party.host;
        u.hostCount += 1;
        users.save(u);*/
        parties.save(party);
        return party;
    }

    /**6**/
    @RequestMapping(path = "/party/favor", method = RequestMethod.POST)
    public Party addFavor( @RequestBody Party party, @RequestBody Catalog item ){
        Party p = parties.findOne(party.id);
        item = new Catalog(item.favorName);
        item.useCount += 1;
        catalog.save(item);
        p.catalogList.add(item);
        parties.save(p);
        return p;

    }

    /**7**/
    @RequestMapping(path = "/party/invite", method = RequestMethod.POST)
    public Party addInvite( @RequestBody Party party, @RequestBody User user, @RequestBody String invitePhone ){
        party.inviteList.add(invitePhone);
        parties.save(party);
        user.inviteCount += 1;
        users.save(user);
        return party;
    }

    /**8**/
    @RequestMapping(path = "/party/rsvp", method = RequestMethod.POST)
    public Party rsvp( @RequestBody Party party, @RequestBody User user, @RequestBody String rsvpStatus){
        party.rsvp.put(user.id, rsvpStatus);
        parties.save(party);
        return party;
    }

    /**9**/
    @RequestMapping(path = "/user/update", method = RequestMethod.POST)
    public User updateUser( @RequestBody User user ){
        users.save(user);
        user.password = null;
        return user;
    }

    @RequestMapping(path = "/party/{id}", method = RequestMethod.GET)
    public Party getParty(@PathVariable("id") int id) {
        return parties.findOne(id);
    }

    @RequestMapping(path = "/party/update", method = RequestMethod.POST)
    public Party updateParty(@RequestBody Party party) {
        parties.save(party);
        return party;
    }

    /** Can't use this until all user methods are in.
//    @RequestMapping(path = "/party/delete", method = RequestMethod.POST)
//    public ArrayList<Party> deleteParty(@RequestBody Party party) {
//        User u = party.host;
//        u.hostCount -= 1;
//        u.inviteCount -= party.inviteList.size();
//        parties.delete(party.id);
//        users.save(u);
//        return (ArrayList<Party>) parties.findAll();
//    }
     */



}
