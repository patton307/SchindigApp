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
import org.springframework.data.annotation.Transient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.*;
import javax.validation.groups.ConvertGroup;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpCookie;
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
                String[] columns = line.split(",");
                String favor = columns[0];
                Boolean genericCheck = Boolean.valueOf(columns[1]);
                Favor fav = new Favor(line);
                fav.favorName = favor;
                fav.generic = genericCheck;
                favors.save(fav);
            }
        }

        User admin = users.findOneByUsername("admin");
        if (admin==null) {
            User newAdmin = new User();
            newAdmin.username = "admin";
            newAdmin.password = "pass";
            newAdmin.firstName = "Admin";
            newAdmin.lastName = "Nimda";
            newAdmin.phone = "1234";
            newAdmin.email = "blah@blah.com";
            users.save(newAdmin);
        }
    }


    /**ALL USER RELATED ROUTES**/
    /**14**/
    @RequestMapping(path = "/user/update", method = RequestMethod.POST)
    public User updateUser(@RequestBody User u){
        User user = users.findOne(u.userID);
        if (u.username != null) {
            user.username = u.username;
        }
        if (u.password != null) {
            user.password = u.password;
        }
        if (u.email != null) {
            user.email = u.email;
        }
        if (u.phone != null) {
            user.phone = u.phone;
        }
        if (u.firstName != null) {
            user.firstName = u.firstName;
        }
        if (u.lastName != null) {
            user.lastName = u.lastName;
        }
        if (u.hostCount != null) {
            user.hostCount = u.hostCount;
        }
        if (u.inviteCount != null) {
            user.inviteCount = u.inviteCount;
        }
        if (u.invitedCount != null) {
            user.invitedCount = u.invitedCount;
        }
        if (u.partyCount != null) {
            user.partyCount = u.partyCount;
        }
        users.save(user);
        user.password = null;
        return user;
    }

    @RequestMapping(path = "/user/create", method = RequestMethod.POST)
    public void createUser(@RequestBody User user, HttpServletResponse response, HttpSession session) throws Exception {
        User u = users.findOneByUsername(user.username);
        if (u  == null) {
            users.save(new User(user));
        }
        //response.addCookie(Methods.bakeCookie(session, user, users));
    }

    @RequestMapping(path = "/user/delete", method = RequestMethod.POST)
    public void deleteUser(@RequestBody User user) {
        users.delete(user);
    }

    @RequestMapping(path = "/user/all", method = RequestMethod.GET)
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
    public void login(@RequestBody User user, HttpServletResponse response, HttpSession session) throws Exception {
        User test = users.findOneByUsername(user.username);
        //response.addCookie(Methods.bakeCookie(session, test, users));
        try {
            if (users.findOneByUsername(user.username) == null) {
                response.sendError(401);
            }
            if (!user.password.equals(test.password)) {
                response.sendError(403);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("username", user.username);
    }

    @RequestMapping(path = "/user/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
                session.invalidate();
    }

    /**ALL PARTY RELATED ROUTES**/
    /**3**/
    @RequestMapping(path = "/party/create", method = RequestMethod.POST)
    public Party createParty(@RequestBody Parameters params, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
//        String sessionID = (String) request.getSession().getId();
//        Cookie[] chip = request.getCookies();
//        String[] currentCookie = chip[0].getValue().split(":");
//        String username = currentCookie[0];
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
//        if (Methods.eatCookie(chip[0], user, users)) {
//            response.sendError(403);
//        }
        Party party = new Party();
        params.party.host = user;
        user.hostCount += 1;
        users.save(user);
        parties.save(party);
        return party;
    }

    /**4**/
    @RequestMapping(path = "/party/favor", method = RequestMethod.POST)
    public Party addFavor(@RequestBody Parameters parameters) {
        ArrayList<String> partyTypes = parties.partyTypes();
        ArrayList<String> subTypes = parties.subTypes();
        Party party = parties.findOne(parameters.party.partyID);
        Favor favor = favors.findOne(parameters.favor.favorID);
        if (!party.favorList.contains(favor)) {
            favor.useCount += 1;
            if (!favor.generic) {
                favor.partyTypeKey = partyTypes.indexOf(party.partyType);
                favor.subTypeKey = subTypes.indexOf(party.subType);
            }
            party.favorList.add(favor);
            parties.save(party);
            favors.save(favor);
            return party;
        } else {
            Integer pos = party.favorList.indexOf(favor);
            party.favorList.set(pos, favor);
            parties.save(party);
            favors.save(favor);
            return party;
        }
    }

    /**5**/
    @RequestMapping(path = "/party/invite", method = RequestMethod.POST)
    public Party addInvite(@RequestBody Parameters parameters) throws Exception {
        Party party = parameters.party;
        User user = parameters.user;
        try {
            if (!party.inviteList.contains(user.phone)) {
                party.inviteList.add(user.phone);
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
    public Party rsvp(@RequestBody Parameters parameters){
        User user = parameters.user;
        Party party = parameters.party;
        party.rsvp.put(user.userID, parameters.rsvpStatus);
        parameters.party.rsvp.put(parameters.user.userID, parameters.rsvpStatus);
        user.invitedCount += 1;
        if (parameters.rsvpStatus.equals("Yes")) {
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
    @RequestMapping(path = "/party/update", method = RequestMethod.PATCH)
    public Party updateParty(@RequestBody Party party, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Party check = parties.findOne(party.partyID);
        check.host = users.findOneByUsername(username);
        if (party.host != null) {
            check.host = users.findOneByUsername(username);
        }

        if (party.partyName != null) {
            check.partyName = party.partyName;
        }
        if (party.partyDate != null) {
            check.partyDate = party.partyDate;
        }
        if (party.partyType != null) {
            check.partyType = party.partyType;
        }
        if (party.subType != null) {
            check.subType = party.subType;
        }
        if (party.street1 != null) {
            check.street1 = party.street1;
        }
        if (party.street2 != null) {
            check.street2 = party.street2;
        }
        if (party.zip != null) {
            check.zip = party.zip;
        }
        if (party.usState != null) {
            check.usState = party.usState;
        }
        if (party.city != null) {
            check.city = party.city;
        }
        if (party.inviteList != null) {
            check.inviteList = party.inviteList;
        }
        if (party.favorList != null) {
            check.favorList = new ArrayList<>();
            check.favorList.addAll(party.favorList.stream().collect(Collectors.toList()));
        }
        if (party.rsvp != null) {
            check.rsvp = party.rsvp;
        }
        if (party.stretchGoal != null) {
            check.stretchGoal = party.stretchGoal;
        }
        if (party.stretchName != null) {
            check.stretchName = party.stretchName;
        }
        parties.save(check);
        Party p  = parties.findOne(1);
        System.out.println(p.favorList.size());
        return check;
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
    public Party deletePartyFavor(@RequestBody Parameters parameters, HttpServletResponse response) throws IOException {
        Party party = parties.findOne(parameters.party.partyID);
        Favor favor = favors.findOne(parameters.favor.favorID);
        Integer pos = party.favorList.indexOf(favor);
        if (party.favorList.get(pos) == null) {
            response.sendError(0, "Favor not found");
        }
        party.favorList.remove(favor);
        parties.save(party);
        return party;
    }

    @RequestMapping(path = "/party/favor/add", method = RequestMethod.POST)
    public void addPartyFavor(@RequestBody Parameters params) {
        Favor f = new Favor();
        Party p = parties.findOne(params.partyID);
        ArrayList<String> partyTypes = parties.partyTypes();
        ArrayList<String> subTypes = parties.subTypes();
        if (!f.generic) {
            f.partyTypeKey = partyTypes.indexOf(p.partyType);
            f.subTypeKey = subTypes.indexOf(p.subType);
        }
        if (f.favorName == null) {
            f.favorName = params.favorName;
        }
        f.useCount += 1;
        favors.save(f);

    }

    @RequestMapping(path = "/party/stats", method = RequestMethod.GET)
    public ArrayList<String> partyStats() {

        return new ArrayList<>();
    }


    /**ALL WIZARD RELATED ROUTES**/
    /**1**/
    @RequestMapping(path = "/wizard", method = RequestMethod.GET)
    public ArrayList<Wizard> getPartyList() {
        return (ArrayList<Wizard>) wizard.findAll();
    }

    @RequestMapping(path = "/wizard/{id}", method = RequestMethod.POST)
    public Party wizardPosition(@RequestBody Party p, @PathVariable("id") int id) {
        Party party = parties.findOne(p.partyID);
        party.wizPosition = id + 1;
        parties.save(party);
        return party;
    }

    @RequestMapping(path = "/wizard/pos", method = RequestMethod.GET)
    public Integer getWizardPosition(@RequestBody Party party) {
        return parties.findOne(party.partyID).wizPosition;
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
