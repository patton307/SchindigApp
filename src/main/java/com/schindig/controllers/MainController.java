package com.schindig.controllers;
import com.schindig.entities.*;
import com.schindig.services.*;
import com.schindig.utils.Methods;
import com.schindig.utils.Parameters;
import com.schindig.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    FavorListRepo favlists;

    @Autowired
    InviteRepo invites;

    @PostConstruct
    public void init() {

        Integer wizCheck = wizard.wizardSize();
        if (wizCheck == 0) {
            String fileContent = Methods.readFile("wizard.csv");

            String[] lines = fileContent.split("\n");

            for (String line : lines) {
                Wizard wiz = new Wizard();
                String[] columns = line.split(",");
                String partyType = columns[0];
                String partyMod = columns[1];

                if (partyMod == null) {
                    partyMod = "empty";
                }
                Wizard check = wizard.findOneByPartyType(partyType);
                if (check == null) {
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
        if (catCheck == 0) {
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

        ArrayList<User> testUsers = (ArrayList<User>) users.findAll();
        if (testUsers.size() < 5) {
            User max = new User();
            max.username = "maxwellsmart84";
            max.password = "max";
            max.firstName = "Max";
            max.lastName = "Krause";
            max.phone = "7733205069";
            max.email = "maxwellsmart84@gmail.com";
            users.save(max);
            User landon = new User();
            landon.username = "landonk";
            landon.password = "landon";
            landon.firstName = "Landon";
            landon.lastName = "Kail";
            landon.phone = "7316161838";
            landon.email = "patton307@gmail.com";
            users.save(landon);
            User blake = new User();
            blake.username = "blake182";
            blake.password = "blake";
            blake.firstName = "Blake";
            blake.lastName = "Guilloud";
            blake.phone = "8438126962";
            blake.email = "fbguillo@gmail.com";
            users.save(blake);
            User josh = new User();
            josh.username = "Agronis";
            josh.password = "josh";
            josh.firstName = "Josh";
            josh.lastName = "Roberson";
            josh.phone = "8439019708";
            josh.email = "agronis@icould.com";
            users.save(josh);
            User holden = new User();
            holden.username = "holden1214";
            holden.password = "holden";
            holden.firstName = "Holden";
            holden.lastName = "Hughes";
            holden.phone = "8434253940";
            holden.email = "holdenmh121489@gmail.com";
            users.save(holden);
        }

        User admin = users.findOneByUsername("admin");
        if (admin == null) {
            User newAdmin = new User();
            newAdmin.username = "admin";
            newAdmin.password = "pass";
            newAdmin.firstName = "Admin";
            newAdmin.lastName = "Nimda";
            newAdmin.phone = "1234";
            newAdmin.email = "blah@blah.com";
            users.save(newAdmin);
        }
        User Admin = users.findOneByUsername("Admin");
        if (Admin == null) {
            User newAdmin = new User();
            newAdmin.username = "Admin";
            newAdmin.password = "Pass";
            newAdmin.firstName = "Admin";
            newAdmin.lastName = "Nimda";
            newAdmin.phone = "1234";
            newAdmin.email = "blah@blah.com";
            users.save(newAdmin);
        }

        Party party = parties.findOne(1);
        if (party == null) {
            Party p = new Party();
            p.userID = users.findOne(1).userID;
            p.partyName = "Party Name";
            p.partyDate = "party Date";
            p.street1 = "Street One";
            p.street2 = "Street Two";
            p.city = "City";
            p.zip = 12345;
            parties.save(p);
        }
        Party party2 = parties.findOne(2);
        if (party == null) {
            Party p = new Party();
            p.userID = users.findOne(1).userID;
            p.partyName = "Party Name";
            p.partyDate = "party Date";
            p.street1 = "Street One";
            p.street2 = "Street Two";
            p.city = "City";
            p.zip = 12345;
            parties.save(p);
        }

        Invite invite = invites.findOne(1);
        if (invite == null) {
            Invite i = new Invite();
            i.party = parties.findOne(1);
            i.user = users.findOne(1);
            i.phone = "238504333";
            i.email = "aksldjf@alsdkfj.com";
            invites.save(i);
        }
        Invite invite2 = invites.findOne(2);
        if (invite == null) {
            Invite i = new Invite();
            i.party = parties.findOne(1);
            i.user = users.findOne(1);
            i.phone = "238504333";
            i.email = "aksldjf@alsdkfj.com";
            invites.save(i);
        }

        FavorList favlist = favlists.findOne(1);
        if (favlist == null) {
            FavorList f = new FavorList();
            f.favor = favors.findOne(1);
            f.party = parties.findOne(1);
            favlists.save(f);
        }
    }


    /**ALL USER RELATED ROUTES**/
    @RequestMapping(path = "/user/update", method = RequestMethod.POST)
    public User updateUser(@RequestBody User u) {

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
        if (u == null) {
            User newUser = user;
            newUser.password = PasswordHash.createHash(user.password);
            users.save(newUser);
        }
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
        User u = users.findOne(id);
        u.password = null;
        return u;
    }

    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    public Integer login(@RequestBody User user, HttpServletResponse response, HttpSession session) throws Exception {
        User test = users.findOneByUsername(user.username);
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
        return test.userID;
    }

    @RequestMapping(path = "/user/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
        session.invalidate();
    }

    /**ALL PARTY RELATED ROUTES**/

    @RequestMapping(path = "/party/create", method = RequestMethod.POST)
    public Party createParty(@RequestBody Parameters params, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        User user = users.findOne(params.userID);
        Party p = params.party;
        p.userID = user.userID;
        user.hostCount += 1;
        users.save(user);
        parties.save(p);
        return p;
    }

    @RequestMapping(path = "/party/favor", method = RequestMethod.POST)
    public ArrayList<Favor> addPartyFavor(@RequestBody Parameters parameters) {
        ArrayList<Favor> newDump = new ArrayList<>();
        for (int i = 0; i < parameters.favorDump.size(); i++) {
            Favor fav = favors.findOne(parameters.favorDump.get(i).favorID);
            Party party = parties.findOne(parameters.partyID);
            FavorList favorList = new FavorList(fav, party);
            favlists.save(favorList);
            Favor favor = favors.findOne(fav.favorID);
            newDump.add(favor);
        }
        return newDump;
    }

    @RequestMapping(path = "/party/{id}/favors", method = RequestMethod.GET)
    public ArrayList<Favor> getFavors(@PathVariable("id") int id) {
        ArrayList<FavorList> favorList = (ArrayList<FavorList>) favlists.findAll();
        favorList = favorList.stream()
                .filter(f -> f.party.partyID == id)
                .collect(Collectors.toCollection(ArrayList<FavorList>::new));
        return favorList.stream()
                .map(favor -> favor.favor)
                .collect(Collectors.toCollection(ArrayList::new));

    }

    @RequestMapping(path = "/party/invite", method = RequestMethod.POST)
    public void addInvite(@RequestBody Parameters parameters) throws Exception {
        Party party = parties.findOne(parameters.party.partyID);
        User user = users.findOne(parameters.user.userID);
        Invite invite = new Invite(
                user, party, parameters.invites.phone, parameters.invites.email, "Undecided", parameters.invites.name
        );
        invites.save(invite);
    }

    @RequestMapping(path = "/party/rsvp", method = RequestMethod.POST)
    public void rsvp(@RequestBody Parameters parameters) {

        User user = parameters.user;
        user.invitedCount += 1;
        switch (parameters.rsvpStatus) {
            case "Yes": {
                user.partyCount += 1;
                Invite i = invites.findByUser(user.userID);
                i.rsvpStatus = "Yes";
                invites.save(i);
                break;
            }
            case "Maybe": {
                Invite i = invites.findByUser(user.userID);
                i.rsvpStatus = "Maybe";
                invites.save(i);
                break;
            }
            case "No": {
                Invite i = invites.findByUser(user.userID);
                i.rsvpStatus = "Yes";
                invites.save(i);
                break;
            }
        }
        users.save(user);
    }

    @RequestMapping(path = "/party/{id}", method = RequestMethod.GET)
    public Party getParty(@PathVariable("id") int id) {
        return parties.findOne(id);
    }

    @RequestMapping(path = "/party/update", method = RequestMethod.PATCH)
    public Party updateParty(@RequestBody Parameters parameters, HttpSession session) {
        Object current = session.getAttribute("User");
        session.setAttribute("User", current);
        Party check = parties.findOne(parameters.party.partyID);
        if (parameters.party.partyName != null) {
            check.partyName = parameters.party.partyName;
        }
        if (parameters.party.partyDate != null) {
            check.partyDate = parameters.party.partyDate;
        }
        if (parameters.party.partyType != null) {
            check.partyType = parameters.party.partyType;
        }
        if (parameters.party.subType != null) {
            check.subType = parameters.party.subType;
        }
        if (parameters.party.street1 != null) {
            check.street1 = parameters.party.street1;
        }
        if (parameters.party.street2 != null) {
            check.street2 = parameters.party.street2;
        }
        if (parameters.party.zip != null) {
            check.zip = parameters.party.zip;
        }
        if (parameters.party.usState != null) {
            check.usState = parameters.party.usState;
        }
        if (parameters.party.city != null) {
            check.city = parameters.party.city;
        }
        if (parameters.party.stretchGoal != null) {
            check.stretchGoal = parameters.party.stretchGoal;
        }
        if (parameters.party.stretchName != null) {
            check.stretchName = parameters.party.stretchName;
        }
        if (parameters.party.themeCheck) {
            check.themeCheck = true;
            check.theme = parameters.party.theme;
        }
        if (parameters.party.byob) {
            check.byob = true;
        }
        if (parameters.party.parking != null) {
            check.parking = parameters.party.parking;
        }
        if (parameters.inviteDump != null) {
            User user = users.findOne(check.userID);
            for (int i = 0; i < parameters.inviteDump.size(); i++) {
                Invite invite = parameters.inviteDump.get(i);
                Methods.newInvite(invite, invites, check);
                user.invitedCount += 1;
            }
        }
        parties.save(check);
        return check;
    }

    @RequestMapping(path = "/parties/host", method = RequestMethod.POST)
    public ArrayList<Party> getAllHosted(@RequestBody User user) {
        User u = users.findOne(user.userID);
        ArrayList<Party> partyList = (ArrayList<Party>) parties.findAll();
        partyList = (ArrayList<Party>) partyList.stream()
                .filter(p -> p.userID == u.userID)
                .collect(Collectors.toCollection(ArrayList<Party>::new));
        return partyList;
    }

    @RequestMapping(path = "/parties/user", method = RequestMethod.POST)
    public ArrayList<Party> getAllParties(@RequestBody User user) {
        User u = users.findOne(user.userID);
        ArrayList<Invite> inviteList = (ArrayList<Invite>) invites.findAll();
        ArrayList<Party> partyList = new ArrayList();
        for (Invite invite : inviteList) {
            String[] nameSplit = invite.name.split(" ");
            if (invite.user == u) {
                partyList.add(invite.party);
            } else if (u.firstName.equals(nameSplit[0])) {
                partyList.add(invite.party);
            } else if (u.lastName.equals(nameSplit[1])) {
                partyList.add(invite.party);
            } else if (u.email.equals(invite.email)) {
                partyList.add(invite.party);
            } else if (u.phone.equals(invite.phone)) {
                partyList.add(invite.party);
            }
        }
        return partyList;
    }

    @RequestMapping(path = "/party/delete", method = RequestMethod.POST)
    public List<Party> deleteParty(@RequestBody Party party, HttpServletResponse response) throws IOException {
        User u = users.findOne(party.userID);
        Party p = parties.findOne(party.partyID);
        if (p.userID != u.userID) {
            response.sendError(403);
        }
        u.hostCount -= 1;
        users.save(u);
        parties.delete(party.partyID);
        return invites.findInvite(u);
    }

    @RequestMapping(path = "/party/favor/delete", method = RequestMethod.POST)
    public void deletePartyFavor(@RequestBody Parameters parameters, HttpServletResponse response) throws IOException {
        parameters.favorListDump.forEach(favlists::delete);
    }

    /**ALL WIZARD RELATED ROUTES**/

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

    @RequestMapping(path = "/favor", method = RequestMethod.GET)
    public ArrayList<Favor> getFavorList() {
        return (ArrayList<Favor>) favors.findAll();
    }

    @RequestMapping(path = "/favor/save", method = RequestMethod.POST)
    public String addFavorItem(@RequestBody Favor favor) {
        if (!favors.exists(favor.favorID)) {
            Favor c = new Favor();
            c.favorName = favor.favorName;
            favors.save(c);
        } else {
            favors.save(favor);
            return "Item updated.";
        }
        return "Item added to database";
    }

    @RequestMapping(path = "/favor/remove", method = RequestMethod.POST)
    public ArrayList<Favor> deleteFavorItem(@RequestBody Favor item) {
        favors.delete(item);
        return (ArrayList<Favor>) favors.findAll();
    }
}

//    @RequestMapping(path = "/party/stats", method = RequestMethod.GET)
//    public ArrayList<String> partyStats() {
//
//        return new ArrayList<>();
//    }

/*
    @RequestMapping(path = "/user/search", method = RequestMethod.GET)
    public ArrayList<User> userSearch(@RequestBody User user) {
        ArrayList<User> allresults = (ArrayList<User>) users.findAll();
        ArrayList<User> results = allresults.stream()
                .filter(u -> u.username.equalsIgnoreCase(user.username) ||
                u.firstName.equalsIgnoreCase(user.firstName) ||
                u.lastName.equalsIgnoreCase(user.lastName) ||
                u.email.equalsIgnoreCase(user.email))
                .collect(Collectors.toCollection(ArrayList<User>::new));
        return results;
    }
    */
//    public void updateUserStats(User user) {
//        HashMap<String, String> stats = user.stats;
//            if (stats.get("partyCount")==null) {
//                stats.put("partyCount", String.valueOf(user.partyCount));
//            } else {
//                stats.replace("partyCount", String.valueOf(user.partyCount));
//            }
//            if (stats.get("hostCount") == null) {
//                stats.put("hostCount", String.valueOf(user.hostCount));
//            } else {
//                stats.replace("hostCount", String.valueOf(user.hostCount));
//            }
//            if (stats.get("inviteCount") == null) {
//                stats.put("inviteCount", String.valueOf(user.inviteCount));
//            } else {
//                stats.replace("inviteCount", String.valueOf(user.inviteCount));
//            }
//            if (stats.get("invitedCount") == null) {
//                stats.put("invitedCount", String.valueOf(user.invitedCount));
//            } else {
//                stats.replace("invitedCount", String.valueOf(user.invitedCount));
//            }
//
//        }
//    }
