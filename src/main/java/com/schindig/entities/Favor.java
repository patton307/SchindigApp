package com.schindig.entities;
import javax.persistence.*;

/**
 * Created by Agronis on 12/9/15.
 */
@Entity
public class Favor {

    @GeneratedValue
    @Id
    public Integer favorID;

//    @Column(nullable = false)
    public String favorName;

    public Integer useCount = 0;

    public Favor(){}
    public Favor(Integer catalogID, String favorName, Integer useCount) {

        this.favorID = catalogID;
        this.favorName = favorName;
        this.useCount = useCount;
    }
    public Favor(String favorName) {

        this.favorName = favorName;
    }
    public String getFavorName() {

        return favorName;
    }
    public Integer getUseCount() {

        return useCount;
    }
    public Integer getCatalogID() {

        return favorID;
    }
}
