package com.schindig.services;
import com.schindig.entities.Wizard;
import com.sun.tools.javac.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Agronis on 12/9/15.
 */
public interface WizardRepo extends CrudRepository<Wizard, Integer> {

    @Query("SELECT COUNT(w) FROM Wizard w")
    Integer wizardSize();

    
}
