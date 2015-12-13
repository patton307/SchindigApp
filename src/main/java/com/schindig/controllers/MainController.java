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
import javax.servlet.http.HttpServletResponse;
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

    /**ALL USER RELATED ROUTES**/
    /**14**/
    @RequestMapping(path = "/user/update", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user){
        users.save(user);
        user.password = null;
        return user;
    }

    @RequestMapping(path = "/user/create-user", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) throws Exception {
        if (user.username == null) {
            users.save(user);
        } else {
            throw new Exception("Username already exists.  Please enter a different username.");
        }
    }

    @RequestMapping(path = "/user/delete", method = RequestMethod.POST)
    public void deleteUser(@RequestBody User user) {
        users.delete(user);
    }

    @RequestMapping(path = "/user/show-all", method = RequestMethod.GET)
    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) users.findAll();
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public User findOneUser(@RequestBody int id) {
        return users.findOne(id);
    }

    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    public void login(@RequestBody User user) throws Exception {
            if (users.findOneByUsername(user.username) == null) {
            throw new Exception("Username does not exist.");
        } else if (user.password.equals(users.findOneByUsername(user.username).password)) {
            throw new Exception("Password is not correct.");
        }
    }























    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    public void login(@RequestBody User user) throws Exception {
        if (users.findOneByUsername(user.username) == null) {
            throw new Exception("Username does not exist.");
        } else if (user.password.equals(users.findOneByUsername(user.username).password)) {
            throw new Exception("Password is not correct.");
        }
    }



    /**ALL PARTY RELATED ROUTES**/
    /**3**/
    @RequestMapping(path = "/party/create", method = RequestMethod.POST)
    public Party createParty( @RequestBody Party party ){
        /**User u = party.host;
         u.hostCount += 1;
         users.save(u);*/
        parties.save(party);
        return party;
    }

    /**4**/
    @RequestMapping(path = "/party/favor", method = RequestMethod.POST)
    public Party addFavor( @RequestBody Party party, @RequestBody Catalog item ){
        Party p = parties.findOne(party.id);
        if(!p.catalogList.contains(item)) {
            item.useCount += 1;
            p.catalogList.add(item);
            parties.save(p);
            return p;
        } else {
            item.useCount += 1;
            Integer pos = p.catalogList.indexOf(item);
            p.catalogList.set(pos, item);
            parties.save(p);
            return p;
        }
    }

    /**5**/
    @RequestMapping(path = "/party/invite", method = RequestMethod.POST)
    public Party addInvite(@RequestBody Party party, @RequestBody User user, @RequestBody String invitePhone ) throws Exception {
        try {
            if (!party.inviteList.contains(invitePhone)) {
                party.inviteList.add(invitePhone);
                parties.save(party);
                user.inviteCount += 1;
                users.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("User already invited.");
        }
        return party;
    }

    /**6**/
    @RequestMapping(path = "/party/rsvp", method = RequestMethod.POST)
    public Party rsvp( @RequestBody Party party, @RequestBody User user, @RequestBody String rsvpStatus){
        party.rsvp.put(user.id, rsvpStatus);
        user.invitedCount += 1;
        if (rsvpStatus.equals("Yes")) {
            user.partyCount += 1;
        }
        users.save(user);
        parties.save(party);
        return party;
    }

    /**7**/
    @RequestMapping(path = "/party/{id}", method = RequestMethod.GET)
    public Party getParty(@PathVariable("id") int id) {
        return parties.findOne(id);
    }

    /**8**/
    @RequestMapping(path = "/party/update", method = RequestMethod.POST)
    public Party updateParty(@RequestBody Party party) {
        parties.save(party);
        return party;
    }

    /**9**/
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

    /**10**/
    @RequestMapping(path = "/party/delete", method = RequestMethod.POST)
    public ArrayList<Party> deleteParty(@RequestBody Party party) {
//        User u = party.host;
//        u.hostCount -= 1;
//        u.inviteCount -= party.inviteList.size();
//        users.save(u);
        parties.delete(party.id);
        return (ArrayList<Party>) parties.findAll();
    }

    /**11**/
    @RequestMapping(path = "/party/favor/delete", method = RequestMethod.POST)
    public Party deletePartyFavor(@RequestBody Party party, @RequestBody Catalog catalog) {
        int favor = party.catalogList.indexOf(catalog);
        party.catalogList.remove(favor);
        parties.save(party);
        return party;
    }



    /**ALL WIZARD RELATED ROUTES**/
    /**1**/
    @RequestMapping(path = "/wizard", method = RequestMethod.GET)
    public ArrayList<Wizard> getPartyList() {
        return (ArrayList<Wizard>) wizard.findAll();
    }

    @RequestMapping(path = "/wizard/{id}", method = RequestMethod.POST)
    public Party wizardPosition(@RequestBody Party party, @PathVariable("id") int id) {
        party.pos += id + 1;
        parties.save(party);
        return party;
    }

    @RequestMapping(path = "/wizard/pos", method = RequestMethod.GET)
    public Integer getWizardPosition(@RequestBody Party party) {
        Integer pos = party.pos;
        return pos;
    }



    /**ALL CATALOG SPECIFIC ROUTES**/
    /**2**/
    @RequestMapping(path = "/catalog", method = RequestMethod.GET)
    public ArrayList<Catalog> getCatalogList() {
        return (ArrayList<Catalog>) catalog.findAll();
    }

    @RequestMapping(path = "/catalog/save", method = RequestMethod.POST)
    public String catalogItem(@RequestBody Catalog item) {
        if (!catalog.exists(item.id)) {
            Catalog c = new Catalog();
            c.favorName = item.favorName;
            catalog.save(c);
        } else {
            catalog.save(item);
            return "Item updated.";
        }
        return "Item added to database";
    }

    @RequestMapping(path = "/catalog/remove", method = RequestMethod.POST)
    public ArrayList<Catalog> deleteCatalogItem(@RequestBody Catalog item) {
        catalog.delete(item);
        return (ArrayList<Catalog>) catalog.findAll();
    }

}
