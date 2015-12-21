package com.schindig.entities;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Agronis on 12/19/15.
 */
@Entity
public class Auth {

    @GeneratedValue
    @Id
    public Integer id;

    @ManyToOne
    public User user;

    public String device;

    public String token;

    public String publicKey;

    public String privateKey;

    public Auth(String device, String token, String publicKey, String privateKey) {

        this.device = device;
        this.token = token;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
    public Integer getId() {

        return id;
    }
    public void setId(Integer id) {

        this.id = id;
    }
    public String getDevice() {

        return device;
    }
    public void setDevice(String device) {

        this.device = device;
    }
    public String getToken() {

        return token;
    }
    public void setToken(String token) {

        this.token = token;
    }
    public String getPublicKey() {

        return publicKey;
    }
    public void setPublicKey(String publicKey) {

        this.publicKey = publicKey;
    }
    public String getPrivateKey() {

        return privateKey;
    }
    public void setPrivateKey(String privateKey) {

        this.privateKey = privateKey;
    }
}
