package com.schindig.entities;
import javax.persistence.*;

/**
 * Created by Agronis on 12/9/15.
 */
@Entity
public class Catalog {

    @GeneratedValue
    @Id
    public Integer catalogID;

//    @Column(nullable = false)
    public String favorName;

    public Integer useCount = 0;

    public Catalog(){}
    public Catalog(Integer catalogID, String favorName, Integer useCount) {

        this.catalogID = catalogID;
        this.favorName = favorName;
        this.useCount = useCount;
    }
    public Catalog(String favorName) {

        this.favorName = favorName;
    }
    public String getFavorName() {

        return favorName;
    }
    public Integer getUseCount() {

        return useCount;
    }
    public Integer getCatalogID() {

        return catalogID;
    }
}
