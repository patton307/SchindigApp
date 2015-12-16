package com.schindig.utils;
import com.schindig.controllers.MainController;
import com.schindig.entities.Party;
import com.schindig.entities.User;
import com.schindig.entities.Wizard;
import com.schindig.services.PartyRepo;
import com.schindig.services.UserRepo;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Agronis on 12/9/15.
 */
public class Methods extends MainController {

    public static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Party party, PartyRepo repo) {
        repo.findOne(party.partyID);
    }

    public static Cookie bakeCookie(HttpSession session, User user, UserRepo repo){
        User u = repo.findOne(user.userID);
        Cookie cookie = new Cookie("User", u.username+":"+session.getId());
        cookie.setMaxAge(21600);
        cookie.setHttpOnly(true);
        if (u.cookie == cookie) {
            u.cookie = new Cookie("User", user.username+":"+session.getId());
            u.cookie.setMaxAge(21600);
            u.cookie.setHttpOnly(true);
            repo.save(u);
            return u.cookie;
        }
        return cookie;
    }

    public static Boolean eatCookie(Cookie cookie, User user, UserRepo repo) {
        if (repo.findOne(user.userID).cookie != cookie) {
            String[] check = cookie.getValue().split(":");
            String[] userCheck = user.cookie.getValue().split(":");
            if (!check[0].equals(user.username) || (check[0].equals(user.username) && (check[1].equals(userCheck[1])))) {
                return false;
            }
        } return true;
    }
    
}
