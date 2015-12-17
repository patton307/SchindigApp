package com.schindig.utils;
import com.schindig.AppConfig;
import com.schindig.controllers.MainController;
import com.schindig.entities.Invite;
import com.schindig.entities.Party;
import com.schindig.entities.User;
import com.schindig.entities.Wizard;
import com.schindig.services.InviteRepo;
import com.schindig.services.PartyRepo;
import com.schindig.services.UserRepo;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.util.CookieGenerator;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    public static void newInvite(Invite i, InviteRepo inv, Party p) {
        Invite invite = new Invite();
        invite.party = p;
        invite.email = i.email;
        invite.phone = i.phone;
        invite.name = i.name;
        if (i.invite) {
            invite.invite = true;
        }
        inv.save(invite);
    }

    public void updateInvitedUser(){}

    public static String[] nameSplit(String string) {
        return string.split(" ");
    }

    public void update(Party party, PartyRepo repo) {
        repo.findOne(party.partyID);
    }

    public static void sendInvite(User user, User host, Party party) throws MessagingException {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
        if (user.phone!=null) {
            MimeMessage attMsg = mailSender.createMimeMessage();
            MimeMessageHelper att = new MimeMessageHelper(attMsg);
            att.setFrom("schindig.app@gmail.com");
            att.setTo(user.phone+"@txt.att.net");
            att.setReplyTo(host.email);
            att.setText("Hey! "+host.firstName+" just invited you to their party! Go to http://www.schindig.com/app to RSVP!");
            mailSender.send(attMsg);
            MimeMessage vzwMsg = mailSender.createMimeMessage();
            MimeMessageHelper vzw = new MimeMessageHelper(vzwMsg);
            vzw.setFrom("schindig.app@gmail.com");
            vzw.setReplyTo(host.email);
            vzw.setTo(user.phone+"@vtext.com");
            vzw.setText("Hey! "+host.firstName+" just invited you to their party! Go to http://www.schindig.com/app to RSVP!");
            mailSender.send(vzwMsg);
            MimeMessage sprintMsg = mailSender.createMimeMessage();
            MimeMessageHelper sprint = new MimeMessageHelper(sprintMsg);
            sprint.setFrom("schindig.app@gmail.com");
            sprint.setReplyTo(host.email);
            sprint.setTo(user.phone+"@messaging.sprintpcs.net");
            sprint.setText("Hey! "+host.firstName+" just invited you to their party! Go to http://www.schindig.com/app to RSVP!");
            mailSender.send(sprintMsg);
            MimeMessage tmoMsg = mailSender.createMimeMessage();
            MimeMessageHelper tmo = new MimeMessageHelper(tmoMsg);
            tmo.setFrom("schindig.app@gmail.com");
            tmo.setReplyTo(host.email);
            tmo.setTo(user.phone+"@tmomail.net");
            tmo.setText("Hey! "+host.firstName+" just invited you to their party! Go to http://www.schindig.com/app to RSVP!");
            mailSender.send(tmoMsg);
        }
        if (user.email!=null) {
            mailMsg.setFrom("schindig.app@gmail.com");
            mailMsg.setReplyTo(host.email);
            mailMsg.setTo(user.email);
            mailMsg.setSubject(host.firstName+" just invited you to their party!");
            mailMsg.setText("Hello World!");
            mailSender.send(mimeMessage);
        }
        System.out.println("---Done---");
    }
    
}
