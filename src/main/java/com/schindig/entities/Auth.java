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

    public Auth(User user, String device, String token) {
        this.user = user;
        this.device = device;
        this.token = token;
    }

    public User getUser() {

        return user;
    }
    public void setUser(User user) {

        this.user = user;
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
}
