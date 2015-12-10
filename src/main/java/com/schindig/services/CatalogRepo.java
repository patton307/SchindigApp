package com.schindig.services;
import com.schindig.entities.Catalog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Agronis on 12/9/15.
 */
public interface CatalogRepo extends CrudRepository<Catalog, Integer> {

    @Query("SELECT COUNT(c) FROM Catalog c")
    Integer catalogSize();
    
}
