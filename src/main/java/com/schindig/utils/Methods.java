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


}
