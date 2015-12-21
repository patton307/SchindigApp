package com.schindig.utils;
import com.schindig.AppConfig;
import com.schindig.controllers.MainController;
import com.schindig.entities.*;
import com.schindig.services.AuthRepo;
import com.schindig.services.InviteRepo;
import com.schindig.services.PartyRepo;
import com.schindig.services.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.web.util.CookieGenerator;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Collectors;

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

    public static Boolean initApp(String device, AuthRepo arepo) {
        Auth a = arepo.findByDevice(device);
        return a != null;
    }


    public static Boolean encrypt(User user, AuthRepo repo, String device, String action) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        ArrayList<Auth> authUsers = repo.findByUser(user);
        final Cipher cipher = Cipher.getInstance("RSA");

        // Generate token key
        KeyGenerator keyGen = KeyGenerator.getInstance(device.concat(user.username).concat(user.password),"AES");
        keyGen.init(256);
        SecretKey token = keyGen.generateKey();
        String secret = Base64.getEncoder().encodeToString(token.getEncoded());

        // Generate Pair of keys based on Token
        KeyPairGenerator keyPair = KeyPairGenerator.getInstance(secret, "RSA");
        keyPair.initialize(256);
        KeyPair pair = keyPair.generateKeyPair();

        cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
        byte[] encryptedBytes = cipher.doFinal(secret.getBytes());
        String privateKey = new String(Base64.getEncoder().encode(encryptedBytes));

        cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
        byte[] ciphertextBytes = Base64.getDecoder().decode(privateKey.getBytes());
        byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);
        String decrypted = new String(decryptedBytes);

        System.out.println(device);
        System.out.println(secret);
//        System.out.println(publicKey);
        System.out.println();

        if (action.equals("login")) {
            Auth auth = repo.findByDevice(device);
            if (auth != null) {
                Auth authenticated = new Auth(user, device, secret);
                repo.save(authenticated);
            } else if (action.equals("validate")) {
                Auth check = repo.findByDevice(device);
                if (!check.token.equals(decrypted)) {
                    return false;
                }

            }
        }
        return true;
    }

    public static Boolean isAlive(User user, String device, AuthRepo arepo) {
        ArrayList<Auth> deviceList = arepo.findByUser(user);
        for (Auth a : deviceList) {
            if (a.device.equals(device)) {
                return true;
            }
        }
        return false;
    }

//    public static Boolean validate(User user, String device, AuthRepo arepo) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
//        final Cipher cipher = Cipher.getInstance("RSA");
//        Auth a = arepo.findByDevice(device);
//        String token = a.token;
//        String privateKey = a.privateKey;
//        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
//        byte[] ciphertextBytes = Base64.getDecoder().decode(chipertext.getBytes());
//        byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);
//        String decryptedString = new String(decryptedBytes);
//    }

}
    

