package com.schindig.entities;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Agronis on 12/9/15.
 */
@Entity
@Table(name = "users")
public class User {

    @GeneratedValue
    @Id
    public Integer id;

    @Column(nullable = false)
//    @Size(min = 4, message = "Username must be a minimum of 4 characters in length.")
    public String username;

    @Column(nullable = false)
//    @Size(min = 5, message = "Password must be at least 5 characters in length.")
    public String password;

    @Column(nullable = false)
//    @Size(min = 2, message = "First name must have a minimum of 2 letters.")
    public String firstName;

    @Column(nullable = false)
//    @Size(min = 2, message = "Last name must have a minimum of 2 letters.")
    public String lastName;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
//    @Size(min = 10, message = "Requires a 10-digit number.")
    public String phone;

    public Integer partyCount;
    public Integer hostCount;
    public Integer inviteCount;
    public Integer invitedCount;

    User(){}
    
}
