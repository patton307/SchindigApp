package com.schindig.controllers;
import com.schindig.PasswordHash;
import com.schindig.entities.*;
import com.schindig.services.*;
import com.schindig.utils.Methods;
import com.schindig.utils.Parameters;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
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

    @Autowired
    AuthRepo auth;



    @PostConstruct
    public void init() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {

        long wizCheck = wizard.count();
        ArrayList<String> partyTypes = new ArrayList<>();
        ArrayList<String> subTypes = new ArrayList<>();
        if (wizCheck == 0) {
            String fileContent = Methods.readFile("wizard.csv");

            String[] lines = fileContent.split("\n");

            for (String line : lines) {
                Wizard wiz = new Wizard();
                String[] columns = line.split(",");
                String partyType = columns[0];
                String partyMod = columns[1];
                partyTypes.add(columns[0]);
                if (columns[1] != null) {
                    subTypes.add(columns[1]);
                }
                if (columns[1] == null) {
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

        long catCheck = favors.count();
        if (catCheck == 0) {
            String fileContent = Methods.readFile("catalog.csv");

            String[] lines = fileContent.split("\n");


            for (String line : lines) {
                Favor fav = new Favor();
                String[] columns = line.split(",");
                String favor = columns[0];
                try {
                    String partyType = columns[1];
                    if (partyType!=null) {
                        fav.partyType = partyType;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }
                fav.favorName = favor;
                favors.save(fav);
            }
        }


        ArrayList<User> userBuild = (ArrayList<User>) users.findAll();
        if (userBuild.size() < 10) {
            String fileContent = Methods.readFile("users.csv");

            String[] lines = fileContent.split("\n");
            for (String line : lines) {
                String randomNumber = RandomStringUtils.randomNumeric(10);
                String[] columns = line.split(",");
                User u = new User(columns[0], columns[1], columns[2], columns[3], columns[2].concat(columns[4]), randomNumber);
                userBuild.add(u);
                users.save(u);
            }

            String description = "Lorem ipsum dolor sit amet, eu ligula faucibus at egestas, est nibh at non in, nec nec massa fusce vitae, lacus at risus, arcu proin pede. ";
            String theme = "This is just a placeholder for what could be an insane theme.";
            String local = "220 E Bryan St, Savannah, GA 31401";
            String stretchName = "One insane crazy impossible goal.";


            for (User user : userBuild) {
                for (int i = 0; i < 5; i++) {
                    String partyType = partyTypes.get(i);

                    String subType;
                    subTypes.get(i);
                    if (subTypes != null) {
                        subType = subTypes.get(i);
                    } else {
                        subType = "No subType";
                    }
                    if (parties.count() < 10) {
                        Party P = new Party(user, "Insert Party Name Here", partyType, description, subType,
                                LocalDateTime.now(), String.valueOf(LocalDateTime.now().plusDays(7)), local, stretchName, 5000,
                                0, true, true, theme, "Valet");
                        parties.save(P);
                        for (int fa = 1; fa < 10; fa++) {
                            Favor f = favors.findOne(fa);
                            f.useCount += 1;
                            FavorList newList = new FavorList(f, P, false);
                            favors.save(f);
                            favlists.save(newList);
                        }
                        for (int u = 0; u < userBuild.size(); u++) {
                            User invUser = userBuild.get(u);
                            ArrayList<Invite> inviteList = invites.findByParty(P);
                            if (inviteList.size() < 10) {
                                Invite inv = new Invite(invUser, P, invUser.phone, invUser.email, "Maybe", invUser.firstName + invUser.lastName);
                                invites.save(inv);
                                u += 3;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("There have been "+ (users.count()+favors.count()+wizard.count()+favlists.count()+auth.count()+parties.count()) + " rows created.");
    }


    @RequestMapping(path = "/validate/{device}", method = RequestMethod.GET)
    public Integer appLoad(@PathVariable("device") String device, HttpServletResponse response) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException {

        Auth a = auth.findByDevice(device);
        if (a == null) {
            return 0;
        } else {
            User u = auth.findByDevice(device).user;
            return u.userID;
        }
    }

    /**
     * ALL USER RELATED ROUTES
     **/
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
            users.save(user);
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
    public Integer login(@RequestBody Parameters p, HttpServletResponse response, HttpSession session, HttpServletRequest request) throws Exception {
        User user = users.findOneByUsername(p.user.username);
        try {
            if (users.findOneByUsername(p.user.username) == null) {
                response.sendError(401);
            }
            if (!user.password.equals(p.user.password)) {
                response.sendError(403);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Auth a = auth.findByDevice(p.device);
//        if (a==null) {
//            Methods.newDevice(user, p.device, auth);
//            return user.userID;
//        }
        return user.userID;
    }

    @RequestMapping(path = "/user/logout", method = RequestMethod.POST)
    public void logout(@RequestBody Parameters p) {
        Auth a = auth.findByDevice(p.device);
        auth.delete(a);
    }

    /**
     * ALL PARTY RELATED ROUTES
     **/

    @RequestMapping(path = "/party/create", method = RequestMethod.POST)
    public Party createParty(@RequestBody Parameters params, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        User user = users.findOne(params.userID);
        Party p = params.party;
        p.host = user;
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
            fav.useCount += 1;
            FavorList favorList = new FavorList(fav, party, false);
            favlists.save(favorList);
            favors.save(fav);
            newDump.add(fav);
        }
        return newDump;
    }

    @RequestMapping(path = "/party/claim", method = RequestMethod.POST)
    public FavorList claimFavor(@RequestBody Parameters p, HttpServletResponse response) throws IOException {

        User u = users.findOne(p.userID);
        FavorList favItem = favlists.findOne(p.listID);
        if (favItem.user != u && favItem.user != null) {
            response.sendError(403, "Not your's to unclaim.");
        } else {
            favItem.user = u;
            if (favItem.claimed) {
                favItem.claimed = false;
            } else {
                favItem.claimed = true;
            }
            favlists.save(favItem);
        }
        return favItem;
    }

    @RequestMapping(path = "/party/{id}/favors", method = RequestMethod.GET)
    public ArrayList<FavorList> getFavors(@PathVariable("id") int id) {

        ArrayList<FavorList> favorList = (ArrayList<FavorList>) favlists.findAll();
        ArrayList<FavorList> newList = favorList.stream().filter(fav -> fav.party.partyID == id).collect(Collectors.toCollection(ArrayList::new));
        if (newList==null) {
            return null;
        }
        return newList;

    }

    @RequestMapping(path = "/party/invite", method = RequestMethod.POST)
    public void addInvite(@RequestBody Parameters parameters) throws Exception {

        Party party = parties.findOne(parameters.party.partyID);
        User user = users.findOne(parameters.user.userID);
        User host = party.host;
        host.invitedCount += 1;
        Invite invite = new Invite(
                user, party, parameters.invites.phone, parameters.invites.email, "Undecided", parameters.invites.name
        );
        users.save(host);
        invites.save(invite);
    }

    @RequestMapping(path = "/party/{id}/rsvp", method = RequestMethod.POST)
    public void rsvp(@RequestBody Parameters parameters, @PathVariable("id") Integer id) {

        Party p = parties.findOne(parameters.partyID);
        User user = parameters.user;
        user.invitedCount += 1;
        switch (parameters.rsvpStatus) {
            case "Yes": {
                user.partyCount += 1;
                Invite i = invites.findByPartyAndUser(p, user);
                i.rsvpStatus = "Yes";
                invites.save(i);
                break;
            }
            case "Maybe": {
                Invite i = invites.findByPartyAndUser(p, user);
                i.rsvpStatus = "Maybe";
                invites.save(i);
                break;
            }
            case "No": {
                Invite i = invites.findByPartyAndUser(p, user);
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

    @RequestMapping(path = "/party/{id}/invites", method = RequestMethod.GET)
    public ArrayList<Invite> getInvites(@PathVariable("id") Integer id) {

        Party p = parties.findOne(id);
        return invites.findByParty(p);
    }

    @RequestMapping(path = "/party/update", method = RequestMethod.PATCH)
    public Party updateParty(@RequestBody Parameters parameters) throws MessagingException {

        Party check = parties.findOne(parameters.party.partyID);

        if (parameters.party.description != null) {
            check.description = parameters.party.description;
        }
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
        if (parameters.party.local != null) {
            check.local = parameters.party.local;
        }
        if (parameters.party.stretchGoal != null) {
            check.stretchGoal = parameters.party.stretchGoal;
        }
        if (parameters.party.stretchName != null) {
            check.stretchName = parameters.party.stretchName;
        }
        if (parameters.party.theme != null) {
            check.theme = parameters.party.theme;
        }
        if (parameters.party.byob) {
            check.byob = true;
        }
        if (parameters.party.parking != null) {
            check.parking = parameters.party.parking;
        }
        if (parameters.inviteDump != null) {
            for (int i = 0; i < parameters.inviteDump.size(); i++) {
                Invite invite = parameters.inviteDump.get(i);
                Methods.newInvite(invite, invites, check);
                Methods.sendInvite(invite, check.host, check);
                invite.sent = true;
                check.host.invitedCount += 1;
            }
        }
        if (parameters.party.wizPosition != null) {
            check.wizPosition = parameters.party.wizPosition;
        }
        parties.save(check);
        return check;
    }

    @RequestMapping(path = "/parties/host", method = RequestMethod.POST)
    public ArrayList<Party> getAllHosted(@RequestBody User user) {

        User u = users.findOne(user.userID);
        ArrayList<Party> partyList = (ArrayList<Party>) parties.findAll();
        partyList = (ArrayList<Party>) partyList.stream()
                .filter(p -> p.host == u)
                .collect(Collectors.toCollection(ArrayList<Party>::new));
        return partyList;
    }

    @RequestMapping(path = "/parties/user", method = RequestMethod.POST)
    public ArrayList<Party> getAllParties(@RequestBody User user) {

        User u = users.findOne(user.userID);
        ArrayList<Invite> inviteList = (ArrayList<Invite>) invites.findAll();
        ArrayList<Party> partyList = new ArrayList();
        for (Invite invite : inviteList) {
            if (u.email.equals(invite.email)) {
                partyList.add(invite.party);
            } else if (u.phone.equals(invite.phone)) {
                partyList.add(invite.party);
            }
        }
        return partyList;
    }

    @RequestMapping(path = "/party/delete", method = RequestMethod.POST)
    public void deleteParty(@RequestBody Party party, HttpServletResponse response) throws IOException {

        //User u = users.findOne(parameters.userID);
        Party p = parties.findOne(party.partyID);
        ArrayList<FavorList> f = favlists.findAllByParty(p);
        ArrayList<Invite> i = invites.findByParty(party);

        if (f != null) {
            for (FavorList stuff : f) {
                favlists.delete(stuff);
            }
        }

        if (i != null) {
            for (Invite list : i) {
                invites.delete(list);
            }
        }

        //u.hostCount -= 1;
        //users.save(u);
            parties.delete(p);
    }

    @RequestMapping(path = "/party/favor/delete", method = RequestMethod.POST)
    public FavorList deletePartyFavor(@RequestBody Parameters parameters, HttpServletResponse response) throws IOException {
        FavorList f = favlists.findOne(parameters.listID);
        Favor fav = f.favor;
        fav.useCount -= 1;
        favors.save(fav);
        favlists.delete(f);
        return f;
    }

    /**
     * ALL WIZARD RELATED ROUTES
     **/

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

    /**
     * ALL FAVOR SPECIFIC ROUTES
     **/

    @RequestMapping(path = "/favor", method = RequestMethod.GET)
    public ArrayList<Favor> getFavorList(@RequestBody Parameters p) {
        Party party = parties.findOne(p.partyID);
        ArrayList<Favor> list = favors.findAllByPartyType(party.partyType);
        ArrayList<Favor> filter = (ArrayList<Favor>) favors.findAll();
        list.addAll(filter.stream()
                .filter(fav -> fav.partyType == party.partyType)
                .sorted(Comparator.comparing(Favor::getUseCount))
                .collect(Collectors.toList()));
        return list;
    }

    @RequestMapping(path = "/favor/save", method = RequestMethod.POST)
    public Favor addFavorItem(@RequestBody Parameters p) {
        Favor fav = new Favor();
        Party party = parties.findOne(p.partyID);
        if (p.favor.favorName==null || p.favor.favorName.isEmpty()){
            return null;
        } else {
            fav.favorName = p.favor.favorName;
            fav.partyType = party.partyType;
//            fav.subType = party.subType;
            fav.useCount += 1;
            favors.save(fav);
            return fav;
        }
    }

    @RequestMapping(path = "/favor/remove", method = RequestMethod.POST)
    public ArrayList<Favor> deleteFavorItem(@RequestBody Favor item) {

        favors.delete(item);
        return (ArrayList<Favor>) favors.findAll();
    }

    @RequestMapping(path = "/party/stats", method = RequestMethod.GET)
    public ArrayList<String> partyStats(@RequestBody Parameters p) {

        ArrayList<Object> stats = new ArrayList<>();
        long party = parties.count();
        long invite = invites.count();
        long wiz = wizard.count();
        long user = users.count();
        long favor = favors.count();

        ArrayList<Object> userStats;
        HashMap<String, Long> databaseStats;
        databaseStats = new HashMap<>();
        stats.add(databaseStats);

        return new ArrayList<>();
    }
}

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
