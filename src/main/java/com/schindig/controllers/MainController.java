package com.schindig.controllers;
import com.schindig.entities.Wizard;
import com.schindig.services.WizardRepo;
import com.schindig.utils.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * Created by Agronis on 12/9/15.
 */
@RestController
public class MainController {

    @Autowired
    WizardRepo wizard;

    @PostConstruct
    public void init() {

        Integer test = wizard.wizardSize();
        if (test==0) {
            String fileContent = Methods.readFile("wizard.csv");

            String[] lines = fileContent.split("\n");

            for (String line : lines) {
                String[] columns = line.split(",");
                Wizard wiz= new Wizard(columns[0], columns[1]);
                wizard.save(wiz);
            }
        }

    }

    @RequestMapping("/")
    public ArrayList<Wizard> partyList() {
        ArrayList<Wizard> wizardList = (ArrayList<Wizard>) wizard.findAll();
        return wizardList;
    }

    
}
