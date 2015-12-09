package com.schindig.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Agronis on 12/9/15.
 */
@Entity
public class Catalog {

    @GeneratedValue
    @Id
    Integer id;
}
