package com.schindig.controllers;
import com.schindig.entities.Favor;
import com.schindig.entities.Party;
import com.schindig.entities.User;
import com.schindig.entities.Wizard;
import com.schindig.services.FavorRepo;
import com.schindig.services.PartyRepo;
import com.schindig.services.UserRepo;
import com.schindig.services.WizardRepo;
import com.schindig.utils.Methods;
import com.schindig.utils.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.CookieGenerator;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.groups.ConvertGroup;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
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
    FavorRepo favors;

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
                Wizard wiz = new Wizard();
                String[] columns = line.split(",");
                String partyType = columns[0];
                String partyMod = columns[1];
                if (partyMod==null) {
                    partyMod = "empty";
                }
                Wizard check = wizard.findOneByPartyType(partyType);
                if (check==null) {
                    Wizard test = new Wizard();
                    test.partyType = partyType;
                    ArrayList<String> subType = new ArrayList<>();
                    subType.add(partyMod);
                    test.subType = subType;
                    wizard.save(test);
                } else if (check.partyType.equals(partyType)) {
                    check.subType.add(partyMod);
                    wizard.save(check);
                } else {
                    wiz.partyType = partyType;
                    wiz.subType.add(partyMod);
                    wizard.save(wiz);
                }

            }
        }

        Integer catCheck = favors.favorSize();
        if (catCheck==0) {
            String fileContent = Methods.readFile("catalog.csv");

            String[] lines = fileContent.split("\n");

            for (String line : lines) {
                Favor fav = new Favor(line);
                favors.save(fav);
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

    @RequestMapping(path = "/user/create", method = RequestMethod.POST)
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
        ArrayList<User> temp = (ArrayList<User>) users.findAll();
                temp = temp.stream()
                                .map(p -> {
                                p.password = null;
                                return p;
                            })
                                .collect(Collectors.toCollection(ArrayList<User>::new));
        return temp;
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public User findOneUser(@PathVariable("id") int id) {
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

    @RequestMapping(path = "/user/update-password", method = RequestMethod.POST)
    public void updateUserPass(@RequestBody User user) {
        users.save(user);
    }

    @RequestMapping(path = "/user/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
                session.invalidate();
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
    public Party addFavor(@RequestBody Parameters parameters) {
        Party p = parties.findOne(parameters.party.partyID);
        if (!p.favorList.contains(parameters.favor)) {
            parameters.favor.useCount += 1;
            p.favorList.add(parameters.favor);
            parties.save(p);
            return p;
        } else {
            parameters.favor.useCount +=1;
            Integer pos = p.favorList.indexOf(parameters.favor);
            p.favorList.set(pos, parameters.favor);
            parties.save(p);
            return p;
        }
    }

    /**5**/
    @RequestMapping(path = "/party/invite", method = RequestMethod.POST)
    public Party addInvite(@RequestBody Parameters parameters) throws Exception {
        try {
            if (!parameters.party.inviteList.contains(parameters.user.phone)) {
                parameters.party.inviteList.add(parameters.user.phone);
                parties.save(parameters.party);
                parameters.user.inviteCount += 1;
                users.save(parameters.user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("User already invited.");
        }
        return parameters.party;
    }

    /**6**/
    @RequestMapping(path = "/party/rsvp", method = RequestMethod.POST)
    public Party rsvp(@RequestBody Parameters parameters){
        parameters.party.rsvp.put(parameters.user.userID, parameters.rsvpStatus);
        parameters.user.invitedCount += 1;
        if (parameters.rsvpStatus.equals("Yes")) {
            parameters.user.partyCount += 1;
        }
        users.save(parameters.user);
        parties.save(parameters.party);
        return parameters.party;
    }

    /**7**/
    @RequestMapping(path = "/party/{id}", method = RequestMethod.GET)
    public Party getParty(@PathVariable("id") int id) {
        return parties.findOne(id);
    }

    /**8**/
    @RequestMapping(path = "/party/update", method = RequestMethod.PATCH)
    public Party updateParty(@RequestBody Party party) {
        parties.save(party);
        return party;
    }

    /**9**/
    @RequestMapping(path = "/parties", method = RequestMethod.GET)
    public ArrayList<Party> getAllParties(@RequestBody User user){
        user = users.findOne(user.userID);
        ArrayList<Party> partyList = (ArrayList<Party>) parties.findAll();
        final User finalUser = user;
        partyList = partyList.stream()
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
//        updateStats(u);
        parties.delete(party.partyID);
        return (ArrayList<Party>) parties.findAll();
    }

    /**11**/
    @RequestMapping(path = "/party/favor/delete", method = RequestMethod.POST)
    public Party deletePartyFavor(@RequestBody Parameters parameters) {
        parameters.party.favorList.remove(parameters.favor);
        parties.save(parameters.party);
        return parameters.party;
    }

    @RequestMapping(path = "/party/stats", method = RequestMethod.GET)
    public ArrayList<String> partyStats() {
        ArrayList<String> stats = new ArrayList<>();
        return stats;
    }


    /**ALL WIZARD RELATED ROUTES**/
    /**1**/
    @RequestMapping(path = "/wizard", method = RequestMethod.GET)
    public ArrayList<Wizard> getPartyList() {
        System.out.println(System.getProperties());
        return (ArrayList<Wizard>) wizard.findAll();
    }

    @RequestMapping(path = "/wizard/{id}", method = RequestMethod.POST)
    public Party wizardPosition(@RequestBody Party party, @PathVariable("id") int id) {
        party.wizPosition = id + 1;
        parties.save(party);
        return party;
    }

    @RequestMapping(path = "/wizard/pos", method = RequestMethod.GET)
    public Integer getWizardPosition(@RequestBody Party party) {
        return party.wizPosition;
    }



    /**ALL FAVOR SPECIFIC ROUTES**/
    /**2**/
    @RequestMapping(path = "/favor", method = RequestMethod.GET)
    public ArrayList<Favor> getFavorList() {
        return (ArrayList<Favor>) favors.findAll();
    }

    @RequestMapping(path = "/favor/save", method = RequestMethod.POST)
    public String favorItem(@RequestBody Favor item) {
        if (!favors.exists(item.favorID)) {
            Favor c = new Favor();
            c.favorName = item.favorName;
            favors.save(c);
        } else {
            favors.save(item);
            return "Item updated.";
        }
        return "Item added to database";
    }

    @RequestMapping(path = "/favor/remove", method = RequestMethod.POST)
    public ArrayList<Favor> deleteFavorItem(@RequestBody Favor item) {
        favors.delete(item);
        return (ArrayList<Favor>) favors.findAll();
    }


    public void updateUserStats(User user) {
        HashMap<String, String> stats = user.stats;
            if (stats.get("partyCount")==null) {
                stats.put("partyCount", String.valueOf(user.partyCount));
            } else {
                stats.replace("partyCount", String.valueOf(user.partyCount));
            }
            if (stats.get("hostCount") == null) {
                stats.put("hostCount", String.valueOf(user.hostCount));
            } else {
                stats.replace("hostCount", String.valueOf(user.hostCount));
            }
            if (stats.get("inviteCount") == null) {
                stats.put("inviteCount", String.valueOf(user.inviteCount));
            } else {
                stats.replace("inviteCount", String.valueOf(user.inviteCount));
            }
            if (stats.get("invitedCount") == null) {
                stats.put("invitedCount", String.valueOf(user.invitedCount));
            } else {
                stats.replace("invitedCount", String.valueOf(user.invitedCount));
            }

        }
    }
